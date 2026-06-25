// ============================================================================
// AnyLogic class: Main
//
// NOTE: This is NOT a standalone compilable file. AnyLogic generates the
// surrounding agent/class scaffolding (imports, fields, GUI bindings, GIS
// space, etc.) automatically; only the user-written action/event code is
// reproduced here, exactly as authored in the .alp model, for readability
// and version control. See the parent README for context.
//
// The four L-D matching strategies formerly inlined in ResetCenters() have
// been split out into MatchingAlgorithms.java for clarity — see that file
// for the actual algorithm implementations.
// ============================================================================

public class Main {

    // ------------------------------------------------------------------
    // ResetCenters (Action)
    //
    // Re-assigns each DistributionCenter to a LogisticsCenter under one of
    // four candidate L-D matching rules (see MatchingAlgorithms.java).
    // In the original model, only one method's code was left uncommented
    // (active) at a time; here that's represented as picking one call.
    // ------------------------------------------------------------------
    void ResetCenters() {
        MatchingAlgorithms matching = new MatchingAlgorithms();

        // Active in the original model — swap to benchmark a different strategy:
        matching.method3_randomUniform();

        // matching.method1_nearestOnly();
        // matching.method2g_greedyStableMatching();
        // matching.method2s_nearestWithUniformityConstraint();
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
