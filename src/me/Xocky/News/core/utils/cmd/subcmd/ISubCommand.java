package me.Xocky.News.core.utils.cmd.subcmd;

import me.Xocky.News.core.utils.cmd.config.CommandConfig;
import org.bukkit.entity.Player;

public interface ISubCommand {
    public abstract String getName();
    public abstract void run(Player p, String[] args);
    public abstract String getPermission();
    public abstract String getDescription();
    public abstract CommandConfig getCommandConfig();
}
