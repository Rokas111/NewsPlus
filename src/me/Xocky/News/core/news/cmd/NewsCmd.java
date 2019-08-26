package me.Xocky.News.core.news.cmd;

import com.google.common.collect.Lists;
import me.Xocky.News.core.News;
import me.Xocky.News.core.cmd.Command;
import me.Xocky.News.core.news.pages.NewsPage;
import org.bukkit.entity.Player;

public class NewsCmd extends Command {

    public NewsCmd() {
        super( "News+.News","news");
    }
    public void run(Player p, String[] args) {
        if (args == null || args.length ==0) {
            News.NM.addNewsPage(p,new NewsPage(News.NM.getGUIFactory().manufacture(News.NM.getNewsConfig().getYaml().getString("main_gui")), Lists.newArrayList(News.NM.getNewsConfig().getYaml().getConfigurationSection("news").getKeys(false))));
            News.NM.getNewsPage(p).open(p);
            return;
        }
        if (!News.NM.getNewsConfig().getYaml().contains("news."+args[0])) {
            return;
        }
        News.NM.getBookFactory().manufacture(News.NM.getNewsConfig().getYaml().getString("news." +args[0]));
    }
}
