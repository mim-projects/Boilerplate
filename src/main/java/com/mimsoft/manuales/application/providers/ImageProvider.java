package com.mimsoft.manuales.application.providers;

import com.mimsoft.manuales.application.utils.http.requests.MultipartForm;
import com.mimsoft.manuales.application.utils.system.FileManager;
import com.mimsoft.manuales.domain.core.Repository;
import com.mimsoft.manuales.domain.core.RepositoryClass;
import com.mimsoft.manuales.domain.entities.Project;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.File;

import static com.mimsoft.manuales.Configuration.ROOT_PATH_FILE_SERVER;
import static com.mimsoft.manuales.Configuration.SERVER_PATH;

@RequestScoped
public class ImageProvider {
    @Inject
    @RepositoryClass(Project.class)
    private Repository<Project> projectRepository;

    public Project updateImageProfile(Integer projectId, MultipartFormDataInput form) {
        if (projectId == null) return null;
        Project project = projectRepository.findId(projectId);
        if (project == null) return null;
        String url = uploadImage(form);
        project.setIcon(url);
        return projectRepository.update(project);
    }

    public File downloadImage(String filename) {
        File file = new File(ROOT_PATH_FILE_SERVER + filename);
        return (!file.exists()) ? null :file;
    }

    public String uploadImage(MultipartFormDataInput form) {
        byte[] bytes = MultipartForm.getBytes(form, "upload");
        String filename = "IMG_" + System.currentTimeMillis() + ".JPEG";
        String path = ROOT_PATH_FILE_SERVER + filename;

        boolean createFile;
        try { createFile = FileManager.Write(bytes, path); }
        catch (Exception e) { createFile = false; }

        if (createFile) return SERVER_PATH + "/api/images/download/" + filename;
        return null;
    }
}