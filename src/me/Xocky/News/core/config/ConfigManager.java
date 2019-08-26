package me.Xocky.News.core.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConfigManager {
    private List<Config> configs;
    public ConfigManager() {
        configs = new ArrayList<>();
        setupDir();
    }
    public void setupDir() {
        File file =new File("plugins//News+");
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
}
