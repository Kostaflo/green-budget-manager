package io.github.codeblooded.database;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.github.codeblooded.database.Database;
import io.github.codeblooded.model.BudgetItem;


class DatabaseTest {

    @Test
    void getAllBudgets_returnsList() {
        Database db = new Database();

        List<BudgetItem> budgets = db.getAllBudgets();

        assertNotNull(budgets);
    }

    @Test
    void getAllBudgets_notEmpty() {
        Database db = new Database();

        List<BudgetItem> budgets = db.getAllBudgets();

        assertFalse(budgets.isEmpty());
    }

    @Test
    void getAllBudgets_firstItemHasValidData() {
        Database db = new Database();

        List<BudgetItem> budgets = db.getAllBudgets();

        BudgetItem first = budgets.get(0);

        assertTrue(first.getId() > 0);
        assertNotNull(first.getForeas());
        assertTrue(first.getPoso() > 0);
    }
}
