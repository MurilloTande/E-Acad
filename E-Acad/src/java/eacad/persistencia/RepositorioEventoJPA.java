/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.persistencia;

import eacad.entidades.Evento;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.EventoExistenteException;
import eacad.exceptions.EventoInexistenteException;
import eacad.exceptions.UsuarioInexistenteException;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class RepositorioEventoJPA implements RepositorioEvento{
    
     public void adicionar(Evento e) throws ErroInternoException, EventoExistenteException{
     
     
     }
     
     
     
    public List<Evento> listarTudoEvento() throws ErroInternoException, EventoExistenteException{return null;
}
    public void atualizar(Evento e) throws ErroInternoException, EventoInexistenteException{}
    public List<Evento> buscarNomeListEvento(String nome) throws ErroInternoException, EventoInexistenteException{return null;
}
    public Evento buscarCodigo(String codigo) throws ErroInternoException, EventoInexistenteException{return null;
}
    public Evento buscarNomeEvento(String nome) throws ErroInternoException, EventoInexistenteException{return null;
}
    public void remover(long codigo) throws ErroInternoException, EventoInexistenteException{}
    
}
