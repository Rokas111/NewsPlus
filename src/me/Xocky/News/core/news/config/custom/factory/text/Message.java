package me.Xocky.News.core.news.config.custom.factory.text;

import me.Xocky.News.core.News;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class Message {
    private String message;
    public Message(String message) {
        this.message = message;
    }
    public String translate() {
        message = ChatColor.translateAlternateColorCodes('&',message);
        return message;
    }
    public TextComponent translateJSON() {
        if (message.contains("*")) {
            String[] words = message.contains(" ") ? message.split(" ") : new String[]{message};
            TextComponent t= new TextComponent("");
            for (String word : words) {
                if (word.startsWith("*") && word.length() > 1 && News.NM.getTextConfig().getYaml().contains(word.substring(1))) {
                    TextComponent text = News.NM.getJSONFactory().manufacture(word.substring(1));
                    t.addExtra(text);
                } else {
                    TextComponent text = new TextComponent(word+" ");
                    t.addExtra(text);
                }
            }
            return t;
        }
        return new TextComponent(message);
    }
    public TextComponent translateAllJSON() {
        message = translate();
        return translateJSON();
    }

}
