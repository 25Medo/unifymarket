package com.unify.pricing;

import com.unify.model.Bucket;
import com.unify.model.Discount;
import com.unify.model.Item;

import java.util.Optional;

/**
 * Process the pricing
 */
public final class PriceCalculator {

    /**
     * return the subtotal meaning the total before applying discounts
     * @param bucket to be priced
     * @return the value of the subtotal
     */
    public static double getSubtotal(Bucket bucket){
        double subTotal = 0;
        for (Item item: bucket.getItems()){
            subTotal += item.getUnitPrice() * item.getQuantity();
        }
        return subTotal/100;
    }

    /**
     * Provide the list of items on discount and the actual discount values
     * @param bucket to be priced
     * @return a text of the items on discount and the actual discount values
     */
    public static String listItemsDiscounts(Bucket bucket){
        StringBuilder builder = new StringBuilder();
        bucket.getItems().forEach( item ->{
            if ( item.getDiscount() != null){

                builder
                        .append(item.getName())
                        .append(" ")
                        .append(item.getDiscount().getValue() * 100)
                        .append(" % off: -");

                //case on price
                if(item.getDiscount().getQuantityRequired() == 0){
                    builder.append((item.getQuantity() * item.getUnitPrice() * item.getDiscount().getValue()));
                }
                //case on quantity
                //this particular case can be improved: like for each 2 Soups 1 Bread will be half priced
                else if(item.getQuantity() >= item.getDiscount().getQuantityRequired()){
                    Optional<Item> itemOnDiscountOpt = bucket.getItems().stream()
                            .filter( i -> i.getName().equalsIgnoreCase(item.getDiscount().getItemName()))
                            .findFirst(); //this is just one case example of 3 items: 2 soups 1 bread
                    if (itemOnDiscountOpt.isPresent()){
                        Item itemOnDiscount = itemOnDiscountOpt.get();
                        builder.append((itemOnDiscount.getUnitPrice() * itemOnDiscount.getQuantity() * item.getDiscount().getValue())/100);

                    }
                }
                builder.append("p\n");
            }
        });
        String itemsDiscount = builder.toString();
        return itemsDiscount.isEmpty() ? "(No offers available)" : itemsDiscount.substring(0, itemsDiscount.length() - 1);
    }

    /**
     * Calculate the total discount of the provided bucket
     * @param bucket to be priced
     * @return total discount of the bucket
     */
    public static double calculateDiscounts(Bucket bucket) {
        double totalDiscount = 0;

        for (Item item : bucket.getItems()) {
            if (item.getDiscount() == null) {
                continue;
            }

            Discount discount = item.getDiscount();

            if (discount.getQuantityRequired() == 0) {
                // Simple percentage discount on the current item
                totalDiscount += item.getUnitPrice() * item.getQuantity() * discount.getValue();
            }
            else if (item.getQuantity() >= discount.getQuantityRequired()) {
                // Discount applies to another item (it can be current item as well)
                totalDiscount += calculateCrossItemDiscount(bucket, discount);
            }
        }

        return totalDiscount/100;
    }

    /**
     * Case of discount on quantity
     * @param bucket to be priced
     * @param discount of the item
     * @return value of the discount for this item
     */
    private static double calculateCrossItemDiscount(Bucket bucket, Discount discount) {
        double crossDiscount = 0;
        Optional<Item> itemOnDiscountOpt = bucket.getItems().stream()
                        .filter( i -> i.getName().equalsIgnoreCase(discount.getItemName()))
                        .findFirst();
        if (itemOnDiscountOpt.isPresent()){
            Item itemOnDiscount = itemOnDiscountOpt.get();
            crossDiscount = itemOnDiscount.getUnitPrice() * itemOnDiscount.getQuantity() * discount.getValue();
        }
        return crossDiscount;
    }
}
