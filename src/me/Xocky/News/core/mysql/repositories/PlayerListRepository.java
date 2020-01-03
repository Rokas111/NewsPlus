package me.Xocky.News.core.mysql.repositories;

import me.Xocky.News.core.News;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PlayerListRepository {
    private final String CREATE = "CREATE TABLE IF NOT EXISTS PlayerList( uuid VARCHAR(36) NOT NULL )";
    private final String INSERT_PLAYER = "INSERT INTO PlayerList(uuid) VALUES(?)";
    private final String CLEAN_PLAYERS = "DELETE FROM PlayerList";
    private final String GET_PLAYERS = "SELECT uuid FROM PlayerList";
    private Plugin pl;
    public PlayerListRepository(Plugin pl) {
        this.pl = pl;
    }
    public void setup() {
                Connection connection = null;
                try {
                    connection = News.mySQL.getHikari().getConnection();
                    PreparedStatement rewards = connection.prepareStatement(CREATE);
                    rewards.execute();
                    rewards.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
    }
    public void addPlayer(UUID id) {
        Connection connection = null;
        try {
            connection = News.mySQL.getHikari().getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_PLAYER);
            ps.setString(1,id.toString());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void cleanPlayers() {
        Connection connection = null;
        try {
            connection = News.mySQL.getHikari().getConnection();
            PreparedStatement rewards = connection.prepareStatement(CLEAN_PLAYERS);
            rewards.execute();
            rewards.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public List<UUID> getPlayers() {
                Connection connection = null;
                ResultSet set = null;
                PreparedStatement ps = null;
                try {
                    connection = News.mySQL.getHikari().getConnection();
                    ps = connection.prepareStatement(GET_PLAYERS);
                    set = ps.executeQuery();
                    List<UUID> players = new ArrayList<>();
                    while (set.next()) {
                        players.add(UUID.fromString(set.getString("uuid")));
                    }
                    return players;
                } catch (SQLException e) {
                    return new ArrayList<>();
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                        if (set != null) {
                            set.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }

    }
    public void setPlayers(List<UUID> players) {
        cleanPlayers();
        players.forEach(this::addPlayer);
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
