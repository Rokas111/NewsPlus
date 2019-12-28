package me.Xocky.News.core.news.config.custom.configs.defaults;

public enum Messages {
    ERROR_NO_SUCH_NEWS_PAGE("&c&lError► &4No such news page"),
    NO_NEXT_PAGE("&c&lError► &4No more pages"),
    NO_PREVIOUS_PAGE("&c&lError► &4No previous page");
    private String message;
    private Messages(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
}