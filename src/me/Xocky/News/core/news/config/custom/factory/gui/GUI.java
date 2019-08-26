package me.Xocky.News.core.news.config.custom.factory.gui;

import me.Xocky.News.core.news.config.custom.factory.item.BItem;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GUI {
    private Inventory inv;
    private String title;
    private HashMap<Integer,String> slottags;
    public GUI(String title,int size) {
        this.title = title;
        this.slottags = new HashMap<>();
        inv = Bukkit.createInventory(null,size, title);
    }
    public int getSize() {
        return inv.getSize();
    }
    public String getTitle() {
        return title;
    }
    public BItem getItem(int slot) {
        return new BItem(inv.getItem(slot));
    }
    public HashMap<Integer,BItem> getItems() {
        HashMap<Integer,BItem> items = new HashMap<>();
        for (int i = 0;i<getSize();i++) {
            items.put(i,getItem(i));
        }
        return items;
    }
    public GUI setItem(int slot, ItemStack item) {
        inv.setItem(slot,item);
        return this;
    }
    public GUI setItems(int start, int end, ItemStack item) {
        for (int i = start;i<= end;i++) {
            setItem(i,item);
        }
        return this;
    }
    public GUI setSlotTag(int slot,String tag) {
        if (!slottags.containsKey(slot)) {slottags.put(slot,tag);}
        return this;
    }
    public GUI setSlotTags(int start,int end,String tag) {
        for (int i = start;i<= end;i++) {
            setSlotTag(i,tag);
        }
        return this;
    }
    public HashMap<Integer,String> getSlotTags() {
        return slottags;
    }
    public HashMap<Integer,String> getSlotTags(String tag) {
        HashMap<Integer,String> map = new HashMap<>();
        if (slottags.isEmpty()) {
            return map;
        }
        slottags.keySet().stream().filter(slot -> slottags.get(slot).equals(tag)).forEachOrdered(slot -> map.put(slot,slottags.get(slot)));
        return map;
    }
    public Inventory getInventory() {
        return inv;
    }
}
