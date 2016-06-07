package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RSSFeed
{ //Contains information about the feed itself.

    private URL url;                //Url to the feeds
    private String description;
    private String link;            //Link provided in channel tags of feed

    private ArrayList<RSSItem> rssItemList;

    public RSSFeed(String feedUrl)
    {
        try {
            this.url = new URL(feedUrl);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }

        this.initialize();

        this.rssItemList = collectFeedItems();      // Collect feedItems for the first time
    }

    private void initialize()
    {
        // gather data for this object like description and link
    }

    private ArrayList<RSSItem> collectFeedItems()
    {
        ArrayList<RSSItem> feedItems = new ArrayList<>();

        try {
            DocumentBuilderFactory documentBuilderFactory   = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder                 = documentBuilderFactory.newDocumentBuilder();
            Document doc                                    = documentBuilder.parse(this.url.openStream());

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("item");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String itemTitle        = eElement.getElementsByTagName("title").item(0).getTextContent();
                    String itemLink         = eElement.getElementsByTagName("link").item(0).getTextContent();
                    String itemDescription  = eElement.getElementsByTagName("description").item(0).getTextContent();
                    String itemPubDate      = eElement.getElementsByTagName("pubDate").item(0).getTextContent();

                    RSSItem rssItem = new RSSItem(itemTitle, itemLink, itemDescription);
                    feedItems.add(rssItem);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
        }

        return feedItems;
    }

    @Override
    public String toString()
    {
        return "RSSFeed: " + this.url;
    }
}
