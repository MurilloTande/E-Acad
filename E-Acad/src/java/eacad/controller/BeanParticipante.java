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
    private Evento eventoSelecionado;
    private SubEvento subEventoSelecionado;
    private List<Evento> part_eventos;
    private List<SubEvento> part_subEvento;
    
    @EJB
    private FachadaSistema fachada;

    public BeanParticipante() {
        this.participante = new Participante();
        this.part_eventos = new ArrayList<>();
        this.part_subEvento = new ArrayList<>();
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Evento getEventoSelecionado() {
        return eventoSelecionado;
    }

    public void setEventoSelecionado(Evento eventoSelecionado) {
        this.eventoSelecionado = eventoSelecionado;
    }

    public SubEvento getSubEventoSelecionado() {
        return subEventoSelecionado;
    }

    public void setSubEventoSelecionado(SubEvento subEventoSelecionado) {
        this.subEventoSelecionado = subEventoSelecionado;
    }

    public List<Evento> getPart_eventos() {
        return part_eventos;
    }

    public void setPart_eventos(Evento part_eventos) {
        this.part_eventos.add(part_eventos);
    }

    public List<SubEvento> getPart_subEvento() {
        return part_subEvento;
    }

    public void setPart_subEvento(SubEvento part_subEvento) {
        this.part_subEvento.add(part_subEvento);
    }

    
    
    public String CadastrarParticipante() throws ErroInternoException, ParticipanteExistenteException, ParticipanteInexistenteException {
        try {
            
            this.participante.setEvento(part_eventos);
            this.participante.setSubEvento(part_subEvento);
            this.fachada.adicionarParticipante(participante);
            
            
            this.participante = new Participante();
            
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
