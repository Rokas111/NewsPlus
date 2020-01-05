package me.Xocky.News.core.utils.config;

import me.Xocky.News.core.utils.custom.book.Book;
import me.Xocky.News.core.utils.custom.gui.GUI;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract interface IConfig {
    public abstract String getConfig();
    public abstract void setupKeys();
    public abstract Section getSection();
    public abstract void reload();
    public abstract void save();
    public abstract void reset();
    public abstract String getString(String key);
    public abstract Integer getInt(String key);
    public abstract Boolean getBool(String key);
    public abstract List<String> getStringList(String key);
    public abstract void addStringToList(String key,String element);
    public abstract void removeStringToList(String key,String element);
    public abstract Book getBook(String name);
    public abstract GUI getGUI(String name);
    public abstract ItemStack getItem(String name);
    public abstract ComponentBuilder getJSON(String name);
    public abstract ComponentBuilder getMessage(String name);
}
