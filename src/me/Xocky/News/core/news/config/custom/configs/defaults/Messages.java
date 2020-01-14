package me.Xocky.News.core.news.config.custom.configs.defaults;

public enum Messages {
    SUCCESSFUL_CONFIG_RELOAD_MESSAGE("&a&lSuccessfully reloaded all configs"),
    SUCCESSFUL_CLEAR_PLAYERS_MESSAGE("&a&lSuccessfully cleared players"),
    SUCCESSFUL_RESET_CONFIGS_MESSAGE("&a&lSuccessfully resetted all configs"),
    SUCCESSFUL_REMOVE_NEWS_PAGE_MESSAGE("&a&lSuccessfully removed this news page"),
    SUCCESSFUL_ADD_BOOK_MESSAGE("&a&lSuccessfully added book to the book config"),
    SUCCESSFUL_ADD_ITEM_MESSAGE("&a&lSuccessfully added item to the item config"),
    SUCCESSFUL_ADD_NEWS_PAGE_MESSAGE("&a&lSuccessfully added news page"),
    SUCCESSFUL_EDIT_NEWS_PAGE_ITEM_MESSAGE("&a&lSuccessfully edited news page item"),
    SUCCESSFUL_EDIT_NEWS_PAGE_MESSAGE("&a&lSuccessfully edited news page"),
    ERROR_PLAYER_LIST_DISABLED_MESSAGE("&c&lError► &4Data player list is disabled"),
    ERROR_NO_SUCH_NEWS_PAGE("&c&lError► &4No such news page"),
    NO_NEXT_PAGE("&c&lError► &4No more pages"),
    NO_PREVIOUS_PAGE("&c&lError► &4No previous page"),
    NO_SUCH_ITEM_MESSAGE("&c&lError► &4No such item in the config"),
    NOT_A_BOOK_MESSAGE("&c&lError► &4Not a book in your hand"),
    NOT_AN_ITEM_MESSAGE("&c&lError► &4Not an item in your hand"),
    NO_SUCH_PAGE_MESSAGE("&c&lError► &4No such GUI/Book page"),
    WRONG_USAGE_REMOVE_MESSAGE("&c&lError► &4Wrong command usage. Please use this format /news remove <NewsPage>"),
    WRONG_USAGE_ADD_BOOK_MESSAGE("&c&lError► &4Wrong command usage. Please use this format /news addbook <BookName>"),
    WRONG_USAGE_ADD_ITEM_MESSAGE("&c&lError► &4Wrong command usage. Please use this format /news additem <ItemName>"),
    WRONG_USAGE_ADD_MESSAGE("&c&lError► &4Wrong command usage. Please use this format /news add <NewsPage> <ItemName> <Book/GUIName>"),
    WRONG_USAGE_EDIT_MESSAGE("&c&lError► &4Wrong command usage. Please use this format /news edit <NewsPage> <item/page> <ItemName/Book/GUIName>"),
    INSUFFICIENT_PERMISSION_COMMAND("&c&lError► &4Insufficient permission to execute this command"),
    INSUFFICIENT_PERMISSION_SUBCOMMAND("&c&lError► &4Insufficient permission to execute this sub-command");
    private String message;
    private Messages(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
}
