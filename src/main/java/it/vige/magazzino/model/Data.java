package it.vige.magazzino.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.jboss.seam.solder.core.Veto;

@Entity
@Table(name = "image")
@Veto
public class Data implements Serializable {
	private static final long serialVersionUID = -3015533027717027819L;
	@Id
	@NotNull
	private String codeData;
	private String name;
	private String mime;
	private long length;
	@Column(length = 100000)
	private byte[] data;
	private String description;
	@ManyToOne
	@JoinColumn(name = "codeCustomer")
	private Customer customer;
	@ManyToOne
	@JoinColumn(name = "codeJar")
	private Magazzino jar;

	public String getCodeData() {
		return codeData;
	}

	public void setCodeData(String codeData) {
		this.codeData = codeData;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Magazzino getJar() {
		return jar;
	}

	public void setJar(Magazzino jar) {
		this.jar = jar;
	}
}
