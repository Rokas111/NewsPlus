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

public class NewsPage extends GUIMultiPage {
    public NewsPage(GUI g, List<String> news,Player p) {
        super(p,g,news);
    }
    public void open() {
        if (!getGUI().getSlotTags("newsslot").isEmpty()) {
            List<Integer> newsslots = Lists.newArrayList(getGUI().getSlotTags("newsslot"));
            Collections.sort(newsslots);
            for (Integer slot : newsslots) {
                getGUI().setItem(slot, News.nm.getItemFactory().manufacture(News.nm.getNewsConfig().getYaml().getString("empty_news_slot_item")));
            }
            int perpagenews = newsslots.size();
            int ie = 0;
            for (int i = perpagenews*(getPage()-1);i<(perpagenews*getPage()>getElements().size()?getElements().size()-perpagenews*(getPage()-1):perpagenews*getPage());i++) {
                getGUI().setItem(newsslots.get(ie),new BItem(News.nm.getItemFactory().manufacture(News.nm.getNewsConfig().getYaml().getString("news."+getElements().get(i)+".item"))).setNBTString("newsitem",getElements().get(i)).build());
                ie++;
            }
        }
        getPlayer().openInventory(getGUI().getInventory());
    }
}
