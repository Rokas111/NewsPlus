package me.Xocky.News.core.news.config.custom;

import me.Xocky.News.core.config.Config;
import me.Xocky.News.core.config.Section;
import me.Xocky.News.core.news.config.custom.factory.text.UncodedText;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.HashMap;

public class NewsTextConfig extends Config {
    private HashMap<UncodedText,String> defaults;
    private YamlConfiguration yaml;
    public NewsTextConfig() {
        super("Text",new Section(new Section("News+"),"Custom"));
        this.yaml = YamlConfiguration.loadConfiguration(getFile());
        defaults = new HashMap<>();
    }

    public void addTextDefault(UncodedText text, String name) {
        defaults.put(text,name);
    }
    public void checkSetup() {
        if (!setup()) {
            setupKeys();
        }
    }
    public void addDefault(UncodedText text, String name) {
        getYaml().set(name+".text",text.getText());
        if (text.hasHover()) {
            getYaml().set(name+".hover.text", text.getHover());
        }
        if (text.hasClick()) {
            getYaml().set(name+".click.value", text.getClick());
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
        defaults.keySet().forEach(text -> addDefault(text,defaults.get(text)));
    }
}
