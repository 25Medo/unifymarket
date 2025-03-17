package com.unify.pricing;

import com.unify.model.Bucket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalculatorTest {
    @Test
    @DisplayName("Should return 0 when bucket is empty")
    void shouldReturnZeroWhenBucketIsEmpty() {

        Bucket emptyBucket = new Bucket("Empty", new HashSet<>());

        double result = PriceCalculator.getSubtotal(emptyBucket);

        assertEquals(0.0, result, 0.001);
    }
}
