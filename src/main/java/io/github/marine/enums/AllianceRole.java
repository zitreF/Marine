package io.github.marine.enums;

public enum AllianceRole {

    GENERAL("General"),
    CAPTAIN("Captain"),
    PRIVATE("Private");

    private final String prefix;

    AllianceRole(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
