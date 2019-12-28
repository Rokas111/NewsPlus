package me.Xocky.News.core.news.config.custom.configs.defaults;

import me.Xocky.News.core.utils.custom.json.UncodedJSON;

public enum JSON{
    UPDATE_FEATURE(new UncodedJSON("&aAdded chocolate &acookies!","&4<3","")),
    BLOG_TEXT(new UncodedJSON("&aMore cookies will be &aadded","","")),
    DISCORD(new UncodedJSON("&8&l&nDiscord","&7Click here to join the discord","https://discord.gg")),
    WEBSITE(new UncodedJSON("&a&l&nWebsite","&7Click here to see the website","https://www.google.com/")),
    NEWS_MORE(new UncodedJSON("&4&l&nMore","&7Click here to see more","/news"));
    private UncodedJSON json;
    private JSON(UncodedJSON json) {
        this.json = json;
    }
    public UncodedJSON getJSON() {
        return this.json;
    }
}
