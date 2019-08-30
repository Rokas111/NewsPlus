package me.Xocky.News.core.news.config;

import me.Xocky.News.core.config.Config;
import me.Xocky.News.core.config.Section;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;

public class NewsConfig extends Config {
    private YamlConfiguration yaml;
    public NewsConfig() {
        super("news", new Section("News+"));
        this.yaml = YamlConfiguration.loadConfiguration(getFile());
        if (!setup()) {
            setupKeys();
        }
    }
    public void setupKeys() {
        getYaml().set("show-latest-news-on-join",true);
        getYaml().set("main_gui","news_update");
        getYaml().set("empty_news_slot_item","none_news");
        getYaml().set("news.update1.item","news_update");
        getYaml().set("news.update1.book","update");
        getYaml().set("news.update2.item","news_blog");
        getYaml().set("news.update2.book","blog");
        try {
            getYaml().save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public YamlConfiguration getYaml() {
        return this.yaml;
    }
}
