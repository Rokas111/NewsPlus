package me.Xocky.News.core.news.cmd.subcmds.custom.newspage;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class Edit extends SubCommand {
    public Edit() {
        super("edit","News+.news.edit","Edits an existing news page");
    }

    public void run(Player p, String[] args) {
        if (args == null || args.length < 3) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("wrong_usage_edit",p).create());
            return;
        }
        String newspage = args[0];
        if (!News.nm.getNewsConfig().containsNewsConfig(newspage)) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("no-such-news-page",p).create());
            return;
        }
        switch(args[1]) {
            case "item":
                if (!News.nm.getItemConfig().getYaml().contains(args[2])) {
                    p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("no_such_item",p).create());
                    return;
                }
                News.nm.getNewsConfig().setItemNewsPage(newspage,args[2]);
                p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("successful_edit_news_page_item",p).create());
                break;
            case "page":
                if (!News.nm.getGUIConfig().getYaml().contains(args[2]) && !News.nm.getBookConfig().getYaml().contains(args[2])) {
                    p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("no_such_page",p).create());
                    return;
                }
                if (News.nm.getBookConfig().getYaml().contains(args[2])) {
                    News.nm.getNewsConfig().setBookNewsPage(newspage, args[2]);
                } else {
                    News.nm.getNewsConfig().setGUINewsPage(newspage, args[2]);
                }
                p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("successful_edit_news_page",p).create());
                break;
            default:
                p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("wrong_usage_edit",p).create());
                break;
        }
    }
    }
