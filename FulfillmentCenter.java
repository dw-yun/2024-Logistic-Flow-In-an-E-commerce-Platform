// ============================================================================
// AnyLogic class: FulfillmentCenter
//
// NOTE: This is NOT a standalone compilable file. AnyLogic generates the
// surrounding agent/class scaffolding automatically; only the user-written
// action/event code is reproduced here, exactly as authored in the .alp
// model. See the parent README for context.
// ============================================================================

public class FulfillmentCenter {

    // ------------------------------------------------------------------
    // loadProudctsOntoTrucks (Action)
    //
    // Releases queued (delayed) units from storage onto outbound trucks
    // once enough stock is available to cover the pending order amount,
    // and releases any orders that were waiting on insufficient stock.
    // ------------------------------------------------------------------
    void loadProudctsOntoTrucks() {
        if (amountStorage1 + amountStorage2 + products >= orderAmount
                && amountStorage1 + amountStorage2 > 0) {
            if (storage1.size() > 0) {
                for (int i = 0; i < min(amountStorage1, orderAmount - products); i++) {
                    storedInStorage1.stopDelay(storedInStorage1.get(i));
                }
            }
            if (storage2.size() > 0) {
                for (int i = 0; i < min(amountStorage2, orderAmount - products); i++) {
                    storedInStorage2.stopDelay(storedInStorage2.get(i));
                }
            }
        }
        if (products >= orderAmount) {
            if (waitForProducts.size() > 0) {
                waitForProducts.stopDelay(waitForProducts.get(0));
            }
        }
    }
}
