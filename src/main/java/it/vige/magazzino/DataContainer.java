package it.vige.magazzino;

import it.vige.magazzino.model.Data;

import java.io.Serializable;
import java.util.List;

public interface DataContainer extends Serializable {

	List<Data> getFiles();

	void setFiles(List<Data> files);
}
