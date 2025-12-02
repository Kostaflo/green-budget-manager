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
            // declare and assign inside the loop
            int id = rs.getInt("id");
            String foreas = rs.getString("foreas");
            int poso = rs.getInt("poso");
            double pososto = rs.getDouble("pososto");

            budgets.add(new BudgetItem(id, foreas, poso, pososto));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return budgets;
}

}
