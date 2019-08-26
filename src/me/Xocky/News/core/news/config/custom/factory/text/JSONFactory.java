package me.Xocky.News.core.news.config.custom.factory.text;

import me.Xocky.News.core.News;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

import java.util.stream.Collectors;

public class JSONFactory {
    public JSONFactory() {

    }
    public TextComponent manufacture(String key) {
        if (News.NM.getTextConfig().getYaml().contains(key)) {
            TextComponent t = new TextComponent(new Message(News.NM.getTextConfig().getYaml().getString(key + ".text")).translate());
            if (News.NM.getTextConfig().getYaml().contains(key + ".hover.text")) {
                t.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(new Message(News.NM.getTextConfig().getYaml().getString(key + ".hover.text")).translate()).create()));
            }
            if (News.NM.getTextConfig().getYaml().contains(key + ".click.value")) {
                t.setClickEvent(new ClickEvent((News.NM.getTextConfig().getYaml().getString(key + ".click.value").startsWith("/") ? ClickEvent.Action.RUN_COMMAND : ClickEvent.Action.OPEN_URL), News.NM.getTextConfig().getYaml().getString(key + ".click.value")));
            }
            return t;
        }
        return null;
    }
}
