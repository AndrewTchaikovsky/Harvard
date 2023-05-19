package com.laba.solvd.enums;

public enum Gender {
    MALE("he/him"),
    FEMALE("she/her"),
    NON_BINARY("they/them");

    private String pronoun;

    private Gender(String pronoun) {
        this.pronoun = pronoun;
    }
    public String getPronoun() {
        return pronoun;
    }
}
