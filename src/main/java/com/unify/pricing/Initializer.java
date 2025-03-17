package com.unify.pricing;

import com.unify.model.Bucket;
import com.unify.model.Item;

/**
 * Template class to initialized default values like prices, units and discounts
 */
public abstract sealed class Initializer permits PriceInitializer, UnitInitializer, DiscountInitializer {

    /**
     * Template method to init prices, discounts and units
     * @param bucket which items need to be initialized
     */
    public final void init(Bucket bucket){
        for (Item i: bucket.getItems()){
            initValue(i);
        }
    }

    /**
     * Method to be implemented by each subclass based on their use case
     * @param item to be initialized
     */
    protected abstract void initValue(Item item);
}
