package me.Xocky.News.core.news.pages;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.custom.gui.GUI;
import me.Xocky.News.core.utils.custom.item.BItem;
import me.Xocky.News.core.utils.pages.GUIPage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GUIEditPage extends GUIPage {
    private String name;
    public GUIEditPage(GUI g, Player p,String name) {
        super(g,p,false);
        this.name = name;
    }
    public void open() {
        getPlayer().openInventory(getGUI().getInventory());
        News.nm.addPage(getPlayer(),this);
    }
    public void interact(int slot, BItem item) {
    }
    public void close() {
        GUI g = new GUI(getPlayer().getOpenInventory().getTopInventory());
        g.getItems().keySet().forEach(slot -> g.setItem(slot,addItem(g.getItem(slot).build())));
        News.nm.getGUIConfig().addDefault(g,name);
        getPlayer().spigot().sendMessage(News.nm.getNewsConfig().getMessage("successful_add_gui",getPlayer()).create());
    }
    private ItemStack addItem(ItemStack item) {
        BItem bitem = new BItem(item);
        if (item != null) {
            String name = item.getType().toString();
            int itemnumber = 0;
            while (News.nm.getItemConfig().getYaml().contains(name)) {
                if (!News.nm.getItemConfig().getYaml().contains(name)) {
                    break;
                }
                itemnumber++;
                name = name + "_" + itemnumber;
            }
            News.nm.getItemConfig().addDefault(bitem, name);
            bitem.setNBTString("itemname", name);
        }
        return bitem.build();
    }
}
