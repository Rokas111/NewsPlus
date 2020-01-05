package me.Xocky.News.core.utils.config;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.custom.book.Book;
import me.Xocky.News.core.utils.custom.gui.GUI;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Config implements IConfig {
    private String configname;
    private YamlConfiguration yaml;
    private Section s;
    public Config (String name,Section s) {
        this.configname = name;
        this.s = s;
        this.yaml = YamlConfiguration.loadConfiguration(getFile());
    }

    public String getConfig() {
        return this.configname;
    }
    public File getFile() {
        return new File(s.getFile().getPath() +"//" + configname+".yml");
    }
    public boolean setup() {
        if (!getFile().exists()) {
            try {
                getFile().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }
    public YamlConfiguration getYaml() {
        return this.yaml;
    }
    public Section getSection() {
            return s;
    }
    public void reload() {
        this.yaml = YamlConfiguration.loadConfiguration(getFile());
    }
    public void save() {
        try {
            getYaml().save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void reset() {
        getFile().delete();
        setup();
        setupKeys();
        save();
    }
    public String getString(String key) {
        return getYaml().contains(key) && getYaml().isString(key)?getYaml().getString(key):null;
    }
    public Integer getInt(String key) {
        return getYaml().contains(key) && getYaml().isInt(key)?getYaml().getInt(key):null;
    }
    public Boolean getBool(String key) {
        return (getYaml().contains(key) && getYaml().isBoolean(key)) && getYaml().getBoolean(key);
    }
    public List<String> getStringList(String key) {
        return getYaml().contains(key) && getYaml().isList(key)?getYaml().getStringList(key):new ArrayList<>();
    }
    public void addStringToList(String key,String element) {
        List<String> list = getStringList(key);
        list.add(element);
        getYaml().set(key,list);
        save();
    }
    public void removeStringToList(String key,String element) {
        List<String> list = getStringList(key);
        list.remove(element);
        getYaml().set(key,list);
        save();
    }
    public Book getBook(String name) {
        return getYaml().contains(name)? News.nm.getBookFactory().manufacture(getYaml().getString(name)) :null;
    }
    public GUI getGUI(String name) {
        return getYaml().contains(name)?News.nm.getGUIFactory().manufacture(getYaml().getString(name)) :null;
    }
    public ItemStack getItem(String name) {
        return getYaml().contains(name)?News.nm.getItemFactory().manufacture(getYaml().getString(name)) :null;
    }
    public ComponentBuilder getJSON(String name) {
        return getYaml().contains(name)?News.nm.getJSONFactory().manufacture(getYaml().getString(name)) :null;
    }
    public ComponentBuilder getMessage(String name) {
        return getYaml().contains(name)?News.nm.getMessageFactory().manufacture(getYaml().getString(name)):null;
    }
}