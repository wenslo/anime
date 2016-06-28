package org.wen.entity;

import java.util.Map;

/**
 * Created by wen on 2016/6/24.
 */
public class Menu {
    private String id;
    private String state;
    private String pid;
    private String text;
    private Map<String,Object> attributes;

    @Override
    public String toString() {
        return "Menu{" +
                "state='" + state + '\'' +
                ", pid='" + pid + '\'' +
                ", text='" + text + '\'' +
                ", arrtibutes=" + attributes +
                '}';
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
