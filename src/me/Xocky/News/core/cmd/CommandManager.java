package me.Xocky.News.core.cmd;

import com.google.common.collect.Lists;
import me.Xocky.News.core.cmd.subcmd.ISubCommand;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CommandManager implements Listener {
    private HashMap<List<String>,ICommand> commands;
    private HashMap<ICommand, List<ISubCommand>> subcommands;
    private Plugin pl;
    public CommandManager(Plugin plugin) {
        commands = new HashMap<>();
        subcommands = new HashMap<>();
        this.pl = plugin;
        pl.getServer().getPluginManager().registerEvents(this,this.pl);
    }
    @EventHandler
    public void command(PlayerCommandPreprocessEvent e) {
            String commandname = e.getMessage().contains(" ") ? e.getMessage().split(" ")[0].substring(1):e.getMessage().substring(1);
            String[] args = e.getMessage().contains(" ")?e.getMessage().substring(e.getMessage().indexOf(' ')+1).split(" "):null;
            ICommand cmd = getCommand(commandname.toLowerCase());
            if (cmd != null) {
                e.setCancelled(true);
                if (args != null &&args.length >= 1 && subcommands.containsKey(cmd)&& getSubCommand(cmd,args[0]) != null ) {
                    if (!e.getPlayer().hasPermission(getSubCommand(cmd,args[0]).getPermission())) {
                        e.getPlayer().sendMessage(ChatColor.RED +"You don't have the permission to execute this command");
                        return;
                    }
                    getSubCommand(cmd,args[0]).run(e.getPlayer(),args);
                    return;
                }
                if (!e.getPlayer().hasPermission(cmd.getPermission())) {
                    e.getPlayer().sendMessage(ChatColor.RED +"You don't have the permission to execute this command");
                    return;
                }
                cmd.run(e.getPlayer(), args);
            }
    }
    private ISubCommand getSubCommand(ICommand root,String name) {
        return !subcommands.get(root).stream().filter(list -> list.getName().equals(name)).collect(Collectors.toList()).isEmpty()?subcommands.get(root).stream().filter(list -> list.getName().equals(name)).collect(Collectors.toList()).get(0):null;
    }
    private ICommand getCommand(String command) {
        return !commands.keySet().stream().filter(list -> list.contains(command)).collect(Collectors.toList()).isEmpty()?commands.get(commands.keySet().stream().filter(list -> list.contains(command)).collect(Collectors.toList()).get(0)):null;
    }
    public void registerCommand(ICommand cmd,String... commandnames) {
        commands.put(Lists.newArrayList(commandnames),cmd);
    }
}
