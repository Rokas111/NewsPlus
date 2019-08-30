package me.Xocky.News.core.news.cmd;

import com.google.common.collect.Lists;
import me.Xocky.News.core.News;
import me.Xocky.News.core.cmd.Command;
import org.bukkit.entity.Player;

public class LatestNewsCmd extends Command {
    public LatestNewsCmd() {
        super( "News+.LatestNews","latestnews");
    }
    public void run(Player p, String[] args) {
        News.NM.openLatest(p);
    }
}
