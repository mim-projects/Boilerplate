package com.mimsoft.manuales.application.controllers.web.views.admin;

import com.mimsoft.manuales.application.controllers.web.common.AbstractSessionController;
import com.mimsoft.manuales.application.controllers.web.common.RouterController;
import com.mimsoft.manuales.application.controllers.web.common.SessionController;
import com.mimsoft.manuales.application.routes.Route;
import com.mimsoft.manuales.application.routes.Routes;
import com.mimsoft.manuales.domain.core.Repository;
import com.mimsoft.manuales.domain.core.RepositoryClass;
import com.mimsoft.manuales.domain.entities.Users;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("loginCtrl")
@ViewScoped
public class LoginController implements Serializable {
    @Inject
    private SessionController sessionController;
    @Inject
    private RouterController routerController;
    @Inject
    @RepositoryClass(Users.class)
    private Repository<Users> usersRepository;

    private String username;
    private String password;

    @PostConstruct()
    public void init() {
        if (sessionController.isActive()) {
            routerController.navigate(Routes.ADMIN_HOME.getRoute());
        }
    }

    public void login() {
        Users user = usersRepository.findOneWhereEqual(
                new String[]{"username", "password"},
                new Object[]{"'" + username + "'", "'" + password + "'"}
        );

        if (user == null) {
            System.out.println("xxxxxxxxxxxxxxxx");
            return;
        }

        sessionController.setCurrentUser(user);
        init();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
