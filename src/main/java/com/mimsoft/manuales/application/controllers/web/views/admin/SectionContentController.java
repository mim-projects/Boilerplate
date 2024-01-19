package com.mimsoft.manuales.application.controllers.web.views.admin;

import com.mimsoft.manuales.application.controllers.shared.RequestController;
import com.mimsoft.manuales.application.controllers.web.common.AbstractSessionController;
import com.mimsoft.manuales.application.data.repositories.DictionaryRepository;
import com.mimsoft.manuales.domain.core.Repository;
import com.mimsoft.manuales.domain.core.RepositoryClass;
import com.mimsoft.manuales.domain.entities.Dictionary;
import com.mimsoft.manuales.domain.entities.Section;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

@Named("sectionContentCtrl")
@ViewScoped
public class SectionContentController extends AbstractSessionController {
    @Inject
    @RepositoryClass(Section.class)
    private Repository<Section> sectionRepository;
    @Inject
    private RequestController requestController;
    @Inject
    private DictionaryRepository dictionaryRepository;

    private Section currentSection;
    private Dictionary[] contentArray;
    private Integer currentIndex;

    @Override
    public void init() {
        String id = requestController.getParam("section");
        if (id == null) return;
        currentIndex = 0;
        currentSection = sectionRepository.findId(Integer.parseInt(id));
        contentArray = dictionaryRepository.findAllLikeKeyword(currentSection.getContentKeyword()).toArray(new Dictionary[0]);
    }

    public void save() {
        for (Dictionary item: contentArray) dictionaryRepository.update(item);
        commonController.FacesMessagesInfo("Successful", "Save Values");
    }

    public void updateIndex(Integer index) {
        currentIndex = index;
    }

    public Section getCurrentSection() {
        return currentSection;
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
