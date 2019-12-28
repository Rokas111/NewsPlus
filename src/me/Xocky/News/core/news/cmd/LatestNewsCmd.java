package me.Xocky.News.core.news.cmd;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.Command;
import org.bukkit.entity.Player;

public class LatestNewsCmd extends Command {
    public LatestNewsCmd() {
        super( "News+.latestnews","latestnews");
    }
    public void run(Player p, String[] args) {
        News.nm.openLatest(p);
    }
}
