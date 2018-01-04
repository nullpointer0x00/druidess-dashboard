package com.beatnikstree.druidess.data;

import javax.persistence.*;

@Entity
@Table(name = "environment_settings")
public class EnvironmentSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    private String name;
    private String overlordUrl;
    private String color;
    private Boolean isDefault;

    public EnvironmentSettings() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverlordUrl() {
        return overlordUrl;
    }

    public void setOverlordUrl(String overlordUrl) {
        this.overlordUrl = overlordUrl;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean aDefault) {
        isDefault = aDefault;
    }
}
