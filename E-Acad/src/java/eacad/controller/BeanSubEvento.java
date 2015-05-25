/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.controller;


import eacad.entidades.Evento;
import eacad.entidades.SubEvento;
import eacad.exceptions.DatasIncorretas;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.EventoInexistenteException;
import eacad.exceptions.SubEventoExistenteException;
import eacad.exceptions.SubEventoInexistenteException;
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
public class BeanSubEvento implements Serializable{
    
    private SubEvento subEvento;
    private Evento evento;
    private SubEvento subEventoSelecionado;
    
     @EJB
    private FachadaSistema fachada;
     
     public BeanSubEvento(){
    this.subEvento = new SubEvento();
    }

    public SubEvento getSubEvento() {
        return subEvento;
    }

    public void setSubEvento(SubEvento subEvento) {
        this.subEvento = subEvento;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public SubEvento getSubEventoSelecionado() {
        return subEventoSelecionado;
    }

    public void setSubEventoSelecionado(SubEvento subEventoSelecionado) {
        this.subEventoSelecionado = subEventoSelecionado;
    }
    
    
    
    public String CadastrarSubEvento() throws ErroInternoException, DatasIncorretas, SubEventoExistenteException {
        try {
            this.subEvento.setEventoPai(evento);
            this.fachada.adicionarSubEvento(subEvento);
            
           this.subEvento = new SubEvento();
            
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cadastro efetuado com sucesso!"));
        } catch (ErroInternoException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ocorreu um erro no sistema. Tente novamente." + e.getMessage()));
            return null;
        } catch (DatasIncorretas ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Datas Incorretas."));
            return null;
        }
        return "meusEventos.xhtml";
    }
    
    public List<SubEvento> listarTudoSubEvento() throws SubEventoExistenteException, SubEventoInexistenteException {
        try {
            List<SubEvento> subEventos = this.fachada.listarTudoSubEvento();
            return subEventos;
        } catch (ErroInternoException ex ) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
    }
    
    public List<SubEvento> buscarListSubEvento() throws SubEventoExistenteException, SubEventoInexistenteException {
        try {
            List<SubEvento> subEventos = this.fachada.buscarListSubEvento(evento);
           
            return subEventos;
            
        } catch (ErroInternoException ex ) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
    }

    public String apagarSubEvento(long codigo) {
        try {
            this.fachada.removerSubEvento(codigo);
            
            FacesContext aviso = FacesContext.getCurrentInstance();
            aviso.addMessage(null, new FacesMessage("SubEvento removido!"));
        } catch (ErroInternoException | SubEventoInexistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
        }

        return "PaginaInicial.xhtml";
    }

    public String atualizarSubEvento() {
        try {
            this.fachada.atualizarSubEvento(subEventoSelecionado);
            
            FacesContext aviso = FacesContext.getCurrentInstance();
            aviso.addMessage(null, new FacesMessage("SubEvento Atualizado!"));
        } catch (ErroInternoException | SubEventoInexistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
        }

        return "meusSubEventos.xhtml";
    }
    
   
     public String SubEventoSelect(long codigo) throws SubEventoInexistenteException {
        try {
            SubEvento e = this.fachada.buscarCodigoSubEvento(codigo);
            this.subEventoSelecionado = e;
            FacesContext aviso = FacesContext.getCurrentInstance();
            aviso.addMessage(null, new FacesMessage("SubEvento Selecionado!"));
        } catch (ErroInternoException | SubEventoInexistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
        }

        return "meusEventos.xhtml";
    }

      public String EventoSelect(long codigo) throws EventoInexistenteException {
        try {
            Evento e = this.fachada.buscarCodigoEvento(codigo);
            this.evento = e;
            FacesContext aviso = FacesContext.getCurrentInstance();
            aviso.addMessage(null, new FacesMessage("Evento Selecionado!"));
        } catch (ErroInternoException | EventoInexistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
        }

        return "meusEventos.xhtml";
    }
    
     
    
}
