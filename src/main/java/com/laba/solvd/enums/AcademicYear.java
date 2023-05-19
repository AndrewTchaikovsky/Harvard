package com.laba.solvd.enums;

public enum AcademicYear {
    FRESHMAN(1, "Freshman"),
    SOPHOMORE(2, "Sophomore"),
    JUNIOR(3, "Junior"),
    SENIOR(4, "Senior");
    private int schoolYear;
    private String yearName;

    private AcademicYear(int schoolYear, String yearName) {
        this.schoolYear = schoolYear;
        this.yearName = yearName;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public String getYearName() {
        return yearName;
    }

    @Override
    public String toString() {
        return yearName;
    }
}
