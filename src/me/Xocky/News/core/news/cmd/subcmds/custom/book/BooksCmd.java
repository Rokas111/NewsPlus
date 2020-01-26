package me.Xocky.News.core.news.cmd.subcmds.custom.book;

import com.google.common.collect.Lists;
import me.Xocky.News.core.News;
import me.Xocky.News.core.news.pages.BookPage;
import me.Xocky.News.core.utils.cmd.config.CommandConfig;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import org.bukkit.entity.Player;

public class BooksCmd extends SubCommand {
    public BooksCmd() {
        super("books","News+.news.books","Shows all books in the books config",new CommandConfig("news.books"));
        addDefaults();
    }
    private void addDefaults() {
        getCommandConfig().addDefault("no_books","no_books_message");
        getCommandConfig().addDefault("book_gui","books");
        if (!getCommandConfig().setup()) {
            getCommandConfig().setupKeys();
        }
    }
    public void run(Player p, String[] args) {
        if (News.nm.getBookConfig().getYaml().getKeys(false).isEmpty()) {
            p.spigot().sendMessage(getCommandConfig().getMessage("no_books",p).create());
        }
        new BookPage(getCommandConfig().getGUI("book_gui"), Lists.newArrayList(News.nm.getBookConfig().getYaml().getKeys(false)),p).open();
    }
}
