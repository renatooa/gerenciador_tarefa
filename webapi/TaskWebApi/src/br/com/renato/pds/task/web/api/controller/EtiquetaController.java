package br.com.renato.pds.task.web.api.controller;

import java.io.Serializable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.renato.pds.task.web.api.dominio.Etiqueta;
import br.com.renato.pds.task.web.api.dto.TarefaBag;
import br.com.renato.pds.task.web.api.modelo.CRUDController;

@Controller
@RequestMapping("/v1/etiquetas")
public class EtiquetaController implements Serializable, CRUDController<Etiqueta, TarefaBag> {

	private static final long serialVersionUID = 1L;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	@Override
	public Object incluir(Etiqueta t) {

		return null;
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	@ResponseBody
	@Override
	public Object editar(Etiqueta t) {

		return null;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@Override
	public Object excluir(int id) {
		return null;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@Override
	public Object get(int id) {

		return null;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@Override
	public TarefaBag getAll() {
		return null;
	}
}
