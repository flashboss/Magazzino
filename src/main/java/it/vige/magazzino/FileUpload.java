package it.vige.magazzino;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@SessionScoped
public class FileUpload implements Serializable {

    private static final long serialVersionUID = -4260367986164333712L;
    private static final Logger log = LoggerFactory.getLogger(FileUpload.class);

    public static final int MAX_FILES_AVAILABLE = 1;
    public static final String FILE_DIR = "/tmp/fileUpload/";

    private String fileTypesAllowed;

    private List<Data> files = new ArrayList<Data>();

    @PostConstruct
    public void init() {

    }

    public String save() {
        try {
            if(files != null) {
                for(Data file : files) {
                    if(file.getData() != null && StringUtils.isNotBlank(file.getName())) {
                        File tmpDir = new File(FILE_DIR);
                        String name;
                        if(StringUtils.contains(file.getName(), "\\")) {
                            name = StringUtils.substringAfterLast(file.getName(), "\\");
                        }
                        else {
                            name = file.getName();
                        }
                        File newFile = new File(tmpDir, name);
                        FileUtils.writeByteArrayToFile(newFile, file.getData());
                        createFacesMessage(null, null, "Saved " + name, null);
                    }
                }
            }
        }
        catch(Exception e) {
            log.error("Error while writing files", e);
            throw new RuntimeException(e);
        }
        files.clear();
        return "/view";
    }

    public void paint(OutputStream stream, Object object) throws IOException {

        stream.write(getFiles().get((Integer)object).getData());
        stream.close();
    }

    public void listener(FileUploadEvent event) throws Exception {
        UploadedFile item = event.getUploadedFile();
        log.debug("File info: " + item.getName() + "," + item.getParameterName());
        Data file = new Data();
        file.setLength(item.getData().length);
        file.setName(item.getName());
        file.setData(item.getData());
        files.add(file);
        log.debug("Added File: " + file.getName());
    }

    public String clearUploadData() {
        files.clear();
        return null;
    }

    public int getSize() {

        if(getFiles().size() > 0) {
            return getFiles().size();
        }
        else {
            return 0;
        }
    }

    public long getTimeStamp() {
        return System.currentTimeMillis();
    }

    public List<Data> getFiles() {
        return files;
    }

    public void setFiles(List<Data> files) {
        this.files = files;
    }

    public int getUploadsAvailable() {
        return MAX_FILES_AVAILABLE;
    }


    public String getFileTypesAllowed() {
        return fileTypesAllowed;
    }

    public void setFileTypesAllowed(String fileTypesAllowed) {
        this.fileTypesAllowed = fileTypesAllowed;
    }

    protected void createFacesMessage(Severity severity, String clientId, String summary, String detail) {
        if(severity == null) {
            severity = FacesMessage.SEVERITY_INFO;
        }

        FacesContext.getCurrentInstance().addMessage(
            clientId,
            new FacesMessage(severity, summary, detail));
    }

}
