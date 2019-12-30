package me.Xocky.News.core.utils.config;

public abstract interface IConfig {
    public abstract String getConfig();
    public abstract void setupKeys();
    public abstract Section getSection();
    public abstract void reload();
    public abstract void save();
}
