package com.unify.parsing;

import com.unify.model.Bucket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class ItemsInputParserTest {

    @Test
    @DisplayName("Test valid input parsing")
    void testValidInputParsing() {
        // Given
        String validInput = "PriceBasket Apple Milk Bread";

        // When
        Bucket bucket = ItemsInputParser.parseInput(validInput);

        // Then
        assertEquals("PriceBasket", bucket.getName());
        assertEquals(3, bucket.getItems().size());

        assertTrue(bucket.getItems().stream()
                .anyMatch(item -> "Apple".equals(item.getName()) && item.getQuantity() == 1));
        assertTrue(bucket.getItems().stream()
                .anyMatch(item -> "Milk".equals(item.getName()) && item.getQuantity() == 1));
        assertTrue(bucket.getItems().stream()
                .anyMatch(item -> "Bread".equals(item.getName()) && item.getQuantity() == 1));
    }

    @Test
    @DisplayName("Test parsing with duplicate items")
    void testParsingWithDuplicateItems() {
        // Given
        String inputWithDuplicates = "PriceBasket Apple Apple Milk Bread Milk";

        // When
        Bucket bucket = ItemsInputParser.parseInput(inputWithDuplicates);

        // Then
        assertEquals("PriceBasket", bucket.getName());
        assertEquals(3, bucket.getItems().size());

        assertTrue(bucket.getItems().stream()
                .anyMatch(item -> "Apple".equals(item.getName()) && item.getQuantity() == 2));
        assertTrue(bucket.getItems().stream()
                .anyMatch(item -> "Milk".equals(item.getName()) && item.getQuantity() == 2));
        assertTrue(bucket.getItems().stream()
                .anyMatch(item -> "Bread".equals(item.getName()) && item.getQuantity() == 1));
    }

    @Test
    @DisplayName("Test empty input throws IllegalArgumentException")
    void testEmptyInputThrowsException() {
        // Given
        String emptyInput = "";

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ItemsInputParser.parseInput(emptyInput)
        );

        assertEquals("You need to provide a valid input", exception.getMessage());
    }

    @Test
    @DisplayName("Test invalid format throws IllegalArgumentException")
    void testInvalidFormatThrowsException() {
        // Given
        String invalidInput = "Basket Apple Milk";

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ItemsInputParser.parseInput(invalidInput)
        );

        assertEquals("The format is not valid", exception.getMessage());
    }

    @Test
    @DisplayName("Test input with too few items throws IllegalArgumentException")
    void testInputWithTooFewItemsThrowsException() {
        // Given
        String tooFewItemsInput = "PriceBasket";

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ItemsInputParser.parseInput(tooFewItemsInput)
        );

        assertEquals("The format is not valid", exception.getMessage());
    }

}
