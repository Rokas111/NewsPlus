package me.Xocky.News.core.utils.custom.message;

import me.Xocky.News.core.News;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;


public class Message {
    private String message;
    public Message(String message) {
        this.message = message;
    }
    public String translate() {
        message = ChatColor.translateAlternateColorCodes('&',message);
        return message;
    }
    public ComponentBuilder translateJSON() {
        if (message.contains("*")) {
            ComponentBuilder t= new ComponentBuilder("");
            int index = message.indexOf('*');
            int textindex = 0;
            do {
                String specialword = message.substring(index).contains(" ")?message.substring(index+1).split(" ")[0]
                        :message.substring(index+1);
                if (!specialword.isEmpty() && News.nm.getJSONConfig().getYaml().contains(specialword)) {
                    ComponentBuilder translatedword =News.nm.getJSONFactory().manufacture(specialword);
                    String previoustext = message.substring(textindex,index);
                    t.append(TextComponent.fromLegacyText(previoustext));
                    t.append(translatedword.create());
                    textindex = 1+(index+specialword.length());
                }
                index = message.indexOf('*', index + 1);
            } while (index >= 0);
            t.append(TextComponent.fromLegacyText(message.substring(textindex)));
            return t;
        }
        ComponentBuilder cb = new ComponentBuilder("");
        cb.append(TextComponent.fromLegacyText(message));
        return cb;
    }
    public ComponentBuilder translateAllJSON() {
        message = translate();
        return translateJSON();
    }

}
