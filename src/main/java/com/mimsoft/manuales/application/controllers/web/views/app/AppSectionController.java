package com.mimsoft.manuales.application.controllers.web.views.app;

import com.mimsoft.manuales.application.controllers.shared.RequestController;
import com.mimsoft.manuales.application.controllers.web.common.RouterController;
import com.mimsoft.manuales.application.data.repositories.DictionaryRepository;
import com.mimsoft.manuales.application.routes.Routes;
import com.mimsoft.manuales.domain.core.Repository;
import com.mimsoft.manuales.domain.core.RepositoryClass;
import com.mimsoft.manuales.domain.entities.Dictionary;
import com.mimsoft.manuales.domain.entities.Project;
import com.mimsoft.manuales.domain.entities.Section;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("appSectionCtrl")
@ViewScoped
public class AppSectionController implements Serializable {
    @Inject
    @RepositoryClass(Section.class)
    private Repository<Section> sectionRepository;
    @Inject
    private RequestController requestController;
    @Inject
    private RouterController routerController;

    private Section currentSection;

    @PostConstruct
    public void init() {
        String id = requestController.getParam("section");
        if (id == null) return;
        currentSection = sectionRepository.findId(Integer.parseInt(id));
    }

    public void navigationBack() {
        routerController.navigate(Routes.APP_PROJECT.getRoute(), "project=" + currentSection.getProjectId().getId());
    }

    public Section getCurrentSection() {
        return currentSection;
    }

    public void setCurrentSection(Section currentSection) {
        this.currentSection = currentSection;
    }
}