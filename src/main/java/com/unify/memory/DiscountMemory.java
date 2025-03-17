package com.unify.memory;

import com.unify.model.Discount;

/**
 * Singleton class(not thread safe) to store initialization discounts values
 */
public final class DiscountMemory extends Memory<Discount> {

    private static final DiscountMemory INSTANCE = new DiscountMemory();

    private DiscountMemory(){
        super();
    }

    public static DiscountMemory getInstance(){
        return INSTANCE;
    }
}
