/**
 * 
 */
package br.com.ohannadeziderio.todo;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author Ohanna Dezidério
 *
 */
@Service
public interface ITarefaService {

	boolean cadastrarTarefa(Tarefa tarefa);

	List<Tarefa> listarTarefas();

	void atualizarTarefa(Tarefa tarefa);

	boolean deletarTarefa(int id);
	
	Tarefa findById(int id);
	
	Tarefa findByTitulo(String titulo);

}
