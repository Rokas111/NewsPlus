package me.Xocky.News.core.utils.cmd.subcmd;

public abstract class SubCommand implements ISubCommand {
    private String name;
    private String permission;
    public SubCommand(String name,String permission) {
        this.name = name;
        this.permission = permission;
    }
    public String getName() {
        return name;
    }
    public String getPermission() {
        return permission;
    }
}
