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
    private boolean cancel;
    public GUIMultiPage(Player p, GUI g, List<String> elements,boolean cancel) {
        this.page = 1;
        this.p =p;
        this.g =g;
        this.elements = elements;
        this.cancel = cancel;
    }
    public void nextPage(List<Integer> slots) {
        if (!(this.page <  (elements.size()>slots.size()?(Integer.parseInt(Double.toString(elements.size() / slots.size()).split("\\.")[0])  +1):1))) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("no-next-page",p).create());
            return;
        }
        page++;
        open();
    }
    public void previousPage() {
        if (this.page <= 1) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("no-previous-page",p).create());
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
    public boolean cancelClick() {
        return this.cancel;
    }
}
