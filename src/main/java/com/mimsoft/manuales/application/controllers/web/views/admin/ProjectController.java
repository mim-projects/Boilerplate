package com.mimsoft.manuales.application.controllers.web.views.admin;

import com.mimsoft.manuales.application.controllers.web.common.AbstractSessionController;
import com.mimsoft.manuales.application.data.repositories.DictionaryRepository;
import com.mimsoft.manuales.application.routes.Routes;
import com.mimsoft.manuales.domain.core.Repository;
import com.mimsoft.manuales.domain.core.RepositoryClass;
import com.mimsoft.manuales.domain.entities.Dictionary;
import com.mimsoft.manuales.domain.entities.Language;
import com.mimsoft.manuales.domain.entities.Project;
import com.mimsoft.manuales.domain.entities.Section;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

import static com.mimsoft.manuales.application.utils.others.StringHelper.CleanRemoveSpaces;

@Named("projectCtrl")
@ViewScoped
public class ProjectController extends AbstractSessionController {
    @Inject
    @RepositoryClass(Project.class)
    private Repository<Project> projectRepository;
    @Inject
    @RepositoryClass(Section.class)
    private Repository<Section> sectionRepository;
    @Inject
    private DictionaryRepository dictionaryRepository;

    private Dictionary[] nameArray;
    private Dictionary[] aboutArray;
    private Project current;
    private String currentName;

    @Override
    public void init() {
        nameArray = dictionaryRepository.findAllLikeKeyword("project_name_%").toArray(new Dictionary[0]);
        aboutArray = dictionaryRepository.findAllLikeKeyword("project_about_%").toArray(new Dictionary[0]);
    }

    public void navigateContent(Project project) {
        routerController.navigate(Routes.ADMIN_PROJECT_CONTENT.getRoute(), "project=" + project.getId());
    }

    public void navigateSection(Project project) {
        routerController.navigate(Routes.ADMIN_SECTION.getRoute(), "project=" + project.getId());
    }

    public boolean renderOptionEditable(Integer index, boolean nameOrAbout) {
        if (current == null || index == null) return false;
        if (nameOrAbout) return nameArray[index].getKeyword().equalsIgnoreCase(current.getNameKeyword());
        else return aboutArray[index].getKeyword().equalsIgnoreCase(current.getAboutKeyword());
    }

    public void preCreateOrEdit(Project project) {
        if (project == null) {
            current = new Project();
            currentName = "";
        } else {
            current = project;
            currentName = current.getNameKeyword().replace("project_name_", "");
        }
    }

    public void create() {
        current.setNameKeyword("project_name_" + CleanRemoveSpaces(currentName));
        current.setAboutKeyword("project_about_" + CleanRemoveSpaces(currentName));
        current.setContentKeyword("project_content_" + CleanRemoveSpaces(currentName));
        dictionaryRepository.createProjectDefaultValueDictionary(projectRepository.create(current), "Empty");
        commonController.FacesMessagesInfo("Successful", "Create Project");
        init();
    }

    public void update() {
        current.setNameKeyword("project_name_" + CleanRemoveSpaces(currentName));
        current.setAboutKeyword("project_about_" + CleanRemoveSpaces(currentName));
        current.setContentKeyword("project_content_" + CleanRemoveSpaces(currentName));
        Project pre = projectRepository.findId(current.getId());
        dictionaryRepository.updateProjectKeywordDictionary(projectRepository.update(current), pre);
        commonController.FacesMessagesInfo("Successful", "Update Project");
        init();
    }

    public void remove(Project project) {
        for (Section item: sectionRepository.findWhere("projectId.id", "=", "'" + project.getId() + "'")) {
            String[] sectionDictionary = new String[]{ item.getContentKeyword(), item.getMetadataKeyword(), item.getNameKeyword(), item.getDescriptionKeyword() };
            sectionRepository.delete(item);
            dictionaryRepository.delete(sectionDictionary);
        }
        String[] projectDictionary = new String[]{ project.getContentKeyword(), project.getAboutKeyword(), project.getNameKeyword() };
        projectRepository.delete(project);
        dictionaryRepository.delete(projectDictionary);
        commonController.FacesMessagesWarn("Successful", "Remove Project");
        init();
    }

    public void preUpdatePropertyProject(Project project) {
        current = project;
    }

    public void updateName() {
        for (Dictionary name: nameArray) dictionaryRepository.update(name);
        init();
    }

    public void updateAbout() {
        for (Dictionary name: aboutArray) dictionaryRepository.update(name);
        init();
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getCurrent() {
        return current;
    }

    public void setCurrent(Project current) {
        this.current = current;
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;

    }

    public Dictionary[] getNameArray() {
        return nameArray;
    }

    public void setNameArray(Dictionary[] nameArray) {
        this.nameArray = nameArray;
    }

    public Dictionary[] getAboutArray() {
        return aboutArray;
    }

    public void setAboutArray(Dictionary[] aboutArray) {
        this.aboutArray = aboutArray;
    }
}
