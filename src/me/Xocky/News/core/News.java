package me.Xocky.News.core;

import me.Xocky.News.core.mysql.MySQL;
import me.Xocky.News.core.utils.UtilManager;
import me.Xocky.News.core.news.NewsManager;
import me.Xocky.News.core.utils.legacy.Version;
import me.Xocky.News.core.utils.metrics.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class News extends JavaPlugin {
    public static final String PLUGIN_FOLDER = "News+";
    public static UtilManager um;
    public static NewsManager nm;
    public static MySQL mySQL;
    public static Version v;
    public void onEnable() {
        v = Version.getVersion();
        um = new UtilManager(this);
        mySQL = new MySQL(this);
        nm = new NewsManager(this);
        Metrics m = new Metrics(this);
        um.initialize();
        mySQL.initialize();
        nm.initialize();
    }
    public void onDisable() {
        if (nm.getNewsConfig().getLatestNewsOneTimeOnly() && nm.getNewsConfig().getLatestNewsOnJoin()) {
            nm.getPlayerList().save();
        }
    }

}
