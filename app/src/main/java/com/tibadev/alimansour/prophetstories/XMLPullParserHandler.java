package com.tibadev.alimansour.prophetstories;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ali Mansour on 5/21/16.
 */
public class XMLPullParserHandler {
    List<Story> stories;
    private Story story;
    private String text;

    public XMLPullParserHandler() {
        stories = new ArrayList<Story>();
    }

    public List<Story> getStories() {
        return stories;
    }

    public List<Story> parse(InputStream is) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("story")) {
                            // create a new instance of story
                            story = new Story();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("story")) {
                            //ToDo: add story object to list
                            stories.add(story);
                        } else if (tagname.equalsIgnoreCase("content")) {
                            story.setContent(text);
                        } else if (tagname.equalsIgnoreCase("prophet")) {
                            story.setProphet(text);
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stories;
    }
}
