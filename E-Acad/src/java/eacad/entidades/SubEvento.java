/**
 * Classe para objetos do tipo subEvento, onde serão contidos, atributos e
 * métodos para o mesmo.<p/>
 *
 * @author Murillo Tande
 * @author Matheus Barbosa
 * @author Hugo Calado
 * @author Felipe Xavier
 */
package eacad.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class SubEvento implements Serializable {

    private long codigo;
    private String nome;
    private String descricao;
    private String apresentador;
    private String tipo;
    private Date data_inicio;
    private Date data_final;
    private int total_vagas;
    private Evento eventoPai;
    private int contVagasSubEvento;

    /**
     * Construtor vazio.
     */
    public SubEvento() {
    }

    /**
     * @param codigo;
     * @param nome;
     * @param descricao;
     * @param apresentador;
     * @param tipo;
     * @param data_inicio;
     * @param data_final;
     * @param total_vagas;
     */
    public SubEvento(long codigo, String nome, String descricao, String apresentador, String tipo, Date data_inicio, Date data_final, int total_vagas) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.apresentador = apresentador;
        this.tipo = tipo;
        this.data_inicio = data_inicio;
        this.data_final = data_final;
        this.total_vagas = total_vagas;

    }

    /**
     * @return long.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getCodigo() {
        return codigo;
    }

    /**
     * @param codigo;
     */
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    /**
     * @return int.
     */
    public int getContVagasSubEvento() {
        return contVagasSubEvento;
    }

    /**
     * @param contVagasSubEvento.
     */
    public void setContVagasSubEvento(int contVagasSubEvento) {
        this.contVagasSubEvento = contVagasSubEvento;
    }

    /**
     * @return Stirng.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome;
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return String.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao;
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return String.
     */
    public String getApresentador() {
        return apresentador;
    }

    /**
     * @param apresentador;
     */
    public void setApresentador(String apresentador) {
        this.apresentador = apresentador;
    }

    /**
     * @return String.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo;
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return Date.
     */
    @Temporal(TemporalType.DATE)
    public Date getData_inicio() {
        return data_inicio;
    }

    /**
     * @param data_inicio;
     */
    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    /**
     * @return Date.
     */
    @Temporal(TemporalType.DATE)
    public Date getData_final() {
        return data_final;
    }

    /**
     * @param data_final;
     */
    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }

    /**
     * @return int.
     */
    public int getTotal_vagas() {
        return total_vagas;
    }

    /**
     * @param total_vagas;
     */
    public void setTotal_vagas(int total_vagas) {
        this.total_vagas = total_vagas;
    }

    /**
     * @return Evento.
     */
    @ManyToOne
    public Evento getEventoPai() {
        return eventoPai;
    }

    /**
     * @param eventoPai;
     */
    public void setEventoPai(Evento eventoPai) {
        this.eventoPai = eventoPai;
    }

}
