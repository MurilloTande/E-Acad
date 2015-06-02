/**
 * Classe de negócios, Usuário, onde serão contidos os métodos da camada de
 * negócio.<p/>
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.negocio;

import eacad.entidades.Evento;
import eacad.entidades.Participante;
import eacad.entidades.SubEvento;
import eacad.entidades.Usuario;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.EventoInexistenteException;
import eacad.exceptions.ParticipanteInexistenteException;
import eacad.exceptions.SubEventoInexistenteException;
import eacad.exceptions.UsuarioExistenteException;
import eacad.exceptions.UsuarioInexistenteException;
import eacad.persistencia.RepositorioEvento;
import eacad.persistencia.RepositorioParticipante;
import eacad.persistencia.RepositorioSubEvento;
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
    @EJB
    private RepositorioSubEvento repSubEvento;
    @EJB
    private RepositorioParticipante repParticipante;

    /**
     * Contrutor Vazio
     */
    public CadastroUsuario() {
    }

    /**
     * @param repUsuario;
     */
    public CadastroUsuario(RepositorioUsuario repUsuario) {
        this.repUsuario = repUsuario;

    }

    /**
     * Método para atualizar Usuário.
     *
     * @see eacad.persistencia.RepositorioUsuario.adicionar;
     * @param usuario;
     * @throws ErroInternoException;
     * @throws UsuarioExistenteException;
     * @throws UsuarioInexistenteException;
     */
    public void adicionarUsuario(Usuario usuario) throws ErroInternoException, UsuarioExistenteException, UsuarioInexistenteException {
        try {
            Usuario u = this.repUsuario.buscar(usuario.getCpf());
            if (u != null) {
                throw new UsuarioExistenteException();
            }
        } catch (UsuarioInexistenteException ui) {
            this.repUsuario.adicionar(usuario);
        }

    }

    /**
     * Método listar, Usuário.
     *
     * @see eacad.persistencia.RepositorioUsuario.listar;
     * @return List.
     * @throws ErroInternoException;
     * @throws UsuarioInexistenteException;
     */
    public List<Usuario> listar() throws ErroInternoException, UsuarioInexistenteException {
        List<Usuario> u = this.repUsuario.listar();
        if (u.isEmpty()) {
            return u;
        } else {
            throw new UsuarioInexistenteException();
        }
    }

    /**
     * Métodos Atualizar, Usuário.
     *
     * @see eacad.persistencia.RepositorioUsuario.atualizar;
     * @param usuario;
     * @throws ErroInternoException;
     * @throws UsuarioInexistenteException;
     */
    public void Atualizar(Usuario usuario) throws ErroInternoException, UsuarioInexistenteException {
        this.repUsuario.atualizar(usuario);
    }

    /**
     * Método buscarNome, Usuário.
     *
     * @see eacad.persistencia.RepositorioUsuario.buscarNome;
     * @param nome;
     * @return Usuário.
     * @throws ErroInternoException;
     * @throws UsuarioInexistenteException;
     */
    public List<Usuario> buscarNome(String nome) throws ErroInternoException, UsuarioInexistenteException {

        List<Usuario> u = this.repUsuario.buscarNome(nome);
        if ((u.isEmpty())) {
            throw new UsuarioInexistenteException();
        }
        return u;
    }

    /**
     * Método buscar, Usuário.
     *
     * @see eacad.persistencia.RepositorioUsuario.buscar;
     * @param cpf;
     * @return Usuário.
     * @throws ErroInternoException;
     * @throws UsuarioInexistenteException;
     */
    public Usuario buscar(String cpf) throws ErroInternoException, UsuarioInexistenteException {

        Usuario u = this.repUsuario.buscar(cpf);

        if (u == null) {
            throw new UsuarioInexistenteException();
        }
        return u;
    }

    /**
     * Método buscarEmail, Usuario.
     *
     * @see eacad.persistencia.RepositorioUsuario.buscarEmail;
     * @param email;
     * @return Usuário.
     * @throws ErroInternoException;
     * @throws UsuarioInexistenteException;
     */
    public Usuario buscarEmail(String email) throws ErroInternoException, UsuarioInexistenteException {

        Usuario u = this.repUsuario.buscarEmail(email);

        if (u == null) {
            throw new UsuarioInexistenteException();
        }
        return u;
    }

    /**
     * Método remover, Usuário.
     *
     * @see eacad.persistencia.RepositorioUsuario.remover;
     * @param cpf;
     * @throws ErroInternoException;
     * @throws UsuarioInexistenteException;
     * @throws EventoInexistenteException;
     * @throws SubEventoInexistenteException;
     */
    public void remover(String cpf) throws ErroInternoException, UsuarioInexistenteException, EventoInexistenteException, SubEventoInexistenteException, ParticipanteInexistenteException {
        Usuario temp = this.repUsuario.buscar(cpf);

        try {

            for (Evento e : this.repEvento.EventosUsuario(temp.getCpf())) {
                if (e == null) {
                    break;
                } else {

                    for (Participante x : this.repParticipante.listarTudoEventoParticipante(e)) {
                        if (x != null) {
                            this.repParticipante.remover(x.getCodigo());
                        }
                    }

                    for (SubEvento a : this.repSubEvento.buscarListSubEvento(this.repEvento.buscarCodigo(e.getCodigo()))) {
                        if (a == null) {
                            break;
                        } else {
                            this.repSubEvento.remover(a.getCodigo());
                        }
                    }

                    this.repEvento.remover(e.getCodigo());
                }
            }

            this.repUsuario.remover(cpf);
        } catch (ErroInternoException ex) {
            throw new ErroInternoException(ex);
        }
    }

}
