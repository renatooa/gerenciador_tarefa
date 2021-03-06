package br.com.renato.pds.task.web.api.controller;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.renato.pds.task.web.api.dominio.Etiqueta;
import br.com.renato.pds.task.web.api.dominio.Tarefa;
import br.com.renato.pds.task.web.api.dto.Mensagem;
import br.com.renato.pds.task.web.api.dto.TarefaBag;
import br.com.renato.pds.task.web.api.modelo.CRUDController;

@Controller
@RequestMapping("/v1/tarefas")
public class TarefaController implements Serializable, CRUDController<Tarefa, TarefaBag> {

	private static final long serialVersionUID = 1L;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	@Override
	public Object incluir(@RequestBody Tarefa t) {

		if (t == null) {
			return Mensagem.builderError(400, "Necessário informar a tarefa");
		} else if (t.getNome() == null || t.getNome().isEmpty()) {
			return Mensagem.builderError(400, "Necessário informar o nome da tarefa");
		}

		t.setIdRandom();

		Tarefa.getTarefas().add(t);

		verificarInclusaoEtiquetas(t.getEtiquetas());

		return t;
	}

	private void verificarInclusaoEtiquetas(List<Etiqueta> etiquetas) {

		if (etiquetas != null && !etiquetas.isEmpty()) {

			etiquetas.forEach(etq -> {
				if (!Etiqueta.isEtiquetasContem(etq.getId())) {
					etq.incluir();
				}
			});
		}
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	@Override
	public Object editar(@RequestBody Tarefa t) {

		if (t == null) {
			return Mensagem.builderError(400, "Necessário informar a tarefa");
		} else if (t.getNome() == null || t.getNome().isEmpty()) {
			return Mensagem.builderError(400, "Necessário informar o nome da tarefa");
		}

		Object object = get(t.getId());

		if (object instanceof Tarefa) {

			Tarefa.getTarefas().remove(object);
			Tarefa.getTarefas().add((Tarefa) t);

			verificarInclusaoEtiquetas(((Tarefa) t).getEtiquetas());
		}

		return t;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@Override
	public Object excluir(@PathVariable(value = "id") int id) {

		if (id == 0) {
			return Mensagem.builderError(400, "Necessário informar o id para exclusão");
		}

		try {

			Tarefa.getTarefas().removeIf(tarefa -> tarefa.getId() == id);

			return Mensagem.builder();

		} catch (Exception e) {
			return Mensagem.builderError(e);
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@Override
	public Object get(@PathVariable(value = "id") int id) {

		try {
			if (id == 0) {
				return Mensagem.builderError(400, "Necessário informar o id para pesquisa");
			}

			Tarefa tarefa = Tarefa.getTarefas().stream().filter(task -> task.getId() == id).findFirst().get();

			return tarefa;

		} catch (NoSuchElementException e) {
			return Mensagem.builderError(404, "Tarefa não encontrada");
		} catch (Exception ex) {
			return Mensagem.builderError(ex);
		}
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@Override
	public TarefaBag getAll() {
		return new TarefaBag(Tarefa.getTarefas(), 100, 0);
	}
}
