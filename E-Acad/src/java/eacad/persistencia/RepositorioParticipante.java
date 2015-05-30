/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.persistencia;

import eacad.entidades.Evento;
import eacad.entidades.Participante;
import eacad.entidades.SubEvento;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.ParticipanteExistenteException;
import eacad.exceptions.ParticipanteInexistenteException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

@Local
public interface RepositorioParticipante extends Serializable{
    
    public List<Participante> listarTudoEventoParticipante(Evento e) throws ErroInternoException, ParticipanteInexistenteException;
    public List<Participante> listarTudoSubEventoParticipante(SubEvento e) throws ErroInternoException, ParticipanteInexistenteException;
    public void adicionar(Participante e) throws ErroInternoException;
    public Participante buscar(String cpf) throws ErroInternoException, ParticipanteInexistenteException;
   

}
