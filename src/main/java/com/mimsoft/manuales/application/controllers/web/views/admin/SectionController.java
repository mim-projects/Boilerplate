package com.mimsoft.manuales.application.controllers.web.views.admin;

import com.mimsoft.manuales.application.controllers.shared.RequestController;
import com.mimsoft.manuales.application.controllers.web.common.AbstractSessionController;
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

import static com.mimsoft.manuales.application.utils.others.StringHelper.CleanRemoveSpaces;

@Named("sectionCtrl")
@ViewScoped
public class SectionController extends AbstractSessionController {
    @Inject
    @RepositoryClass(Project.class)
    private Repository<Project> projectRepository;
    @Inject
    @RepositoryClass(Section.class)
    private Repository<Section> sectionRepository;
    @Inject
    private RequestController requestController;
    @Inject
    private DictionaryRepository dictionaryRepository;

    private Section current;
    private Project currentProject;
    private String currentName;
    private Dictionary[] nameArray;
    private Dictionary[] descriptionArray;
    private Dictionary[] metadataArray;

    @Override
    public void init() {
        String id = requestController.getParam("project");
        if (id == null) return;
        currentProject = projectRepository.findId(Integer.parseInt(id));
    }

    public void preEditSection(Section section) {
        current = section;
        nameArray = dictionaryRepository.findAllLikeKeyword(section.getNameKeyword()).toArray(new Dictionary[0]);
        descriptionArray = dictionaryRepository.findAllLikeKeyword(section.getDescriptionKeyword()).toArray(new Dictionary[0]);
        metadataArray = dictionaryRepository.findAllLikeKeyword(section.getMetadataKeyword()).toArray(new Dictionary[0]);
    }

    public void preCreateOrEdit(Section section) {
        if (section == null) {
            current = new Section();
            currentName = "";
        } else {
            current = section;
            currentName = current.getNameKeyword().replace("p" + currentProject.getId() + "sn_", "");
        }
    }

    public void create() {
        current.setProjectId(currentProject);
        current.setNameKeyword("p" + currentProject.getId() + "sn_" + CleanRemoveSpaces(currentName));
        current.setDescriptionKeyword("p" + currentProject.getId() + "sd_" + CleanRemoveSpaces(currentName));
        current.setContentKeyword("p" + currentProject.getId() + "sc_" + CleanRemoveSpaces(currentName));
        current.setMetadataKeyword("p" + currentProject.getId() + "sm_" + CleanRemoveSpaces(currentName));
        dictionaryRepository.createSectionDefaultValueDictionary(sectionRepository.create(current), "Empty");
        commonController.FacesMessagesInfo("Successful", "Create Section");
        init();
    }

    public void update() {
        current.setNameKeyword("p" + currentProject.getId() + "sn_" + CleanRemoveSpaces(currentName));
        current.setDescriptionKeyword("p" + currentProject.getId() + "sd_" + CleanRemoveSpaces(currentName));
        current.setContentKeyword("p" + currentProject.getId() + "sc_" + CleanRemoveSpaces(currentName));
        current.setMetadataKeyword("p" + currentProject.getId() + "sm_" + CleanRemoveSpaces(currentName));
        Section pre = sectionRepository.findId(current.getId());
        dictionaryRepository.updateSectionKeywordDictionary(sectionRepository.update(current), pre);
        commonController.FacesMessagesInfo("Successful", "Update Section");
        init();
    }

    public void updateName() {
        for (Dictionary name: nameArray) dictionaryRepository.update(name);
        commonController.FacesMessagesInfo("Successful", "Update Names");
        init();
    }

    public void updateDescription() {
        for (Dictionary name: descriptionArray) dictionaryRepository.update(name);
        commonController.FacesMessagesInfo("Successful", "Update Description");
        init();
    }

    public void updateMetadata() {
        for (Dictionary name: metadataArray) dictionaryRepository.update(name);
        commonController.FacesMessagesInfo("Successful", "Update Metadata");
        init();
    }

    public void navigateContent(Section section) {
        routerController.navigate(Routes.ADMIN_SECTION_CONTENT.getRoute(), "section=" + section.getId());
    }

    public void remove(Section section) {
        String[] keywords = new String[] {section.getNameKeyword(), section.getContentKeyword(), section.getMetadataKeyword(), section.getDescriptionKeyword()};
        sectionRepository.delete(section);
        dictionaryRepository.delete(keywords);
        commonController.FacesMessagesWarn("Successful", "Remove Section");
        init();
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

    public Section getCurrent() {
        return current;
    }

    public void setCurrent(Section current) {
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

    public Dictionary[] getDescriptionArray() {
        return descriptionArray;
    }

    public void setDescriptionArray(Dictionary[] descriptionArray) {
        this.descriptionArray = descriptionArray;
    }

    public Dictionary[] getMetadataArray() {
        return metadataArray;
    }

    public void setMetadataArray(Dictionary[] metadataArray) {
        this.metadataArray = metadataArray;
    }
}