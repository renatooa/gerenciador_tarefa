package br.com.renato.pds.task.web.api.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.renato.pds.task.web.api.dominio.Tarefa;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TarefaBag implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElementWrapper
	private List<Tarefa> tarefas = null;

	@XmlElementWrapper
	private HashMap<String, String> metadados = null;

	public TarefaBag() {
	}

	public TarefaBag(List<Tarefa> tarefas, int limit, int offSet) {
		super();
		this.tarefas = tarefas;
		metadados = new HashMap<String, String>();

		metadados.put("totalCount", Integer.toString(tarefas.size()));
		metadados.put("limit", Integer.toString(limit));
		metadados.put("offset", Integer.toString(offSet));
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public HashMap<String, String> getMetadados() {
		return metadados;
	}

	public void setMetadados(HashMap<String, String> metadados) {
		this.metadados = metadados;
	}
}
