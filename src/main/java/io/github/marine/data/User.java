package io.github.marine.data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class User {

    private final String name;
    private final long id;
    private final int level;
    private final String alliance;
    private final String avatar;
    private final long playTime;
    private final int chipsSpent;
    private final int coloniesMoved;
    private final int giftsSent;
    private final int starbasesDestroyed;
    private final int timesAttacked;
    private final int playersAttacked;
    private final int nukesUsed;
    private final int friendsHelped;
    private final int obstaclesRecycled;
    private final int troopsDonated;
    private final List<Planet> planets;

    public User(String result, String statsResult) {
        JSONObject json = new JSONObject(result);

        this.name = json.getString("Name");
        this.id = json.getLong("Id");
        this.level = json.getInt("Level");
        this.alliance = json.isNull("AllianceId") ? "No clan" : json.getString("AllianceId");

        this.planets = new ArrayList<>();

        JSONArray planetsArray = json.getJSONArray("Planets");

        for (int i = 0; i < planetsArray.length(); i++) {
            JSONObject object = planetsArray.getJSONObject(i);
            planets.add(new Planet(object.getInt("HQLevel")));
        }

        this.avatar = json.getString("Avatar");

        JSONObject stats = new JSONObject(statsResult);

        this.playTime = stats.getLong("TotalPlayTimeInMs");
        this.chipsSpent = stats.getInt("ChipsSpent");
        this.coloniesMoved = stats.getInt("ColoniesMoved");
        this.giftsSent = stats.getInt("GiftsSent");
        this.starbasesDestroyed = stats.getInt("StarbasesDestroyed");
        this.timesAttacked = stats.getInt("TimesAttacked");
        this.playersAttacked = stats.getInt("PlayersAttacked");
        this.nukesUsed = stats.getInt("NukesUsed");
        this.friendsHelped = stats.getInt("FriendsHelped");
        this.obstaclesRecycled = stats.getInt("ObstaclesRecycled");
        this.troopsDonated = stats.getInt("TroopSizesDonated");
    }

    public String getName() {
        return name;
    }

    public List<Planet> getColonies() {
        return planets;
    }

    public int getLevel() {
        return level;
    }

    public String getAlliance() {
        return alliance;
    }

    public String getAvatar() {
        return avatar;
    }

    public long getId() {
        return id;
    }

    public long getPlayTime() {
        return playTime;
    }

    public int getChipsSpent() {
        return chipsSpent;
    }

    public int getColoniesMoved() {
        return coloniesMoved;
    }

    public int getGiftsSent() {
        return giftsSent;
    }

    public int getStarbasesDestroyed() {
        return starbasesDestroyed;
    }

    public int getTimesAttacked() {
        return timesAttacked;
    }

    public Planet getMainPlanet() {
        return this.planets.get(0);
    }

    public int getPlayersAttacked() {
        return this.playersAttacked;
    }

    public int getNukesUsed() {
        return nukesUsed;
    }

    public int getFriendsHelped() {
        return friendsHelped;
    }

    public int getObstaclesRecycled() {
        return obstaclesRecycled;
    }

    public int getTroopsDonated() {
        return troopsDonated;
    }

    public int getUsedNukes() {
        return this.nukesUsed;
    }
}
