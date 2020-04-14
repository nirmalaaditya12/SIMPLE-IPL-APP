package com.ipl.india.ipl.form;

public enum PlayerRole {
    ALL_ROUNDER("ALL_ROUNDER"),
    WICKET_KEEPER("WICKET_KEEPER"),
    BATSMAN("BATSMAN"),
    BOWLER("BOWLER");
    public String role;

    PlayerRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
