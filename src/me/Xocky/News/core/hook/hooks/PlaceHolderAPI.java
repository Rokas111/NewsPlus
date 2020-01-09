package me.Xocky.News.core.hook.hooks;

import me.Xocky.News.core.hook.Hook;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlaceHolderAPI extends Hook {
    public PlaceHolderAPI() {
        super("PlaceholderAPI");
    }
    public String translatePlaceHolders(String text, OfflinePlayer p) {
        if  (!isEnabled()) return text;
        try {
            return (String)Class.forName("me.clip.placeholderapi.PlaceholderAPI").getMethod("setPlaceHolders", Player.class, String.class).invoke(Class.forName("me.clip.placeholderapi.PlaceholderAPI"), p, text);
        } catch(Exception e) {
            return text;
        }
    }
}
