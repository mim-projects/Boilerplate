package com.mimsoft.manuales.domain.entities;

import com.mimsoft.manuales.domain.core.EntityCore;
import jakarta.persistence.*;

@Entity
@Table(name = "section")
public class Section extends EntityCore {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name_keyword")
    private String nameKeyword;

    @Column(name = "description_keyword")
    private String descriptionKeyword;

    @Column(name = "content_keyword")
    private String contentKeyword;

    @Column(name = "metadata_keyword")
    private String metadataKeyword;

    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Project projectId;

    public Section() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameKeyword() {
        return nameKeyword;
    }

    public void setNameKeyword(String nameKeyword) {
        this.nameKeyword = nameKeyword;
    }

    public String getDescriptionKeyword() {
        return descriptionKeyword;
    }

    public void setDescriptionKeyword(String descriptionKeyword) {
        this.descriptionKeyword = descriptionKeyword;
    }

    public String getContentKeyword() {
        return contentKeyword;
    }

    public void setContentKeyword(String contentKeyword) {
        this.contentKeyword = contentKeyword;
    }

    public String getMetadataKeyword() {
        return metadataKeyword;
    }

    public void setMetadataKeyword(String metadataKeyword) {
        this.metadataKeyword = metadataKeyword;
    }

    public Project getProjectId() {
        return projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", nameKeyword='" + nameKeyword + '\'' +
                ", descriptionKeyword='" + descriptionKeyword + '\'' +
                ", contentKeyword='" + contentKeyword + '\'' +
                ", metadataKeyword='" + metadataKeyword + '\'' +
                ", projectId=" + projectId +
                '}';
    }
}
