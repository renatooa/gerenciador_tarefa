package br.com.renato.pds.task.web.api.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Etiqueta implements Serializable {

	private static final long serialVersionUID = 1L;
	private static ArrayList<Etiqueta> etiquetas = null;

	private int id = 0;
	private String descricao = null;

	public Etiqueta() {
	}

	public Etiqueta(int id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public void incluir() {
		setIdRandom();
		getEtiquetas().add(this);
	}

	public void setIdRandom() {
		Random random = new Random();
		setId(random.nextInt());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static List<Etiqueta> getEtiquetas() {
		if (etiquetas == null) {
			etiquetas = new ArrayList<Etiqueta>();

			etiquetas.add(new Etiqueta(1, "BATCH"));
			etiquetas.add(new Etiqueta(2, "IMERSÃO"));
			etiquetas.add(new Etiqueta(3, "EM_ESPERA"));
		}
		return etiquetas;
	}

	public static boolean isEtiquetasContem(int id) {
		try {
			return getEtiquetas().stream().filter(etq -> etq.getId() == id).count() > 0;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
