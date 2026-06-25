// ============================================================================
// AnyLogic class: Vehicle
//
// Long-haul trucks moving products between FulfillmentCenters and
// LogisticsCenters.
//
// NOTE: This is NOT a standalone compilable file. AnyLogic generates the
// surrounding agent/class scaffolding automatically; only the user-written
// action/event code is reproduced here, exactly as authored in the .alp
// model. See the parent README for context.
// ============================================================================

public class Vehicle {

    // ------------------------------------------------------------------
    // transition2 (Action)
    //
    // Fired on arrival: releases the delivered Order from the source
    // center's "delivering" delay queue.
    // ------------------------------------------------------------------
    void transition2() {
        center.delivering.stopDelay(order);
    }

    // ------------------------------------------------------------------
    // transition4 (Action)
    //
    // Fired on dispatch: moves the vehicle toward the order's destination
    // LogisticsCenter and updates the on-screen destination label.
    // ------------------------------------------------------------------
    void transition4() {
        moveTo(order.logistics_center);
        dest.setText(order.logistics_center.name);
    }
}
