package com.mimsoft.manuales.domain.entities;

import com.mimsoft.manuales.domain.core.EntityCore;
import jakarta.persistence.*;

@Entity
@Table(name = "dictionary")
public class Dictionary extends EntityCore {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @JoinColumn(name = "language_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Language languageId;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "value")
    private String value;

    public Dictionary() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Language getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Language languageId) {
        this.languageId = languageId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "id=" + id +
                ", languageId=" + languageId +
                ", keyword='" + keyword + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
