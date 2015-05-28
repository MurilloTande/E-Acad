/** Classe para objetos do tipo Evento, onde serão contidos, atributos e métodos para o mesmo.<p/>
 * 
 *  @author Murillo Tande
 *  @author Matheus Barbosa
 *  @author Hugo Calado
 *  @author Felipe Xavier
 */
package eacad.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * "@Entity" é usada para informar que a classe Evento é uma entidade no JPA.
 */
@Entity
public class Evento implements Serializable{
    
    private long codigo;
    private String nome;
    private String descricao;
    private Date data_inicio;
    private Date data_final;
    private String localidade;
    private String endereco;
    private String cidade;
    private String estado;
    private int total_vagas;
    private Usuario criador;
    private List<Participante> participantes;
    private List<SubEvento> subEventos;
    

    /**
     * Construtor vazio.
     */
    public Evento() {
    }

    /**
     * Construtor com todos os atritutos inicializados.
     * @param codigo
     * @param nome
     * @param descricao
     * @param data_inicio
     * @param data_final
     * @param localidade
     * @param endereco
     * @param cidade
     * @param total_vagas
     * @param estado
     */
    public Evento(long codigo, String nome, String descricao, Date data_inicio, Date data_final, String localidade, String endereco, String cidade, String estado, int total_vagas) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.data_inicio = data_inicio;
        this.data_final = data_final;
        this.localidade = localidade;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.total_vagas = total_vagas;  
    }
    
    
    /**
     * @return long - codigo do evento.
     * "@Id" e "@GeneratedValue" indicam respectivamente que o atributo é a chave principal do Evento e nele o valor é gerado automaticamente. 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getCodigo() {
        return codigo;
    }
    /**
     * @param codigo
     */
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
    
    /**
     * "@Column" define que a coluna será única e não poderá receber um atributo null
     * @return  String - Nome do Evento.
     * 
    */
    @Column (unique = true, nullable = false)
    public String getNome() {
        return nome;
    }

    /**
     * @param nome
    */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * @return String - Descrição do evento
    */
    public String getDescricao() {
        return descricao;
    }
    
    /**
     * @param descricao
    */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return Date - Data em que o evento será iniciado.
     * "@Temporal" é usado para definir o tipo Date no JPA.
    */
    @Temporal(TemporalType.DATE)
    public Date getData_inicio() {
        return data_inicio;
    }

    /**
     * @param data_inicio
    */
    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    /**
     * @return Date - Data em que o evento terá fim.
     * "@Temporal" é usado para definir o tipo Date no JPA.
    */
    @Temporal(TemporalType.DATE)
    public Date getData_final() {
        return data_final;
    }
    
    /**
     * @param data_final
     */
    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }
    
    /**
     * @return String - Local onde ocorrerá o evento.
     */
    public String getLocalidade() {
        return localidade;
    }

    /**
     * @param localidade
     */
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    /**
     * @return String - Endereço onde ocorrerá o evento
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return String - Cidade onde ocorrerá o evento.
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return String - Estado onde ocorrerá o evento.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return int = Total de vagas disponibilizadas.
     */
    public int getTotal_vagas() {
        return total_vagas;
    }

    /**
     * @param total_vagas
     */
    public void setTotal_vagas(int total_vagas) {
        this.total_vagas = total_vagas;
    }
    
    /**
     * @return Usuario - Indica o criador do evento.
     * "@ManyToOne" anotação utilizada para informar uma relação Muitos para um.
     */
    @ManyToOne
    public Usuario getCriador(){
        return this.criador;
    }
    
    /**
     * @param criador
     */
    public void setCriador(Usuario criador){
    this.criador = criador;
    }
    
    /**
     * @return List - retorna todos os subeventos do Evento principal<p/>
     * @see java.util.List<p/>
     * "@OneToMany" indica o mapeamento de um para muitos no JPA, e o mappedBy indica onde está mapeado.
     */
    @OneToMany(mappedBy = "eventoPai")
    public List<SubEvento> getSubEventos() {
        return subEventos;
    }

    /**
     * @param subEventos
     */
    public void setSubEventos(List<SubEvento> subEventos) {
        this.subEventos = subEventos;
    }

   /**
     * @return List - retorna lista de participantes registrados no evento.
     * @see java.util.List
     * "@ManyToMany" indica muitos para muitos no JPA.
    */
    @OneToMany(mappedBy = "evento")
    @ManyToMany
    public List<Participante> getParticipantes() {
        return participantes;
    }
    
    /**
     * @param participantes
     */
    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    /**
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.codigo ^ (this.codigo >>> 32));
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + Objects.hashCode(this.descricao);
        hash = 79 * hash + Objects.hashCode(this.data_inicio);
        hash = 79 * hash + Objects.hashCode(this.data_final);
        hash = 79 * hash + Objects.hashCode(this.localidade);
        hash = 79 * hash + Objects.hashCode(this.endereco);
        hash = 79 * hash + Objects.hashCode(this.cidade);
        hash = 79 * hash + Objects.hashCode(this.estado);
        hash = 79 * hash + this.total_vagas;
       
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evento other = (Evento) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.data_inicio, other.data_inicio)) {
            return false;
        }
        if (!Objects.equals(this.data_final, other.data_final)) {
            return false;
        }
        if (!Objects.equals(this.localidade, other.localidade)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (this.total_vagas != other.total_vagas) {
            return false;
        }
        
        return true;
    }

 
    
    
    
}
