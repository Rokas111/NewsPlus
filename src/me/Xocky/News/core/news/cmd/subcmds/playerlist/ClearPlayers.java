package me.Xocky.News.core.news.cmd.subcmds.playerlist;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.config.CommandConfig;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class ClearPlayers extends SubCommand {
    public ClearPlayers() {
        super("clearplayers","News+.news.clearplayers", "Clears data player list",new CommandConfig("news.clearplayers"));
        addDefaults();
    }
    private void addDefaults() {
        getCommandConfig().addDefault("successful_clear_players","successful_clear_players_message");
        getCommandConfig().addDefault("error_player_list_disabled","error_player_list_disabled_message");
        if (!getCommandConfig().setup()) {
            getCommandConfig().setupKeys();
        }
    }
    public void run(Player p, String[] args) {
        if (News.nm.getNewsConfig().getLatestNewsOneTimeOnly() && News.nm.getNewsConfig().getLatestNewsOnJoin()) {
            News.nm.getPlayerList().clearPlayers();
            p.spigot().sendMessage(getCommandConfig().getMessage("successful_clear_players",p).create());
            return;
        }
        p.spigot().sendMessage(getCommandConfig().getMessage("error_player_list_disabled",p).create());
    }
}
