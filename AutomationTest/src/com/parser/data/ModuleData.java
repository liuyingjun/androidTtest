
package com.parser.data;


import java.util.ArrayList;

public class ModuleData {

    private String name;

    private ArrayList<ProductData> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ProductData> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductData> products) {
        this.products = products;
    }

}
