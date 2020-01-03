package me.Xocky.News.core.utils.cmd;

import me.Xocky.News.core.utils.cmd.subcmd.ISubCommand;
import org.bukkit.entity.Player;

import java.util.List;

public abstract interface ICommand {
    public abstract String getName();
    public abstract void run(Player p, String[] args);
    public abstract String getPermission();
    public abstract List<ISubCommand> getSubCommands();
}
