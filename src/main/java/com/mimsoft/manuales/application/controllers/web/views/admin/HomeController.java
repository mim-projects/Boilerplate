package com.mimsoft.manuales.application.controllers.web.views.admin;

import com.mimsoft.manuales.application.controllers.web.common.AbstractSessionController;
import com.mimsoft.manuales.application.data.repositories.DictionaryRepository;
import com.mimsoft.manuales.domain.core.Repository;
import com.mimsoft.manuales.domain.core.RepositoryClass;
import com.mimsoft.manuales.domain.entities.Dictionary;
import com.mimsoft.manuales.domain.entities.Language;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named("homeCtrl")
@ViewScoped
public class HomeController extends AbstractSessionController {
    @Inject
    @RepositoryClass(Language.class)
    private Repository<Language> languageRepository;
    @Inject
    private DictionaryRepository dictionaryRepository;

    private Dictionary currentDictionary;

    @Override
    public void init() {

    }

    public void updateDictionary() {
        if (currentDictionary == null) return;
        dictionaryRepository.update(currentDictionary);
    }

    public Dictionary getKeywordValue(Language language, String keyword) {
        return dictionaryRepository.findOneWhereLanguageAndKeyword(language, keyword);
    }

    public void updateParams(Language language, String keyword) {
        currentDictionary = dictionaryRepository.findOneWhereLanguageAndKeyword(language, keyword);
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    public List<String> getAllKeywords() {
        return dictionaryRepository.findAllKeywords();
    }

    public Dictionary getCurrentDictionary() {
        return currentDictionary;
    }

    public void setCurrentDictionary(Dictionary currentDictionary) {
        this.currentDictionary = currentDictionary;
    }
}
