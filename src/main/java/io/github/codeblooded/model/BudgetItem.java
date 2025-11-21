package io.github.codeblooded.model;

public class BudgetItem {

  private int id;
  private String Agency;
  private int Amount;
  private double Percentage;

  public BudgetItem(int id, String agency, int amount, double percentage) {
    this.id = id;
    Agency = agency;
    Amount = amount;
    Percentage = percentage;
  }

  public int getId() {
    return id;
  }

  public String getAgency() {
    return Agency;
  }

  public int getAmount() {
    return Amount;
  }

  public double getPercentage() {
    return Percentage;
  }
}
