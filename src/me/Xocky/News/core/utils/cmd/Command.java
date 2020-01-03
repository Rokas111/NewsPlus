package me.Xocky.News.core.utils.cmd;

import me.Xocky.News.core.utils.cmd.subcmd.ISubCommand;

import java.util.ArrayList;
import java.util.List;

public abstract class Command implements ICommand {
    private String name;
    private String permission;
    private List<ISubCommand> subCommands;
    public Command(String permission,String names) {
        this.name = names;
        this.permission = permission;
        this.subCommands = new ArrayList<>();
    }
    public Command(String permission,String names,List<ISubCommand> subCommands) {
        this.name = names;
        this.permission = permission;
        this.subCommands = subCommands;
    }
    public List<ISubCommand> getSubCommands() {
        return this.subCommands;
    }
    public String getName() {
        return this.name;
    }
    public String getPermission() {
        return this.permission;
    }

}
