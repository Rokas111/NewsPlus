package me.Xocky.News.core.news.config.custom.factory.message;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.custom.message.Message;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.OfflinePlayer;

public class MessageFactory {
    public MessageFactory() {

    }
    public ComponentBuilder manufacture(String name) {
        if (News.nm.getMessageConfig().getYaml().contains(name)) {
            return new Message(News.nm.getMessageConfig().getYaml().getString(name)).translateAllJSON();
        }
        return new ComponentBuilder("");
    }
}
