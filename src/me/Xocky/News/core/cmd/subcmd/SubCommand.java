package me.Xocky.News.core.cmd.subcmd;

public abstract class SubCommand implements ISubCommand {
    private String root;
    private String name;
    private String permission;
    public SubCommand(String name,String root,String permission) {
        this.name = name;
        this.root = root;
        this.permission = permission;
    }
    public String getName() {
        return name;
    }
    public String getRoot() {
        return root;
    }
    public String getPermission() {
        return permission;
    }
}
