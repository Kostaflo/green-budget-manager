package io.github.codeblooded.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BudgetItemTest {

    @Test
    void testBudgetItemConstructorAndGetters() {
        // Arrange
        int id = 1001;
        String agency = "Government Agency";
        int amount = 5000000;
        double percentage = 0.2;

        // Act
        BudgetItem item = new BudgetItem(id, agency, amount, percentage);

        // Assert
        assertEquals(id, item.getId(), "ID should match the value passed to constructor");
        assertEquals(agency, item.getAgency(), "Agency should match the value passed to constructor");
        assertEquals(amount, item.getAmount(), "Amount should match the value passed to constructor");
        assertEquals(percentage, item.getPercentage(), "Percentage should match the value passed to constructor");
    }

    @Test
    void testPercentageCalculation() {
        // Optional: if you want to check percentage logic
        int amount = 5000000;
        double expectedPercentage = amount / 25_000_000.0;
        BudgetItem item = new BudgetItem(1001, "Test Agency", amount, expectedPercentage);

        assertEquals(expectedPercentage, item.getPercentage(), 0.0001, "Percentage should be correctly calculated");
    }
}

