package me.Xocky.News.core.news.config.data;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.config.Config;
import me.Xocky.News.core.utils.config.Section;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        List<String> players = getPlayerList();
        players.add(id.toString());
        getYaml().set("players",players);
        save();
    }
    public void removePlayer(UUID id) {
        List<String> players = getPlayerList();
        players.remove(id.toString());
        getYaml().set("players",players);
        save();
    }
    public boolean playerExists(UUID id) {
        return getPlayerList().contains(id.toString());
    }
    public List<UUID> getPlayers() {
        List<UUID> players = new ArrayList<>();
        getPlayerList().forEach(player -> players.add(UUID.fromString(player)));
        return players;
    }
    public void clearPlayers() {
        getYaml().set("players",null);
        save();
    }
    public boolean isEmpty() {
        return !getYaml().contains("players") || getPlayerList().isEmpty();
    }
    private List<String> getPlayerList() {
        return getYaml().getStringList("players");
    }
}
