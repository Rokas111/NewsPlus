package me.Xocky.News.core.utils.cmd.subcmd;

public abstract class SubCommand implements ISubCommand {
    private String name;
    private String permission;
    private String description;
    public SubCommand(String name,String permission,String description) {
        this.name = name;
        this.permission = permission;
        this.description = description;
    }
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
