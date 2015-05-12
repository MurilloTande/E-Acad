/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eacad.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    private Usuario usuario;


    public Evento() {
    }

    public Evento(long codigo, String nome, String descricao, Date data_inicio, Date data_final, String localidade, String endereco, String cidade, String estado, int total_vagas, Usuario usuario) {
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
        this.usuario = usuario;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
    
    @Column (unique = true, nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Temporal(TemporalType.DATE)
    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    @Temporal(TemporalType.DATE)
    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }
    
    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTotal_vagas() {
        return total_vagas;
    }

    public void setTotal_vagas(int total_vagas) {
        this.total_vagas = total_vagas;
    }
    
    public Usuario getusuario(){
    return usuario;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario=usuario;
    }
    
    
    
}
