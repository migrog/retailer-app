package com.negomatic.retailer.model;

public class Menu {
    private int id;
    private String value;
    private String description;
    private int order_menu;
    private String icon;

    public Menu(int id, String value, String description, int order_menu, String icon) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.order_menu = order_menu;
        this.icon = icon;
    }
    //GETS

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public int getOrder_menu() {
        return order_menu;
    }

    public String getIcon(){return icon;}
}
