package br.com.renato.pds.task.web.api.modelo;

import java.io.Serializable;

public interface CRUDController<T extends Serializable, B> {

	public Object incluir(T t);

	public Object editar(T t);

	public Object excluir(int id);

	public B getAll();

	public Object get(int id);

}
