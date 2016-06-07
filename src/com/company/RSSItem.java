package com.company;

public class RSSItem
{

    private String title;
    private String link;
    private String description;

    public RSSItem(String title, String link, String description)
    {
        this.title = title;
        this.link = link;
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "RSSItem{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
