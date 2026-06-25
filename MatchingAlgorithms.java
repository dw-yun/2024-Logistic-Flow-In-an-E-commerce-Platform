// ============================================================================
// Source: Main.ResetCenters (Action)
//
// Four candidate Logistics–Distribution (L-D) center matching strategies,
// benchmarked against each other in the simulation study. In the original
// model these all live inside a single ResetCenters() action, with only one
// method's code uncommented (active) at a time; they are split out here into
// separate methods for clarity. Toggle which strategy runs by calling the
// corresponding method from Main.ResetCenters().
//
// NOTE: This is NOT a standalone compilable file — see the parent README.
// ============================================================================

public class MatchingAlgorithms {

    // ------------------------------------------------------------------
    // Method 1 — Nearest-Only L-D Matching Rule
    //
    // No explicit reassignment logic; centers keep their default
    // nearest-by-route assignment (see Main.setCenters()). This block
    // just dumps the full distance matrix (6 logistics x 25 distribution
    // centers) via trace(), for inspection/debugging.
    // ------------------------------------------------------------------
    void method1_nearestOnly() {
        // Do Nothing — relies on Main.setCenters() nearest-by-route assignment

        // Code for Getting Distances //
        trace("[");
        for (int i = 0; i < 6; i++) {
            trace("[");
            for (int j = 0; j < 25; j++) {
                double val = getDistance(
                        distributionCenters.get(j).getX(),
                        distributionCenters.get(j).getY(),
                        logisticsCenters.get(i).getX(),
                        logisticsCenters.get(i).getY()
                );
                trace(val + ",");
            }
            trace("]\n");
        }
        trace("]");
    }

    // ------------------------------------------------------------------
    // Method 2(g) — Stable Matching Algorithm (Greedy)
    //
    // Each distribution center is greedily assigned to its
    // cheapest-available logistics center, subject to a per-center
    // capacity cap, using a precomputed distance/cost coefficient matrix.
    // ------------------------------------------------------------------
    void method2g_greedyStableMatching() {
        double[][] coefficientsList = {
            // 6 logistics centers x 25 distribution centers,
            // precomputed distance/cost coefficients used for greedy assignment
            { /* row 0: coefficients for logistics center 0 vs each of 25 distribution centers */ },
            { /* row 1 */ },
            { /* row 2 */ },
            { /* row 3 */ },
            { /* row 4 */ },
            { /* row 5 */ },
        };

        int numLogisticsCenters = coefficientsList.length;
        int numDistributionCenters = coefficientsList[0].length;
        int maxCapacity = 5;

        int[] logisticsCapacity = new int[numLogisticsCenters];
        Arrays.fill(logisticsCapacity, maxCapacity);

        int[] matches = new int[numDistributionCenters];
        Arrays.fill(matches, -1);

        for (int distIdx = 0; distIdx < numDistributionCenters; distIdx++) {
            ArrayList<int[]> distances = new ArrayList<>();
            for (int logIdx = 0; logIdx < numLogisticsCenters; logIdx++) {
                distances.add(new int[]{logIdx, distIdx});
            }

            // sort candidate logistics centers by ascending coefficient (cost/distance)
            distances.sort(Comparator.comparingDouble(o -> coefficientsList[o[0]][o[1]]));

            // assign to the cheapest logistics center that still has capacity
            for (int[] logDist : distances) {
                int logIdx = logDist[0];
                if (logisticsCapacity[logIdx] > 0) {
                    matches[distIdx] = logIdx;
                    logisticsCapacity[logIdx]--;
                    break;
                }
            }
        }

        for (int distIdx = 0; distIdx < matches.length; distIdx++) {
            distributionCenters.get(distIdx).center = logisticsCenters.get(matches[distIdx]);
        }
    }

    // ------------------------------------------------------------------
    // Method 2(s) — Nearest with Uniformity Constraint
    //
    // Hand-picked, fixed groupings of distribution-center indices, each
    // group assigned wholesale to one logistics center. Approximates a
    // nearest-distance assignment while forcing an even (4-5 per center)
    // distribution across all 6 logistics centers.
    // ------------------------------------------------------------------
    void method2s_nearestWithUniformityConstraint() {
        int[] data1 = new int[]{4, 9, 14, 22};
        int[] data2 = new int[]{1, 19, 21, 25};
        int[] data3 = new int[]{10, 15, 17, 18, 23};
        int[] data4 = new int[]{6, 11, 12, 24};
        int[] data5 = new int[]{2, 3, 7, 8};
        int[] data6 = new int[]{5, 13, 16, 20};

        for (int i = 0; i < data1.length; i++) {
            (distributionCenters.get(data1[i] - 1)).center = logisticsCenters.get(0);
        }
        for (int i = 0; i < data2.length; i++) {
            (distributionCenters.get(data2[i] - 1)).center = logisticsCenters.get(1);
        }
        for (int i = 0; i < data3.length; i++) {
            (distributionCenters.get(data3[i] - 1)).center = logisticsCenters.get(2);
        }
        for (int i = 0; i < data4.length; i++) {
            (distributionCenters.get(data4[i] - 1)).center = logisticsCenters.get(3);
        }
        for (int i = 0; i < data5.length; i++) {
            (distributionCenters.get(data5[i] - 1)).center = logisticsCenters.get(4);
        }
        for (int i = 0; i < data6.length; i++) {
            (distributionCenters.get(data6[i] - 1)).center = logisticsCenters.get(5);
        }
    }

    // ------------------------------------------------------------------
    // Method 3 — Random, Uniform L-D Matching Rule
    //
    // Randomly assigns each of the first 24 distribution centers to one
    // of 6 logistics centers, capped at 4 per logistics center; the 25th
    // distribution center is assigned to a uniformly random logistics
    // center with no capacity check.
    // ------------------------------------------------------------------
    void method3_randomUniform() {
        int[] arr = new int[]{0, 0, 0, 0, 0, 0};
        for (int i = 0; i < 24; i++) {
            int s = 0;
            while (s == 0) {
                int idx = uniform_discr(0, 5);
                if (arr[idx] < 4) {
                    arr[idx]++;
                    s = 1;
                    (distributionCenters.get(i)).center = logisticsCenters.get(idx);
                }
            }
        }
        int idx = uniform_discr(0, 5);
        (distributionCenters.get(24)).center = logisticsCenters.get(idx);
    }
}
