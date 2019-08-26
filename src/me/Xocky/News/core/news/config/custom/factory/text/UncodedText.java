package me.Xocky.News.core.news.config.custom.factory.text;

public class UncodedText {
    private String text;
    private String hovertext;
    private String clickvalue;
    public UncodedText(String text, String hovertext, String clickvalue) {
        this.text = text;
        this.hovertext = hovertext;
        this.clickvalue = clickvalue;
    }
    public UncodedText(String text, String hovertext) {
        this.text = text;
        this.hovertext = hovertext;
        this.clickvalue = "";
    }
    public UncodedText(String text) {
        this.text = text;
        this.hovertext = "";
        this.clickvalue = "";
    }
    public boolean hasHover() {
        return !hovertext.isEmpty();
    }
    public boolean hasClick() {
        return !clickvalue.isEmpty();
    }

    public String getText() {
        return text;
    }
    public String getHover() {
        return hovertext;
    }
    public String getClick() {
        return clickvalue;
    }
}
