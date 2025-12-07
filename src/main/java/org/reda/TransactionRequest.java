package org.reda;

// (Gardez les imports existants s'il y en a)

public class TransactionRequest {
    // AJOUTER CECI :
    private String userName;

    private double v1;
    private double v2;
    private double v3;
    private double amount;

    // Getters et Setters POUR TOUS LES CHAMPS, y compris userName
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public double getV1() { return v1; }
    public void setV1(double v1) { this.v1 = v1; }
    public double getV2() { return v2; }
    public void setV2(double v2) { this.v2 = v2; }
    public double getV3() { return v3; }
    public void setV3(double v3) { this.v3 = v3; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}