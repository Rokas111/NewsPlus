package me.Xocky.News.core.news.config.custom.configs;

import me.Xocky.News.core.News;
import me.Xocky.News.core.news.config.custom.configs.defaults.JSON;
import me.Xocky.News.core.utils.config.Config;
import me.Xocky.News.core.utils.config.Section;
import me.Xocky.News.core.utils.custom.json.UncodedJSON;

import java.io.IOException;
import java.util.HashMap;

public class NewsJSONConfig extends Config {
    private HashMap<UncodedJSON,String> defaults;
    public NewsJSONConfig() {
        super("JSON",new Section(new Section(News.PLUGIN_FOLDER),"Custom"));
        defaults = new HashMap<>();
    }

    public void addJSONDefault(UncodedJSON json, String name) {
        defaults.put(json,name);
    }
    public void addJSONDefaults(JSON[] jsons) {
        for (JSON json: jsons) {
            addJSONDefault(json.getJSON(),json.toString().toLowerCase());
        }
    }
    public void checkSetup() {
        if (!setup()) {
            setupKeys();
        }
    }
    public void addDefault(UncodedJSON json, String name) {
        getYaml().set(name+".json",json.getText());
        if (json.hasHover()) {
            getYaml().set(name+".hover.json", json.getHover());
        }
        if (json.hasClick()) {
            getYaml().set(name+".click.value", json.getClick());
        }
        save();
    }
    public void setupKeys() {
        defaults.keySet().forEach(json -> addDefault(json,defaults.get(json)));
    }
}
