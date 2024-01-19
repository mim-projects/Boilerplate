package com.mimsoft.manuales.application.data.repositories;

import com.mimsoft.manuales.domain.core.Repository;
import com.mimsoft.manuales.domain.core.RepositoryClass;
import com.mimsoft.manuales.domain.entities.Dictionary;
import com.mimsoft.manuales.domain.entities.Language;
import com.mimsoft.manuales.domain.entities.Project;
import com.mimsoft.manuales.domain.entities.Section;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class DictionaryRepository {
    @Inject
    @RepositoryClass(Dictionary.class)
    private Repository<Dictionary> dictionaryRepository;
    @Inject
    @RepositoryClass(Language.class)
    private Repository<Language> languageRepository;

    public List<Dictionary> findAllLikeKeyword(String keyword) {
        return dictionaryRepository.findWhere("keyword", "like", "'" + keyword + "%'");
    }

    public List<String> findAllKeywords() {
        List<String> list = new ArrayList<>();
        String query = "select substring_index(group_concat(id), ',', -1) as id, substring_index(group_concat(language_id), ',', -1) as language_id, keyword, null as value from dictionary group by keyword";
        for (Dictionary item: dictionaryRepository.findNativeAll(query)) list.add(item.getKeyword());
        return list;
    }

    public Dictionary update(Dictionary item) {
        return dictionaryRepository.update(item);
    }

    public Dictionary findOneWhereLanguageAndKeyword(Language language, String keyword) {
        return dictionaryRepository.findOneWhere(
                new String[]{"languageId.id", "keyword"},
                new String[]{"=", "="},
                new Object[]{"'" + language.getId() + "'", "'" + keyword + "'"}
        );
    }

    public void createSectionDefaultValueDictionary(Section section, String value) {
        if (section == null) return;

        for (Language language: languageRepository.findAll()) {
            for (String item: new String[] {
                    section.getNameKeyword(),
                    section.getContentKeyword(),
                    section.getMetadataKeyword(),
                    section.getDescriptionKeyword()
            }) {
                if (findOneWhereLanguageAndKeyword(language, item) == null) {
                    Dictionary dictionary = new Dictionary();
                    dictionary.setLanguageId(language);
                    dictionary.setKeyword(item);
                    dictionary.setValue(value);
                    dictionaryRepository.create(dictionary);
                }
            }
        }
    }

    public void createProjectDefaultValueDictionary(Project project, String value) {
        if (project == null) return;

        for (Language language: languageRepository.findAll()) {
            for (String item : new String[]{project.getNameKeyword(), project.getAboutKeyword(), project.getContentKeyword()}) {
                if (findOneWhereLanguageAndKeyword(language, item) == null) {
                    Dictionary dictionary = new Dictionary();
                    dictionary.setLanguageId(language);
                    dictionary.setKeyword(item);
                    dictionary.setValue(value);
                    dictionaryRepository.create(dictionary);
                }
            }
        }
    }

    public void updateProjectKeywordDictionary(Project project, Project original) {
        if (project == null) return;

        for (Language language: languageRepository.findAll()) {
            String[] currentArr = new String[] {project.getNameKeyword(), project.getAboutKeyword(), project.getContentKeyword()};
            String[] originalArr = new String[] {original.getNameKeyword(), original.getAboutKeyword(), original.getContentKeyword()};

            for (int k = 0; k < currentArr.length; k++) {
                Dictionary current = findOneWhereLanguageAndKeyword(language, originalArr[k]);
                if (current != null) {
                    current.setKeyword(currentArr[k]);
                    dictionaryRepository.update(current);
                }
            }
        }
    }

    public void updateSectionKeywordDictionary(Section section, Section original) {
        if (section == null) return;

        for (Language language: languageRepository.findAll()) {
            for (String item: new String[] {
                    section.getNameKeyword(),
                    section.getDescriptionKeyword(),
                    section.getMetadataKeyword(),
                    section.getContentKeyword()
            }) {
                Dictionary current = findOneWhereLanguageAndKeyword(language, item);
                if (current != null) {
                    current.setKeyword(item);
                    dictionaryRepository.update(current);
                }
            }

            String[] currentArr = new String[] {
                    section.getNameKeyword(),
                    section.getDescriptionKeyword(),
                    section.getMetadataKeyword(),
                    section.getContentKeyword()
            };
            String[] originalArr = new String[] {
                    original.getNameKeyword(),
                    original.getDescriptionKeyword(),
                    original.getMetadataKeyword(),
                    original.getContentKeyword()
            };

            for (int k = 0; k < currentArr.length; k++) {
                Dictionary current = findOneWhereLanguageAndKeyword(language, originalArr[k]);
                if (current != null) {
                    current.setKeyword(currentArr[k]);
                    dictionaryRepository.update(current);
                }
            }
        }
    }

    public void delete(Language language) {
        for (Dictionary item: dictionaryRepository.findWhere("languageId.id", "=", "'" + language.getId() + "'")) {
            dictionaryRepository.delete(item);
        }
    }

    public void delete(String[] keywords) {
        for (String item: keywords) {
            for (Dictionary dictionary: dictionaryRepository.findWhere("keyword", "=", "'" + item + "'")) {
                dictionaryRepository.delete(dictionary);
            }
        }
    }
}
