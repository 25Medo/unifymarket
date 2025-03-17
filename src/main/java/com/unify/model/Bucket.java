package com.unify.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Class to hold purchased items
 */
public class Bucket {
    private String name;
    private Set<Item> items;

    /**
     *
     * @param name of the bucket
     * @param items list of the products in the bucket
     */
    public Bucket(String name, Set<Item> items) {
        this.name = name;
        this.items = items;
    }

    public Bucket() {
        super();
    }

    public String getName() {
        return name;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void addItem(Item item){
        if (this.items != null) {
            this.items.add(item);
        }
        this.items = new HashSet<>();
        items.add(item);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
