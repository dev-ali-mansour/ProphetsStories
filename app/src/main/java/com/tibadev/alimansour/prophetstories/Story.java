package com.tibadev.alimansour.prophetstories;

/**
 * Created by Ali Mansour on 5/21/16.
 */
public class Story {
    private String prophet;
    private String content;

    public String getProphet() {
        return prophet;
    }

    public void setProphet(String prophet) {
        this.prophet = prophet;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return prophet;
    }
}