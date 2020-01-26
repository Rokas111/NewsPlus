package me.Xocky.News.core.news.pages;

import com.google.common.collect.Lists;
import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.custom.gui.GUI;
import me.Xocky.News.core.utils.custom.item.BItem;
import me.Xocky.News.core.utils.pages.GUIMultiPage;
import me.Xocky.News.core.utils.pages.interfaces.IGUIPage;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class NewsPage extends GUIMultiPage {
    public NewsPage(GUI g, List<String> news,Player p) {
        super(p,g,news,true);
    }
    public void open() {
        if (!getGUI().getSlotTags("newsslot").isEmpty()) {
            List<Integer> newsslots = Lists.newArrayList(getGUI().getSlotTags("newsslot"));
            Collections.sort(newsslots);
            int perpagenews = newsslots.size();
            int ie = 0;
            for (int i = perpagenews*(getPage()-1);i<(perpagenews*getPage()>getElements().size()?getElements().size()-perpagenews*(getPage()-1):perpagenews*getPage());i++) {
                getGUI().setItem(newsslots.get(ie),new BItem(News.nm.getNewsConfig().getItem("news."+getElements().get(i)+".item")).setNBTString("newsitem",getElements().get(i)).build());
                ie++;
            }
        }
        getPlayer().openInventory(getGUI().getInventory());
        News.nm.addPage(getPlayer(),this);
    }
    public void interact(int slot, BItem item) {
        if (item.getNBTString("newsitem") != null) {
            getPlayer().closeInventory();
            if (News.nm.getNewsConfig().getYaml().contains("news." + item.getNBTString("newsitem") + ".book")) {
                News.nm.getNewsConfig().getBook("news." + item.getNBTString("newsitem") + ".book",getPlayer()).openBook();
                return;
            }
            if (News.nm.getNewsConfig().getYaml().contains("news." + item.getNBTString("newsitem") + ".gui")) {
                IGUIPage page = News.nm.getPage(getPlayer());
                getPlayer().openInventory(News.nm.getNewsConfig().getGUI("news." + item.getNBTString("newsitem") + ".gui").getInventory());
                News.nm.addPage(getPlayer(),page);
            }
            return;
        }
        if (item.hasSignature()) {
            if (item.getNBTString("signature").equals("nextpage")) {
                nextPage(getGUI().getSlotTags("newsslot"));
            } else if (item.getNBTString("signature").equals("backpage")) {
                previousPage();
            }
        }
    }
    public void close(){
    }
}
