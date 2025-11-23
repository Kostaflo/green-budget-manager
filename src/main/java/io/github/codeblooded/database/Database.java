package io.github.codeblooded.database;

import io.github.codeblooded.model.BudgetItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

  private static final String URL = "jdbc:sqlite:budget.db";

  public List<BudgetItem> getAllBudgets() {
    List<BudgetItem> budgets = new ArrayList<>();

    String sql = "SELECT id, foreas, poso, pososto FROM budget";

    try (Connection conn = DriverManager.getConnection(URL);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int amount = rs.getInt("amount");
        double percentage = rs.getDouble("percentage");
        budgets.add(new BudgetItem(id, name, amount, percentage));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return budgets;
  }
}
