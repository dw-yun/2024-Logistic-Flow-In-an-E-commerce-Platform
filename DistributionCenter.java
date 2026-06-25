// ============================================================================
// AnyLogic class: DistributionCenter
//
// NOTE: This is NOT a standalone compilable file. AnyLogic generates the
// surrounding agent/class scaffolding automatically; only the user-written
// action/event code is reproduced here, exactly as authored in the .alp
// model. See the parent README for context.
// ============================================================================

public class DistributionCenter {

    // ------------------------------------------------------------------
    // generateDemand (Action)
    //
    // Generates a randomized end-customer order (1-5 units) and forwards
    // it as a CustomerOrder message to this center's assigned
    // LogisticsCenter.
    // ------------------------------------------------------------------
    void generateDemand() {
        CustomerOrder customerOrder = new CustomerOrder(this, uniform_discr(1, 5));
        send(customerOrder, center);
        customerOrderAmount = customerOrder.amount;
    }
}
