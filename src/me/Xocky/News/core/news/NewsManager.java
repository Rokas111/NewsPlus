package me.Xocky.News.core.news;

import me.Xocky.News.core.News;
import me.Xocky.News.core.news.cmd.NewsCmd;
import me.Xocky.News.core.news.config.NewsConfig;
import me.Xocky.News.core.news.config.custom.NewsBookConfig;
import me.Xocky.News.core.news.config.custom.NewsGUIConfig;
import me.Xocky.News.core.news.config.custom.NewsItemConfig;
import me.Xocky.News.core.news.config.custom.NewsTextConfig;
import me.Xocky.News.core.news.config.custom.factory.book.BookFactory;
import me.Xocky.News.core.news.config.custom.factory.gui.GUI;
import me.Xocky.News.core.news.config.custom.factory.gui.GUIFactory;
import me.Xocky.News.core.news.config.custom.factory.item.BItem;
import me.Xocky.News.core.news.config.custom.factory.item.ItemFactory;
import me.Xocky.News.core.news.config.custom.factory.item.Items;
import me.Xocky.News.core.news.config.custom.factory.text.JSONFactory;
import me.Xocky.News.core.news.config.custom.factory.text.UncodedText;
import me.Xocky.News.core.news.pages.NewsPage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.Collections;
import java.util.HashMap;

