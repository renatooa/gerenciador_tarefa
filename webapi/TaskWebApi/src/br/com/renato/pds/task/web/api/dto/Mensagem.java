package br.com.renato.pds.task.web.api.dto;

import java.io.Serializable;

public class Mensagem implements Serializable {

	private static final long serialVersionUID = 1L;
	private int codigo = 200;
	private String mensagemUsuario;
	private String mensagemDesenvovedor;
	private String urlDetalhesErro;

	public Mensagem() {
	}

	public Mensagem(int codigo, String mensagemUsuario, String mensagemDesenvovedor, String urlDetalhesErro) {
		super();
		this.codigo = codigo;
		this.mensagemUsuario = mensagemUsuario;
		this.mensagemDesenvovedor = mensagemDesenvovedor;
		this.urlDetalhesErro = urlDetalhesErro;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

	public void setMensagemUsuario(String mensagemUsuario) {
		this.mensagemUsuario = mensagemUsuario;
	}

	public String getMensagemDesenvovedor() {
		return mensagemDesenvovedor;
	}

	public void setMensagemDesenvovedor(String mensagemDesenvovedor) {
		this.mensagemDesenvovedor = mensagemDesenvovedor;
	}

	public String getUrlDetalhesErro() {
		return urlDetalhesErro;
	}

	public void setUrlDetalhesErro(String urlDetalhesErro) {
		this.urlDetalhesErro = urlDetalhesErro;
	}

	public static Mensagem builder() {
		return new Mensagem();
	}

	public static Mensagem builderError(int status, String mensagemUsuario) {
		return new Mensagem(status, mensagemUsuario, "Desenvolvedor faça algo a respeito " + mensagemUsuario,
				"https://fr.wikipedia.org/wiki/Liste_des_codes_HTTP");
	}

	public static Mensagem builderError(Exception exception) {
		return new Mensagem(500, "Servidor com problemas",
				"Desenvolvedor faça algo a respeito " + exception.getMessage(),
				"https://fr.wikipedia.org/wiki/Liste_des_codes_HTTP");
	}
}
