package me.Xocky.News.core.news.config.custom.configs.defaults;

import java.util.Collections;
import java.util.List;

public enum Books {
    UPDATE(Collections.singletonList(new String[]{"*update_feature", "", "", "", "", "", "", "*discord", "", "*website", "", "*news_more"})),
    BLOG(Collections.singletonList(new String[]{"*blog_text","","","","","","","*discord","","*website","","*news_more"}));
    private List<String[]> book;
    private Books(List<String[]> book) {
        this.book = book;
    }
    public List<String[]> getBook() {
        return this.book;
    }
}
