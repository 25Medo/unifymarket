package com.unify.pricing;

import com.unify.memory.PriceMemory;
import com.unify.model.Item;

/**
 * Initialize prices values
 */
public final class PriceInitializer extends Initializer {

    /**
     *
     * @param item to be initialized
     */
    @Override
    protected void initValue(Item item) {
        item.setUnitPrice(PriceMemory.getInstance().get(item.getName()));
    }
}
