package Classes;

import java.io.Serializable;
import java.util.Date;

public class Tarefa implements Serializable{

    private Date dataInicio;
    private Date dataFim;
    private String descricao;
    private String nome;
    private float preco;
    private boolean finalizada;

    /**
     * CONSTRUTOR
     * @param descricao - descricao da tarefa
     * @param nome - nome da tarefa
     * @param dataInicio - data inicial da tarefa
     * @param preco - preco da tarefa
     */
    
    public Tarefa(String descricao, String nome, Date dataInicio, float preco){
        this.dataInicio = dataInicio;
        this.descricao = descricao;
        this.nome = nome;
        this.preco = preco;
        this.finalizada = false;
    }

    /**
     * @return the dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim the dataFim to set
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the preco
     */
    public float getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(float preco) {
        this.preco = preco;
    }

    /**
     * @return the finalizada
     */
    public boolean isFinalizada() {
        return finalizada;
    }

    /**
     * @param finalizada the finalizada to set
     */
    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }
    
}
