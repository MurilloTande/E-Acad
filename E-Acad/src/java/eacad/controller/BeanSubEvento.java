/**
 * Classe de controlador, Participante, onde serão contidos os métodos da camada
 * de controller para conexão com a view.<p/>
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.controller;

import eacad.entidades.Evento;
import eacad.entidades.Participante;
import eacad.entidades.SubEvento;
import eacad.exceptions.DatasIncorretas;
import eacad.exceptions.ErroInternoException;
import eacad.exceptions.EventoInexistenteException;
import eacad.exceptions.ParticipanteExistenteException;
import eacad.exceptions.ParticipanteInexistenteException;
import eacad.exceptions.SubEventoExistenteException;
import eacad.exceptions.SubEventoInexistenteException;
import eacad.fachada.FachadaSistema;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class BeanSubEvento implements Serializable {

    private SubEvento subEvento;
    private Evento evento;
    private SubEvento subEventoSelecionado;
    private Participante participanteSelecionado;

    @EJB
    private FachadaSistema fachada;

    public BeanSubEvento() {
        this.subEvento = new SubEvento();
    }

    /**
     * @return Participante.
     */
    public Participante getParticipanteSelecionado() {
        return participanteSelecionado;
    }

    /**
     * @param participanteSelecionado;
     */
    public void setParticipanteSelecionado(Participante participanteSelecionado) {
        this.participanteSelecionado = participanteSelecionado;
    }

    /**
     * @return SubEvento.
     */
    public SubEvento getSubEvento() {
        return subEvento;
    }

    /**
     * @param subEvento;
     */
    public void setSubEvento(SubEvento subEvento) {
        this.subEvento = subEvento;
    }

    /**
     * @return Evento.
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * @param evento;
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     * @return SubEvento.
     */
    public SubEvento getSubEventoSelecionado() {
        return subEventoSelecionado;
    }

    /**
     * @param subEventoSelecionado;
     */
    public void setSubEventoSelecionado(SubEvento subEventoSelecionado) {
        this.subEventoSelecionado = subEventoSelecionado;
    }

    /**
     * @return String.
     * @throws ErroInternoException;
     * @throws DatasIncorretas;
     * @throws SubEventoExistenteException;
     * @throws EventoInexistenteException;
     */
    public String CadastrarSubEvento() throws ErroInternoException, DatasIncorretas, SubEventoExistenteException, EventoInexistenteException {
        try {
            this.subEvento.setEventoPai(evento);
          
            if (evento.getContVagasEvento() >= subEvento.getTotal_vagas()) {
                      this.subEvento.setContVagasSubEvento(subEvento.getTotal_vagas());
                this.fachada.atualizarVagasEvento(evento.getContVagasEvento() - subEvento.getTotal_vagas(), evento);
                this.fachada.adicionarSubEvento(subEvento);
                this.subEvento = new SubEvento();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Quantidade de Vagas para SubEvento indisponível. Restam apenas: " + evento.getContVagasEvento() + "Vagas"));
                return "CriarSubEventos.xhtml";
            }

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cadastro efetuado com sucesso!"));
        } catch (ErroInternoException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ocorreu um erro no sistema. Tente novamente." + e.getMessage()));
            return null;
        } catch (DatasIncorretas ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Datas Incorretas."));
            return null;
        }
        return "meusEventos.xhtml";
    }

    /**
     * Método para listar SubEventos
     *
     * @return List.
     * @throws SubEventoExistenteException;
     * @throws SubEventoInexistenteException;
     */
    public List<SubEvento> listarTudoSubEvento() throws SubEventoExistenteException, SubEventoInexistenteException {
        try {
            List<SubEvento> subEventos = this.fachada.buscarListSubEvento(evento);

            return subEventos;

        } catch (SubEventoInexistenteException ez) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ez.getMessage()));
            return null;
        } catch (ErroInternoException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }

    }

    /**
     * Método para buscar SubEvento.
     *
     * @return List.
     * @throws SubEventoExistenteException;
     */
    public List<SubEvento> buscarListSubEvento() throws SubEventoExistenteException {
        try {
            List<SubEvento> subEventos = this.fachada.buscarListSubEvento(evento);

            return subEventos;

        } catch (SubEventoInexistenteException | ErroInternoException ez) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ez.getMessage()));
            return null;
        }

    }

    /**
     * Método para apagar SubEvento.
     *
     * @param codigo;
     * @return String.
     */
    public String apagarSubEvento(long codigo) {
        try {
            this.fachada.removerSubEvento(codigo);

            FacesContext aviso = FacesContext.getCurrentInstance();
            aviso.addMessage(null, new FacesMessage("SubEvento removido!"));
            return "meusEventos.xhtml";
        } catch (ErroInternoException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ocorreu um erro no sistema. Tente novamente." + ex.getMessage()));
        } catch (SubEventoInexistenteException ex1) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("SubEvento inexistente!" + ex1.getMessage()));
        } catch (ParticipanteInexistenteException ex2) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Participante inexistente!" + ex2.getMessage()));
        }

        return null;
    }

    /**
     * Método para atualizar SubEvento.
     *
     * @return String.
     */
    public String atualizarSubEvento() {
        try {
            this.fachada.atualizarSubEvento(subEventoSelecionado);

            FacesContext aviso = FacesContext.getCurrentInstance();
            aviso.addMessage(null, new FacesMessage("SubEvento Atualizado!"));
        } catch (ErroInternoException | SubEventoInexistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
        }

        return "meusSubEventos.xhtml";
    }

    /**
     * Método para selecionar SubEvento.
     *
     * @param codigo;
     * @return String.
     * @throws eacad.exceptions.SubEventoInexistenteException;
     */
    public String SubEventoSelect(long codigo) throws SubEventoInexistenteException {
        try {
            SubEvento e = this.fachada.buscarCodigoSubEvento(codigo);
            this.subEventoSelecionado = e;
            FacesContext aviso = FacesContext.getCurrentInstance();
            aviso.addMessage(null, new FacesMessage("SubEvento Selecionado!"));
        } catch (ErroInternoException | SubEventoInexistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
        }

        return "meusEventos.xhtml";
    }

    /**
     * Método para seleção de Evento.
     *
     * @param codigo;
     * @return String.
     * @throws EventoInexistenteException;
     */
    public String EventoSelect(long codigo) throws EventoInexistenteException {
        try {
            Evento e = this.fachada.buscarCodigoEvento(codigo);
            this.evento = e;
            FacesContext aviso = FacesContext.getCurrentInstance();
            aviso.addMessage(null, new FacesMessage("Evento Selecionado!"));
        } catch (ErroInternoException | EventoInexistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
        }

        return "meusEventos.xhtml";
    }

}
