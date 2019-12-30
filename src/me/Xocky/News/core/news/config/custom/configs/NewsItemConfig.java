package me.Xocky.News.core.news.config.custom.configs;

import me.Xocky.News.core.News;
import me.Xocky.News.core.news.config.custom.configs.defaults.Items;
import me.Xocky.News.core.utils.config.Config;
import me.Xocky.News.core.utils.config.Section;
import me.Xocky.News.core.utils.custom.item.BItem;

import java.io.IOException;
import java.util.HashMap;

public class NewsItemConfig extends Config {
    private HashMap<BItem,String> defaults;
    public NewsItemConfig() {
        super("Items",new Section(new Section(News.PLUGIN_FOLDER),"Custom"));
        this.defaults = new HashMap<>();
    }
    public void addItemDefault(BItem item, String name) {
        defaults.put(item,name);
    }
    public void addItemDefaults(Items[] items) {
        for (Items item: items) {
            addItemDefault(item.getItem(),item.toString().toLowerCase());
        }
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
        if (b.hasSignature()) {
            getYaml().set(name + ".signature",b.getSignature());
        }
        save();
    }
    public void setupKeys() {
        defaults.keySet().forEach(item -> addDefault(item,defaults.get(item)));
    }
}
