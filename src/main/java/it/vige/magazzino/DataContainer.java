package it.vige.magazzino;

import it.vige.magazzino.model.Data;

import java.io.Serializable;
import java.util.ArrayList;

public interface DataContainer extends Serializable {

	ArrayList<Data> getFiles();

	void setFiles(ArrayList<Data> files);
}
