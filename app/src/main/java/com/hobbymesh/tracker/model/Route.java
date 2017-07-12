package com.hobbymesh.tracker.model;

import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 * Created by janmusil on 25/06/2017.
 */

public class Route {

    @Id(autoincrement = true)
    private long id;

    private String name;

    private Date createdAt;

    public Route(String name) {
        this.name = name;
        this.createdAt = new Date();
    }

    public Route(long id, String name, Date createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
