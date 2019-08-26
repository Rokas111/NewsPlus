package me.Xocky.News.core.news.pages;

import com.google.common.collect.Lists;
import me.Xocky.News.core.News;
import me.Xocky.News.core.news.config.custom.factory.gui.GUI;
import me.Xocky.News.core.news.config.custom.factory.item.BItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class NewsPage {
    private int page;
    private GUI g;
    private List<String> news;
    public NewsPage(GUI g, List<String> news) {
        this.page = 1;
        this.g =g;
        this.news =news;
    }
    public NewsPage open(Player p) {
        if (!g.getSlotTags("newsslot").isEmpty()) {
            List<Integer> newsslots = Lists.newArrayList(g.getSlotTags("newsslot").keySet());
            Collections.sort(newsslots);
            for (Integer slot : newsslots) {
                g.setItem(slot, News.NM.getItemFactory().manufacture(News.NM.getNewsConfig().getYaml().getString("empty_news_slot_item")));
            }
            int perpagenews = newsslots.size();
            int ie = 0;
            for (int i = perpagenews*(page-1);i<(perpagenews*page>news.size()?news.size()-perpagenews*(page-1):perpagenews*page);i++) {
                g.setItem(newsslots.get(ie),new BItem(News.NM.getItemFactory().manufacture(News.NM.getNewsConfig().getYaml().getString("news."+news.get(i)+".item"))).setNBTString("newsitem",news.get(i)).build());
                ie++;
            }
        }
        p.openInventory(g.getInventory());
        return this;
    }
    public NewsPage nextPage(Player p) {
        if (!(this.page <  (news.size()>g.getSlotTags("newsslot").size()?(Integer.parseInt(Double.toString(news.size() / g.getSlotTags("newsslot").size()).split("\\.")[0])  +1):1))) {
            p.sendMessage(ChatColor.RED +"No more pages");
            return this;
        }
        page++;
        open(p);
        return this;
    }
    public NewsPage previousPage(Player p) {
        if (this.page <= 1) {
            p.sendMessage(ChatColor.RED +"No more previous pages");
            return this;
        }
        page--;
        open(p);
        return this;
    }
}
