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

@Named("appProjectCtrl")
@ViewScoped
public class AppProjectController implements Serializable {
    @Inject
    @RepositoryClass(Project.class)
    private Repository<Project> projectRepository;
    @Inject
    @RepositoryClass(Section.class)
    private Repository<Section> sectionRepository;
    @Inject
    private RequestController requestController;
    @Inject
    private RouterController routerController;

    private Project currentProject;

    @PostConstruct
    public void init() {
        String id = requestController.getParam("project");
        if (id == null) return;
        currentProject = projectRepository.findId(Integer.parseInt(id));
    }

    public void navigationSection(Section section) {
        routerController.navigate(Routes.APP_SECTION.getRoute(), "section=" + section.getId());
    }

    public List<Section> getAllSection() {
        return sectionRepository.findWhere("projectId.id", "=", "'" + currentProject.getId() + "'");
    }

    public Project getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(Project currentProject) {
        this.currentProject = currentProject;
    }
}
