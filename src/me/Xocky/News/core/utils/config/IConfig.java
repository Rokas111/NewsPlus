package me.Xocky.News.core.utils.config;

import java.util.List;

public abstract interface IConfig {
    public abstract String getConfig();
    public abstract void setupKeys();
    public abstract Section getSection();
    public abstract void reload();
    public abstract void save();
    public abstract String getString(String key);
    public abstract Integer getInt(String key);
    public abstract Boolean getBool(String key);
    public abstract List<String> getStringList(String key);
    public abstract void addStringToList(String key,String element);
    public abstract void removeStringToList(String key,String element);
}
