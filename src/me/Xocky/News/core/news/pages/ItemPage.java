package me.Xocky.News.core.news.pages;

import com.google.common.collect.Lists;
import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.custom.gui.GUI;
import me.Xocky.News.core.utils.custom.item.BItem;
import me.Xocky.News.core.utils.pages.GUIMultiPage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;

public class ItemPage extends GUIMultiPage {
    public ItemPage(GUI g, List<String> items, Player p) {
        super(p,g,items,true);
    }
    public void open() {
        if (!getGUI().getSlotTags("itemslot").isEmpty()) {
            List<Integer> itemslots = Lists.newArrayList(getGUI().getSlotTags("itemslot"));
            Collections.sort(itemslots);
            for (Integer slot : itemslots) {
                getGUI().setItem(slot, new ItemStack(Material.AIR));
            }
            int perpagenews = itemslots.size();
            int ie = 0;
            for (int i = perpagenews*(getPage()-1);i<(perpagenews*getPage()>getElements().size()?getElements().size()-perpagenews*(getPage()-1):perpagenews*getPage());i++) {
                getGUI().setItem(itemslots.get(ie),new BItem(News.nm.getItemFactory().manufacture(getElements().get(i))).setNBTString("itempreview",getElements().get(i)).build());
                ie++;
            }
        }
        getPlayer().openInventory(getGUI().getInventory());
        News.nm.addPage(getPlayer(),this);
    }
    public void interact(int slot, BItem item) {
        if (item.getNBTString("itempreview") != null) {
            getPlayer().closeInventory();
            if (News.nm.getItemConfig().getYaml().contains(item.getNBTString("itempreview"))) {
                getPlayer().getInventory().addItem(News.nm.getItemFactory().manufacture(item.getNBTString("itempreview")));
                getPlayer().updateInventory();
            }
            return;
        }
        if (item.hasSignature()) {
            if (item.getNBTString("signature").equals("nextpage")) {
                nextPage(getGUI().getSlotTags("itemslot"));
            } else if (item.getNBTString("signature").equals("backpage")) {
                previousPage();
            }
        }
    }
    public void close() {

    }
}
