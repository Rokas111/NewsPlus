package me.Xocky.News.core.news.config.custom.factory.json;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.custom.message.Message;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class JSONFactory {
    public JSONFactory() {

    }
    public ComponentBuilder manufacture(String key) {
        if (News.nm.getJSONConfig().getYaml().contains(key)) {
            ComponentBuilder t = new ComponentBuilder("");
            t.append(new Message(News.nm.getJSONConfig().getYaml().getString(key + ".json")).translate());
            if (News.nm.getJSONConfig().getYaml().contains(key + ".hover.json")) {
                t.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(new Message(News.nm.getJSONConfig().getYaml().getString(key + ".hover.json")).translate()).create()));
            }
            if (News.nm.getJSONConfig().getYaml().contains(key + ".click.value")) {
                t.event(new ClickEvent((News.nm.getJSONConfig().getYaml().getString(key + ".click.value").startsWith("/") ? ClickEvent.Action.RUN_COMMAND : ClickEvent.Action.OPEN_URL), News.nm.getJSONConfig().getYaml().getString(key + ".click.value")));
            }
            return t;
        }
        return null;
    }
}
