package me.Xocky.News.core.news.cmd;

import com.google.common.collect.Lists;
import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.Command;
import me.Xocky.News.core.news.pages.NewsPage;
import me.Xocky.News.core.utils.cmd.config.CommandConfig;
import me.Xocky.News.core.utils.cmd.subcmd.ISubCommand;
import org.bukkit.entity.Player;

import java.util.List;

public class NewsCmd extends Command {

    public NewsCmd(List<ISubCommand> subCommands) {
        super( "News+.news","news",subCommands,"Opens the news GUI",new CommandConfig("news"));
        addDefaults();
    }
    private void addDefaults() {
        getCommandConfig().addDefault("main_gui","news_update");
        getCommandConfig().addDefault("no_such_news_page","error_no_such_news_page");
        if (!getCommandConfig().setup()) {
            getCommandConfig().setupKeys();
        }
    }
    public void run(Player p, String[] args) {
        if (args == null || args.length ==0) {
            new NewsPage(getCommandConfig().getGUI("main_gui"), Lists.newArrayList(News.nm.getNewsConfig().getYaml().getConfigurationSection("news").getKeys(false)),p).open();
            return;
        }
        if (!News.nm.getNewsConfig().containsNewsConfig(args[0])) {
            p.spigot().sendMessage(getCommandConfig().getMessage("no_such_news_page",p).create());
            return;
        }
        News.nm.getNewsConfig().getBook("news." +args[0],p).openBook();
    }
}
