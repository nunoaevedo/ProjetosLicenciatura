package Classes;

import Classes.Role;
import Exceptions.nomeRepetido;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Utilizador implements Serializable {

    //private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String nome;
    private int nHorasDiarias;
    private Role role;
    private ArrayList<String> historico;
    private ArrayList<Tarefa> tarefas;
    private ArrayList<String> convites;

    
    /**
     * CONSTRUTOR
     * @param username - username do utilizador
     * @param password - password do utilizador
     * @param nome - nome do utilizador
     * @param nHorasDiarias - horas diarias do utilizador
     * @param role - role do utilizador (User, UserManager, Admin)
     */
    public Utilizador( String username, String password, String nome, int nHorasDiarias, Role role){
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.nHorasDiarias = nHorasDiarias;
        this.role = role;
        this.historico = new ArrayList<>();
        this.tarefas = new ArrayList<>();
        this.convites = new ArrayList<>();
    }
    
    
     /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return the nHorasDiarias
     */
    public int getnHorasDiarias() {
        return nHorasDiarias;
    }

    /**
     * @param nHorasDiarias the nHorasDiarias to set
     */
    public void setnHorasDiarias(int nHorasDiarias) {
        this.nHorasDiarias = nHorasDiarias;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * @return the historico
     */
    public ArrayList<String> getHistorico() {
        return historico;
    }

    /**
     * @param historico the historico to set
     */
    public void setHistorico(ArrayList<String> historico) {
        this.historico = historico;
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
     * @return the convites
     */
    public ArrayList<String> getConvites() {
        return convites;
    }

    /**
     * @param convites the convites to set
     */
    public void setConvites(ArrayList<String> convites) {
        this.convites = convites;
    }
    
    
    /**
     * 
     * @param name - nome da tarefa
     * @return tarefa
     */
    public Tarefa getTarefaByName(String name){
        for(Tarefa t : tarefas)
            if(t.getNome().equals(name))
                return t;
        
        throw new IllegalArgumentException(String.format("Não existe nenhuma tarefa com o nome : %s", name));
    }
    
    
    /**
     * ADICIONAR TAREFA
     * @param tarefa - tarefa a adicionar
     * @throws nomeRepetido - se já existir uma tarefa com o nome indicado
     */
    public void addTarefa(Tarefa tarefa) throws nomeRepetido{
        for(Tarefa t : getTarefas())
            if(t.getNome().equals(tarefa.getNome()))
                throw new nomeRepetido("Já existe uma tarefa com o nome indicado");
        
        this.getTarefas().add(tarefa);
    }

    /**
     * TERMINAR TAREFA
     * @param tarefa - tarefa a finalizar
     * @param datafim - data de fim da tarefa
     */

    public void terminaTarefa(String tarefa, Date datafim){
        Tarefa t = getTarefaByName(tarefa);

        if (datafim.before(t.getDataInicio())){
            throw new IllegalArgumentException(String.format("A data final indicada(%s) é anterior à data inicial(%s)", datafim.toString(), t.getDataInicio().toString()));
        }

        t.setDataFim(datafim);
        t.setFinalizada(true);

    }
    /**
     * REMOVER TAREFA DA LISTA DO UTILIZADOR
     * @param tarefa - tarefa a remover
     */
    public void removeTarefa(Tarefa tarefa){
        this.tarefas.remove(tarefa);
    }
    
    /**
     * ADICIONAR HISTORICO
     * @param acao - acao a adicionar ao historico
     */
    
    public void addHistorico(String acao){
        this.historico.add(acao);
    }
  
    /**
     * ADICIONAR CONVITE
     * @param nomeProjeto - nome do projeto para o qual foi convidado 
     */
  
    public void addConvite(String nomeProjeto){
        this.convites.add(nomeProjeto);
    }
  
  
    /**
     * REMOVER CONVITE
     * @param nomeProjeto - nome projeto a remover dos convites 
     */
    public void removeConvite(String nomeProjeto){
        this.convites.remove(nomeProjeto);
    }

    

    

    
    
    








}
