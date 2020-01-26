package me.Xocky.News.core.utils.cmd.subcmd;

import me.Xocky.News.core.utils.cmd.config.CommandConfig;

public abstract class SubCommand implements ISubCommand {
    private String name;
    private String permission;
    private String description;
    private CommandConfig config;
    public SubCommand(String name,String permission,String description) {
        this.name = name;
        this.permission = permission;
        this.description = description;
    }
    public SubCommand(String name,String permission,String description,CommandConfig config) {
        this.name = name;
        this.permission = permission;
        this.description = description;
        this.config = config;
    }
    public CommandConfig getCommandConfig() {return this.config;}
    public String getName() {
        return name;
    }
    public String getPermission() {
        return permission;
    }
    public String getDescription() {
        return this.description;
    }
}
