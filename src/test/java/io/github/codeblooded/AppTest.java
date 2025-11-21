package io.github.codeblooded;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    void testAddition() {
        int result = App.addNumbers(2, 3);
        assertEquals(5, result, "2 + 3 should equal 5");
    }

    @Test
    void testNegativeNumbers() {
        int result = App.addNumbers(-2, -3);
        assertEquals(-5, result, "Negative numbers should work correctly");
    }

    @Test
    void testZeroCase() {
        int result = App.addNumbers(0, 5);
        assertEquals(10, result, "Adding zero should not change the result");
    }
}

