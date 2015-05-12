
package eacad.controller;

import eacad.entidades.Evento;
import eacad.entidades.Usuario;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.EventoExistenteException;
import eacad.exceptions.EventoInexistenteException;
import eacad.fachada.FachadaSistema;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class BeanEvento implements Serializable{
  
    private Evento evento;
    private Usuario usuario;
    
       @EJB
    private FachadaSistema fachada;
       
    public BeanEvento(){
    this.evento=new Evento();
    }
    
    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    public String CadastrarEvento() throws ErroInternoException, EventoExistenteException{
        try {
            this.evento.setUsuario(BeanUsuario.getInstancia());
            
            this.fachada.adicionarEvento(evento);
            
        } catch (ErroInternoException e){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ocorreu um erro no sistema. Tente novamente." + e.getMessage()));
        }
        
        return "PaginaInicial.xhtml";
    }
       
}
