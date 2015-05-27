/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.negocio;

import eacad.entidades.Evento;
import eacad.entidades.Participante;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.ParticipanteExistenteException;
import eacad.exceptions.ParticipanteInexistenteException;
import eacad.persistencia.RepositorioParticipante;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CadastroParticipante implements Serializable{
    
    @EJB
   private RepositorioParticipante repParticipante;

    public CadastroParticipante() {
    }

    public CadastroParticipante(RepositorioParticipante repParticipante) {
        this.repParticipante = repParticipante;
    }
    
    public void adicionar(Participante e) throws ErroInternoException, ParticipanteExistenteException, ParticipanteInexistenteException{
    
        try {
            Participante u = this.repParticipante.buscar(e.getCpf());
            if ( u!=null   ) {
                throw new ParticipanteExistenteException();
            } 
        } catch (ParticipanteInexistenteException ui) {
            this.repParticipante.adicionar(e);
        }
    }
    
    
    public Participante buscar(String cpf) throws ErroInternoException, ParticipanteInexistenteException {

        Participante u = this.repParticipante.buscar(cpf);

        if (u == null) {
            throw new ParticipanteInexistenteException();
        }
        return u;
    }
    
   public Participante buscarValidarPartipante(Evento e) throws ErroInternoException, ParticipanteExistenteException {

        Participante u = this.repParticipante.buscarValidarPartipante(e);

      
        return u;
    }
    
    
}
