package me.Xocky.News.core.news.cmd.subcmds.misc;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Help extends SubCommand {
    public Help() {
        super("help","News+.news.help","Shows all News+ commands,subcommands with thier description");
    }
    public void run(Player p, String[] args) {
        p.sendMessage(ChatColor.GREEN+"Commands of [" + ChatColor.YELLOW+News.PLUGIN_FOLDER + ChatColor.GREEN+"]:");
        News.um.getCommandManager().getCommands().forEach(cmd -> {
            p.sendMessage(ChatColor.YELLOW+"/" + cmd.getName() + " " + ChatColor.GREEN +cmd.getDescription());
            cmd.getSubCommands().forEach(subcmd -> {
                p.sendMessage(ChatColor.YELLOW+"/" + cmd.getName() + " " +subcmd.getName()+" "+ ChatColor.GREEN +subcmd.getDescription());
            });
        });
    }
}
