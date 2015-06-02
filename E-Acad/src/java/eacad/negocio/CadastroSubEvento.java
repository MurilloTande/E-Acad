/**
 * Classe de negócios, SubEvento, onde serão contidos os métodos da camada de
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
import eacad.exceptions.DatasIncorretas;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.ParticipanteInexistenteException;
import eacad.exceptions.SubEventoInexistenteException;
import eacad.persistencia.RepositorioParticipante;
import eacad.persistencia.RepositorioSubEvento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CadastroSubEvento implements Serializable {

    @EJB
    private RepositorioSubEvento repSubEvento;
    @EJB
    private RepositorioParticipante repParticipante;

    /**
     * @param repSubEvento;
     */
    public CadastroSubEvento(RepositorioSubEvento repSubEvento) {
        this.repSubEvento = repSubEvento;
    }

    /**
     * Construtor Vazio.
     */
    public CadastroSubEvento() {
    }

    /**
     * Método adicionar, SubEvento.
     *
     * @see eacad.persistencia.RepositorioSubEvento.adicionar;
     * @param e;
     * @throws ErroInternoException;
     * @throws DatasIncorretas;
     */
    public void adicionar(SubEvento e) throws ErroInternoException, DatasIncorretas {
        if ((e.getData_final().after(e.getData_inicio()) || e.getData_final().equals(e.getData_inicio()))
                & ((e.getData_inicio().after(e.getEventoPai().getData_inicio()) || e.getData_inicio().equals(e.getEventoPai().getData_inicio()))
                & (e.getData_inicio().before(e.getEventoPai().getData_final())) || e.getData_inicio().equals(e.getEventoPai().getData_final()))
                & ((e.getData_final().before(e.getEventoPai().getData_final())) || e.getData_final().equals(e.getEventoPai().getData_final()))) {
            try {
                this.repSubEvento.adicionar(e);
            } catch (ErroInternoException ui) {
                throw new ErroInternoException(ui);
            }
        } else {
            throw new DatasIncorretas();
        }
    }

    /**
     * Método atualizarvagasSubEvento.
     *
     * @param vagas;
     * @param ev;
     * @throws ErroInternoException;
     * @throws SubEventoInexistenteException;
     */
    public void atualizarVagasSubEvento(int vagas, SubEvento ev) throws ErroInternoException, SubEventoInexistenteException {
        this.repSubEvento.atualizarVagasSubEvento(vagas, ev);
    }

    /**
     * Método listarTudoSubEvento.
     *
     * @see eacad.persistencia.RepositorioSubEvento.listarTudoSubEvento;
     * @return List.
     * @throws ErroInternoException;
     * @throws SubEventoInexistenteException;
     */
    public List<SubEvento> listarTudoSubEvento() throws ErroInternoException, SubEventoInexistenteException {

        List<SubEvento> e = this.repSubEvento.listarTudoSubEvento();
        if (e != null) {
            return e;
        } else {
            throw new SubEventoInexistenteException();
        }
    }

    /**
     * Método atualizar SubEvento.
     *
     * @see eacad.persistencia.RepositorioSubEvento.atualizar;
     * @param e;
     * @throws ErroInternoException;
     * @throws SubEventoInexistenteException;
     */
    public void atualizar(SubEvento e) throws ErroInternoException, SubEventoInexistenteException {

        this.repSubEvento.atualizar(e);
    }

    /**
     * @see eacad.persistencia.RepositorioSubEvento.buscarListSubEvento;
     * @param evento;
     * @return List.
     * @throws ErroInternoException;
     * @throws SubEventoInexistenteException;
     */
    public List<SubEvento> buscarListSubEvento(Evento evento) throws ErroInternoException, SubEventoInexistenteException {
        List<SubEvento> e = this.repSubEvento.buscarListSubEvento(evento);
        if (e.isEmpty()) {
            throw new SubEventoInexistenteException();
        } else {
            return e;
        }
    }

    /**
     * Método buscarCodigo, subEvento.
     *
     * @see eacad.persistencia.RepositorioSubEvento.buscarCodigo;
     * @param codigo;
     * @return SubEvento.
     * @throws ErroInternoException;
     * @throws SubEventoInexistenteException;
     */
    public SubEvento buscarCodigo(long codigo) throws ErroInternoException, SubEventoInexistenteException {
        SubEvento e = this.repSubEvento.buscarCodigo(codigo);

        if (e == null) {
            throw new SubEventoInexistenteException();
        }
        return e;
    }

    /**
     * Método buscarNomeSubEvento;
     *
     * @see eacad.persistencia.RepositorioSubEvento.buscarNomSubEvento;
     * @param nome;
     * @return SubEvento.
     * @throws ErroInternoException;
     * @throws SubEventoInexistenteException;
     */
    public SubEvento buscarNomeSubEvento(String nome) throws ErroInternoException, SubEventoInexistenteException {
        SubEvento e = this.repSubEvento.buscarNomeSubEvento(nome);

        if (e == null) {
            throw new SubEventoInexistenteException();
        }
        return e;
    }

    /**
     * Método remover, SubEvento.
     *
     * @throws eacad.exceptions.ParticipanteInexistenteException
     * @see eacad.persistencia.RepositorioSubEvento.remover;
     * @param codigo;
     * @throws ErroInternoException;
     * @throws SubEventoInexistenteException;
     */
    public void remover(long codigo) throws ErroInternoException, SubEventoInexistenteException, ParticipanteInexistenteException {
        SubEvento temp = this.repSubEvento.buscarCodigo(codigo);
        ArrayList<SubEvento> n = new ArrayList<>();

        try {

            for (Participante x : this.repParticipante.listarTudoSubEventoParticipante(temp)) {
                if (x != null) {
                    if (1 == x.getSubEvento().size()) {
                        this.repParticipante.remover(x.getCodigo());
                    } else {

                        for (SubEvento a : x.getSubEvento()) {
                            if (temp.getCodigo() != a.getCodigo()) {
                                n.add(a);
                            }
                        }
                        x.setSubEvento(n);
                        this.repParticipante.atualizar(x);
                        n = new ArrayList<>();
                    }

                }
            }

            this.repSubEvento.remover(codigo);

        } catch (ErroInternoException e) {
            throw new ErroInternoException(e);
        }
    }

}
