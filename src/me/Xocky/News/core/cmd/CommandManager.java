package me.Xocky.News.core.cmd;

import com.google.common.collect.Lists;
import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.ICommand;
import me.Xocky.News.core.utils.cmd.subcmd.ISubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CommandManager implements Listener {
    private HashMap<List<String>, ICommand> commands;
    private Plugin pl;
    public CommandManager(Plugin plugin) {
        this.pl = plugin;
        commands = new HashMap<>();
    }
    public void initialize() {
        pl.getServer().getPluginManager().registerEvents(this,this.pl);
    }
    @EventHandler
    public void command(PlayerCommandPreprocessEvent e) {
            Player p = e.getPlayer();
            String commandname = e.getMessage().contains(" ") ? e.getMessage().split(" ")[0].substring(1):e.getMessage().substring(1);
            String[] args = e.getMessage().contains(" ")?e.getMessage().substring(e.getMessage().indexOf(' ')+1).split(" "):null;
            ICommand cmd = getCommand(commandname.toLowerCase());
            if (cmd != null) {
                e.setCancelled(true);
                if (args != null &&args.length >= 1 && !cmd.getSubCommands().isEmpty() && !cmd.getSubCommands().stream().filter(subcommand -> subcommand.getName().equals(args[0])).collect(Collectors.toList()).isEmpty() ) {
                    if (!p.hasPermission(getSubCommand(cmd,args[0]).getPermission())) {
                        p.spigot().sendMessage(News.nm.getMessageFactory().manufacture(News.nm.getNewsConfig().getYaml().getString("insufficient-permission-sub-command")).create());
                        return;
                    }
                    getSubCommand(cmd,args[0]).run(p,args);
                    return;
                }
                if (!p.hasPermission(cmd.getPermission())) {
                    p.spigot().sendMessage(News.nm.getMessageFactory().manufacture(News.nm.getNewsConfig().getYaml().getString("insufficient-permission-command")).create());
                    return;
                }
                cmd.run(p, args);
            }
    }
    private ISubCommand getSubCommand(ICommand root,String name) {
        return !root.getSubCommands().isEmpty()?
                (!root.getSubCommands().stream().filter(subcommand -> subcommand.getName().equals(name)).collect(Collectors.toList()).isEmpty()?root.getSubCommands().stream().filter(subcommand -> subcommand.getName().equals(name)).collect(Collectors.toList()).get(0):null)
                :null;
    }
    private ICommand getCommand(String command) {
        return !commands.keySet().stream().filter(list -> list.contains(command)).collect(Collectors.toList()).isEmpty()?commands.get(commands.keySet().stream().filter(list -> list.contains(command)).collect(Collectors.toList()).get(0)):null;
    }
    public void registerCommand(ICommand cmd,String... commandnames) {
        commands.put(Lists.newArrayList(commandnames),cmd);
    }
}
