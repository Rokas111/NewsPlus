package me.Xocky.News.core.news.cmd;

import com.google.common.collect.Lists;
import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.Command;
import me.Xocky.News.core.news.pages.NewsPage;
import me.Xocky.News.core.utils.cmd.subcmd.ISubCommand;
import org.bukkit.entity.Player;

import java.util.List;

public class NewsCmd extends Command {

    public NewsCmd(List<ISubCommand> subCommands) {
        super( "News+.news","news",subCommands,"Opens the news GUI");
    }
    public void run(Player p, String[] args) {
        if (args == null || args.length ==0) {
            new NewsPage(News.nm.getNewsConfig().getGUI("main_gui"), Lists.newArrayList(News.nm.getNewsConfig().getYaml().getConfigurationSection("news").getKeys(false)),p).open();
            return;
        }
        if (!News.nm.getNewsConfig().getYaml().contains("news."+args[0])) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("no-such-news-page",p).create());
            return;
        }
        News.nm.getNewsConfig().getBook("news." +args[0],p).openBook();
    }
}
