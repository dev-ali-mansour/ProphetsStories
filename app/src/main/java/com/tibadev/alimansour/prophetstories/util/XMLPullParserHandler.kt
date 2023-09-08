package com.tibadev.alimansour.prophetstories.util

import com.tibadev.alimansour.prophetstories.models.Story
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class XMLPullParserHandler {
    private var _stories: MutableList<Story> = mutableListOf()
    private lateinit var story: Story
    private lateinit var text: String
    val stories:List<Story> = _stories

    fun parse(inputStream: InputStream?): List<Story> {
        val factory: XmlPullParserFactory
        val parser: XmlPullParser
        try {
            factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when (eventType) {
                    XmlPullParser.START_TAG -> if (tagName.equals("story", ignoreCase = true)) {
                        story = Story()
                    }

                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> if (tagName.equals("story", ignoreCase = true)) {
                        _stories.add(story)
                    } else if (tagName.equals("content", ignoreCase = true)) {
                        story.content = text
                    } else if (tagName.equals("prophet", ignoreCase = true)) {
                        story.prophet = text
                    }

                    else -> {}
                }
                eventType = parser.next()
            }
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return _stories
    }
}