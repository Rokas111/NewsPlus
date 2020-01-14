package me.Xocky.News.core.news.config.custom.configs;

import com.google.common.collect.Lists;
import me.Xocky.News.core.News;
import me.Xocky.News.core.news.config.custom.configs.defaults.Books;
import me.Xocky.News.core.utils.config.Config;
import me.Xocky.News.core.utils.config.Section;
import me.Xocky.News.core.utils.custom.book.Book;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class NewsBookConfig extends Config {
    private HashMap<List<String[]>,String> defaults;
    public NewsBookConfig() {
        super("Books",new Section(new Section(News.PLUGIN_FOLDER),"Custom"));
        this.defaults = new HashMap<>();
    }
    public void addBookDefault(List<String[]> book,String name) {
        defaults.put(book,name);
    }
    public void addBookDefaults(Books[] books) {
        for (Books book: books) {
            addBookDefault(book.getBook(),book.toString().toLowerCase());
        }
    }
    public void checkSetup() {
        if (!setup()) {
            setupKeys();
        }
    }
    public void add( List<String[]> pages, String name) {
        if (!pages.isEmpty()) {
            int p = 1;
            for (String[] page : pages) {
                getYaml().set(name + ".pages." + p, Lists.newArrayList(page));
                p++;
            }
        }
        save();
    }
    public void addBook(Book book,String name) {
        add(book.getPages(),name);
    }
    public void setupKeys() {
        defaults.keySet().forEach(book -> add(book,defaults.get(book)));
    }
}
