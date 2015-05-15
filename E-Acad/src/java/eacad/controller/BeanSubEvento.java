/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.controller;


import eacad.entidades.SubEvento;
import eacad.exceptions.ErroInternoException;
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

    
    public String CadastrarSubEvento() throws ErroInternoException, SubEventoExistenteException, SubEventoInexistenteException {
        try {
            
            this.fachada.adicionarSubEvento(subEvento);
            
            subEvento = new SubEvento();
            
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cadastro efetuado com sucesso!"));
        } catch (ErroInternoException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ocorreu um erro no sistema. Tente novamente." + e.getMessage()));
            return null;
        } catch (SubEventoExistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("SubEvento Existente no sistema."));
            return null;
        }
        return "criarEvento.xhtml";
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

    void CadastrarSubEvento(SubEvento a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     
     
}
