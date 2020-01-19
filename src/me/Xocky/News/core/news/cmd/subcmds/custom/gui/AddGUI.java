package me.Xocky.News.core.news.cmd.subcmds.custom.gui;

import me.Xocky.News.core.News;
import me.Xocky.News.core.news.pages.GUIEditPage;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import me.Xocky.News.core.utils.custom.gui.GUI;
import org.bukkit.entity.Player;

public class AddGUI extends SubCommand {
    public AddGUI() {
        super("addgui","News+.news.addgui","Adds a GUI to the GUIs config");
    }

    public void run(Player p, String[] args) {
        if (args == null || args.length < 3) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("wrong_usage_add_gui",p).create());
            return;
        }
        if (!args[1].matches("-?\\d+(\\.\\d+)?") || Integer.parseInt(args[1]) % 9 != 0) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("not_a_gui_size_number",p).create());
            return;
        }
        new GUIEditPage(new GUI(args[2],Integer.parseInt(args[1])),p,args[0]).open();
    }
}
