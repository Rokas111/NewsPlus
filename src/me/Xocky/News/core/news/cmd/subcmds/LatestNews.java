package me.Xocky.News.core.news.cmd.subcmds;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class LatestNews extends SubCommand {
    public LatestNews() {
        super( "latestnews","News+.news.latestnews","Opens the latest news page");
    }
    public void run(Player p, String[] args) {
        News.nm.openLatest(p);
    }
}
