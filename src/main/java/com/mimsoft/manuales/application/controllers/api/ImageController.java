package com.mimsoft.manuales.application.controllers.api;

import com.mimsoft.manuales.application.providers.ImageProvider;
import com.mimsoft.manuales.application.utils.http.responses.ResponseHelper;
import com.mimsoft.manuales.domain.entities.Language;
import com.mimsoft.manuales.domain.entities.Project;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.File;

@Path("images")
public class ImageController {
    @Inject
    private ImageProvider imageProvider;

    @GET
    @Produces({"image/jpeg"})
    @Path("download/{filename}")
    public Response download(@PathParam("filename") String filename) {
        File file = imageProvider.downloadImage(filename);
        if (file == null) return ResponseHelper.Error(Response.Status.NOT_FOUND, "No se encontro el recurso");
        return ResponseHelper.DisableCORS(Response.ok(file))
                .header("Content-Disposition", "attachment; filename=" + filename)
                .header("Content-Type", "image/jpeg")
                .header("filename", filename).build();
    }

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response upload(MultipartFormDataInput form) {
        String url = imageProvider.uploadImage(form);
        if (url == null) return ResponseHelper.ErrorMessage(Response.Status.NOT_IMPLEMENTED, "Error al subir la imagen");
        else return ResponseHelper.SuccessMessage("url", url);
    }

    @POST
    @Path("upload/project/{projectId}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response upload(@PathParam("projectId") Integer projectId, MultipartFormDataInput form) {
        Project project = imageProvider.updateImageProfile(projectId, form);
        if (project == null) return ResponseHelper.ErrorMessage(Response.Status.NOT_IMPLEMENTED, "Error al subir la imagen");
        else return ResponseHelper.SuccessMessage("url", project.getIcon());
    }


    public void search(Language language, String searchString) {

    }
}