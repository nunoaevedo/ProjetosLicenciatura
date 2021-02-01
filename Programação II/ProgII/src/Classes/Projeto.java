package Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Projeto implements Serializable{

    private String nomeCliente;
    private String nome;
    private float precoHora;
    private ArrayList<Tarefa> tarefas;
    private String dono;
    private ArrayList<String> convidados;
    
    /**
     * CONSTRUTOR
     * @param nomeCliente - nome do cliente do projeto
     * @param nome - nome do projeto
     * @param precoHora - preco por hora do projeto
     * @param dono - username do dono do projeto
     */

    public Projeto(String nomeCliente, String nome, float precoHora, String dono){
        this.nomeCliente = nomeCliente;
        this.nome = nome;
        this.precoHora = precoHora;
        this.tarefas = new ArrayList<>();
        this.dono = dono;
        this.convidados = new ArrayList<>();
    }

    /**
     * @return the nomeCliente
     */
    public String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * @param nomeCliente the nomeCliente to set
     */
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
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
     * @return the precoHora
     */
    public float getPrecoHora() {
        return precoHora;
    }

    /**
     * @param precoHora the precoHora to set
     */
    public void setPrecoHora(float precoHora) {
        this.precoHora = precoHora;
    }

    /**
     * @return the tarefas
     */
    public ArrayList<Tarefa> getTarefas() {
        return tarefas;
    }

    /**
     * @param tarefas the tarefas to set
     */
    public void setTarefas(ArrayList<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    /**
     * @return the dono
     */
    public String getDono() {
        return dono;
    }

    /**
     * @param dono the dono to set
     */
    public void setDono(String dono) {
        this.dono = dono;
    }

    /**
     * @return the convidados
     */
    public ArrayList<String> getConvidados() {
        return convidados;
    }

    /**
     * @param convidados the convidados to set
     */
    public void setConvidados(ArrayList<String> convidados) {
        this.convidados = convidados;
    }

    
    /**
     * ADICIONAR UTILIZADOR CONVIDADO AO PROJETO
     * @param username - uername do utilizador a adicionar
     */
    
    public void addConvidado(String username){
        this.convidados.add(username);
    }
    
    /**
     * REMOVER CONVIDADO
     * @param username -username do utilizador a remover
     */
    
    public void removeConvidado(String username){
        this.convidados.remove(username);
    }

    /**
     * ADICIONAR TAREFA AO PROJETO
     * @param t - tarefa a adicionar
     */
    public void addTarefa(Tarefa t){
        if(this.tarefas.contains(t)){
            throw new IllegalArgumentException("Tarefa já existe no projeto");
        }
        
        this.tarefas.add(t);
    }
    
    /**
     * REMOVER TAREFA DO PROJETO
     * @param t - tarefa a remover
     */
    
    public void removeTarefa(Tarefa t){
        if(this.tarefas.contains(t)){
            this.tarefas.remove(t);
        }
        
        throw new IllegalArgumentException("Tarefa não existe no projeto");
    }






}
