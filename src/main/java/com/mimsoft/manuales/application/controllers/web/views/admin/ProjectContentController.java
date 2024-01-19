package com.mimsoft.manuales.application.controllers.web.views.admin;

import com.mimsoft.manuales.application.controllers.shared.RequestController;
import com.mimsoft.manuales.application.controllers.web.common.AbstractSessionController;
import com.mimsoft.manuales.application.data.repositories.DictionaryRepository;
import com.mimsoft.manuales.domain.core.Repository;
import com.mimsoft.manuales.domain.core.RepositoryClass;
import com.mimsoft.manuales.domain.entities.Dictionary;
import com.mimsoft.manuales.domain.entities.Project;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("projectContentCtrl")
@ViewScoped
public class ProjectContentController extends AbstractSessionController {
    @Inject
    @RepositoryClass(Project.class)
    private Repository<Project> projectRepository;
    @Inject
    private RequestController requestController;
    @Inject
    private DictionaryRepository dictionaryRepository;

    private Project currentProject;
    private Dictionary[] contentArray;
    private Integer currentIndex;

    @Override
    public void init() {
        String id = requestController.getParam("project");
        if (id == null) return;
        currentIndex = 0;
        currentProject = projectRepository.findId(Integer.parseInt(id));
        contentArray = dictionaryRepository.findAllLikeKeyword(currentProject.getContentKeyword()).toArray(new Dictionary[0]);
    }

    public void save() {
        for (Dictionary item: contentArray) dictionaryRepository.update(item);
        commonController.FacesMessagesInfo("Successful", "Save Values");
    }

    public void updateIndex(Integer index) {
        currentIndex = index;
    }

    public Project getCurrentProject() {
        return currentProject;
    }

    public Dictionary[] getContentArray() {
        return contentArray;
    }

    public void setContentArray(Dictionary[] contentArray) {
        this.contentArray = contentArray;
    }

    public Integer getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(Integer currentIndex) {
        this.currentIndex = currentIndex;
    }
}
