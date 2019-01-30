/**
 * 
 */
package br.com.ohannadeziderio.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ohanna Dezidério
 *
 */
@Service
public class TarefaService implements ITarefaService {
	@Autowired
	private TarefaRepository tarefaRepository;
	
	/**
	 * Método para cadastro de tarefa
	 */
	@Transactional
	public boolean cadastrarTarefa(Tarefa tarefa) {
		List<Tarefa> tarefas = tarefaRepository.findByTitulo(tarefa.getTitulo());
		if (tarefas.size() > 0) {
			return false;
		} else {
			tarefaRepository.save(tarefa);
			return true;
		}
	}
	
	/**
	 * Método para listar tarefas
	 */
	@Transactional
	public List<Tarefa> listarTarefas() {
		List<Tarefa> tarefas = new ArrayList<>();
		tarefas = (List<Tarefa>) tarefaRepository.findAll();
		return tarefas;
	}
	
	/**
	 * Método para atualização de tarefa
	 */
	@Transactional
	public void atualizarTarefa(Tarefa tarefa) {
		Tarefa tarefaCadastrada = tarefaRepository.findById(tarefa.getId()).orElse(null);
		
		if(tarefaCadastrada != null) {
			tarefaCadastrada.setTitulo(tarefa.getTitulo());
			tarefaCadastrada.setAtiva(tarefa.isAtiva());
			
			tarefaRepository.save(tarefa);
		}
	}

	/**
	 * Método para remoção a partir do id
	 */
	@Transactional
	public boolean deletarTarefa(int id) {
		Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
		
		if(tarefa != null) {
			tarefaRepository.delete(tarefa);
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Método para busca pelo id
	 */
	@Transactional
	public Tarefa findById(int id) {
		Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
		
		return tarefa;
	}
	
	/**
	 * Método para busca pelo titulo
	 */
	@Transactional
	public Tarefa findByTitulo(String titulo) {
		Tarefa tarefa = (Tarefa) tarefaRepository.findByTitulo(titulo);
		
		return tarefa;
	}
}
