package me.Xocky.News.core.news.cmd.subcmds.custom.item;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class GetItem extends SubCommand {
    public GetItem() {
        super("getitem","News+.news.getitem","Gives you an item from the items config");
    }

    public void run(Player p, String[] args) {
        if (args == null || args.length == 0) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("wrong_usage_get_item",p).create());
            return;
        }
        if (!News.nm.getItemConfig().getYaml().contains(args[0])) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("no_such_item",p).create());
            return;
        }
        p.getInventory().addItem(News.nm.getItemFactory().manufacture(args[0]));
        p.updateInventory();
    }
}
