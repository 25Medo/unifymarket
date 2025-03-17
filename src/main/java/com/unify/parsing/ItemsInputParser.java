package com.unify.parsing;

import com.unify.model.Bucket;
import com.unify.model.Item;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class to parse the user input and create a bucket
 */
public final class ItemsInputParser {

    private ItemsInputParser() {
        throw new AssertionError("Not to be instantiated!");
    }

    /**
     * method to parse the input
     * @param input the text to be parsed
     * @return a Bucket containing a name and a Set of Item
     * @throws IllegalArgumentException when the input is not valid
     */
    public static Bucket parseInput(String input) {
        if(input == null || input.isBlank()){
            throw new IllegalArgumentException("You need to provide a valid input");
        }
        input = input.strip();
        Bucket bucket = new Bucket();
        //Parsing the input
        String[] names = input.split("\\s+");

        if(names.length < 2 || !names[0].equalsIgnoreCase("PriceBasket")){
            throw new IllegalArgumentException("The format is not valid");
        }
        //Getting the bucket name
        bucket.setName(names[0]);

        Set<Item> items = Arrays.stream(names)
                .filter(item -> !item.isEmpty())
                .skip(1) // the first element is the names of the bucket
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .map(e -> new Item(e.getKey(), e.getValue().intValue()))
                .collect(Collectors.toSet());

        //Setting the bucket items
        bucket.setItems(items);
        return bucket;
    }
}
