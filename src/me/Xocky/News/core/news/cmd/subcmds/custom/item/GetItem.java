package me.Xocky.News.core.news.cmd.subcmds.custom.item;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.config.CommandConfig;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class GetItem extends SubCommand {
    public GetItem() {
        super("getitem","News+.news.getitem","Gives you an item from the items config",new CommandConfig("news.getitem"));
        addDefaults();
    }
    private void addDefaults() {
        getCommandConfig().addDefault("wrong_usage_get_item","wrong_usage_get_item_message");
        getCommandConfig().addDefault("no_such_item","no_such_item_message");
        if (!getCommandConfig().setup()) {
            getCommandConfig().setupKeys();
        }
    }
    public void run(Player p, String[] args) {
        if (args == null || args.length == 0) {
            p.spigot().sendMessage(getCommandConfig().getMessage("wrong_usage_get_item",p).create());
            return;
        }
        if (!News.nm.getItemConfig().getYaml().contains(args[0])) {
            p.spigot().sendMessage(getCommandConfig().getMessage("no_such_item",p).create());
            return;
        }
        p.getInventory().addItem(News.nm.getItemFactory().manufacture(args[0]));
        p.updateInventory();
    }
}
