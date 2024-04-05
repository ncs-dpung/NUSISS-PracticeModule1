package com.nusiss.inventory.backend.observers;

public interface InventorySubject {
    void addObserver(InventoryObserver observer);
    void removeObserver(InventoryObserver observer);
    void notifyObservers();
}

