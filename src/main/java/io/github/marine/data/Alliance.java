package io.github.marine.data;

import io.github.marine.utils.ReaderUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class Alliance {

    private final List<AllianceUser> users;
    private final String name;
    private final String description;
    private final long warPoints;
    private final int warsWon;
    private final int warsLost;

    public Alliance(String result) {
        JSONObject json = new JSONObject(result);

        this.name = json.getString("Name");
        this.description = json.getString("Description");
        this.warPoints = json.getLong("WarPoints");
        this.warsWon = json.getInt("WarsWon");
        this.warsLost = json.getInt("WarsLost");

        this.users = new ArrayList<>();

        JSONArray array = json.getJSONArray("Members");

        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            String name = object.getString("Name");
            String avatar = object.getString("Avatar");
            long totalWarPoints = object.getLong("TotalWarPoints");
            int allianceRole = object.getInt("AllianceRole");
            this.users.add(new AllianceUser(name, avatar, totalWarPoints, allianceRole));
        }
    }

    public List<AllianceUser> getUsers() {
        return users;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getWarPoints() {
        return warPoints;
    }

    public int getWarsWon() {
        return warsWon;
    }

    public int getWarsLost() {
        return warsLost;
    }
}
