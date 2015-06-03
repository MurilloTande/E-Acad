/**
 * Classe de negócios, Evento, onde serão contidos os métodos da camada de
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
import eacad.exceptions.VagasIncorretasException;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.EventoInexistenteException;
import eacad.exceptions.ParticipanteExistenteException;
import eacad.exceptions.ParticipanteInexistenteException;
import eacad.exceptions.SubEventoInexistenteException;
import eacad.persistencia.RepositorioEvento;
import eacad.persistencia.RepositorioParticipante;
import eacad.persistencia.RepositorioSubEvento;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CadastroEvento implements Serializable {

    @EJB
    private RepositorioEvento repEvento;
    @EJB
    private RepositorioSubEvento repSubEvento;
    @EJB
    private RepositorioParticipante repParticipante;

    /**
     * Construtor vazio.
     */
    public CadastroEvento() {
    }

    /**
     * Cosntrutor com o RepositorioEvento para implementação da camada de
     * negócio.
     *
     * @param repEvento;
     */
    public CadastroEvento(RepositorioEvento repEvento) {
        this.repEvento = repEvento;
    }

    /**
     * Método para adicionar Evento;
     *
     * @see eacad.persistencia.RepositorioEvento.adicionar;
     * @param e;
     * @throws ErroInternoException;
     * @throws DatasIncorretas;
     */
    public void adicionar(Evento e) throws ErroInternoException, DatasIncorretas {

        if (e.getData_final().after(e.getData_inicio()) || e.getData_inicio().equals(e.getData_final())) {
            try {
                this.repEvento.adicionar(e);
            } catch (ErroInternoException ui) {
                throw new ErroInternoException(ui);
            }
        } else {
            throw new DatasIncorretas();
        }
    }

    /**
     * Método ListarTudoEvento.
     *
     * @see eacad.persistencia.RepositorioEvento.litsarTudoEvento;
     * @return List.
     * @throws ErroInternoException;
     * @throws EventoInexistenteException;
     */
    public List<Evento> listarTudoEvento() throws ErroInternoException, EventoInexistenteException {

        try {
            List<Evento> e = this.repEvento.listarTudoEvento();
            if (e != null) {
                return e;
            }
        } catch (EventoInexistenteException a) {
            throw new EventoInexistenteException();
        } catch (ErroInternoException r) {
            throw new ErroInternoException(r);
        }
        return listarTudoEvento();
    }

    /**
     * Método EventosUsuário.
     *
     * @see eacad.persistencia.RepositorioEvento.EventosUsuario;
     * @param cpf;
     * @return List;
     * @throws ErroInternoException;
     * @throws EventoInexistenteException;
     */
    public List<Evento> EventosUsuario(String cpf) throws ErroInternoException, EventoInexistenteException {
        try {
            List<Evento> e = this.repEvento.EventosUsuario(cpf);
            if (e != null) {
                return e;
            }
        } catch (EventoInexistenteException a) {
            throw new EventoInexistenteException();
        } catch (ErroInternoException r) {
            throw new ErroInternoException(r);
        }
        return EventosUsuario(cpf);

    }

    /**
     * Método atualizar Evento.
     *
     * @see eacad.persistencia.RepositorioEvento.atualizar;
     * @param e;
     * @throws ErroInternoException;
     * @throws EventoInexistenteException;
     */
    public void atualizar(Evento e) throws ErroInternoException, EventoInexistenteException, VagasIncorretasException {
        
        Evento s = this.repEvento.buscarCodigo(e.getCodigo());
        int antigo=s.getTotal_vagas();
        int novo = e.getTotal_vagas();
        int aumenta;
        int diminui;
        
        if(novo>antigo){
        aumenta=novo - antigo;
        this.atualizarVagasEvento(e.getContVagasEvento()+aumenta, e);
        
        }else if(antigo>novo){
        diminui = antigo-novo;
        if(e.getContVagasEvento()>=diminui){
        this.repEvento.atualizarVagasEvento(e.getContVagasEvento()-diminui, e);
        }else{
        throw new VagasIncorretasException();
        }
        }
        
        this.repEvento.atualizar(e);

    }

    /**
     * Método atualizarVagasEvento.
     *
     * @see eacad.persistencia.RepositorioEvento.atualizarVagasEvento;
     * @param vagas;
     * @param ev;
     * @throws ErroInternoException;
     * @throws EventoInexistenteException;
     */
    public void atualizarVagasEvento(int vagas, Evento ev) throws ErroInternoException, EventoInexistenteException {
        this.repEvento.atualizarVagasEvento(vagas, ev);
    }

    /**
     * Método buscarNomeListEvento.
     *
     * @see eacad.persistencia.RepositorioEvento.buscarNomeListEvento;
     * @param nome;
     * @return List.
     * @throws ErroInternoException;
     * @throws EventoInexistenteException;
     */
    public List<Evento> buscarNomeListEvento(String nome) throws ErroInternoException, EventoInexistenteException {
        List<Evento> e = this.repEvento.buscarNomeListEvento(nome);
        if ((e.isEmpty())) {
            throw new EventoInexistenteException();
        }
        return e;
    }

    /**
     * Método buscarValidarParticipante, Evento.
     *
     * @see eacad.persistencia.RepositorioEvento.buscarValidarParticipante;
     * @param f;
     * @param p;
     * @return Evento
     * @throws ErroInternoException;
     * @throws ParticipanteExistenteException;
     */
    public Evento buscarValidarPartipante(Evento f, Participante p) throws ErroInternoException, ParticipanteExistenteException {
        Evento e = this.repEvento.buscarValidarPartipante(f, p);
        if (e != null) {
            throw new ParticipanteExistenteException();
        }
        return e;
    }

    /**
     * Método buscarCodigo Evento.
     *
     * @see eacad.persistencia.RepositorioEvento.buscarCodigo;
     * @param codigo;
     * @return Evento;
     * @throws ErroInternoException;
     * @throws EventoInexistenteException;
     */
    public Evento buscarCodigo(long codigo) throws ErroInternoException, EventoInexistenteException {
        Evento e = this.repEvento.buscarCodigo(codigo);

        if (e == null) {
            throw new EventoInexistenteException();
        }
        return e;
    }

    /**
     * Método buscarNomeEvento.
     *
     * @see eacad.persistencia.RepositorioEvento.buscarNomeEvento;
     * @param nome;
     * @return Evento.
     * @throws ErroInternoException;
     * @throws EventoInexistenteException;
     */
    public Evento buscarNomeEvento(String nome) throws ErroInternoException, EventoInexistenteException {
        Evento e = this.repEvento.buscarNomeEvento(nome);
        if (e == null) {
            throw new EventoInexistenteException();
        }
        return e;
    }

    /**
     * Método para remover Evento.
     *
     * @throws eacad.exceptions.ParticipanteInexistenteException
     * @see eacad.persistencia.RepositorioEvento.remover;
     * @param codigo;
     * @throws ErroInternoException;
     * @throws EventoInexistenteException;
     * @throws SubEventoInexistenteException;
     */
    public void remover(long codigo) throws ErroInternoException, EventoInexistenteException, SubEventoInexistenteException, ParticipanteInexistenteException {
        Evento temp = this.repEvento.buscarCodigo(codigo);

        try {

            for (Participante x : this.repParticipante.listarTudoEventoParticipante(temp)) {
                if (x != null) {
                    this.repParticipante.remover(x.getCodigo());
                }
            }

            for (SubEvento e : this.repSubEvento.buscarListSubEvento(temp)) {
                if (e != null) {
                    this.repSubEvento.remover(e.getCodigo());
                }
            }

            this.repEvento.remover(codigo);
        } catch (ErroInternoException e) {
            throw new ErroInternoException(e);
        }

    }

}
