package me.Xocky.News.core.config.cmds;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.Command;
import org.bukkit.entity.Player;

public class ConfigReload extends Command {
    public ConfigReload() {
        super( "News+.configreload","configreload");
    }
    public void run(Player p, String[] args) {
        News.um.getConfigManager().reloadAllConfigs();
    }
}
