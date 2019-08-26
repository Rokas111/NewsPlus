package me.Xocky.News.core;

import me.Xocky.News.core.cmd.CommandManager;
import me.Xocky.News.core.config.ConfigManager;
import me.Xocky.News.core.news.NewsManager;
import me.Xocky.News.legacy.Version;
import org.bukkit.plugin.java.JavaPlugin;

public class News extends JavaPlugin {
    public static CommandManager CC;
    public static ConfigManager CM;
    public static NewsManager NM;
    public static Version V;
    public void onEnable() {
        V = Version.getVersion();
        CC = new CommandManager(this);
        CM = new ConfigManager();
        NM = new NewsManager(this);
    }
    public void onDisable() {}

}
