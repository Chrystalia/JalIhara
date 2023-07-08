package com.example.jalihara;

import java.util.ArrayList;
import java.util.List;

public class TicketList {

    private static TicketList instance;
    private List<Item> itemList = new ArrayList<Item>();

    private TicketList(){

    }

    public static synchronized TicketList getInstance(){
        if(instance == null){
            instance = new TicketList();
        }
        return instance;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
