package com.laba.solvd.enums;

public enum EmploymentStatus {
    FULL_TIME("Full-Time"),
    PART_TIME("Part-Time"),
    ADJUNCT("Adjunct");
    private String professorStatus;

    private EmploymentStatus(String professorStatus) {
        this.professorStatus = professorStatus;
    }

    public String getProfessorStatus() {
        return professorStatus;
    }
}
