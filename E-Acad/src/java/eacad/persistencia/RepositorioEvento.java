/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.persistencia;

import eacad.entidades.Evento;
import eacad.entidades.Participante;
import eacad.exceptions.DatasIncorretas;
import java.io.Serializable;
import javax.ejb.Local;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.EventoExistenteException;
import eacad.exceptions.EventoInexistenteException;
import eacad.exceptions.ParticipanteExistenteException;
import java.util.List;

@Local
public interface RepositorioEvento extends Serializable{

    public Evento buscarValidarPartipante(Evento e, Participante p) throws ErroInternoException, ParticipanteExistenteException;
    public void adicionar(Evento e) throws ErroInternoException,DatasIncorretas;
    public List<Evento> listarTudoEvento() throws ErroInternoException, EventoInexistenteException;
    public void atualizar(Evento e) throws ErroInternoException, EventoInexistenteException;
    public List<Evento> buscarNomeListEvento(String nome) throws ErroInternoException, EventoInexistenteException;
    public Evento buscarCodigo(long codigo) throws ErroInternoException, EventoInexistenteException;
    public Evento buscarNomeEvento(String nome) throws ErroInternoException, EventoInexistenteException;
    public void remover(long codigo) throws ErroInternoException, EventoInexistenteException;
    public List<Evento> EventosUsuario(String cpf) throws ErroInternoException, EventoInexistenteException;
}
