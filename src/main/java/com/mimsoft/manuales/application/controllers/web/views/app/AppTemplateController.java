package com.mimsoft.manuales.application.controllers.web.views.app;

import com.mimsoft.manuales.application.controllers.shared.RequestController;
import com.mimsoft.manuales.application.data.repositories.DictionaryRepository;
import com.mimsoft.manuales.domain.core.Repository;
import com.mimsoft.manuales.domain.core.RepositoryClass;
import com.mimsoft.manuales.domain.entities.Dictionary;
import com.mimsoft.manuales.domain.entities.Language;
import com.mimsoft.manuales.domain.entities.Project;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named("appTemplateCtrl")
@SessionScoped
public class AppTemplateController implements Serializable {
    @Inject
    @RepositoryClass(Language.class)
    private Repository<Language> languageRepository;
    @Inject
    @RepositoryClass(Project.class)
    private Repository<Project> projectRepository;
    @Inject
    private DictionaryRepository dictionaryRepository;
    @Inject
    private RequestController requestController;

    private Language current;
    private String search;

    @PostConstruct
    public void init() {
        if (current == null) {
            List<Language> list = getAllLanguage();
            if (!list.isEmpty() && list.get(0) != null) current = list.get(0);
        }
    }

    public void searchEvent() {
        System.out.println(search);
    }

    public void navigateRouteProject(Project project) throws IOException {
        String route = requestController.getContext() + "/app/project.xhtml?project=" + project.getId();
        FacesContext.getCurrentInstance().getExternalContext().redirect(route);
    }

    public String getKeyword(String keyword) {
        if (current == null || keyword == null || keyword.isEmpty()) return "";
        Dictionary dictionary = dictionaryRepository.findOneWhereLanguageAndKeyword(current, keyword);
        return dictionary == null ?"" :dictionary.getValue();
    }

    public MenuModel getMenuButtonModel() {
        MenuModel menuModel = new DefaultMenuModel();
        DefaultSubMenu submenu = new DefaultSubMenu();
        submenu.setIcon(current == null ?"pi pi-globe" :current.getIcon());
        submenu.setStyle("text-transform: uppercase");
        submenu.setLabel(getCurrentLanguageStr());

        for (Language current: getAllLanguage()) {
            DefaultMenuItem item = new DefaultMenuItem();
            item.setValue(current.getName());
            item.setIcon(current.getIcon());
            item.setCommand("#{appTemplateCtrl.updateLanguage(" + current.getId() + ")}");
            submenu.getElements().add(item);
        }

        menuModel.getElements().add(submenu);
        menuModel.generateUniqueIds();
        return menuModel;
    }

    public String getCurrentLanguageStr() {
        if (current == null) return "-";
        return current.getName();
    }

    public List<Project> getAllProject() {
        return projectRepository.findAll();
    }

    public List<Language> getAllLanguage() {
        return languageRepository.findAll();
    }

    public void updateLanguage(Integer languageId) {
        Language temp = languageRepository.findId(languageId);
        if (temp != null) current = temp;
        PrimeFaces.current().executeScript("window.location.reload()");
    }

    public void updateCurrentLanguage() {
        if (current == null) return;
        current = languageRepository.findId(current.getId());
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
