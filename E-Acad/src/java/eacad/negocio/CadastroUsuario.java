/**
 * @author Tande
 */
package eacad.negocio;

import eacad.entidades.Evento;
import eacad.entidades.Usuario;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.EventoInexistenteException;
import eacad.exceptions.SubEventoInexistenteException;
import eacad.exceptions.UsuarioExistenteException;
import eacad.exceptions.UsuarioInexistenteException;
import eacad.persistencia.RepositorioEvento;
import eacad.persistencia.RepositorioUsuario;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;


@Stateless
public class CadastroUsuario implements Serializable {

    @EJB
   private RepositorioUsuario repUsuario;
     @EJB
   private RepositorioEvento repEvento;

    public CadastroUsuario() {
    }

    public CadastroUsuario(RepositorioUsuario repUsuario) {
        this.repUsuario = repUsuario;

    }

    public void adicionarUsuario(Usuario usuario) throws ErroInternoException, UsuarioExistenteException, UsuarioInexistenteException {
        try {
            Usuario u = this.repUsuario.buscar(usuario.getCpf());
            if ( u!=null   ) {
                throw new UsuarioExistenteException();
            } 
        } catch (UsuarioInexistenteException ui) {
            this.repUsuario.adicionar(usuario);
        }

    }

    public List<Usuario> listar() throws ErroInternoException, UsuarioInexistenteException {
        List<Usuario> u = this.repUsuario.listar();
        if(u.isEmpty()){
            return u;
        }else{
        throw new UsuarioInexistenteException();
        }
    }

    public void Atualizar(Usuario usuario) throws ErroInternoException, UsuarioInexistenteException {
        this.repUsuario.atualizar(usuario);
    }

    public List<Usuario> buscarNome(String nome) throws ErroInternoException, UsuarioInexistenteException {

        List<Usuario> u = this.repUsuario.buscarNome(nome);
        if ((u.isEmpty())) {
            throw new UsuarioInexistenteException();
        }
        return u;
    }
    
    public Usuario buscar(String cpf) throws ErroInternoException, UsuarioInexistenteException {

        Usuario u = this.repUsuario.buscar(cpf);

        if (u == null) {
            throw new UsuarioInexistenteException();
        }
        return u;
    }
    
    public Usuario buscarEmail(String email) throws ErroInternoException, UsuarioInexistenteException {

        Usuario u = this.repUsuario.buscarEmail(email);

        if (u == null) {
            throw new UsuarioInexistenteException();
        }
        return u;
    }
    
    public void remover(String cpf) throws ErroInternoException, UsuarioInexistenteException, EventoInexistenteException, SubEventoInexistenteException {
        Usuario temp = this.repUsuario.buscar(cpf);        
       
        try {
            
            for(Evento e: this.repEvento.EventosUsuario(temp.getCpf())){
            if(e != null){
                this.repEvento.remover(e.getCodigo());
            }}
            
            this.repUsuario.remover(cpf);
        } catch (ErroInternoException e) {
            throw new ErroInternoException(e);
        }
    
    }

}
