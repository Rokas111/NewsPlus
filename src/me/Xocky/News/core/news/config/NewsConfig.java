package me.Xocky.News.core.news.config;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.config.Config;
import me.Xocky.News.core.utils.config.Section;

public class NewsConfig extends Config {
    public NewsConfig() {
        super("news", new Section(News.PLUGIN_FOLDER));
        if (!setup()) {
            setupKeys();
        }
    }

    public void setupKeys() {
        getYaml().set("show-latest-news-on-join", true);
        getYaml().set("show-latest-news-one-time-only", true);
        getYaml().set("latest-news-one-time-only-use-mysql", false);
        getYaml().set("news.update1.item", "news_update");
        getYaml().set("news.update1.book", "update");
        getYaml().set("news.update2.item", "news_blog");
        getYaml().set("news.update2.book", "blog");
        getYaml().set("no_previous_page", "no_previous_page_message");
        getYaml().set("no_next_page", "no_next_page_message");
        getYaml().set("insufficient-permission-command", "insufficient_permission_command");
        getYaml().set("insufficient-permission-sub-command", "insufficient_permission_subcommand");
        save();
    }

    public boolean containsNewsConfig(String newspage) {
        return getYaml().contains("news."+newspage);
    }
    public boolean getLatestNewsOnJoin() {
        return getBool("show-latest-news-on-join");
    }
    public boolean getLatestNewsOneTimeOnly() {
        return getBool("show-latest-news-one-time-only");
    }
    public boolean getLatestNewsOneTimeOnlyUseMySQL() {
        return getBool("latest-news-one-time-only-use-mysql");
    }
    public void removeNewsPage(String newspage) {
        getYaml().set("news." + newspage, null);
        save();
    }
    public void setGUINewsPage(String newspage,String gui) {
        getYaml().set("news." + newspage + ".gui", gui);
        save();
    }
    public void setBookNewsPage(String newspage,String book) {
        getYaml().set("news." + newspage + ".book", book);
        save();
    }
    public void setItemNewsPage(String newspage,String item) {
        getYaml().set("news." + newspage + ".item", item);
        save();
    }
}
