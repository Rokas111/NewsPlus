package me.Xocky.News.core.news.cmd.subcmds.custom;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class Add extends SubCommand {
    public Add() {
        super("add","News+.news.add","Adds a news page");
    }

    public void run(Player p, String[] args) {
        if (args == null || args.length < 3) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("wrong_usage_add",p).create());
            return;
        }
        String newspage = args[0];
        String item = args[1];
        String page = args[2];
        boolean isBook = News.nm.getBookConfig().getYaml().contains(page);
        if (!News.nm.getItemConfig().getYaml().contains(item)) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("no_such_item",p).create());
            return;
        }
        if (isBook?!News.nm.getBookConfig().getYaml().contains(page):!News.nm.getGUIConfig().getYaml().contains(page)) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("no_such_page",p).create());
            return;
        }
        News.nm.getNewsConfig().setItemNewsPage(newspage,item);
        if (isBook) {
            News.nm.getNewsConfig().setBookNewsPage(newspage,page);
        } else {
            News.nm.getNewsConfig().setGUINewsPage(newspage, page);
        }
        p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("successful_add_news_page",p).create());
    }

}
