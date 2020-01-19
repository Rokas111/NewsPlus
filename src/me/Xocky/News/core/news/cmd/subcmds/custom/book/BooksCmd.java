package me.Xocky.News.core.news.cmd.subcmds.custom.book;

import com.google.common.collect.Lists;
import me.Xocky.News.core.News;
import me.Xocky.News.core.news.pages.BookPage;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class BooksCmd extends SubCommand {
    public BooksCmd() {
        super("books","News+.news.books","Shows all books in the books config");
    }

    public void run(Player p, String[] args) {
        new BookPage(News.nm.getNewsConfig().getGUI("book_gui"), Lists.newArrayList(News.nm.getBookConfig().getYaml().getKeys(false)),p).open();
    }
}
