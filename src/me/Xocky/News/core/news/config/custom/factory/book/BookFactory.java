package me.Xocky.News.core.news.config.custom.factory.book;

import me.Xocky.News.core.News;

public class BookFactory {
    public BookFactory() {

    }
    public Book manufacture(String key) {
        if (News.NM.getBookConfig().getYaml().contains(key)) {
            Book book = new Book();
            book.setTitle("Book");
            book.setAuthor("News+");
            int p = 1;
            for (String page:News.NM.getBookConfig().getYaml().getConfigurationSection(key+".pages").getKeys(false)) {
                String[] pa = new String[News.NM.getBookConfig().getYaml().getStringList(key +".pages." + page).size()];
                book.addPage(News.NM.getBookConfig().getYaml().getStringList(key +".pages." + page).toArray(pa));
                p++;
            }
            return book;
        }
        return null;
    }
}
