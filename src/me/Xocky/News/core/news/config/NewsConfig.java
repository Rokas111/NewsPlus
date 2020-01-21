package me.Xocky.News.core.news.config;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.config.Config;
import me.Xocky.News.core.utils.config.Section;

public class NewsConfig extends Config {
    public NewsConfig() {
        super("news", new Section(News.PLUGIN_FOLDER));
        if (!setup()) {
            setupKeys();
        }
    }

    public void setupKeys() {
        getYaml().set("show-latest-news-on-join", true);
        getYaml().set("show-latest-news-one-time-only", true);
        getYaml().set("latest-news-one-time-only-use-mysql", false);
        getYaml().set("main_gui", "news_update");
        getYaml().set("book_gui", "books");
        getYaml().set("item_gui", "items");
        getYaml().set("empty_news_slot_item", "empty_slot");
        getYaml().set("news.update1.item", "news_update");
        getYaml().set("news.update1.book", "update");
        getYaml().set("news.update2.item", "news_blog");
        getYaml().set("news.update2.book", "blog");
        getYaml().set("no-such-news-page", "error_no_such_news_page");
        getYaml().set("no-previous-page", "no_previous_page");
        getYaml().set("no-next-page", "no_next_page");
        getYaml().set("successful-config-reload", "successful_config_reload_message");
        getYaml().set("successful-clear-players", "successful_clear_players_message");
        getYaml().set("successful-reset-configs", "successful_reset_configs_message");
        getYaml().set("successful_add_news_page", "successful_add_news_page_message");
        getYaml().set("successful_edit_news_page_item", "successful_edit_news_page_item_message");
        getYaml().set("successful_edit_news_page", "successful_edit_news_page_message");
        getYaml().set("successful_remove_news_page", "successful_remove_news_page_message");
        getYaml().set("successful_add_book", "successful_add_book_message");
        getYaml().set("successful_add_item", "successful_add_item_message");
        getYaml().set("successful_add_gui", "successful_add_gui_message");
        getYaml().set("wrong_usage_remove", "wrong_usage_remove_message");
        getYaml().set("wrong_usage_add_book", "wrong_usage_add_book_message");
        getYaml().set("wrong_usage_add_item", "wrong_usage_add_item_message");
        getYaml().set("wrong_usage_add_gui", "wrong_usage_add_gui_message");
        getYaml().set("wrong_usage_get_item", "wrong_usage_get_item_message");
        getYaml().set("wrong_usage_add", "wrong_usage_add_message");
        getYaml().set("wrong_usage_edit", "wrong_usage_edit_message");
        getYaml().set("no_such_page", "no_such_page_message");
        getYaml().set("not_a_book", "not_a_book_message");
        getYaml().set("not_an_item", "not_an_item_message");
        getYaml().set("no_such_item", "no_such_item_message");
        getYaml().set("not_a_gui_size_number", "not_a_gui_size_number_message");
        getYaml().set("error-player-list-disabled", "error_player_list_disabled_message");
        getYaml().set("insufficient-permission-command", "insufficient_permission_command");
        getYaml().set("insufficient-permission-sub-command", "insufficient_permission_subcommand");
        save();
    }

    public boolean containsNewsConfig(String newspage) {
        return getYaml().contains("news."+newspage);
    }
    public boolean getLatestNewsOnJoin() {
        return getBool("show-latest-news-on-join");
    }
    public boolean getLatestNewsOneTimeOnly() {
        return getBool("show-latest-news-one-time-only");
    }
    public boolean getLatestNewsOneTimeOnlyUseMySQL() {
        return getBool("latest-news-one-time-only-use-mysql");
    }
    public void removeNewsPage(String newspage) {
        getYaml().set("news." + newspage, null);
        save();
    }
    public void setGUINewsPage(String newspage,String gui) {
        getYaml().set("news." + newspage + ".gui", gui);
        save();
    }
    public void setBookNewsPage(String newspage,String book) {
        getYaml().set("news." + newspage + ".book", book);
        save();
    }
    public void setItemNewsPage(String newspage,String item) {
        getYaml().set("news." + newspage + ".item", item);
        save();
    }
}
