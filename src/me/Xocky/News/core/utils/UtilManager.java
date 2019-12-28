package me.Xocky.News.core.utils;

import me.Xocky.News.core.cmd.CommandManager;
import me.Xocky.News.core.config.ConfigManager;
import org.bukkit.plugin.Plugin;

public class UtilManager {
    private Plugin pl;
    private CommandManager cc;
    private ConfigManager cm;
    public UtilManager(Plugin pl) {
        this.pl = pl;
        cm = new ConfigManager(pl);
        cc = new CommandManager(pl);
    }
    public void initialize() {
        cm.initialize();
        cc.initialize();
    }
    public ConfigManager getConfigManager() {
        return this.cm;
    }
    public CommandManager getCommandManager() {
        return this.cc;
    }
}
