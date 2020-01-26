package me.Xocky.News.core.news.cmd.subcmds.custom.book;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.cmd.config.CommandConfig;
import me.Xocky.News.core.utils.cmd.subcmd.SubCommand;
import me.Xocky.News.core.utils.custom.book.Book;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class AddBook extends SubCommand {
    public AddBook() {
        super("addbook","News+.news.addbook","Adds a book that is in your hand to the books config",new CommandConfig("news.addbook"));
        addDefaults();
    }
    private void addDefaults() {
        getCommandConfig().addDefault("wrong_usage_add_book","wrong_usage_add_book_message");
        getCommandConfig().addDefault("not_a_book","not_a_book_message");
        getCommandConfig().addDefault("successful_add_book","successful_add_book_message");
        if (!getCommandConfig().setup()) {
            getCommandConfig().setupKeys();
        }
    }
    public void run(Player p, String[] args) {
        if (args == null || args.length == 0) {
            p.spigot().sendMessage(getCommandConfig().getMessage("wrong_usage_add_book",p).create());
            return;
        }
        if (p.getItemInHand() == null || p.getItemInHand().getType() != Material.WRITTEN_BOOK) {
            p.spigot().sendMessage(getCommandConfig().getMessage("not_a_book",p).create());
            return;
        }
        News.nm.getBookConfig().addBook(new Book(p.getItemInHand()),args[0]);
        p.spigot().sendMessage(getCommandConfig().getMessage("successful_add_book",p).create());
    }
}
