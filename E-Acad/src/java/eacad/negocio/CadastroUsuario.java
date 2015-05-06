/**
 * @author Tande
 */
package eacad.negocio;

import eacad.entidades.Usuario;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.UsuarioExistenteException;
import eacad.exceptions.UsuarioInexistenteException;
import eacad.persistencia.RepositorioUsuario;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;


@Stateless
public class CadastroUsuario implements Serializable {

    @EJB
   private RepositorioUsuario repUsuario;

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

    public List<Usuario> listar() throws ErroInternoException {
        List<Usuario> u = this.repUsuario.listar();
        return u;
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
    
    public void remover(String cpf) throws ErroInternoException, UsuarioInexistenteException{
        this.repUsuario.remover(cpf);
    }

}
