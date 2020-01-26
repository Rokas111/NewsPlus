package me.Xocky.News.core.news.cmd.subcmds.custom.item;

import com.google.common.collect.Lists;
import me.Xocky.News.core.News;
import me.Xocky.News.core.news.pages.ItemPage;
import me.Xocky.News.core.utils.cmd.config.CommandConfig;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class ItemsCmd extends SubCommand {
    public ItemsCmd() {
        super("items","News+.news.items","Shows all items in the items config",new CommandConfig("news.items"));
        addDefaults();
    }
    private void addDefaults() {
        getCommandConfig().addDefault("no_items","no_items_message");
        getCommandConfig().addDefault("item_gui","items");
        if (!getCommandConfig().setup()) {
            getCommandConfig().setupKeys();
        }
    }
    public void run(Player p, String[] args) {
        if (News.nm.getItemConfig().getYaml().getKeys(false).isEmpty()) {
            p.spigot().sendMessage(getCommandConfig().getMessage("no_items",p).create());
        }
        new ItemPage(getCommandConfig().getGUI("item_gui"), Lists.newArrayList(News.nm.getItemConfig().getYaml().getKeys(false)),p).open();
    }
}
