
package eacad.controller;

import eacad.entidades.Evento;
import eacad.entidades.Usuario;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.EventoExistenteException;
import eacad.exceptions.EventoInexistenteException;
import eacad.fachada.FachadaSistema;
import java.io.Serializable;
import java.util.List;
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
    
       public String CadastrarEvento() throws ErroInternoException, EventoExistenteException, EventoInexistenteException {
        try {
            this.fachada.adicionarEvento(evento);
            evento = new Evento();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cadastro efetuado com sucesso!"));
        } catch (ErroInternoException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ocorreu um erro no sistema. Tente novamente." + e.getMessage()));
            return null;
        } catch (EventoExistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Usuario Existente no sistema."));
            return null;
        }
        return "PaginaInicial.xhtml"/*Falta criar home page*/;

    }
       
    public List<Evento> listarTudoEvento() throws EventoExistenteException, EventoInexistenteException {
        try {
            List<Evento> eventos = this.fachada.listarTudoEvento();
            return eventos;
        } catch (ErroInternoException ex ) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
    }
    
}
