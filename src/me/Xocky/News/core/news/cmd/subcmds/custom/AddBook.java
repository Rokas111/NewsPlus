package me.Xocky.News.core.news.cmd.subcmds.custom;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import me.Xocky.News.core.utils.custom.book.Book;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class AddBook extends SubCommand {
    public AddBook() {
        super("addbook","News+.news.addbook","Adds a book that is in your hand to the books config");
    }

    public void run(Player p, String[] args) {
        if (args == null || args.length == 0) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("wrong_usage_add_book",p).create());
            return;
        }
        if (p.getItemInHand() == null || p.getItemInHand().getType() != Material.WRITTEN_BOOK) {
            p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("not_a_book",p).create());
            return;
        }
        News.nm.getBookConfig().addBook(new Book(p.getItemInHand()),args[0]);
        p.spigot().sendMessage(News.nm.getNewsConfig().getMessage("successful_add_book",p).create());
    }
}
