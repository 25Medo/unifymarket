package com.unify.memory;

/**
 * Singleton class(not thread safe) to store initialization units values
 */
public final class UnitMemory extends Memory<String> {
     private static final UnitMemory INSTANCE = new UnitMemory();

    public UnitMemory() {
        super();
    }

    public static UnitMemory getInstance(){
        return INSTANCE;
    }
}
