package com.mimsoft.manuales.application.controllers.web.views;

import com.mimsoft.manuales.application.controllers.web.views.app.AppTemplateController;
import com.mimsoft.manuales.application.data.repositories.DictionaryRepository;
import com.mimsoft.manuales.domain.core.Repository;
import com.mimsoft.manuales.domain.core.RepositoryClass;
import com.mimsoft.manuales.domain.entities.Dictionary;
import com.mimsoft.manuales.domain.entities.Project;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("indexCtrl")
@ViewScoped
public class IndexController  implements Serializable {
    @Inject
    @RepositoryClass(Project.class)
    private Repository<Project> projectRepository;

    @PostConstruct
    public void init() {
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}