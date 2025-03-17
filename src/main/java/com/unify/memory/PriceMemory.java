package com.unify.memory;

/**
 * Singleton class(not thread safe) to store initialization prices values
 */
public final class PriceMemory extends Memory<Double> {
    private static final PriceMemory INSTANCE = new PriceMemory();

    private PriceMemory() {
        super();
    }

    public static PriceMemory getInstance(){
        return INSTANCE;
    }
}
