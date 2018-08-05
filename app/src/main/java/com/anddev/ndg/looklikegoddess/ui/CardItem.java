package com.anddev.ndg.looklikegoddess.ui;


public class CardItem {

    private String mDescription;
    private String mTitle;


    private String mImageUrl;

    public CardItem(String title, String text, String imageUrl) {
        mDescription = title;
        mTitle = text;
        mImageUrl = imageUrl;
    }

    public String getText() {
        return mDescription;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getImageUrl() {
        return mImageUrl;
    }


}
