package me.Xocky.News.core.news.config.custom.configs.defaults;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.custom.gui.GUI;

public enum GUIs {
    NEWS_UPDATE(new GUI("News",27).setItems(0,26 , News.nm.getItemFactory().manufacture("none_item")).setItem(18,News.nm.getItemFactory().manufacture("previous_page_item")).setItem(26,News.nm.getItemFactory().manufacture("next_page_item")).setItems(9,17,News.nm.getItemFactory().manufacture("empty_slot")).setSlotTags(9,17,"newsslot")),
    BOOKS(new GUI("Books",54).setItems(0,44 , News.nm.getItemFactory().manufacture("none_item")).setItems(45,53,News.nm.getItemFactory().manufacture("empty_slot")).setItem(45,News.nm.getItemFactory().manufacture("previous_page_item")).setItem(53,News.nm.getItemFactory().manufacture("next_page_item")).setSlotTags(0,44,"bookslot")),
    ITEMS(new GUI("Items",54).setItems(0,44 , News.nm.getItemFactory().manufacture("air_item")).setItems(45,53,News.nm.getItemFactory().manufacture("empty_slot")).setItem(45,News.nm.getItemFactory().manufacture("previous_page_item")).setItem(53,News.nm.getItemFactory().manufacture("next_page_item")).setSlotTags(0,44,"itemslot"));
    private GUI gui;
    private GUIs(GUI gui) {
        this.gui = gui;
    }
    public GUI getGUI() {
        return this.gui;
    }
}
