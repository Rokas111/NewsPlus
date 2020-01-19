package me.Xocky.News.core.utils.pages.interfaces;

import me.Xocky.News.core.utils.custom.item.BItem;

public interface IGUIPage {
    public abstract void open();
    public abstract void interact(int slot, BItem item);
    public abstract void close();
    public abstract boolean cancelClick();
}
