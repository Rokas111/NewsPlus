package me.Xocky.News.core.utils.pages.interfaces;

import java.util.List;

public abstract interface IGUIMultiPage extends IGUIPage {
    public abstract void nextPage(List<Integer> slots);
    public abstract void previousPage();
}
