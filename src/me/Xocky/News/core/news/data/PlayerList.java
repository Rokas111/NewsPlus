package me.Xocky.News.core.news.data;

import me.Xocky.News.core.News;
import me.Xocky.News.core.news.config.data.ConfigPlayerList;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerList {
    public enum DataType {MYSQL,CONFIG}
    private ConfigPlayerList list;
    private DataType type;
    private Plugin pl;
    private static List<UUID> players;
    public PlayerList(Plugin pl,ConfigPlayerList list) {
        this.pl = pl;
        this.list = list;
        this.type = DataType.CONFIG;
    }
    public PlayerList(Plugin pl) {
        this.pl = pl;
        this.type = DataType.MYSQL;
        Bukkit.getScheduler().runTaskAsynchronously(pl, new Runnable() {
            @Override
            public void run() {
                News.mySQL.getPlayerListRepository().setup();
            }
        });
    }
    public void load() {
        switch (type) {
            case CONFIG:
                players = list.getPlayers();
                break;
            case MYSQL:
                Bukkit.getScheduler().runTaskAsynchronously(pl, new Runnable() {
                    @Override
                    public void run() {
                        players = News.mySQL.getPlayerListRepository().getPlayers();
                    }
                });
                break;
        }
    }
    public void save() {
        switch (type) {
            case CONFIG:
                list.save(players);
                break;
            case MYSQL:
                News.mySQL.getPlayerListRepository().save(players);
                break;
        }
    }
    public void addPlayer(UUID id) {
        players.add(id);
    }
    public void removePlayer(UUID id) {
        players.remove(id);
    }
    public boolean playerExists(UUID id) {
        return players.contains(id);
    }
    public boolean isEmpty() {
        return players.isEmpty();
    }
    public void clearPlayers() {
        players.clear();
    }
    public List<UUID> getPlayers() {
        return players;
    }
}
