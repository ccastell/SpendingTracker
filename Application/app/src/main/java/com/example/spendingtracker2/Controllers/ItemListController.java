package com.example.spendingtracker2.Controllers;

import com.example.spendingtracker2.Models.Item;
import com.example.spendingtracker2.Models.ItemList;

import java.util.ArrayList;

/**
 * Created by carlcastello on 16/03/17.
 */

public class ItemListController {
    private ItemList itemList;

    public ItemListController() {
        this.itemList = new ItemList();
    }

    public ItemListController(ArrayList<Item> itemList) {
        this.itemList = new ItemList(itemList);
    }

}
