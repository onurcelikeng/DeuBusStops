package com.onurcelikeng.deubusstops.Models;

import java.io.Serializable;

public class BusStopModel implements Serializable {
    private int id;
    private String name;
    private String latitude;
    private String longitude;
    private String totalPoint;
    private int voteCount;


    public BusStopModel(int id, String name, String latitude, String longitude, String totalPoint, int voteCount) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.totalPoint = totalPoint;
        this.voteCount = voteCount;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(String totalPoint) {
        this.totalPoint = totalPoint;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
