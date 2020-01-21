package me.Xocky.News.core.news.cmd.subcmds.custom.item;

import com.google.common.collect.Lists;
import me.Xocky.News.core.News;
import me.Xocky.News.core.news.pages.ItemPage;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class ItemsCmd extends SubCommand {
    public ItemsCmd() {
        super("items","News+.news.items","Shows all items in the items config");
    }

    public void run(Player p, String[] args) {
        new ItemPage(News.nm.getNewsConfig().getGUI("item_gui"), Lists.newArrayList(News.nm.getItemConfig().getYaml().getKeys(false)),p).open();
    }
}
