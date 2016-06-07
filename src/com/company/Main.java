package com.company;

public class Main {

    public static void main(String[] args)
    {
        RSSFeed rssFeed = new RSSFeed("http://feeds.ign.com/ign/all?format=xml");
        RSSFeed rsssFeed = new RSSFeed("http://www.4gamers.be/rss/all-gamenews");
    }

}
