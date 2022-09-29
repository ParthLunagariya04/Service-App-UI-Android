package com.parth.design.model;

public class ConstructionCardsModel {
    private final int image;
    private final String categoryName;

    public ConstructionCardsModel(int image, String categoryName){
        this.image = image;
        this.categoryName = categoryName;
    }

    public int getImage() {
        return image;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
