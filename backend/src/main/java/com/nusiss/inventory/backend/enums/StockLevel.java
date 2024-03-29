package com.nusiss.inventory.backend.enums;


public enum StockLevel {
    LOW,
    SUFFICIENT;


    public static StockLevel fromString(String stockLevel) {
        for (StockLevel sl : StockLevel.values()) {
            if (sl.name().equalsIgnoreCase(stockLevel)) {
                return sl;
            }
        }
        return null;
    }
}
