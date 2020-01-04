package me.Xocky.News.core.news.cmd.subcmds;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class ClearPlayers extends SubCommand {
    public ClearPlayers() {
        super("clearplayers","News+.news.clearplayers");
    }
    public void run(Player p, String[] args) {
        if (News.nm.getNewsConfig().getLatestNewsOneTimeOnly() && News.nm.getNewsConfig().getLatestNewsOnJoin()) {
            News.nm.getPlayerList().clearPlayers();
            p.spigot().sendMessage(News.nm.getMessageFactory().manufacture(News.nm.getNewsConfig().getYaml().getString("successful-clear-players")).create());
            return;
        }
        p.spigot().sendMessage(News.nm.getMessageFactory().manufacture(News.nm.getNewsConfig().getYaml().getString("error-player-list-disabled")).create());
    }
}
