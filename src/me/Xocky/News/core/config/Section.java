package me.Xocky.News.core.config;

import java.io.File;

public class Section {
    private File parent;
    private String name;
    public Section(Section s,String name) {
        this.parent = s.getFile();
        this.name = name;
        create();
    }
    public Section(String name) {
        this.parent = new File("plugins");
        this.name = name;
        create();
    }
    private void create() {
        if (!getFile().exists()) {
            getFile().mkdir();
        }
    }
    public File getFile() {
        return new File(parent.getPath() +"//" + name);
    }
    public File getParent() {
        return this.parent;
    }
    public String getName() {
        return this.name;
    }

}
