package me.Xocky.News.core.utils.cmd.subcmd;

import org.bukkit.entity.Player;

public interface ISubCommand {
    public abstract String getName();
    public abstract String getRoot();
    public abstract void run(Player p, String[] args);
    public abstract String getPermission();
}
