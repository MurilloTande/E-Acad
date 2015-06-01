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

/**
 * "@Entity" é usada para informar que a classe subEvento é uma entidade no JPA.
 */
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
     * @return long - Retorna codigo do subEvento. "@Id" e "@GeneratedValue"
     * indicam respectivamente que o atributo é a chave principal do subEvento e
     * nele o valor é gerado automaticamente.
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
     * @return Stirng - Retorna o nome
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
     * @return String - Retorna a descrição
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
     * @return String - Retorna o apresentador
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
     * @return String - Retorna o tipo de subEvento.
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
     * @return Date - Retorna a data de inicio do subEvento
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
     * @return Date - Retorna a data em que o subEvento termina.
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
     * @return int - Retorna o total de vagas para inscrição.
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
     * @return Evento - Retorna o eventoPai
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
