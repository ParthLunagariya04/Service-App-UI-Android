package com.parth.design.model;

public class OrderCardModel {
    private final int image;
    private final String userNameOrder;
    private final String mainCategoryName;
    private final String subCategoryName;
    private final String date;
    private final String stateOfOrder;

    public OrderCardModel(int image, String userNameOrder, String mainCategoryName, String subCategoryName, String date, String stateOfOrder) {
        this.image = image;
        this.userNameOrder = userNameOrder;
        this.mainCategoryName = mainCategoryName;
        this.subCategoryName = subCategoryName;
        this.date = date;
        this.stateOfOrder = stateOfOrder;
    }

    public int getImage() {
        return image;
    }

    public String getUserNameOrder() {
        return userNameOrder;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public String getDate() {
        return date;
    }

    public String getStateOfOrder() {
        return stateOfOrder;
    }
}
