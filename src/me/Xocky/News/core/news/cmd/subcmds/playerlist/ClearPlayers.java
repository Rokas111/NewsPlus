package me.Xocky.News.core.news.cmd.subcmds.playerlist;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class ClearPlayers extends SubCommand {
    public ClearPlayers() {
        super("clearplayers","News+.news.clearplayers", "Clears data player list");
    }
    public void run(Player p, String[] args) {
        if (News.nm.getNewsConfig().getLatestNewsOneTimeOnly() && News.nm.getNewsConfig().getLatestNewsOnJoin()) {
            News.nm.getPlayerList().clearPlayers();
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("successful-clear-players",p).create());
            return;
        }
        p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("error-player-list-disabled",p).create());
    }
}
