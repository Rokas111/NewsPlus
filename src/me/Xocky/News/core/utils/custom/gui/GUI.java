package me.Xocky.News.core.utils.custom.gui;

import me.Xocky.News.core.utils.custom.item.BItem;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GUI {
    private Inventory inv;
    private String title;
    private HashMap<Integer,String> slotTags;
    private HashMap<Integer,String> commandTags;
    public GUI(String title,int size) {
        this.title = title;
        this.slotTags = new HashMap<>();
        this.commandTags = new HashMap<>();
        inv = Bukkit.createInventory(null,size, title);
    }
    public GUI(Inventory inv) {
        this.title = inv.getTitle();
        this.slotTags = new HashMap<>();
        this.commandTags = new HashMap<>();
        this.inv = inv;
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
        if (!slotTags.containsKey(slot)) {slotTags.put(slot,tag);}
        return this;
    }
    public GUI setCommandTag(int slot,String command) {
        if (!commandTags.containsKey(slot)) {commandTags.put(slot,command);}
        return this;
    }
    public GUI setSlotTags(int start,int end,String tag) {
        for (int i = start;i<= end;i++) {
            setSlotTag(i,tag);
        }
        return this;
    }
    public List<Integer> getSlotTags(String tag) {
        List<Integer> slots = new ArrayList<>();
        if (slotTags.isEmpty()) {
            return slots;
        }
        slotTags.keySet().stream().filter(slot -> slotTags.get(slot).equals(tag)).forEachOrdered(slots::add);
        return slots;
    }
    public HashMap<Integer,String> getSlotTags() {
        return slotTags;
    }
    public HashMap<Integer,String> getCommandTags() {
        return commandTags;
    }
    public Inventory getInventory() {
        return inv;
    }
}
