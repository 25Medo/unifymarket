package com.unify.pricing;

import com.unify.memory.UnitMemory;
import com.unify.model.Item;

/**
 * Initialize units values
 */
public final class UnitInitializer extends Initializer{

    /**
     *
     * @param item to be initialized
     */
    @Override
    protected void initValue(Item item) {
        item.setUnit(UnitMemory.getInstance().get(item.getName()));
    }
}
