package me.Xocky.News.core.news.config.custom.configs;

import me.Xocky.News.core.News;
import me.Xocky.News.core.news.config.custom.configs.defaults.Messages;
import me.Xocky.News.core.utils.config.Config;
import me.Xocky.News.core.utils.config.Section;

import java.io.IOException;
import java.util.HashMap;

public class NewsMessagesConfig extends Config {
    private HashMap<String,String> defaults;
    public NewsMessagesConfig() {
        super("Messages",new Section(new Section(News.PLUGIN_FOLDER),"Custom"));
        defaults = new HashMap<>();
    }

    public void addMessageDefault(String message, String name) {
        defaults.put(message,name);
    }
    public void addMessageDefaults(Messages[] messages) {
        for (Messages message: messages) {
            addMessageDefault(message.getMessage(),message.toString().toLowerCase());
        }
    }
    public void checkSetup() {
        if (!setup()) {
            setupKeys();
        }
    }
    public void addDefault(String message, String name) {
        getYaml().set(name,message);
        save();
    }
    public void setupKeys() {
        defaults.keySet().forEach(message -> addDefault(message,defaults.get(message)));
    }
}
