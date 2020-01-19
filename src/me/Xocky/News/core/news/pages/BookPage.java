package me.Xocky.News.core.news.pages;

import com.google.common.collect.Lists;
import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.custom.gui.GUI;
import me.Xocky.News.core.utils.custom.item.BItem;
import me.Xocky.News.core.utils.pages.GUIMultiPage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class BookPage extends GUIMultiPage {
    public BookPage(GUI g, List<String> books, Player p) {
        super(p,g,books,true);
    }
    public void open() {
        if (!getGUI().getSlotTags("bookslot").isEmpty()) {
            List<Integer> bookslots = Lists.newArrayList(getGUI().getSlotTags("bookslot"));
            Collections.sort(bookslots);
            for (Integer slot : bookslots) {
                getGUI().setItem(slot, News.nm.getItemFactory().manufacture("none_item"));
            }
            int perpagenews = bookslots.size();
            int ie = 0;
            for (int i = perpagenews*(getPage()-1);i<(perpagenews*getPage()>getElements().size()?getElements().size()-perpagenews*(getPage()-1):perpagenews*getPage());i++) {
                getGUI().setItem(bookslots.get(ie),News.nm.getBookFactory().manufacture(getElements().get(i),getPlayer()).getItem().setDisplayName(ChatColor.GREEN + getElements().get(i)).setNBTString("bookname",getElements().get(i)).build());
                ie++;
            }
        }
        getPlayer().openInventory(getGUI().getInventory());
        News.nm.addPage(getPlayer(),this);
    }
    public void interact(int slot, BItem item) {
        if (item.getNBTString("bookname") != null) {
            getPlayer().closeInventory();
            if (News.nm.getBookConfig().getYaml().contains(item.getNBTString("bookname"))) {
                News.nm.getBookFactory().manufacture(item.getNBTString("bookname"),getPlayer()).openBook();
                return;
            }
            return;
        }
        if (item.hasSignature()) {
            if (item.getNBTString("signature").equals("nextpage")) {
                nextPage();
            } else if (item.getNBTString("signature").equals("backpage")) {
                previousPage();
            }
        }
    }
    public void close() {

    }
}
