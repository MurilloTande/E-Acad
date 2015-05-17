/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.persistencia;

import eacad.entidades.SubEvento;
import eacad.exceptions.DatasIncorretas;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.SubEventoInexistenteException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Matheus
 */
public interface RepositorioSubEvento extends Serializable{
    
    public void adicionar(SubEvento e) throws ErroInternoException,DatasIncorretas;
    public List<SubEvento> listarTudoSubEvento() throws ErroInternoException, SubEventoInexistenteException;
    public void atualizar(SubEvento e) throws ErroInternoException, SubEventoInexistenteException;
    public List<SubEvento> buscarNomeListSubEvento(String nome) throws ErroInternoException, SubEventoInexistenteException;
    public SubEvento buscarCodigo(long codigo) throws ErroInternoException, SubEventoInexistenteException;
    public SubEvento buscarNomeSubEvento(String nome) throws ErroInternoException, SubEventoInexistenteException;
    public void remover(long codigo) throws ErroInternoException, SubEventoInexistenteException;
    
}
