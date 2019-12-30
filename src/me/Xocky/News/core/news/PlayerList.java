package me.Xocky.News.core.news;

import me.Xocky.News.core.news.config.data.ConfigPlayerList;

import java.util.List;
import java.util.UUID;

public class PlayerList {
    private ConfigPlayerList list;
    public PlayerList(ConfigPlayerList list) {
        this.list = list;
    }
    public void addPlayer(UUID id) {
        list.addPlayer(id);
    }
    public void removePlayer(UUID id) {
        list.removePlayer(id);
    }
    public boolean playerExists(UUID id) {
        return list.playerExists(id);
    }
    public boolean isEmpty() {
        return list.isEmpty();
    }
    public List<UUID> getPlayers() {
        return list.getPlayers();
    }
    public void clearPlayers() {
        list.clearPlayers();
    }

}
