package it.vige.magazzino.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.jboss.seam.solder.core.Veto;

@Entity
@Table(name = "image")
@Veto
public class Data implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private String mime;
	private long length;
	private byte[] data;
	private String description;

	@Id
	@NotNull
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {

		this.data = data;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
		int extDot = name.lastIndexOf('.');
		if (extDot > 0) {
			String extension = name.substring(extDot + 1);
			if ("bmp".equals(extension)) {
				mime = "image/bmp";
			} else if ("jpg".equals(extension)) {
				mime = "image/jpeg";
			} else if ("gif".equals(extension)) {
				mime = "image/gif";
			} else if ("png".equals(extension)) {
				mime = "image/png";
			} else {
				mime = "image/unknown";
			}
		}
	}

	public long getLength() {

		return length;
	}

	public void setLength(long length) {

		this.length = length;
	}

	public String getMime() {

		return mime;
	}

	public void setMime(String mime) {

		this.mime = mime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
