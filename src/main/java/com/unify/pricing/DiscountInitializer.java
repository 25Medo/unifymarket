package com.unify.pricing;

import com.unify.memory.DiscountMemory;
import com.unify.model.Item;

/**
 * Initialize discounts values
 */
public final class DiscountInitializer extends Initializer{

    /**
     *
     * @param item to be initialized
     */
    @Override
    protected void initValue(Item item) {
        item.setDiscount(DiscountMemory.getInstance().get(item.getName()));
    }
}
