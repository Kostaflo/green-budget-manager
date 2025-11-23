package io.github.codeblooded.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final String URL = "jdbc:sqlite:budget.db";

    public List<budget> getAllBudgets() {
        List<budget> budgets = new ArrayList<>();

        String sql = "SELECT id, foreas, poso FROM budget";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double amount = rs.getDouble("amount");

                budgets.add(new budget(id, name, amount));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return budgets;
    }
}
