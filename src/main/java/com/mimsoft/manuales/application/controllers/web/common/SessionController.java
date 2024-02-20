package com.mimsoft.manuales.application.controllers.web.common;

import com.mimsoft.manuales.application.controllers.shared.RequestController;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;

@Named("sessionCtrl")
@SessionScoped
public class SessionController implements Serializable {
    @Inject
    private RequestController requestController;

    public void validateSession() {
        if (!isActive()) logout();
    }

    public boolean isActive() {
        return true;
    }

    public void logout() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect(requestController.getContext() + "/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
