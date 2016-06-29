package br.com.renato.pds.task.web.api.controller;

import java.io.Serializable;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.renato.pds.task.web.api.dominio.Etiqueta;
import br.com.renato.pds.task.web.api.dto.EtiquetaBag;
import br.com.renato.pds.task.web.api.dto.Mensagem;
import br.com.renato.pds.task.web.api.modelo.CRUDController;

@Controller
@RequestMapping("/v1/etiquetas")
public class EtiquetaController implements Serializable, CRUDController<Etiqueta, EtiquetaBag> {

	private static final long serialVersionUID = 1L;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	@Override
	public Object incluir(@RequestBody Etiqueta t) {

		if (t == null) {
			return Mensagem.builderError(400, "Necessário informar a Etiqueta");
		} else if (t.getDescricao() == null || t.getDescricao().isEmpty()) {
			return Mensagem.builderError(400, "Necessário informar a descrição da Etiqueta");
		}

		t.incluir();

		return t;
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	@ResponseBody
	@Override
	public Object editar(@RequestBody Etiqueta t) {

		if (t == null) {
			return Mensagem.builderError(400, "Necessário informar a Etiqueta");
		} else if (t.getDescricao() == null || t.getDescricao().isEmpty()) {
			return Mensagem.builderError(400, "Necessário informar a descrição da Etiqueta");
		}

		Object object = get(t.getId());

		if (object instanceof Etiqueta) {

			Etiqueta.getEtiquetas().remove(object);
			Etiqueta.getEtiquetas().add((Etiqueta) t);
		}

		return t;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@Override
	public Object excluir(@PathVariable(value = "id") int id) {

		if (id == 0) {
			return Mensagem.builderError(400, "Necessário informar o id para exclusão");
		}

		try {

			Etiqueta.getEtiquetas().removeIf(etq -> etq.getId() == id);

			return Mensagem.builder();

		} catch (Exception e) {
			return Mensagem.builderError(e);
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@Override
	public Object get(@PathVariable(value = "id") int id) {

		try {
			if (id == 0) {
				return Mensagem.builderError(400, "Necessário informar o id para pesquisa");
			}

			Etiqueta etiqueta = Etiqueta.getEtiquetas().stream().filter(etq -> etq.getId() == id).findFirst().get();

			return etiqueta;

		} catch (NoSuchElementException e) {
			return Mensagem.builderError(404, "Tarefa não encontrada");
		} catch (Exception ex) {
			return Mensagem.builderError(ex);
		}
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@Override
	public EtiquetaBag getAll() {
		return new EtiquetaBag(Etiqueta.getEtiquetas(), 100, 0);
	}
}
