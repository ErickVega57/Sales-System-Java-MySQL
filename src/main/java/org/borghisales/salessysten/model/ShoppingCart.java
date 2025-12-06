package org.borghisales.salessysten.model;

import org.borghisales.salessysten.controllers.GenerateSaleController;

import java.sql.ResultSet;
import java.sql.SQLException;

public record ShoppingCart(int nr, String cod, String product, int quantity, double price, double discount,double iva, double subtotal, double total){
    public ShoppingCart(int nr, String cod, String product, int quantity, double price, double discount, double iva) {
        this(nr, cod, product, quantity, price, discount, iva, Double.parseDouble(String.format("%.2f",quantity*price)),
                Double.parseDouble(String.format("%.2f",(quantity*price)*(1+iva)*discount)));
    }
    public static ShoppingCart fromResultSet(ResultSet rs) throws SQLException {
        int nr = rs.getInt("nr");
        String cod = rs.getString("cod");
        String product = rs.getString("product");
        int quantity = rs.getInt("quantity");
        double price = rs.getDouble("price");
        //nuevos atributos
        //double discount = rs.getDouble("discount");
        //double iva = rs.getDouble("iva");
        //double subtotal = rs.getDouble("subtotal");
        double total = rs.getDouble("total");
        double iva = 0;
        double discount = 0;
        double subtotal = 0;


        return new ShoppingCart(nr, cod, product, quantity, price, discount, iva, subtotal, total);
    }
}
