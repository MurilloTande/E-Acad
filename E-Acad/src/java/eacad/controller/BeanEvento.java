/**
 * Classe de controlador, Evento, onde serão contidos os métodos da camada de
 * controller para conexão com a view.<p/>
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
import eacad.exceptions.EventoExistenteException;
import eacad.exceptions.EventoInexistenteException;
import eacad.exceptions.ParticipanteInexistenteException;
import eacad.exceptions.SubEventoInexistenteException;
import eacad.exceptions.VagasIncorretasException;
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
public class BeanEvento implements Serializable {

    private Evento evento;
    private SubEvento subEvento;
    private Evento eventoSelecionado;
    private Participante participanteSelecionado;
    private String pesquisa;
    private List<Evento> eventosPesquisa;

    @EJB
    private FachadaSistema fachada;

    public BeanEvento() {
        this.evento = new Evento();
    }

    /**
     * @return List.
     */
    public List<Evento> getEventosPesquisa() {
        return eventosPesquisa;
    }

    /**
     * @param eventosPesquisa;
     */
    public void setEventosPesquisa(List<Evento> eventosPesquisa) {
        this.eventosPesquisa = eventosPesquisa;
    }

    /**
     * @return Participante.
     */
    public Participante getParticipanteSelecionado() {
        return participanteSelecionado;
    }

    /**
     * @return String.
     */
    public String getPesquisa() {
        return pesquisa;
    }

    /**
     * @param pesquisa;
     */
    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    /**
     * @param participanteSelecionado;
     */
    public void setParticipanteSelecionado(Participante participanteSelecionado) {
        this.participanteSelecionado = participanteSelecionado;
    }

    /**
     * @return Evento - um Evento selecionado.
     */
    public Evento getEventoSelecionado() {
        return eventoSelecionado;
    }

    /**
     * @param eventoSelecionado;
     */
    public void setEventoSelecionado(Evento eventoSelecionado) {
        this.eventoSelecionado = eventoSelecionado;
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
     * Método para cadastrar Evento no sistema.
     *
     * @return String.
     * @throws ErroInternoException
     * @throws DatasIncorretas
     * @throws EventoExistenteException
     */
    public String CadastrarEvento() throws ErroInternoException, DatasIncorretas, EventoExistenteException {
        try {

            evento.setCriador(BeanUsuario.getInstancia());
            evento.setContVagasEvento(evento.getTotal_vagas());
            this.fachada.adicionarEvento(evento);

            evento = new Evento();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cadastro efetuado com sucesso!"));
        } catch (ErroInternoException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ocorreu um erro no sistema. Tente novamente." + e.getMessage()));
            return null;
        } catch (DatasIncorretas e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Data Final menor que data inicial"));
            return null;
        }
        return "meusEventos.xhtml";

    }

    /**
     * Método para Listar Evento no sistema.
     *
     * @return List.
     * @throws EventoExistenteException
     * @throws EventoInexistenteException
     */
    public List<Evento> listarTudoEvento() throws EventoExistenteException, EventoInexistenteException {
        try {
            List<Evento> eventos = this.fachada.listarTudoEvento();
            return eventos;
        } catch (ErroInternoException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
    }

    /**
     * Método para listar Eventos de um Usuários.
     *
     * @return List.
     * @throws EventoExistenteException
     * @throws EventoInexistenteException
     */
    public List<Evento> eventosUsuario() throws EventoExistenteException, EventoInexistenteException {
        try {
            List<Evento> eventos = this.fachada.EventosUsuario(BeanUsuario.getInstancia().getCpf());
            return eventos;
        } catch (ErroInternoException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
    }

    /**
     * Método atualizar Evento no sistema.
     *
     * @return String.
     */
    public String atualizarEvento() {
        try {
            this.fachada.atualizarEvento(eventoSelecionado);

            FacesContext aviso = FacesContext.getCurrentInstance();
            aviso.addMessage(null, new FacesMessage("Evento Atualizado!"));
        } catch (ErroInternoException | EventoInexistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
        } catch (VagasIncorretasException fd) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Vagas Incorretas: " + fd.getMessage()));
            return "editarEvento.xhtml";
        }

        return "meusEventos.xhtml";
    }

    public String pesquisarEvento() {
        try {
            this.eventosPesquisa = new ArrayList<>();

            //for(Evento e: this.fachada.listarTudoEvento()){
            // if(e.getNome().startsWith(pesquisa)){
            //   this.eventosPesquisa.add(e);
               //} else {
              // }
            //}
            this.eventosPesquisa = this.fachada.buscarNomeListEvento(pesquisa);

            this.pesquisa = "";

            return "resultadoPesquisa.xhtml";

        } catch (ErroInternoException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        } catch (EventoInexistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Evento não encontrado no sistema."));
            return null;
        }

        return null;
    }

    /**
     * Método para apagar Evento do sistema.
     *
     * @param codigo;
     * @return String.
     * @throws EventoInexistenteException
     */
    public String apagarEvento(long codigo) throws EventoInexistenteException {
        try {
            this.fachada.removerEvento(codigo);

            FacesContext aviso = FacesContext.getCurrentInstance();
            aviso.addMessage(null, new FacesMessage("Evento Removido!"));
        } catch (ErroInternoException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ocorreu um erro no sistema. Tente novamente." + ex.getMessage()));
        } catch (EventoInexistenteException ex1) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Evento inexistente!" + ex1.getMessage()));
        } catch (SubEventoInexistenteException ex2) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("SubEvento inexistente!" + ex2.getMessage()));
        } catch (ParticipanteInexistenteException ex3) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Participante inexistente!" + ex3.getMessage()));
        }

        return "paginaProdutos.xhtml";
    }

    /**
     * Método para selecionar Evento no sistema.
     *
     * @param codigo;
     * @return String.
     * @throws EventoInexistenteException
     */
    public String EventoSelect(long codigo) throws EventoInexistenteException {
        try {
            Evento e = this.fachada.buscarCodigoEvento(codigo);
            this.eventoSelecionado = e;
            FacesContext aviso = FacesContext.getCurrentInstance();
            aviso.addMessage(null, new FacesMessage("Evento Selecionado!"));
        } catch (ErroInternoException | EventoInexistenteException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
        }

        return "meusEventos.xhtml";
    }

}
