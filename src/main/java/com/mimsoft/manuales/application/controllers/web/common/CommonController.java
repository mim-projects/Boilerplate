package com.mimsoft.manuales.application.controllers.web.common;

import com.mimsoft.manuales.Configuration;
import com.mimsoft.manuales.application.utils.others.IFileDownload;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import org.primefaces.PrimeFaces;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

@Named("commonCtrl")
@RequestScoped
public class CommonController {
    public String getServerPath() {
        return Configuration.SERVER_PATH;
    }

    public void FacesMessage(FacesMessage.Severity severity, String title, String details) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, details));
        PrimeFaces.current().ajax().update("messages");
    }

    public void FacesMessagesError(String title, String details) {
        FacesMessage(FacesMessage.SEVERITY_ERROR, title, details);
    }

    public void FacesMessagesInfo(String title, String details) {
        FacesMessage(FacesMessage.SEVERITY_INFO, title, details);
    }

    public void FacesMessagesWarn(String title, String details) {
        FacesMessage(FacesMessage.SEVERITY_WARN, title, details);
    }

    public void FileDownload(String filename, String contentType, IFileDownload fileDownload) {
        try {
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            fileDownload.onBody(byteArrayOutputStream);
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            response.setContentType(contentType);
            response.setContentLength(byteArrayOutputStream.size());
            OutputStream outputStream = response.getOutputStream();
            byteArrayOutputStream.writeTo(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FacesContext.getCurrentInstance().responseComplete();
    }
}
