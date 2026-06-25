// ============================================================================
// AnyLogic class: Main
//
// NOTE: This is NOT a standalone compilable file. AnyLogic generates the
// surrounding agent/class scaffolding (imports, fields, GUI bindings, GIS
// space, etc.) automatically; only the user-written action/event code is
// reproduced here, exactly as authored in the .alp model, for readability
// and version control. See the parent README for context.
// ============================================================================

public class Main {

    // ------------------------------------------------------------------
    // ResetCenters (Action)
    //
    // Re-assigns each DistributionCenter to a LogisticsCenter under one of
    // three candidate Logistics-Distribution (L-D) matching rules. Only one
    // method is active (uncommented) at a time; the others are kept as
    // commented-out alternatives that were benchmarked against each other
    // in the simulation study (see presentation appendix).
    // ------------------------------------------------------------------
    void ResetCenters() {
        //// Method 1 - Nearest-Only L-D Matching Rule ////
        // Do Nothing //

        // Code for Getting Distances //
        // /*
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
        // */

        //// Method 2 (g) - Stable Matching Algorithm (Greedy Algorithm) ////
        /*
        double[][] coefficientsList = {
            // 6 logistics centers x 25 distribution centers,
            // precomputed distance/cost coefficients used for greedy assignment
            { ... },
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

            distances.sort(Comparator.comparingDouble(o -> coefficientsList[o[0]][o[1]]));

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
        */

        //// Method 2 (s) - Nearest with Uniformity Constraint L-D Matching Rule ////
        /*
        int[] data1 = new int[] {4,9,14,22};
        int[] data2 = new int[] {1,19,21,25};
        int[] data3 = new int[] {10,15,17,18,23};
        int[] data4 = new int[] {6,11,12,24};
        int[] data5 = new int[] {2,3,7,8};
        int[] data6 = new int[] {5,13,16,20};
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
        */

        //// Method 3 - Random, Uniform L-D Matching Rule ////
        // /*
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
        // */
    }

    // ------------------------------------------------------------------
    // setCenters (Body)
    //
    // Default nearest-by-route assignment: every DistributionCenter picks
    // its nearest LogisticsCenter by route distance, and every
    // LogisticsCenter in turn picks its nearest FulfillmentCenter.
    // ------------------------------------------------------------------
    void setCenters() {
        for (DistributionCenter distribution_center : distributionCenters) {
            distribution_center.set_center(
                    distribution_center.getNearestAgentByRoute(logisticsCenters)
            );
        }
        for (LogisticsCenter logistics_center : logisticsCenters) {
            logistics_center.set_center(
                    logistics_center.getNearestAgentByRoute(fulfillmentCenters)
            );
        }
    }

    // ------------------------------------------------------------------
    // viewArea (Body)
    //
    // GUI helper: navigates the presentation camera to the selected view
    // area and repositions the main menu group accordingly.
    // ------------------------------------------------------------------
    void viewArea() {
        selectedViewArea = viewArea;
        viewArea.navigateTo();
        groupMainMenu.setPos(viewArea.getX(), viewArea.getY());
    }
}
