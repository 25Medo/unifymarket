package com.unify.memory;

import java.util.HashMap;
import java.util.Map;

/**
 * Generic super class for storing initialization values
 * @param <T> type of the values to be stored
 */
public class Memory<T> {
    private final Map<String, T> memory = new HashMap<>();

    /**
     * add a new element in the memory
     * @param itemName the key of the element to add
     * @param t the element to add to the memory
     * @throws IllegalArgumentException when the element already exist
     */
    public void add(String itemName, T t){
        if(memory.containsKey(itemName)){
            throw new IllegalArgumentException("This element already exists!");
        }
        this.memory.put(itemName, t);
    }
    /**
     * Retrieves an element from memory
     * @param itemName The key for the element
     * @return The element, or null if not found
     */
    public T get(String itemName){
        return memory.get(itemName); //of course can be improved to handle null cases
    }
}
