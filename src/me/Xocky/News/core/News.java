package me.Xocky.News.core;

import me.Xocky.News.core.hook.HookManager;
import me.Xocky.News.core.mysql.MySQL;
import me.Xocky.News.core.utils.UtilManager;
import me.Xocky.News.core.news.NewsManager;
import me.Xocky.News.core.utils.cmd.ICommand;
import me.Xocky.News.core.utils.legacy.Version;
import me.Xocky.News.core.utils.metrics.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class News extends JavaPlugin {
    public static final String PLUGIN_FOLDER = "News+";
    public static UtilManager um;
    public static NewsManager nm;
    public static HookManager hm;
    public static MySQL mySQL;
    public static Version v;
    public void onEnable() {
        v = Version.getVersion();
        um = new UtilManager(this);
        mySQL = new MySQL(this);
        hm = new HookManager(this);
        nm = new NewsManager(this);
        Metrics m = new Metrics(this);
        um.initialize();
        mySQL.initialize();
        hm.initialize();
        nm.initialize();
    }
    public void onDisable() {
        if (nm.getNewsConfig().getLatestNewsOneTimeOnly() && nm.getNewsConfig().getLatestNewsOnJoin()) {
            nm.getPlayerList().save();
        }
    }
    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
        Player p = (Player)s;
        ICommand cmd = um.getCommandManager().getCommand(command.getName().toLowerCase());
        if (cmd != null) {
            if (args != null &&args.length >= 1 && !cmd.getSubCommands().isEmpty() && !cmd.getSubCommands().stream().filter(subcommand -> subcommand.getName().equals(args[0])).collect(Collectors.toList()).isEmpty() ) {
                if (!p.hasPermission( um.getCommandManager().getSubCommand(cmd,args[0]).getPermission())) {
                    p.spigot().sendMessage(nm.getNewsConfig().getMessage("insufficient-permission-sub-command",p).create());
                    return true;
                }
                um.getCommandManager().getSubCommand(cmd,args[0]).run(p,args);
                return true;
            }
            if (!p.hasPermission(cmd.getPermission())) {
                p.spigot().sendMessage(nm.getNewsConfig().getMessage("insufficient-permission-command",p).create());
                return true;
            }
            cmd.run(p, args);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> suggestions = new ArrayList<>();
        List<String> completions = new ArrayList<>();
        um.getCommandManager().getCommands().forEach(cmd -> {
            completions.add(cmd.getName());
            cmd.getSubCommands().forEach(subcmd -> {
                completions.add(cmd.getName() + " " + subcmd.getName());
            });
        });
        StringUtil.copyPartialMatches(args[0], completions, suggestions);
        Collections.sort(suggestions);
        return suggestions;
    }

}
