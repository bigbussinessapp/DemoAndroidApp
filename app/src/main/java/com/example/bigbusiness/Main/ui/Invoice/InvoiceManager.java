package com.example.bigbusiness.Main.ui.Invoice;

import com.example.bigbusiness.Main.ui.Inventory.InventoryManager;
import com.example.bigbusiness.Models.InvoiceItem;
import com.example.bigbusiness.Models.InvoiceProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvoiceManager {

    List<InvoiceItem> invoices;
    private static InvoiceManager instance;
    private InventoryManager inventoryManager;
    private InvoiceFBHelper invoiceFBHelper;
    private HashMap<String, InvoiceProduct> invoiceProducts; //item id, item
    private HashMap<String, String> productsWithQuantity = new HashMap<>();//itemid, quantity

    private InvoiceManager() {
        invoices = new ArrayList<>();
        invoiceProducts = new HashMap<>();
        inventoryManager = InventoryManager.getInstance();
        invoiceFBHelper = InvoiceFBHelper.getInstance();
//        InventoryItem dummy1 = new InventoryItem("item 1", "20", "1", "nos", "300", "invoiceId1");
//        InventoryItem dummy2 = new InventoryItem("item 2", "30", "1","nos", "30", "invoiceId1");
//        InventoryItem dummy3 = new InventoryItem("item 3", "50", "1", "nos", "3000", "invoiceId1");
//        addedInventoryItems.add(dummy1);
//        addedInventoryItems.add(dummy2);
//        addedInventoryItems.add(dummy3);
//        addItem(new InvoiceItem(1, "prod1"));
//        addItem(new InvoiceItem(2,"prod2"));
//        addItem(new InvoiceItem(3, "prod3"));
    }

    public static InvoiceManager getInstance()
    {
        if(instance == null)
        {
            instance = new InvoiceManager();
        }
        return instance;
    }

    public void addInvoice(InvoiceItem item)
    {
//        this.invoices.add(item);
        invoiceFBHelper.addInvoice(item);
        invoiceProducts.clear();
        productsWithQuantity.clear();
//        inventoryManager.updateItems(productsWithQuantity);
    }

    public void addProduct(InvoiceProduct item)
    {
        if(!productsWithQuantity.containsKey(item.getProductCode()))
        {
            productsWithQuantity.put(item.getProductCode(), item.getQuantity());
        }
        else
        {
            String quantity = productsWithQuantity.get(item.getProductCode());
            String updatedQuantity = String.valueOf(Integer.parseInt(quantity) + Integer.parseInt(item.getQuantity()));
            productsWithQuantity.put(item.getProductCode(), updatedQuantity);
            item.setQuantity(updatedQuantity);
            invoiceProducts.replace(item.getProductCode(), item);
        }
        this.invoiceProducts.put(item.getProductCode(), item);
    }

    public List<InvoiceItem> getAllInvoices() {
        return this.invoices;
    }

    public HashMap<String, InvoiceProduct> getAddedItemsWithIds()
    {
        return this.invoiceProducts;
    }

    public List<InvoiceProduct> getAddedItems()
    {
        return new ArrayList<>(this.invoiceProducts.values());
    }


    public void deleteItem(int position) {
        this.invoiceProducts.remove(position);
    }

    public void clearItems() {
        this.invoiceProducts = new HashMap<>();
    }

    public int getAddedQuantityById(String itemID) {
        if(productsWithQuantity.containsKey(String.valueOf(itemID)))
        {
            return Integer.parseInt(productsWithQuantity.get(String.valueOf(itemID)));
        }
        else
        {
            return 0;
        }
    }
}
