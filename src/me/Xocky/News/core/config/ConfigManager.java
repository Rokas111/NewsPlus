package me.Xocky.News.core.config;

import me.Xocky.News.core.News;
import me.Xocky.News.core.config.cmds.ConfigReload;
import me.Xocky.News.core.utils.config.Config;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConfigManager {
    private List<Config> configs;
    private Plugin pl;
    public ConfigManager(Plugin pl) {
        this.pl = pl;
        configs = new ArrayList<>();
    }
    public void initialize() {
        registerCommands();
        setupDir();
    }
    private void registerCommands() {
        News.um.getCommandManager().registerCommand(new ConfigReload(),"configreload");
    }
    public void setupDir() {
        File file =new File("plugins//" + News.PLUGIN_FOLDER);
        if (!file.exists()) {
            file.mkdir();
        }
    }
    public Config getYaml(String name) {
        return !configs.stream().filter(c -> c.getConfig().equals(name)).collect(Collectors.toList()).isEmpty()?configs.stream().filter(c -> c.getConfig().equals(name)).collect(Collectors.toList()).get(0):null;
    }
    public void registerConfig(Config c) {
        configs.add(c);
    }
    public void reloadAllConfigs() {
        configs.forEach(Config::reload);
    }
}
