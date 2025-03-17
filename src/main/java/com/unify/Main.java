package com.unify;

import com.unify.memory.DiscountMemory;
import com.unify.memory.Memory;
import com.unify.memory.PriceMemory;
import com.unify.memory.UnitMemory;
import com.unify.model.Bucket;
import com.unify.model.Discount;
import com.unify.parsing.ItemsInputParser;
import com.unify.pricing.*;

import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome to Unify market!");
        System.out.println("Hold on it is getting set up...");

        //Add initial prices and aggregate to pence
        Memory<Double> priceMemory = PriceMemory.getInstance();
        priceMemory.add("Apples", 100.0);
        priceMemory.add("Soup", 65.0);
        priceMemory.add("Bread", 80.0);
        priceMemory.add("Milk", 130.0);

        Memory<String> unitMemory = UnitMemory.getInstance();
        unitMemory.add("Apples", "bag");
        unitMemory.add("Soup", "tin");
        unitMemory.add("Bread", "loaf");
        unitMemory.add("Milk", "bottle");

        Memory<Discount> discountMemory = DiscountMemory.getInstance();
        discountMemory.add("Apples", new Discount("Apples", 0.10, 0));
        discountMemory.add("Soup", new Discount("Bread", 0.50, 2));

        System.out.println("************** ready **************\n");

        Scanner scanner = new Scanner(System.in);
        System.out.println("What is in your bucket? ");
        String listings = scanner.nextLine();

        Bucket bucket = ItemsInputParser.parseInput(listings);

        Initializer priceInitializer = new PriceInitializer();
        Initializer unitInitializer = new UnitInitializer();
        Initializer discountInitializer = new DiscountInitializer();

        priceInitializer.init(bucket);
        unitInitializer.init(bucket);
        discountInitializer.init(bucket);

        double subtotal = PriceCalculator.getSubtotal(bucket);
        String subTotal = "Subtotal: £"+ subtotal;
        String total = "Total: £"+ (subtotal - PriceCalculator.calculateDiscounts(bucket));
        String listItemsDiscounts = PriceCalculator.listItemsDiscounts(bucket);

        System.out.println(subTotal);
        System.out.println(listItemsDiscounts);
        System.out.println(total);
    }
}