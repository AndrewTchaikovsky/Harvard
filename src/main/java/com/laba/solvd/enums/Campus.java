package com.laba.solvd.enums;

public enum Campus {
    CAMBRIDGE("Harvard Yard"),
    ALLSTON("Allston campus"),
    BOSTON("Boston campus");

    private String campusName;
    private Campus(String campusName) {
        this.campusName = campusName;
    }
    public String getCampusName() {
        return campusName;
    }
}
