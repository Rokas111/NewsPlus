package me.Xocky.News.core.news.cmd.subcmds;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class Reload extends SubCommand {
    public Reload() {
        super("reload","News+.news.reload");
    }
    public void run(Player p, String[] args) {
        News.um.getConfigManager().reloadAllConfigs();
        p.spigot().sendMessage(News.nm.getMessageFactory().manufacture(News.nm.getNewsConfig().getYaml().getString("successful-config-reload")).create());
    }
}