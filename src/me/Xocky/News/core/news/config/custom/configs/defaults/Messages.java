package me.Xocky.News.core.news.config.custom.configs.defaults;

public enum Messages {
    SUCCESSFUL_CONFIG_RELOAD_MESSAGE("&a&lSuccessfully reloaded all configs"),
    ERROR_NO_SUCH_NEWS_PAGE("&c&lError► &4No such news page"),
    NO_NEXT_PAGE("&c&lError► &4No more pages"),
    NO_PREVIOUS_PAGE("&c&lError► &4No previous page"),
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
