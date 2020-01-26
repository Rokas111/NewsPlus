package me.Xocky.News.core.utils.cmd;

import me.Xocky.News.core.utils.cmd.config.CommandConfig;
import me.Xocky.News.core.utils.cmd.subcmd.ISubCommand;

import java.util.ArrayList;
import java.util.List;

public abstract class Command implements ICommand {
    private String name;
    private String permission;
    private String description;
    private List<ISubCommand> subCommands;
    private CommandConfig config;
    public Command(String permission,String name,String description) {
        this.name = name;
        this.permission = permission;
        this.subCommands = new ArrayList<>();
        this.description = description;
        this.config = null;
    }
    public Command(String permission, String name, List<ISubCommand> subCommands, String description, CommandConfig config) {
        this.name = name;
        this.permission = permission;
        this.subCommands = subCommands;
        this.description = description;
        this.config = config;
    }
    public Command(String permission, String name, List<ISubCommand> subCommands, String description) {
        this.name = name;
        this.permission = permission;
        this.subCommands = subCommands;
        this.description = description;
        this.config = null;
    }
    public CommandConfig getCommandConfig() {
        return this.config;
    }
    public List<ISubCommand> getSubCommands() {
        return this.subCommands;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public String getPermission() {
        return this.permission;
    }

}
