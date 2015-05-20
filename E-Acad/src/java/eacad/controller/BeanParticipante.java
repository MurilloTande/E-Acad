/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.controller;

import eacad.entidades.Evento;
import eacad.entidades.Participante;
import eacad.entidades.SubEvento;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.ParticipanteExistenteException;
import eacad.exceptions.ParticipanteInexistenteException;
import eacad.fachada.FachadaSistema;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class BeanParticipante implements Serializable{
    
    private Participante participante;
    private Evento Evento;
    private List<SubEvento> subEvento;
    
    @EJB
    private FachadaSistema fachada;

    public BeanParticipante() {
        this.participante = new Participante();
        this.Evento = new Evento();
        this.subEvento = new ArrayList<>();
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Evento getEvento() {
        return Evento;
    }

    public void setEvento(Evento Evento) {
        this.Evento = Evento;
    }

    public List<SubEvento> getSubEvento() {
        return subEvento;
    }

    public void setSubEvento(List<SubEvento> subEvento) {
        this.subEvento = subEvento;
    }
    
    public String CadastrarParticipante() throws ErroInternoException, ParticipanteExistenteException, ParticipanteInexistenteException {
        try {
            
            this.fachada.adicionarParticipante(participante); 
            
            participante = new Participante();
            
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Inscrição efetuado com sucesso!"));
        } catch (ErroInternoException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ocorreu um erro no sistema. Tente novamente." + e.getMessage()));
            return null;
        } catch (ParticipanteInexistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Participante já cadastrado no sistema."));
            return null;
        }
        
        return "PaginaInicial.xhtml";
    }
    
    
    
    
}
