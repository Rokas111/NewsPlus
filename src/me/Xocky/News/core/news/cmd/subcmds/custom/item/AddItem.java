package me.Xocky.News.core.news.cmd.subcmds.custom.item;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.config.CommandConfig;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import me.Xocky.News.core.utils.custom.item.BItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class AddItem extends SubCommand {
    public AddItem() {
        super("additem","News+.news.additem","Adds an item that is in your hand to the items config",new CommandConfig("news.additem"));
        addDefaults();
    }
    private void addDefaults() {
        getCommandConfig().addDefault("wrong_usage_add_item","wrong_usage_add_item_message");
        getCommandConfig().addDefault("not_an_item","not_an_item_message");
        getCommandConfig().addDefault("successful_add_item","successful_add_item_message");
        if (!getCommandConfig().setup()) {
            getCommandConfig().setupKeys();
        }
    }
    public void run(Player p, String[] args) {
        if (args == null || args.length == 0) {
            p.spigot().sendMessage(getCommandConfig().getMessage("wrong_usage_add_item",p).create());
            return;
        }
        if (p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR) {
            p.spigot().sendMessage(getCommandConfig().getMessage("not_an_item",p).create());
            return;
        }
        News.nm.getItemConfig().addDefault(new BItem(p.getItemInHand()),args[0]);
        p.spigot().sendMessage(getCommandConfig().getMessage("successful_add_item",p).create());
    }
}
