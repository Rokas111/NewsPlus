package me.Xocky.News.core.cmd;

import me.Xocky.News.core.utils.cmd.ICommand;
import me.Xocky.News.core.utils.cmd.subcmd.ISubCommand;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandManager{
    private List<ICommand> commands;
    private Plugin pl;
    public CommandManager(Plugin plugin) {
        this.pl = plugin;
        commands = new ArrayList<>();
    }
    public ISubCommand getSubCommand(ICommand root,String name) {
        return !root.getSubCommands().isEmpty()?
                (!root.getSubCommands().stream().filter(subcommand -> subcommand.getName().equals(name)).collect(Collectors.toList()).isEmpty()?root.getSubCommands().stream().filter(subcommand -> subcommand.getName().equals(name)).collect(Collectors.toList()).get(0):null)
                :null;
    }
    public ICommand getCommand(String command) {
        return !commands.stream().filter(cmd -> cmd.getName().equals(command)).collect(Collectors.toList()).isEmpty()?commands.stream().filter(cmd -> cmd.getName().equals(command)).collect(Collectors.toList()).get(0):null;
    }
    public List<ICommand> getCommands() {
        return this.commands;
    }
    public void registerCommand(ICommand cmd) {
        commands.add(cmd);
    }

}
