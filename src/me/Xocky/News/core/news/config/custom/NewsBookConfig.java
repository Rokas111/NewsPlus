package me.Xocky.News.core.news.config.custom;

import com.google.common.collect.Lists;
import me.Xocky.News.core.config.Config;
import me.Xocky.News.core.config.Section;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class NewsBookConfig extends Config {
    private HashMap<List<String[]>,String> defaults;
    private YamlConfiguration yaml;
    public NewsBookConfig() {
        super("Books",new Section(new Section("News+"),"Custom"));
        this.yaml = YamlConfiguration.loadConfiguration(getFile());
        this.defaults = new HashMap<>();
    }
    public void addBookDefault(List<String[]> book,String name) {
        defaults.put(book,name);
    }
    public void checkSetup() {
        if (!setup()) {
            setupKeys();
        }
    }
    public void addDefault( List<String[]> pages, String name) {
        if (!pages.isEmpty()) {
            int p = 1;
            for (String[] page : pages) {
                getYaml().set(name + ".pages." + p, Lists.newArrayList(page));
                p++;
            }
        }
        try {
            getYaml().save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public YamlConfiguration getYaml() {
        return this.yaml;
    }
    public void setupKeys() {
        defaults.keySet().forEach(book -> addDefault(book,defaults.get(book)));
    }
}
