package br.com.renato.pds.task.web.api.dominio;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Tarefa implements Serializable {

	private static final long serialVersionUID = 1L;
	private static ArrayList<Tarefa> tarefas = null;

	private int id = 0;

	@NotNull
	private String nome = null;

	private String descricao = null;

	private Prioridade prioridade = null;

	private String dataInicioDDMMAAAA = null;

	private String dataFimDDMMAAAA = null;

	@JsonIgnore
	private LocalDateTime dataInicio = null;

	@JsonIgnore
	private LocalDateTime dataFim = null;

	@XmlElementWrapper
	private List<Etiqueta> etiquetas = null;

	public Tarefa() {
	}

	public Tarefa(int id, String nome, String descricao, Prioridade prioridade, LocalDateTime dataInicio,
			LocalDateTime dataFim, List<Etiqueta> etiquetas) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.prioridade = prioridade;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.etiquetas = etiquetas;

		DateTimeFormatter formatador = getDateFormater();
		this.dataInicioDDMMAAAA = dataInicio.format(formatador);
		this.dataFimDDMMAAAA = dataFim.format(formatador);
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	public String getDataInicioDDMMAAAA() {
		return dataInicioDDMMAAAA;
	}

	public void setDataInicioDDMMAA(String dataInicioDDMMAAAA) {
		this.dataInicioDDMMAAAA = dataInicioDDMMAAAA;
		this.dataInicio = LocalDateTime.parse(dataInicioDDMMAAAA, getDateFormater());
	}

	public String getDataFimDDMMAAAA() {
		return dataFimDDMMAAAA;
	}

	public void setDataFimDDMMAA(String dataFimDDMMAAAA) {
		this.dataFimDDMMAAAA = dataFimDDMMAAAA;
		this.dataFim = LocalDateTime.parse(dataFimDDMMAAAA, getDateFormater());
	}

	public LocalDateTime getDataInicio() {
		if (dataInicio == null && dataInicioDDMMAAAA != null) {
			this.dataInicio = LocalDateTime.parse(dataInicioDDMMAAAA, getDateFormater());
		}
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		if (dataFim == null && dataFimDDMMAAAA != null) {
			this.dataFim = LocalDateTime.parse(dataFimDDMMAAAA, getDateFormater());
		}
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public List<Etiqueta> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(List<Etiqueta> etiquetas) {
		this.etiquetas = etiquetas;
	}

	private DateTimeFormatter getDateFormater() {
		return DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}

	public static List<Tarefa> getTarefas() {
		if (tarefas == null) {
			tarefas = new ArrayList<Tarefa>();

			for (int i = 1; i <= 15; i++) {

				Prioridade prioridade = (i % 5 == 0 ? Prioridade.ALTA
						: (i % 2 == 0 ? Prioridade.MEDIA : Prioridade.BAIXA));

				tarefas.add(new Tarefa(i, "Tarefa " + i, "Tarefa gerada automaticamente para teste id:" + i, prioridade,
						LocalDateTime.now(), LocalDateTime.now().plusDays(i), Etiqueta.getEtiquetas()));
			}
		}
		return tarefas;
	}
}
