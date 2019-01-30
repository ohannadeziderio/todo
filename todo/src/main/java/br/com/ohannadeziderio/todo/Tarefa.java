/**
 * 
 */
package br.com.ohannadeziderio.todo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Ohanna Dezidério
 *
 */
@Entity
public class Tarefa implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String titulo;
	private boolean ativa;
	
	public Tarefa() {
		
	}
	
	/**
	 * @param titulo
	 * @param ativa
	 */
	public Tarefa(String titulo, boolean ativa) {
		super();
		this.titulo = titulo;
		this.ativa = ativa;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
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
