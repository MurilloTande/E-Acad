/**
 * @author Tande
 */
package eacad.persistencia;

import eacad.entidades.Usuario;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.UsuarioInexistenteException;
import java.io.Serializable;
import java.util.List;

public interface RepositorioUsuario extends Serializable{
    public void adicionar(Usuario usuario) throws ErroInternoException;
    public List<Usuario> listar() throws ErroInternoException;
    public void atualizar(Usuario usuario) throws ErroInternoException, UsuarioInexistenteException;
    public List<Usuario> buscarNome(String nome) throws ErroInternoException, UsuarioInexistenteException;
    public Usuario buscar(String email) throws ErroInternoException, UsuarioInexistenteException;
    public void remover(String cpf) throws ErroInternoException, UsuarioInexistenteException;
}
