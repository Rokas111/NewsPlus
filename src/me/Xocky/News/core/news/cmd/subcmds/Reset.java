package me.Xocky.News.core.news.cmd.subcmds;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class Reset extends SubCommand {
    public Reset() {
        super("reset","News+.news.reset", "Resets all News+ configs");
    }
    public void run(Player p, String[] args) {
        News.um.getConfigManager().resetAllConfigs();
        p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("successful-reset-configs",p).create());
    }
}
