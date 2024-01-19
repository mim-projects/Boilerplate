package com.mimsoft.manuales.application.controllers.web.views.admin;

import com.mimsoft.manuales.application.controllers.web.common.AbstractSessionController;
import com.mimsoft.manuales.application.controllers.web.views.app.AppTemplateController;
import com.mimsoft.manuales.application.data.repositories.DictionaryRepository;
import com.mimsoft.manuales.domain.core.Repository;
import com.mimsoft.manuales.domain.core.RepositoryClass;
import com.mimsoft.manuales.domain.entities.Language;
import com.mimsoft.manuales.domain.entities.Project;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.*;

import java.util.List;

@Named("languageCtrl")
@ViewScoped
public class LanguageController extends AbstractSessionController {
    @Inject
    @RepositoryClass(Language.class)
    private Repository<Language> languageRepository;
    @Inject
    @RepositoryClass(Project.class)
    private Repository<Project> projectRepository;

    @Inject
    private AppTemplateController appTemplateController;

    @Inject
    private DictionaryRepository dictionaryRepository;

    private Language current;

    @Override
    public void init() {
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    public void preCreateOrEdit(Language language) {
        if (language == null) current = new Language();
        else current = language;
    }

    public void create() {
        if (current == null) {
            commonController.FacesMessagesError("Failed", "Language is null");
            return;
        }

        if (languageRepository.findOneWhere("name", "=", "'" + current.getName() + "'") != null) {
            commonController.FacesMessagesError("Failed", "Language Name exist");
            return;
        }

        Language result = languageRepository.create(current);
        if (result == null) {
            commonController.FacesMessagesError("Failed", "Failed Persistence");
            return;
        }

        int size = languageRepository.findAll().size();
        try {
            languageRepository.getUserTransaction().begin();
            if (size == 1) languageRepository.getEntityManager().createNativeQuery("insert into dictionary (language_id, keyword, value) (select " + result.getId() + " as language_id, keyword_temp.keyword, keyword_temp.keyword as value from keyword_temp)").executeUpdate();
            else languageRepository.getEntityManager().createNativeQuery("insert into dictionary (language_id, keyword, value) (select " + result.getId() + " as language_id, c.keyword, c.value from dictionary as c where language_id = (select d.language_id from dictionary as d group by d.language_id order by count(d.language_id) desc limit 1))").executeUpdate();
            languageRepository.getUserTransaction().commit();
        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | RollbackException e) {
            e.printStackTrace();
        }

        if (size == 1) appTemplateController.updateLanguage(result.getId());
        else appTemplateController.updateCurrentLanguage();
    }

    public void update() {
        if (current == null) {
            commonController.FacesMessagesError("Failed", "Language is null");
            return;
        }

        Language result = languageRepository.update(current);
        if (result == null) {
            commonController.FacesMessagesError("Failed", "Failed Persistence");
            return;
        }

        appTemplateController.updateCurrentLanguage();
    }

    public void remove(Language language) {
        if (languageRepository.findAll().size() == 1 && projectRepository.findAll().size() == 1) {
            commonController.FacesMessagesError("Failed", "You cannot remove the last language if there are projects");
            return;
        }
        dictionaryRepository.delete(language);
        languageRepository.delete(language);
        appTemplateController.updateCurrentLanguage();
        commonController.FacesMessagesWarn("Successful", "Remove Language");
    }

    public Language getCurrent() {
        return current;
    }

    public void setCurrent(Language current) {
        this.current = current;
    }
}
