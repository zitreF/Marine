package io.github.marine.data;

import io.github.marine.enums.AllianceRole;

public final class AllianceUser {

    private final String name;
    private final String avatar;
    private final long warPoints;
    private final AllianceRole role;

    public AllianceUser(String name, String avatar, long warPoints, int allianceRole) {
        this.name = name;
        this.avatar = avatar;
        this.warPoints = warPoints;
        this.role = AllianceRole.values()[allianceRole];
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public long getWarPoints() {
        return warPoints;
    }

    public AllianceRole getRole() {
        return role;
    }
}
