package io.github.codeblooded.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class Database {


  private static final String DB_URL = "jdbc:sqlite:green_budget.db";

  private static Connection connection;


  private Database() {}

  /**
   * Returns a single shared database connection.
   *
   * @return Connection
   * @throws SQLException if connection fails
   */
  public static Connection getConnection() throws SQLException {
    if (connection == null || connection.isClosed()) {
      connection = DriverManager.getConnection(DB_URL);
      enableForeignKeys(connection);
    }
    return connection;
  }

  private static void enableForeignKeys(Connection conn) throws SQLException {
    try (Statement stmt = conn.createStatement()) {
      stmt.execute("PRAGMA foreign_keys = ON;");
    }
  }


  public static void closeConnection() {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}

