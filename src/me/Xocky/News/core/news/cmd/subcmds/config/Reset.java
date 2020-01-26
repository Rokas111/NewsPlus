package me.Xocky.News.core.news.cmd.subcmds.config;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.config.CommandConfig;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class Reset extends SubCommand {
    public Reset() {
        super("reset","News+.news.reset", "Resets all News+ configs",new CommandConfig("news.reset"));
        addDefaults();
    }
    private void addDefaults() {
        getCommandConfig().addDefault("successful_reset_configs","successful_reset_configs_message");
        if (!getCommandConfig().setup()) {
            getCommandConfig().setupKeys();
        }
    }
    public void run(Player p, String[] args) {
        News.um.getConfigManager().resetAllConfigs();
        p.spigot().sendMessage(getCommandConfig().getMessage("successful_reset_configs",p).create());
    }
}
