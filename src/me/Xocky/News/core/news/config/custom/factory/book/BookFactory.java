package me.Xocky.News.core.news.config.custom.factory.book;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.custom.book.Book;
import org.bukkit.entity.Player;

public class BookFactory {
    public BookFactory() {

    }
    public Book manufacture(String key, Player pl) {
        if (News.nm.getBookConfig().getYaml().contains(key)) {
            Book book = new Book(pl);
            book.setTitle("Book");
            book.setAuthor("News+");
            int p = 1;
            for (String page:News.nm.getBookConfig().getYaml().getConfigurationSection(key+".pages").getKeys(false)) {
                String[] pa = new String[News.nm.getBookConfig().getYaml().getStringList(key +".pages." + page).size()];
                book.addPage(News.nm.getBookConfig().getYaml().getStringList(key +".pages." + page).toArray(pa));
                p++;
            }
            return book;
        }
        return null;
    }
}
