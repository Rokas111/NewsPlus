package me.Xocky.News.core.news.config.custom;

import me.Xocky.News.core.config.Config;
import me.Xocky.News.core.config.Section;
import me.Xocky.News.core.news.config.custom.factory.item.BItem;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.HashMap;

public class NewsItemConfig extends Config {
    private HashMap<BItem,String> defaults;
    private YamlConfiguration yaml;
    public NewsItemConfig() {
        super("Items",new Section(new Section("News+"),"Custom"));
        this.yaml = YamlConfiguration.loadConfiguration(getFile());
        this.defaults = new HashMap<>();
    }
    public void addItemDefault(BItem item, String name) {
        defaults.put(item,name);
    }
    public void checkSetup() {
        if (!setup()) {
            setupKeys();
        }
    }
    public void addDefault(BItem b, String name) {
        getYaml().set(name+".material",b.getMaterial().toString());
        getYaml().set(name+".durability",b.getDurability());
        getYaml().set(name+".amount",b.getAmount());
        getYaml().set(name + ".enchanted",b.isEnchanted());
        if (b.hasMeta()) {
            if (b.hasDisplayName()) {
                getYaml().set(name + ".name", b.getDisplayName());
            }
            if (b.hasLore()) {
                getYaml().set(name + ".lore", b.getLore());
            }
        }
        if (b.getNBTString("command") != null) {
            getYaml().set(name + ".command",b.getNBTString("command"));
        }
        if (b.getNBTString("tag") != null) {
            getYaml().set(name + ".tag",b.getNBTString("tag"));
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
        defaults.keySet().forEach(item -> addDefault(item,defaults.get(item)));
    }
}
