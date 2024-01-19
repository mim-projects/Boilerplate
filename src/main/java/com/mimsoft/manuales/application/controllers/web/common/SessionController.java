package com.mimsoft.manuales.application.controllers.web.common;

import com.mimsoft.manuales.application.controllers.shared.RequestController;
import com.mimsoft.manuales.domain.core.Repository;
import com.mimsoft.manuales.domain.core.RepositoryClass;
import com.mimsoft.manuales.domain.entities.Users;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.IOException;
import java.io.Serializable;

@Named("sessionCtrl")
@SessionScoped
public class SessionController implements Serializable {
    @Inject
    private RequestController requestController;
    @Inject
    @RepositoryClass(Users.class)
    private Repository<Users> usersRepository;

    private Users currentUser;

    public void validateSession() {
        if (!isActive()) logout();
    }

    public boolean isActive() {
        return currentUser != null;
    }

    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }

    public Users getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        try {
            currentUser = null;
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect(requestController.getContext() + "/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
