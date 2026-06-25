// ============================================================================
// AnyLogic class: LocalVehicle
//
// Last-mile delivery vehicles moving products between LogisticsCenters and
// end-customer DistributionCenters.
//
// NOTE: This is NOT a standalone compilable file. AnyLogic generates the
// surrounding agent/class scaffolding automatically; only the user-written
// action/event code is reproduced here, exactly as authored in the .alp
// model. See the parent README for context.
// ============================================================================

public class LocalVehicle {

    // ------------------------------------------------------------------
    // transition (Action)
    //
    // Fired on message receipt: stores the incoming CustomerOrder for
    // this delivery run.
    // ------------------------------------------------------------------
    void transition() {
        customerOrder = msg;
    }

    // ------------------------------------------------------------------
    // transition2 (Action)
    //
    // Fired on arrival: releases the delivered CustomerOrder from the
    // source center's "delivering" delay queue.
    // ------------------------------------------------------------------
    void transition2() {
        center.delivering.stopDelay(customerOrder);
    }

    // ------------------------------------------------------------------
    // transition4 (Action)
    //
    // Fired on dispatch: moves the vehicle toward the customer order's
    // destination DistributionCenter and updates the on-screen
    // destination label.
    // ------------------------------------------------------------------
    void transition4() {
        moveTo(customerOrder.distribution_center);
        dest.setText(customerOrder.distribution_center.name);
    }
}
