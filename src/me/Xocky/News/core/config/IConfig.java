package me.Xocky.News.core.config;

public abstract interface IConfig {
    public abstract String getConfig();
    public abstract void setupKeys();
    public abstract Section getSection();
}
