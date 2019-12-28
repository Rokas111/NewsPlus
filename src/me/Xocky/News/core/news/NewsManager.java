package me.Xocky.News.core.news;

import me.Xocky.News.core.News;
import me.Xocky.News.core.news.cmd.LatestNewsCmd;
import me.Xocky.News.core.news.cmd.NewsCmd;
import me.Xocky.News.core.news.config.NewsConfig;
import me.Xocky.News.core.news.config.custom.configs.*;
import me.Xocky.News.core.news.config.custom.configs.defaults.*;
import me.Xocky.News.core.news.config.custom.factory.book.BookFactory;
import me.Xocky.News.core.news.config.custom.factory.message.MessageFactory;
import me.Xocky.News.core.utils.custom.gui.GUI;
import me.Xocky.News.core.news.config.custom.factory.gui.GUIFactory;
import me.Xocky.News.core.utils.custom.item.BItem;
import me.Xocky.News.core.news.config.custom.factory.item.ItemFactory;
import me.Xocky.News.core.news.config.custom.factory.json.JSONFactory;
import me.Xocky.News.core.utils.custom.json.UncodedJSON;
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

import java.util.HashMap;

public class NewsManager implements Listener {
    private Plugin pl;
    private NewsConfig nc;
    private HashMap<Player, NewsPage> newspages;
    private NewsBookConfig bookConfig;
    private NewsItemConfig itemConfig;
    private NewsGUIConfig guiConfig;
    private NewsJSONConfig jsonConfig;
    private NewsMessagesConfig messageConfig;
    private GUIFactory guiFactory;
    private ItemFactory itemFactory;
    private JSONFactory jsonFactory;
    private BookFactory bookFactory;
    private MessageFactory messageFactory;
    public NewsManager(Plugin pl) {
        this.pl = pl;
        this.newspages = new HashMap<>();
        this.guiFactory = new GUIFactory();
        this.itemFactory = new ItemFactory();
        this.jsonFactory = new JSONFactory();
        this.bookFactory = new BookFactory();
        this.messageFactory = new MessageFactory();
    }
    public void initialize() {
        registerConfigs();
        registerCommands();
        setupDefaults();
        pl.getServer().getPluginManager().registerEvents(this,pl);
    }
    private void registerConfigs() {
        News.um.getConfigManager().registerConfig(new NewsConfig());
        nc = (NewsConfig) News.um.getConfigManager().getYaml("news");
        News.um.getConfigManager().registerConfig(new NewsBookConfig());
        bookConfig = (NewsBookConfig) News.um.getConfigManager().getYaml("Books");
        News.um.getConfigManager().registerConfig(new NewsItemConfig());
        itemConfig = (NewsItemConfig) News.um.getConfigManager().getYaml("Items");
        News.um.getConfigManager().registerConfig(new NewsJSONConfig());
        jsonConfig = (NewsJSONConfig) News.um.getConfigManager().getYaml("JSON");
        News.um.getConfigManager().registerConfig(new NewsGUIConfig());
        guiConfig = (NewsGUIConfig) News.um.getConfigManager().getYaml("Guis");
        News.um.getConfigManager().registerConfig(new NewsMessagesConfig());
        messageConfig = (NewsMessagesConfig) News.um.getConfigManager().getYaml("Messages");
    }
    private void setupDefaults() {
        setupBookDefaults();
        setupItemsDefaults();
        setupJSONDefaults();
        setupMessagesDefaults();
        Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
            @Override
            public void run() {
                setupGUIDefaults();
                guiConfig.checkSetup();
            }
        },10);
        bookConfig.checkSetup();
        itemConfig.checkSetup();
        jsonConfig.checkSetup();
        messageConfig.checkSetup();
    }
    private void setupBookDefaults() {
        bookConfig.addBookDefaults(Books.values());
    }
    private void setupGUIDefaults() {
        guiConfig.addGUIDefaults(GUIs.values());
    }
    private void setupItemsDefaults() {
        itemConfig.addItemDefaults(Items.values());
    }
    private void setupJSONDefaults() {
        jsonConfig.addJSONDefaults(JSON.values());
    }
    private void setupMessagesDefaults() {
        messageConfig.addMessageDefaults(Messages.values());
    }
    public NewsConfig getNewsConfig() {
        return this.nc;
    }
    public NewsJSONConfig getJSONConfig() {
        return this.jsonConfig;
    }
    public NewsItemConfig getItemConfig() {
        return this.itemConfig;
    }
    public NewsGUIConfig getGUIConfig() {
        return this.guiConfig;
    }
    public NewsBookConfig getBookConfig() {
        return this.bookConfig;
    }
    public NewsMessagesConfig getMessageConfig() {
        return this.messageConfig;
    }
    private void registerCommands() {
        News.um.getCommandManager().registerCommand(new NewsCmd(),"news");
        News.um.getCommandManager().registerCommand(new LatestNewsCmd(),"latestnews");
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
        return this.jsonFactory;
    }
    public BookFactory getBookFactory() {
        return this.bookFactory;
    }
    public MessageFactory getMessageFactory() {
        return this.messageFactory;
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
                    getBookFactory().manufacture(getNewsConfig().getYaml().getString("news." + item.getNBTString("newsitem") + ".book")).openBook(p);
                    return;
                }
                if (item.getNBTString("signature") != null) {
                    if (item.getNBTString("signature").equals("nextpage")) {
                        getNewsPage(p).nextPage();
                    } else if (item.getNBTString("signature").equals("previouspage")) {
                        getNewsPage(p).previousPage();
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
        if (getNewsConfig().getYaml().getBoolean("show-latest-news-on-join")) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(pl, () -> openLatest(e.getPlayer()), 10);
        }
    }
    public void openLatest(Player p) {
        if (!getNewsConfig().getYaml().getConfigurationSection("news").getKeys(false).isEmpty()) {
            String newest = (String) getNewsConfig().getYaml().getConfigurationSection("news").getKeys(false).toArray()[0];
            getBookFactory().manufacture(getNewsConfig().getYaml().getString("news."+newest+".book")).openBook(p);
        }
    }
}
