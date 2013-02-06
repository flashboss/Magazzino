package it.vige.magazzino;

import it.vige.magazzino.model.Data;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class FileUpload implements DataContainer {

	private static final long serialVersionUID = -4260367986164333712L;
	private static final Logger log = LoggerFactory.getLogger(FileUpload.class);

	public static final int MAX_FILES_AVAILABLE = 1;
	public static final String FILE_DIR = "/tmp/fileUpload/";

	private String fileTypesAllowed = "jpg,gif,bmp";

	private String fileName;

	@PostConstruct
	public void init() {

	}

	public void paint(OutputStream stream, Object object) throws IOException {

		stream.write(getFiles().get((Integer) object).getData());
		stream.close();
	}

	public void listener(FileUploadEvent event) throws Exception {
		UploadedFile item = event.getUploadedFile();
		log.debug("File info: " + item.getName() + ","
				+ item.getParameterName());
		Data file = new Data();
		file.setCodeData(new Date().toString());
		file.setLength(item.getData().length);
		file.setName(item.getName());
		file.setData(item.getData());
		getFiles().add(file);
		log.debug("Added File: " + file.getName());
	}

	public void clearUploadData() {
		Iterator<Data> i = getFiles().iterator();
		while (i.hasNext()) {
			Data file = i.next();
			if (file.getName().equals(this.fileName)) {
				i.remove();
				break;
			}
		}
	}

	public void clearAllUploadData() {
		getFiles().removeAll(getFiles());
	}

	public int getSize() {

		if (getFiles().size() > 0) {
			return getFiles().size();
		} else {
			return 0;
		}
	}

	public long getTimeStamp() {
		return System.currentTimeMillis();
	}

	public abstract List<Data> getFiles();

	public abstract void setFiles(List<Data> files);

	public int getUploadsAvailable() {
		return MAX_FILES_AVAILABLE;
	}

	public String getFileTypesAllowed() {
		return fileTypesAllowed;
	}

	public void setFileTypesAllowed(String fileTypesAllowed) {
		this.fileTypesAllowed = fileTypesAllowed;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	protected void createFacesMessage(Severity severity, String clientId,
			String summary, String detail) {
		if (severity == null) {
			severity = FacesMessage.SEVERITY_INFO;
		}

		FacesContext.getCurrentInstance().addMessage(clientId,
				new FacesMessage(severity, summary, detail));
	}

}
