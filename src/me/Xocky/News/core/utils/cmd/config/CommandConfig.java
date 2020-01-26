package me.Xocky.News.core.utils.cmd.config;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.config.Config;
import me.Xocky.News.core.utils.config.Section;

import java.io.IOException;
import java.util.HashMap;

public class CommandConfig extends Config {
    private HashMap<String,String> messages;
    public CommandConfig(String name) {
        super(name, new Section(new Section(News.PLUGIN_FOLDER),"Commands"));
        this.messages = new HashMap<>();
    }
    public void addDefault(String name,String message) {
        messages.put(name,message);
    }
    public void addMessage(String name,String message) {
        getYaml().set(name,message);
        try {
            getYaml().save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setupKeys() {
        messages.keySet().forEach(message -> addMessage(message,messages.get(message)));
    }
}
