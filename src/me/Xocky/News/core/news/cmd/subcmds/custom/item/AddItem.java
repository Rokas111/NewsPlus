package me.Xocky.News.core.news.cmd.subcmds.custom.item;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import me.Xocky.News.core.utils.custom.item.BItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class AddItem extends SubCommand {
    public AddItem() {
        super("additem","News+.news.additem","Adds an item that is in your hand to the items config");
    }

    public void run(Player p, String[] args) {
        if (args == null || args.length == 0) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("wrong_usage_add_item",p).create());
            return;
        }
        if (p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("not_an_item",p).create());
            return;
        }
        News.nm.getItemConfig().addDefault(new BItem(p.getItemInHand()),args[0]);
        p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("successful_add_item",p).create());
    }
}
