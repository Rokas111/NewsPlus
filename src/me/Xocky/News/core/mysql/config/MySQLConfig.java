package me.Xocky.News.core.mysql.config;

import me.Xocky.News.core.News;
import me.Xocky.News.core.utils.config.Config;
import me.Xocky.News.core.utils.config.Section;

public class MySQLConfig extends Config {
    public MySQLConfig() {
        super("MySQL", new Section(new Section(News.PLUGIN_FOLDER),"MySQL"));
        if (!setup()) {
            setupKeys();
        }
    }

    public void setupKeys() {
        getYaml().set("host", "localhost");
        getYaml().set("port", 0);
        getYaml().set("username", "");
        getYaml().set("password", "");
        getYaml().set("database", "");
        save();
    }
    public Integer getPort() {
        return getInt("port");
    }

    public String getHost() {
        return getString("host");
    }
    public String getUsername() {
        return getString("username");
    }
    public String getPassword() {
        return getString("password");
    }
    public String getDatabase() {
        return getString("database");
    }
}
