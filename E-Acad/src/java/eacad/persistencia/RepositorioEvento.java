/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.persistencia;

import eacad.entidades.Evento;
import java.io.Serializable;
import javax.ejb.Local;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.EventoExistenteException;
import eacad.exceptions.EventoInexistenteException;
import java.util.List;

@Local
public interface RepositorioEvento extends Serializable{

    public void adicionar(Evento e) throws ErroInternoException, EventoExistenteException;
    public List<Evento> listarTudoEvento() throws ErroInternoException, EventoExistenteException;
    public void atualizar(Evento e) throws ErroInternoException, EventoInexistenteException;
    public List<Evento> buscarNomeListEvento(String nome) throws ErroInternoException, EventoInexistenteException;
    public Evento buscarCodigo(String codigo) throws ErroInternoException, EventoInexistenteException;
    public Evento buscarNomeEvento(String nome) throws ErroInternoException, EventoInexistenteException;
    public void remover(long codigo) throws ErroInternoException, EventoInexistenteException;
    
}
