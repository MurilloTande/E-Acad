
package eacad.controller;

import eacad.entidades.Evento;
import eacad.entidades.SubEvento;
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
    private SubEvento subEvento;
    private Evento eventoSelecionado;

    public Evento getEventoSelecionado() {
        return eventoSelecionado;
    }

    public void setEventoSelecionado(Evento eventoSelecionado) {
        this.eventoSelecionado = eventoSelecionado;
    }
    
    
       @EJB
    private FachadaSistema fachada;
       
    public BeanEvento(){
    this.evento=new Evento();
    this.subEvento=new SubEvento();
    }
    
    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public SubEvento getSubEvento() {
        return subEvento;
    }

    public void setSubEvento(SubEvento subEvento) {
        this.subEvento = subEvento;
    }
    
    
       public String CadastrarEvento() throws ErroInternoException, EventoExistenteException, EventoInexistenteException {
        try {
            evento.setCriador(BeanUsuario.getInstancia());
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
                    new FacesMessage("Evento Existente no sistema."));
            return null;
        }
        return "PaginaInicial.xhtml";

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
