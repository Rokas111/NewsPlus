package me.Xocky.News.core.news.cmd;

import com.google.common.collect.Lists;
import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.Command;
import me.Xocky.News.core.news.pages.NewsPage;
import org.bukkit.entity.Player;

public class NewsCmd extends Command {

    public NewsCmd() {
        super( "News+.news","news");
    }
    public void run(Player p, String[] args) {
        if (args == null || args.length ==0) {
            News.nm.addNewsPage(p,new NewsPage(News.nm.getGUIFactory().manufacture(News.nm.getNewsConfig().getYaml().getString("main_gui")), Lists.newArrayList(News.nm.getNewsConfig().getYaml().getConfigurationSection("news").getKeys(false)),p));
            News.nm.getNewsPage(p).open();
            return;
        }
        if (!News.nm.getNewsConfig().getYaml().contains("news."+args[0])) {
            p.spigot().sendMessage(News.nm.getMessageFactory().manufacture(News.nm.getNewsConfig().getYaml().getString("no-such-news-page")).create());
            return;
        }
        News.nm.getBookFactory().manufacture(News.nm.getNewsConfig().getYaml().getString("news." +args[0])).openBook(p);
    }
}
