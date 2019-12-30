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
        getYaml().set("main_gui", "news_update");
        getYaml().set("empty_news_slot_item", "empty_slot");
        getYaml().set("news.update1.item", "news_update");
        getYaml().set("news.update1.book", "update");
        getYaml().set("news.update2.item", "news_blog");
        getYaml().set("news.update2.book", "blog");
        getYaml().set("no-such-news-page", "error_no_such_news_page");
        getYaml().set("no-previous-page", "no_previous_page");
        getYaml().set("no-next-page", "no_next_page");
        save();
    }


    public boolean getLatestNewsOnJoin() {
        return getYaml().getBoolean("show-latest-news-on-join");
    }
    public boolean getLatestNewsOneTimeOnly() {
        return getYaml().getBoolean("show-latest-news-one-time-only");
    }
}
