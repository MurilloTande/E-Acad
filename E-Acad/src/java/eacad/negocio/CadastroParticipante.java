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
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.EventoInexistenteException;
import eacad.exceptions.ParticipanteExistenteException;
import eacad.exceptions.ParticipanteInexistenteException;
import eacad.exceptions.SubEventoInexistenteException;
import eacad.persistencia.RepositorioEvento;
import eacad.persistencia.RepositorioParticipante;
import eacad.persistencia.RepositorioSubEvento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CadastroParticipante implements Serializable {

    @EJB
    private RepositorioParticipante repParticipante;
    
    @EJB
    private RepositorioEvento repEvento;
    
    @EJB
    private RepositorioSubEvento repSubEvento;
    
    /**
     * Contrutor Vazio.
     */
    public CadastroParticipante() {
    }

    /**
     * Contrutor
     *
     * @param repParticipante;
     */
    public CadastroParticipante(RepositorioParticipante repParticipante) {
        this.repParticipante = repParticipante;
    }

    /**
     */
    public void adicionar(Participante e) throws ErroInternoException, ParticipanteExistenteException {

        try {
            Participante u = this.repParticipante.buscarCodigo(e.getCodigo());

        } catch (ParticipanteInexistenteException ui) {
            this.repParticipante.adicionar(e);
        }

    }

    /**
     * Método buscar, Participante.
     *
     * @see eacad.persistencia.RepositorioParticipante.buscar;
     * @param cpf;
     * @return Participante.
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.ParticipanteInexistenteException;
     */
    public Participante buscar(String cpf) throws ErroInternoException, ParticipanteInexistenteException {

        Participante u = this.repParticipante.buscar(cpf);

        if (u == null) {
            throw new ParticipanteInexistenteException();
        }
        return u;
    }

    /**
     * Método listar Participantes de um Evento.
     *
     * @see eacad.persistencia.RepositorioParticipante.buscar;
     * @param e;
     * @return List.
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.ParticipanteInexistenteException;
     */
    public List<Participante> listarTudoEventoParticipante(Evento e) throws ErroInternoException, ParticipanteInexistenteException {
        return this.repParticipante.listarTudoEventoParticipante(e);
    }

    /**
     * Método listar Participantes de um SubEvento
     *
     * @param e;
     * @return List.
     * @throws ErroInternoException;
     * @throws ParticipanteInexistenteException;
     */
    public List<Participante> listarTudoSubEventoParticipante(SubEvento e) throws ErroInternoException, ParticipanteInexistenteException {
        return this.repParticipante.listarTudoSubEventoParticipante(e);
    }

    /**
     * Método remover, Participante.
     *
     * @param codigo;
     * @throws ErroInternoException;
     * @throws ParticipanteInexistenteException;
     */
    public void remover(long codigo) throws ErroInternoException, ParticipanteInexistenteException, SubEventoInexistenteException, EventoInexistenteException {
        try {
            Participante p = this.buscarCodigo(codigo);
            
            
            if(p.getSubEvento().size()!=0){
                for(SubEvento s : p.getSubEvento()){
            this.repSubEvento.atualizarVagasSubEvento(s.getContVagasSubEvento()+1, s);
            }}
            
            if(p.getEvento()!=null){
                 for(Evento e : p.getEvento()){
            this.repEvento.atualizarVagasEvento(e.getContVagasEvento()+1,e);
            }}
            
            this.repParticipante.remover(codigo);

        } catch (ErroInternoException e) {
            throw new ErroInternoException(e);
        }
    }

    /**
     * Método para atualizar Participante.
     *
     * @param participante;
     * @throws ErroInternoException;
     * @throws ParticipanteInexistenteException;
     */
    public void atualizar(Participante participante) throws ErroInternoException, ParticipanteInexistenteException {
        this.repParticipante.atualizar(participante);
    }

    /**
     * Método para buscar Participante pelo código.
     *
     * @param codigo;
     * @return Participante;
     * @throws eacad.exceptions.ErroInternoException;
     * @throws eacad.exceptions.ParticipanteInexistenteException;
     */
    public Participante buscarCodigo(long codigo) throws ErroInternoException, ParticipanteInexistenteException {
        return this.repParticipante.buscarCodigo(codigo);
    }

}
