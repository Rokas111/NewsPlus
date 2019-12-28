package me.Xocky.News.core.news.config.custom.configs.defaults;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.custom.gui.GUI;

public enum GUIs {
    NEWS_UPDATE(new GUI("News",27).setItems(0,26 , News.nm.getItemFactory().manufacture("none_item")).setItem(18,News.nm.getItemFactory().manufacture("previous_page_item")).setItem(26,News.nm.getItemFactory().manufacture("next_page_item")).setSlotTags(9,17,"newsslot"));
    private GUI gui;
    private GUIs(GUI gui) {
        this.gui = gui;
    }
    public GUI getGUI() {
        return this.gui;
    }
}
