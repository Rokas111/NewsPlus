package me.Xocky.News.core.utils.pages;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.custom.gui.GUI;
import me.Xocky.News.core.utils.pages.interfaces.IGUIMultiPage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class GUIMultiPage implements IGUIMultiPage {
    private int page;
    private Player p;
    private List<String> elements;
    private GUI g;
    public GUIMultiPage(Player p, GUI g, List<String> elements) {
        this.page = 1;
        this.p =p;
        this.g =g;
        this.elements = elements;
    }
    public void nextPage() {
        if (!(this.page <  (elements.size()>g.getSlotTags("newsslot").size()?(Integer.parseInt(Double.toString(elements.size() / g.getSlotTags("newsslot").size()).split("\\.")[0])  +1):1))) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("no-next-page").create());
            return;
        }
        page++;
        open();
    }
    public void previousPage() {
        if (this.page <= 1) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("no-previous-page").create());
            return;
        }
        page--;
        open();
    }
    public List<String> getElements() {
        return this.elements;
    }
    public int getPage() {
        return this.page;
    }
    public GUI getGUI() {
        return this.g;
    }
    public Player getPlayer() {
        return this.p;
    }
    public void setItem(int slot, ItemStack item) {
        g.setItem(slot,item);
    }
    public List<Integer> getElementSlots() {
        return g.getSlotTags("elementslot");
    }
}