public class NewsManager implements Listener {
    private Plugin pl;
    private NewsConfig nc;
    private HashMap<Player, NewsPage> newspages;
    private NewsBookConfig bookconfig;
    private NewsItemConfig itemconfig;
    private NewsGUIConfig guiconfig;
    private NewsTextConfig textconfig;
    private GUIFactory guiFactory;
    private ItemFactory itemFactory;
    private JSONFactory textFactory;
    private BookFactory bookFactory;
    public NewsManager(Plugin pl) {
        this.pl = pl;
        this.newspages = new HashMap<>();
        this.guiFactory = new GUIFactory();
        this.itemFactory = new ItemFactory();
        this.textFactory = new JSONFactory();
        this.bookFactory = new BookFactory();
        registerConfigs();
        registerCommands();
        setupDefaults();
        pl.getServer().getPluginManager().registerEvents(this,pl);
    }
    private void registerConfigs() {
        News.CM.registerConfig(new NewsConfig());
        nc = (NewsConfig) News.CM.getYaml("news");
        News.CM.registerConfig(new NewsBookConfig());
        bookconfig = (NewsBookConfig) News.CM.getYaml("Books");
        News.CM.registerConfig(new NewsItemConfig());
        itemconfig = (NewsItemConfig) News.CM.getYaml("Items");
        News.CM.registerConfig(new NewsTextConfig());
        textconfig = (NewsTextConfig) News.CM.getYaml("Text");
        News.CM.registerConfig(new NewsGUIConfig());
        guiconfig = (NewsGUIConfig) News.CM.getYaml("Guis");
    }
    private void setupDefaults() {
        setupBookDefaults();
        setupItemsDefaults();
        setupTextDefaults();
        Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
            @Override
            public void run() {
                setupGUIDefaults();
                guiconfig.checkSetup();
            }
        },10);
        bookconfig.checkSetup();
        itemconfig.checkSetup();
        textconfig.checkSetup();
    }
    private void setupBookDefaults() {
        bookconfig.addBookDefault(Collections.singletonList(new String[]{"*update_feature", "", "", "", "", "", "", "*discord", "", "*website", "", "*news_more"}),"update");
        bookconfig.addBookDefault(Collections.singletonList(new String[]{"*blog_text","","","","","","","*discord","","*website","","*news_more"}),"blog");
    }
    private void setupGUIDefaults() {
        guiconfig.addGUIDefault(new GUI("News",27).setItems(0,26 ,getItemFactory().manufacture("none_item")).setItem(18,getItemFactory().manufacture("previous_page")).setItem(26,getItemFactory().manufacture("next_page")).setSlotTags(9,17,"newsslot"),"news_update");
    }
    private void setupItemsDefaults() {
        itemconfig.addItemDefault(Items.NONE_ITEM.getItem(),"none_item");
        itemconfig.addItemDefault(Items.EMPTY_SLOT.getItem(),"none_news");
        itemconfig.addItemDefault(Items.NEXT_PAGE_ITEM.getItem().setNBTString("tag","nextpage"),"next_page");
        itemconfig.addItemDefault(Items.PREVIOUS_PAGE_ITEM.getItem().setNBTString("tag","previouspage"),"previous_page");
        itemconfig.addItemDefault(Items.NEWS_UPDATE.getItem(),"news_update");
        itemconfig.addItemDefault(Items.NEWS_BLOG.getItem(),"news_blog");
    }
    private void setupTextDefaults() {
        textconfig.addTextDefault(new UncodedText("&aAdded chocolate &acookies!","&4<3",""),"update_feature");
        textconfig.addTextDefault(new UncodedText("&aMore cookies will be &aadded","",""),"blog_text");
        textconfig.addTextDefault(new UncodedText("&8&l&nDiscord","&7Click here to join the discord","https://discord.gg"),"discord");
        textconfig.addTextDefault(new UncodedText("&a&l&nWebsite","&7Click here to see the website","https://www.google.com/"),"website");
        textconfig.addTextDefault(new UncodedText("&4&l&nMore","&7Click here to see more","/news"),"news_more");
    }
    public NewsConfig getNewsConfig() {
        return this.nc;
    }
    public NewsTextConfig getTextConfig() {
        return this.textconfig;
    }
    public NewsItemConfig getItemConfig() {
        return this.itemconfig;
    }
    public NewsGUIConfig getGUIConfig() {
        return this.guiconfig;
    }
    public NewsBookConfig getBookConfig() {
        return this.bookconfig;
    }
    private void registerCommands() {
        News.CC.registerCommand(new NewsCmd(),"news");
    }

    public NewsPage getNewsPage(Player p) {
        return newspages.get(p);
    }
    public GUIFactory getGUIFactory() {
        return this.guiFactory;
    }
    public ItemFactory getItemFactory() {
        return this.itemFactory;
    }
    public JSONFactory getJSONFactory() {
        return this.textFactory;
    }
    public BookFactory getBookFactory() {
        return this.bookFactory;
    }
    public void addNewsPage(Player p, NewsPage page) {
        newspages.put(p,page);
    }
    @EventHandler
    public void click(InventoryClickEvent e) {
        if (newspages.containsKey(e.getWhoClicked())) {
            e.setCancelled(true);
            if ((e.getCurrentItem()!= null&& e.getCurrentItem().getType() != Material.AIR)) {
                BItem item = new BItem(e.getCurrentItem());
                Player p = (Player) e.getWhoClicked();
                if (item.getNBTString("newsitem") != null) {
                    p.closeInventory();
                    getBookFactory().manufacture(News.NM.getNewsConfig().getYaml().getString("news." + item.getNBTString("newsitem") + ".book")).openBook(p);
                    return;
                }
                if (item.getNBTString("tag") != null) {
                    if (item.getNBTString("tag").equals("nextpage")) {
                        getNewsPage(p).nextPage(p);
                    } else if (item.getNBTString("tag").equals("previouspage")) {
                        getNewsPage(p).previousPage(p);
                    }
                }
            }
        }
    }
    @EventHandler
    public void close(InventoryCloseEvent e) {
        if (newspages.containsKey(e.getPlayer())) {
            newspages.remove(e.getPlayer());
        }
    }
    @EventHandler
    public void join(PlayerJoinEvent e) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(pl, () -> openLatest(e.getPlayer()),10);
    }
    public void openLatest(Player p) {
        if (!News.NM.getNewsConfig().getYaml().getConfigurationSection("news").getKeys(false).isEmpty()) {
            String newest = (String) News.NM.getNewsConfig().getYaml().getConfigurationSection("news").getKeys(false).toArray()[0];
            getBookFactory().manufacture(News.NM.getNewsConfig().getYaml().getString("news."+newest+".book")).openBook(p);
        }
    }
}
