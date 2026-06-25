// ============================================================================
// AnyLogic class: LogisticsCenter
//
// NOTE: This is NOT a standalone compilable file. AnyLogic generates the
// surrounding agent/class scaffolding automatically; only the user-written
// action/event code is reproduced here, exactly as authored in the .alp
// model. See the parent README for context.
// ============================================================================

public class LogisticsCenter {

    // ------------------------------------------------------------------
    // accumulateAndSendCustomerOrders (Action)
    //
    // Batches up accumulated customer order volume into a single Order
    // message sent to this center's assigned FulfillmentCenter, then
    // resets the accumulator.
    // ------------------------------------------------------------------
    void accumulateAndSendCustomerOrders() {
        Order order = new Order(this, customerOrderAmount);
        send(order, center);
        customerOrderAmount = 0;
        countCustomerOrders = 0;
    }

    // ------------------------------------------------------------------
    // distributeProductsToCustomers (Action)
    //
    // Releases the next queued customer order once enough product has
    // been received from the fulfillment center to satisfy it.
    // ------------------------------------------------------------------
    void distributeProductsToCustomers() {
        if (waitingForProduct.size() > 0) {
            if (productsReceived > 0) {
                if (customerOrderAmount_single > 0) {
                    if (productsReceived >= customerOrderAmount_single) {
                        productsReceived -= customerOrderAmount_single;
                        waitingForProduct.stopDelay(waitingForProduct.get(0));
                    }
                }
            }
        }
    }

    // ------------------------------------------------------------------
    // ResetCenter (Action)
    //
    // Randomly reassigns this LogisticsCenter to one of the seven
    // FulfillmentCenters (used as an alternative/randomized assignment
    // rule, analogous to ResetCenters() in Main).
    // ------------------------------------------------------------------
    void ResetCenter() {
        center = main.fulfillmentCenters.get(uniform_discr(0, 6));
    }
}
