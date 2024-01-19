package com.mimsoft.manuales.domain.entities;

import com.mimsoft.manuales.domain.core.EntityCore;
import jakarta.persistence.*;

@Entity
@Table(name = "project")
public class Project extends EntityCore {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "icon")
    private String icon;

    @Column(name = "name_keyword")
    private String nameKeyword;

    @Column(name = "about_keyword")
    private String aboutKeyword;

    @Column(name = "content_keyword")
    private String contentKeyword;

    @Column(name = "visible_code")
    private String visibleCode;

    public Project() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNameKeyword() {
        return nameKeyword;
    }

    public void setNameKeyword(String nameKeyword) {
        this.nameKeyword = nameKeyword;
    }

    public String getAboutKeyword() {
        return aboutKeyword;
    }

    public void setAboutKeyword(String aboutKeyword) {
        this.aboutKeyword = aboutKeyword;
    }

    public String getContentKeyword() {
        return contentKeyword;
    }

    public void setContentKeyword(String contentKeyword) {
        this.contentKeyword = contentKeyword;
    }

    public String getVisibleCode() {
        return visibleCode;
    }

    public void setVisibleCode(String visibleCode) {
        this.visibleCode = visibleCode;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                ", nameKeyword='" + nameKeyword + '\'' +
                ", aboutKeyword='" + aboutKeyword + '\'' +
                ", contentKeyword='" + contentKeyword + '\'' +
                ", visibleCode='" + visibleCode + '\'' +
                '}';
    }
}
