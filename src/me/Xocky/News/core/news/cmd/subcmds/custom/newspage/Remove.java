package me.Xocky.News.core.news.cmd.subcmds.custom.newspage;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class Remove extends SubCommand {
    public Remove() {
        super("remove","News+.news.remove","Removes a specific news page");
    }

    public void run(Player p, String[] args) {
        if (args == null || args.length ==0) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("wrong_usage_remove",p).create());
            return;
        }
        if (!News.nm.getNewsConfig().containsNewsConfig(args[0])) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("no-such-news-page",p).create());
            return;
        }
        News.nm.getNewsConfig().removeNewsPage(args[0]);
        p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("successful_remove_news_page",p).create());
    }
}
