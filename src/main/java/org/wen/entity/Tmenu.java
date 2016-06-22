package org.wen.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 树列表实体
 */
public class Tmenu {
    private Integer id;
    private Tmenu tmenu;
    private String text;
    private String iconcls;
    private String url;
    private Set<Tmenu> tmenus = new HashSet<Tmenu>();
    public Tmenu(){}

    public Tmenu(Integer id, Tmenu tmenu, String text, String iconcls, String url, Set<Tmenu> tmenus) {
        this.id = id;
        this.tmenu = tmenu;
        this.text = text;
        this.iconcls = iconcls;
        this.url = url;
        this.tmenus = tmenus;
    }

    @Override
    public String toString() {
        return "Tmenu{" +
                "id=" + id +
                ", tmenu=" + tmenu +
                ", text='" + text + '\'' +
                ", iconcls='" + iconcls + '\'' +
                ", url='" + url + '\'' +
                ", tmenus=" + tmenus +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tmenu getTmenu() {
        return tmenu;
    }

    public void setTmenu(Tmenu tmenu) {
        this.tmenu = tmenu;
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

    public Set<Tmenu> getTmenus() {
        return tmenus;
    }

    public void setTmenus(Set<Tmenu> tmenus) {
        this.tmenus = tmenus;
    }
}
