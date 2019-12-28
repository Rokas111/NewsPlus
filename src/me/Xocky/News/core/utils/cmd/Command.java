package me.Xocky.News.core.utils.cmd;

public abstract class Command implements ICommand {
    private String name;
    private String permission;
    public Command(String permission,String names) {
        this.name = names;
        this.permission = permission;
    }
    public String getName() {
        return this.name;
    }
    public String getPermission() {
        return this.permission;
    }

}
