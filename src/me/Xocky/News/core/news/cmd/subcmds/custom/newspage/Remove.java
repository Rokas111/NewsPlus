package me.Xocky.News.core.news.cmd.subcmds.custom.newspage;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.config.CommandConfig;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class Remove extends SubCommand {
    public Remove() {
        super("remove","News+.news.remove","Removes a specific news page",new CommandConfig("news.remove"));
        addDefaults();
    }
    private void addDefaults() {
        getCommandConfig().addDefault("no_such_news_page","error_no_such_news_page");
        getCommandConfig().addDefault("wrong_usage_remove","wrong_usage_remove_message");
        getCommandConfig().addDefault("successful_remove_news_page","successful_remove_news_page_message");
        if (!getCommandConfig().setup()) {
            getCommandConfig().setupKeys();
        }
    }
    public void run(Player p, String[] args) {
        if (args == null || args.length ==0) {
            p.spigot().sendMessage(getCommandConfig().getMessage("wrong_usage_remove",p).create());
            return;
        }
        if (!News.nm.getNewsConfig().containsNewsConfig(args[0])) {
            p.spigot().sendMessage(getCommandConfig().getMessage("no_such_news_page",p).create());
            return;
        }
        News.nm.getNewsConfig().removeNewsPage(args[0]);
        p.spigot().sendMessage(getCommandConfig().getMessage("successful_remove_news_page",p).create());
    }
}
