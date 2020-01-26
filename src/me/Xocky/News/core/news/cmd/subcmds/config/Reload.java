package me.Xocky.News.core.news.cmd.subcmds.config;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.config.CommandConfig;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class Reload extends SubCommand {
    public Reload() {
        super("reload","News+.news.reload", "Reloads all News+ configs",new CommandConfig("news.reload"));
        addDefaults();
    }
    private void addDefaults() {
        getCommandConfig().addDefault("successful_config_reload","successful_config_reload_message");
        if (!getCommandConfig().setup()) {
            getCommandConfig().setupKeys();
        }
    }
    public void run(Player p, String[] args) {
        News.um.getConfigManager().reloadAllConfigs();
        p.spigot().sendMessage(getCommandConfig().getMessage("successful_config_reload",p).create());
    }
}
