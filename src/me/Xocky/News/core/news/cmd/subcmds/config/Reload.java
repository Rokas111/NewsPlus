package me.Xocky.News.core.news.cmd.subcmds.config;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class Reload extends SubCommand {
    public Reload() {
        super("reload","News+.news.reload", "Reloads all News+ configs");
    }
    public void run(Player p, String[] args) {
        News.um.getConfigManager().reloadAllConfigs();
        p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("successful-config-reload",p).create());
    }
}
