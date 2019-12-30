package me.Xocky.News.core.utils.config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class Config implements IConfig {
    private String configname;
    private YamlConfiguration yaml;
    private Section s;
    public Config (String name,Section s) {
        this.configname = name;
        this.s = s;
        this.yaml = YamlConfiguration.loadConfiguration(getFile());
    }

    public String getConfig() {
        return this.configname;
    }
    public File getFile() {
        return new File(s.getFile().getPath() +"//" + configname+".yml");
    }
    public boolean setup() {
        if (!getFile().exists()) {
            try {
                getFile().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }
    public YamlConfiguration getYaml() {
        return this.yaml;
    }
    public Section getSection() {
            return s;
    }
    public void reload() {
        this.yaml = YamlConfiguration.loadConfiguration(getFile());
    }
    public void save() {
        try {
            getYaml().save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}