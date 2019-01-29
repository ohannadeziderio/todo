package br.com.ohannadeziderio.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
public class TarefaController {

	@Autowired
	private ITarefaService tarefaService;
	private List<Tarefa> tarefas = new ArrayList<Tarefa>();
	private Tarefa tarefa = new Tarefa();

	@PostMapping("index")
	public String cadastrarTarefa() {
		tarefa.setAtiva(true);
		boolean cadastrou = tarefaService.cadastrarTarefa(tarefa);
		if (cadastrou == false) {
			return "index.xhtml?faces-redirect=true";
		}
		
		tarefa = new Tarefa();
		
		return "index.xhtml?faces-redirect=true";
	}

	@PutMapping("index/{tarefa}")
	public String atualizarTarefa(@RequestBody Tarefa tarefa) {
		tarefaService.atualizarTarefa(tarefa);
		return "index.xhtml?faces-redirect=true";
	}

	@DeleteMapping("index/{id}")
	public String deletarTarefa(@PathVariable("id") Integer id) {
		tarefaService.deletarTarefa(id);
		return "index.xhtml?faces-redirect=true";
	}
	
	@PostMapping()
	public String concluir(int id) {
		Tarefa tarefa = tarefaService.findById(id);
		
		if (tarefa.isAtiva()) {
			tarefa.setAtiva(false);
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
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Celula atualizada", "Antiga: " + oldValue + ", Nova:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

	/**
	 * @return the tarefas
	 */
	public List<Tarefa> getTarefas() {
		this.tarefas = tarefaService.listarTarefas();
		return tarefas;
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
	
	
	
}
