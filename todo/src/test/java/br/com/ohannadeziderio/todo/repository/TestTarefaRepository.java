/**
 * 
 */
package br.com.ohannadeziderio.todo.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ohannadeziderio.todo.Tarefa;
import br.com.ohannadeziderio.todo.TarefaRepository;
import junit.framework.TestCase;

/**
 * @author Ohanna Dezidério
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestTarefaRepository extends TestCase {
	@Autowired
	private TarefaRepository tarefaRepository;
	
	/**
	 * Teste para cadastro
	 */
	@Test
	public void testSalvar() {
		Tarefa tarefa = new Tarefa("Teste", true);
		tarefaRepository.save(tarefa);
		Assert.assertNotNull(tarefa.getId());
	}
	
	/**
	 * Teste para remoção
	 */
	@Test
	public void testApagar() {
		Tarefa tarefa = new Tarefa("Teste", true);
		tarefaRepository.save(tarefa);
		tarefaRepository.delete(tarefa);
		Assert.assertNull(tarefa);
	}
}
