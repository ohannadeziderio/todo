/**
 * 
 */
package br.com.ohannadeziderio.todo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ohanna Dezidério
 *
 */
@Repository
public interface TarefaRepository extends CrudRepository<Tarefa, Integer> {
	List<Tarefa> findByTitulo(String titulo);
}
