package com.huyvo.dronedeliverymanagementapp.classes.enums;

public enum IdType {
    DRONE("DR_"),
    CUSTOMER("CU_"),
    PACKAGE("PK_"),
    LOCATION("LO_"),
    BASE("BA_"),
    DELIVERY_HISTORY("DH_"),
    DRONE_BASE_ASSIGNMENT("DBS_");

    private final String prefix;

    IdType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    public String toString() {
        return prefix;
    }
}


