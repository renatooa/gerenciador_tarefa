package br.com.renato.pds.task.web.api.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.renato.pds.task.web.api.dominio.Etiqueta;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EtiquetaBag implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElementWrapper
	private List<Etiqueta> etiquetas = null;

	@XmlElementWrapper
	private HashMap<String, String> metadados = null;

	public EtiquetaBag() {
	}

	public EtiquetaBag(List<Etiqueta> etiquetas, int limit, int offSet) {
		super();
		this.etiquetas = etiquetas;
		metadados = new HashMap<String, String>();

		metadados.put("totalCount", Integer.toString(etiquetas.size()));
		metadados.put("limit", Integer.toString(limit));
		metadados.put("offset", Integer.toString(offSet));
	}

	public List<Etiqueta> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(List<Etiqueta> etiquetas) {
		this.etiquetas = etiquetas;
	}

	public HashMap<String, String> getMetadados() {
		return metadados;
	}

	public void setMetadados(HashMap<String, String> metadados) {
		this.metadados = metadados;
	}
}
