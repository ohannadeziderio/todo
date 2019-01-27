/**
 * 
 */
package br.com.ohannadeziderio.todo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Ohanna Dezidério
 *
 */
@Entity
public class Tarefa {
	
	@Id
	@GeneratedValue
	private int id;
	private String titulo;
	private boolean ativa;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * @return the ativa
	 */
	public boolean isAtiva() {
		return ativa;
	}
	/**
	 * @param ativa the ativa to set
	 */
	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}
	
}
