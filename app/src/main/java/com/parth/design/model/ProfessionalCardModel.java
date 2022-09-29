package com.parth.design.model;

public class ProfessionalCardModel {
    private final int professionalCardImage;
    private final String personName;
    private final String ratingText;
    private final String distanceText;

    public ProfessionalCardModel(int professionalCardImage, String personName, String ratingText, String distanceText) {
        this.professionalCardImage = professionalCardImage;
        this.personName = personName;
        this.ratingText = ratingText;
        this.distanceText = distanceText;
    }

    public int getProfessionalCardImage() {
        return professionalCardImage;
    }

    public String getPersonName() {
        return personName;
    }

    public String getRatingText() {
        return ratingText;
    }

    public String getDistanceText() {
        return distanceText;
    }

}
