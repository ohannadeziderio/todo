package br.com.ohannadeziderio.todo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ohanna Dezidério
 *
 */

@Controller(value="tarefaController")
@RequestMapping("index")
@Join(path = "/index", to = "/index.jsf")
public class TarefaController implements Serializable {

	@Autowired
	private ITarefaService tarefaService;
	private List<Tarefa> tarefas = new ArrayList<Tarefa>();
	private Tarefa tarefa = new Tarefa();

	public String cadastrarTarefa() {
		tarefa.setAtiva(true);
		boolean cadastrou = tarefaService.cadastrarTarefa(tarefa);
		if (cadastrou == false) {
			return "index.xhtml?faces-redirect=true";
		}
		
		tarefa = new Tarefa();
		
		return "index.xhtml?faces-redirect=true";
	}

	/*
	 * public void atualizarTarefa(@RequestBody String titulo, CellEditEvent event)
	 * { Object oldValue = event.getOldValue(); Object newValue =
	 * event.getNewValue();
	 * 
	 * if(newValue != null && !newValue.equals(oldValue)) {
	 * System.out.println("AQUI DE NOVO"); FacesMessage msg = new
	 * FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue +
	 * ", New:" + newValue); FacesContext.getCurrentInstance().addMessage(null,
	 * msg);
	 * 
	 * 
	 * } }
	 */

	public String deletarTarefa(@PathVariable("id") Integer id) {
		tarefaService.deletarTarefa(id);
		return "index.xhtml?faces-redirect=true";
	}
	
	public String concluirTarefa(int id) {
		Tarefa tarefa = tarefaService.findById(id);
		
		if (tarefa != null && tarefa.isAtiva()) {
			tarefa.setAtiva(false);
			tarefaService.atualizarTarefa(tarefa);
			
			return "index.xhtml?faces-redirect=true";
		}
		
		return "index.xhtml?faces-redirect=true";
	}
	
	public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Tarefa atualizada", ((Tarefa) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edição cancelada", ((Tarefa) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    /**
     * 
     * @param titulo
     * @param event
     * @return
     */
    public String onCellEdit(String titulo, CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
            Tarefa tarefa = tarefaService.findByTitulo(titulo);
            tarefaService.atualizarTarefa(tarefa);
            
            return "index.xhtml?faces-redirect=true";
        }
        
        return "index.xhtml?faces-redirect=true";
    }
     
	/**
	 * @return the tarefas
	 */
	public List<Tarefa> getTarefas() {
		this.tarefas = tarefaService.listarTarefas();
		return this.tarefas;
	}

	/**
	 * @param tarefas the tarefas to set
	 */
	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefaService.listarTarefas();
	}

	/**
	 * @return the tarefa
	 */
	public Tarefa getTarefa() {
		return tarefa;
	}

	/**
	 * @param tarefa the tarefa to set
	 */
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	/**
	 * Método responsável pelo retorno de uma String com o status da tarefa
	 * @param id
	 * @return
	 */
	public String retornarStatus(int id) {
		Tarefa tarefa = tarefaService.findById(id);
		String status = "";
		
		if(tarefa != null) {
			if(tarefa.isAtiva()) {
				status = "Em espera";
			} else {
				status = "Concluída";
			}
			return status;
		}
		
		return "index.xhtml?faces-redirect=true";
	}
	
}
