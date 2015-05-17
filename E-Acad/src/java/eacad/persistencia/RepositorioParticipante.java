/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.persistencia;

import eacad.entidades.Participante;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.ParticipanteInexistenteException;
import java.io.Serializable;
import javax.ejb.Local;

@Local
public interface RepositorioParticipante extends Serializable{
    
    public void adicionar(Participante e) throws ErroInternoException;
    public Participante buscar(String cpf) throws ErroInternoException, ParticipanteInexistenteException;
    
}
