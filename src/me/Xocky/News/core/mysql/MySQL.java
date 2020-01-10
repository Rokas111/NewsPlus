package me.Xocky.News.core.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.Xocky.News.core.News;
import me.Xocky.News.core.mysql.config.MySQLConfig;
import me.Xocky.News.core.mysql.repositories.PlayerListRepository;
import org.bukkit.plugin.Plugin;

public class MySQL {
    private Integer port;
    private String host;
    private String username;
    private String password;
    private MySQLConfig config;
    private PlayerListRepository plr;
    private String database;
    private HikariDataSource hikari;
    private boolean connected;
    private Plugin pl;
    public MySQL(Plugin pl) {
        this.pl = pl;
        plr = new PlayerListRepository(pl);
    }
    public void initialize() {
        registerConfigs();
        setupDatabase();
        setupMysql();
    }
    private void registerConfigs() {
        News.um.getConfigManager().registerConfig(new MySQLConfig());
        config = (MySQLConfig) News.um.getConfigManager().getYaml("MySQL");
    }
    private void setupDatabase() {
        this.username = config.getUsername();
        this.host = config.getHost();
        this.port = config.getPort();
        this.password = config.getPassword();
        this.database = config.getDatabase();
    }
    private void setupMysql() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            HikariConfig jdbcConfig = new HikariConfig();
            jdbcConfig.setPoolName("pool");
            jdbcConfig.setMaximumPoolSize(10);
            jdbcConfig.setMinimumIdle(2);
            jdbcConfig.setJdbcUrl("jdbc:mysql://"+host+":"+port+"/"+database);
            jdbcConfig.setUsername(username);
            jdbcConfig.setPassword(password);

            hikari = new HikariDataSource(jdbcConfig);
            connected = true;
        } catch (Exception e) {
            connected = false;
        }
    }
    public boolean isConnected() {
        return connected;
    }
    public PlayerListRepository getPlayerListRepository() {
        return this.plr;
    }
    public HikariDataSource getHikari() {
        return this.hikari;
    }
}
