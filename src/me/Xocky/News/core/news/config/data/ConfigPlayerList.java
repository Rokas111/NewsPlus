package me.Xocky.News.core.news.config.data;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.config.Config;
import me.Xocky.News.core.utils.config.Section;

import java.util.*;

public class ConfigPlayerList extends Config {
    public ConfigPlayerList() {
        super("DataPlayerList", new Section(new Section(News.PLUGIN_FOLDER),"Data"));
        if (!setup()) {
            setupKeys();
        }
    }
    public void setupKeys() {
    }
    public void addPlayer(UUID id) {
        addStringToList("players",id.toString());
    }
    public void removePlayer(UUID id) {
        removeStringToList("players",id.toString());
    }
    public boolean playerExists(UUID id) {
        return getPlayers().contains(id);
    }
    public void clearPlayers() {
        getYaml().set("players",null);
        save();
    }
    public void setPlayers(List<UUID> players) {
        List<String> newlist = new ArrayList<>();
        players.forEach(player -> newlist.add(player.toString()));
        getYaml().set("players",newlist);
        save();
    }
    public boolean isEmpty() {
        return !getYaml().contains("players") || getPlayers().isEmpty();
    }
    public List<UUID> getPlayers() {
        List<UUID> players = new ArrayList<>();
        getStringList("players").forEach(player -> players.add(UUID.fromString(player)));
        return players;
    }
    public void save(List<UUID> players) {
        List<UUID> old = getPlayers();
        Collections.sort(old);
        Collections.sort(players);
        if (!Objects.equals(old,players)) {
            setPlayers(players);
        }
    }
}
