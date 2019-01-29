/**
 * 
 */
package br.com.ohannadeziderio.todo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Ohanna Dezidério
 *
 */
@ManagedBean(name="editarView")
public class EditarView implements Serializable {
	private List<Tarefa> tarefas = new ArrayList<>();
	
	@Autowired
    private ITarefaService tarefaService;

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
		this.tarefas = tarefas;
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
}
