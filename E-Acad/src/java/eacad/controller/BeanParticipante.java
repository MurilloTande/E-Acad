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
import eacad.exceptions.EventoInexistenteException;
import eacad.exceptions.ParticipanteExistenteException;
import eacad.exceptions.ParticipanteInexistenteException;
import eacad.exceptions.SubEventoExistenteException;
import eacad.exceptions.SubEventoInexistenteException;
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

    
    
    public String CadastrarParticipante() throws ErroInternoException, ParticipanteExistenteException, ParticipanteInexistenteException, SubEventoInexistenteException {
        try {
            
            this.participante.setEvento(part_eventos);
            
            if(subEventoSelecionado.getContVagasSubEvento()>0){
               
            this.participante.setSubEvento(part_subEvento);
            this.fachada.adicionarParticipante(participante);
            this.fachada.atualizarVagasSubEvento(subEventoSelecionado.getContVagasSubEvento()-1, subEventoSelecionado);
            this.participante = new Participante();
            this.part_eventos = new ArrayList<>();
            this.part_subEvento = new ArrayList<>();
            
            }else{
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Quantidade de vagas preenchida"));
            return "inscricaoEventoP3.xhtml";
            }
            
           
            
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Inscrição efetuado com sucesso!"));
        } catch (ErroInternoException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ocorreu um erro no sistema. Tente novamente." + e.getMessage()));
            return null;
        } catch (ParticipanteExistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Participante já cadastrado no sistema."));
            return null;
        }
        
        return "PaginaInicial.xhtml";
    }
    
    public String EventoSelect(long codigo) throws EventoInexistenteException {
        try {
            Evento e = this.fachada.buscarCodigoEvento(codigo);
            this.eventoSelecionado = e;
            this.part_eventos.add(e);
            FacesContext aviso = FacesContext.getCurrentInstance();
            aviso.addMessage(null, new FacesMessage("Evento Selecionado!"));
        } catch (ErroInternoException | EventoInexistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
        }

        return "PaginaInicial.xhtml";
    }
    
     public List<Participante> eventosParticipante() throws ParticipanteInexistenteException {
        try {
            List<Participante> a= this.fachada.listarTudoEventoParticipante(eventoSelecionado);
            return a;
        } catch (ErroInternoException ex ) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
    }
      
    public List<Participante> subEventosParticipante() throws ParticipanteInexistenteException {
        try {
            List<Participante> a= this.fachada.listarTudoSubEventoParticipante(subEventoSelecionado);
            return a;
        } catch (ErroInternoException ex ) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
    }
     
    public String ValidarParticipante() throws ParticipanteExistenteException, ErroInternoException {
      
            this.fachada.buscarValidarPartipante(eventoSelecionado,participante);
            
            
        
        return null;
    
    }
    
    public String SubEventoSelect(long codigo) throws SubEventoInexistenteException, ErroInternoException {
        try {
            SubEvento e = this.fachada.buscarCodigoSubEvento(codigo);
            int cont=0;
            this.subEventoSelecionado = e;
            
            
            if(part_subEvento.isEmpty()){
           this.part_subEvento.add(e);
            }else{
                for(SubEvento x: part_subEvento){
                if(e.getCodigo()!=x.getCodigo()){
                cont++;    
                }}}
            
            
            if(cont >= part_subEvento.size()){
            this.part_subEvento.add(e);
            }    
                
            
            
            FacesContext aviso = FacesContext.getCurrentInstance();
            aviso.addMessage(null, new FacesMessage("SubEvento Selecionado!"));
        } catch (SubEventoInexistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
        }

        return "inscricaoEventoP2.xhtml";
    }
    
    public List<SubEvento> buscarListSubEvento() throws SubEventoExistenteException, SubEventoInexistenteException {
        try {
            List<SubEvento> subEventos = this.fachada.buscarListSubEvento(this.eventoSelecionado);
           
            return subEventos;
            
        } catch (ErroInternoException ex ) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
    }
       
    public String buscarParticipanteEvento() throws ErroInternoException{
    
        List<Participante> p;
        try {
            p = this.fachada.listarTudoEventoParticipante(eventoSelecionado);
        } catch (ParticipanteInexistenteException ex) {
            return "inscricaoEventoP2.xhtml";
        }
        
        for(Participante x : p){
        if(participante.getCpf().equals(x.getCpf())){
            
             FacesContext aviso = FacesContext.getCurrentInstance();
            aviso.addMessage(null, new FacesMessage("CPF de Participante Já cadastrado no Evento!"));
            
            return "inscricaoEventoP1.xhtml";
        
        } 
        }
        return "inscricaoEventoP2.xhtml";
    }
    
    public String removerParticipante2(String cpf){
    
        try { 
                
            this.fachada.removerParticipante2(cpf, this.subEventoSelecionado);
            
            FacesContext aviso = FacesContext.getCurrentInstance();
            aviso.addMessage(null, new FacesMessage("Participante Excluido!"));           
        } catch (ErroInternoException e) {
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }catch ( ParticipanteInexistenteException e1) {
           FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Participante inexistente!" + e1.getMessage()));
        }
        
         return null;     
    }
    
    public String removerParticipante(String cpf){
    
        try { 
            
            this.fachada.removerParticipante(cpf);
            
            FacesContext aviso = FacesContext.getCurrentInstance();
            aviso.addMessage(null, new FacesMessage("Participante Excluido!"));           
        } catch (ErroInternoException e) {
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }catch ( ParticipanteInexistenteException e1) {
           FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Participante inexistente!" + e1.getMessage()));
        }
        
         return null;     
    }
    
}
