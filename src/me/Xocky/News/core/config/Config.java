package me.Xocky.News.core.config;

import java.io.File;
import java.io.IOException;

public abstract class Config implements IConfig {
    private String configname;
    private Section s;
    public Config (String name,Section s) {
        this.configname = name;
        this.s = s;
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
public Section getSection() {
        return s;
}
}