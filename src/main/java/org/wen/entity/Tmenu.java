package org.wen.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 树列表实体
 */
public class Tmenu {
    private String id;
    private String pid;
    private String text;
    private String iconcls;
    private String url;
    public Tmenu(){}

    @Override
    public String toString() {
        return "Tmenu{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", text='" + text + '\'' +
                ", iconcls='" + iconcls + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIconcls() {
        return iconcls;
    }

    public void setIconcls(String iconcls) {
        this.iconcls = iconcls;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
