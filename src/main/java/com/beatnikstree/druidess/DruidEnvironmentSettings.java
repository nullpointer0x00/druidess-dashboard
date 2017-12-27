package com.beatnikstree.druidess;

public class DruidEnvironmentSettings {

    public Integer id;
    public String name;
    public String overlordUrl;
    public String color;

    public DruidEnvironmentSettings(Integer id, String name, String overlordUrl, String color) {
        this.id = id;
        this.name = name;
        this.overlordUrl = overlordUrl;
        this.color = color;
    }
}
