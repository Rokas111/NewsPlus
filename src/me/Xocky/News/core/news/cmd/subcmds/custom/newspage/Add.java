package me.Xocky.News.core.news.cmd.subcmds.custom.newspage;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.config.CommandConfig;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class Add extends SubCommand {
    public Add() {
        super("add","News+.news.add","Adds a news page",new CommandConfig("news.add"));
        addDefaults();
    }
    private void addDefaults() {
        getCommandConfig().addDefault("wrong_usage_add","wrong_usage_add_message");
        getCommandConfig().addDefault("no_such_item","no_such_item_message");
        getCommandConfig().addDefault("no_such_page","no_such_page_message");
        getCommandConfig().addDefault("successful_add_news_page","successful_add_news_page_message");
        if (!getCommandConfig().setup()) {
            getCommandConfig().setupKeys();
        }
    }
    public void run(Player p, String[] args) {
        if (args == null || args.length < 3) {
            p.spigot().sendMessage(getCommandConfig().getMessage("wrong_usage_add",p).create());
            return;
        }
        String newspage = args[0];
        String item = args[1];
        String page = args[2];
        boolean isBook = News.nm.getBookConfig().getYaml().contains(page);
        if (!News.nm.getItemConfig().getYaml().contains(item)) {
            p.spigot().sendMessage(getCommandConfig().getMessage("no_such_item",p).create());
            return;
        }
        if (isBook?!News.nm.getBookConfig().getYaml().contains(page):!News.nm.getGUIConfig().getYaml().contains(page)) {
            p.spigot().sendMessage(getCommandConfig().getMessage("no_such_page",p).create());
            return;
        }
        News.nm.getNewsConfig().setItemNewsPage(newspage,item);
        if (isBook) {
            News.nm.getNewsConfig().setBookNewsPage(newspage,page);
        } else {
            News.nm.getNewsConfig().setGUINewsPage(newspage, page);
        }
        p.spigot().sendMessage(getCommandConfig().getMessage("successful_add_news_page",p).create());
    }

}
