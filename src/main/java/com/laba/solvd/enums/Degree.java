package com.laba.solvd.enums;

public enum Degree {
    BACHELORS("Bachelor's Degree"),
    MASTERS("Master's Degree"),
    DOCTORAL("Doctoral Degree"),
    PROFESSIONAL("Professional Degrees");
    private String degreeLevel;
    private Degree(String degreeLevel) {
        this.degreeLevel = degreeLevel;
    }
    public String getDegreeLevel() {
        return degreeLevel;
    }

}
