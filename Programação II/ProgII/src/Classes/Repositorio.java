/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Exceptions.ListaVazia;
import Exceptions.LoginErrado;
import Exceptions.ProjetoRepetido;
import Exceptions.UsernameInexistente;
import Exceptions.UtilizadorRepetido;
import Exceptions.nomeRepetido;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author Nuno
 */
public class Repositorio implements Serializable{
    
    private ArrayList<Projeto> projetos;
    private ArrayList<Utilizador> utilizadores;
    private Utilizador user = null;
    private static Repositorio repo = null;
    private static String ficheiro = "repositorio.txt";
    
    public Repositorio(){
        this.projetos = new ArrayList<>();
        this.utilizadores = new ArrayList<>();
    }
    
    public static Repositorio getInstance() {
        if (repo == null) {
            repo = new Repositorio();
        }
        return repo;
    }
    
    
    
    
    /**
     * GETTERS E SETTERS
     */

    /**
     * @return the projetos
     */
    public ArrayList<Projeto> getProjetos() {
        return projetos;
    }

    /**
     * @param projetos the projetos to set
     */
    public void setProjetos(ArrayList<Projeto> projetos) {
        this.projetos = projetos;
    }

    /**
     * @return the utilizadores
     */
    public ArrayList<Utilizador> getUtilizadores() {
        return utilizadores;
    }

    /**
     * @param utilizadores the utilizadores to set
     */
    public void setUtilizadores(ArrayList<Utilizador> utilizadores) {
        this.utilizadores = utilizadores;
    }
    
    /**
     * @return the ficheiro
     */
    public static String getFicheiro() {
        return ficheiro;
    }

    /**
     * @param aFicheiro the ficheiro to set
     */
    public static void setFicheiro(String aFicheiro) {
        ficheiro = aFicheiro;
    }

    /**
     * @return the user
     */
    public Utilizador getUser() {
        return user;
    }
    
    /**
     * ADICIONAR ADMIN
     */
    
    /**
     * ADICIONAR ADMINISTRADOR
     * @throws UtilizadorRepetido - JÁ EXISTE UM UTILIZADOR REGISTADO 
     */
    public void addAdmin() throws UtilizadorRepetido{
        
        String password = Encripta.encriptaPassword("admin");
        
        for(Utilizador u: utilizadores ){
            if(u.getUsername().equals("admin")){
                u.setNome("admin");
                u.setUsername("admin");
                u.setPassword(password);
                u.setRole(Role.Admin);
            }
        }
        
        Utilizador utilizador = new Utilizador("admin", password, "admin", 0, Role.Admin);
        this.addUtilizador(utilizador);
        
    }
    
    /**
     * LOGIN E REGISTO DE UTILIZADORES
     */
    
    
    /**
     * LOGIN
     * @param user - username do utilizador a logar
     * @param pass - password do utilizador a logar
     * @throws ListaVazia - Se não houver nenhum utilizador registado lança uma exceção de lista vazia
     * @throws LoginErrado - Se o username ou a password estiver errados lança exceção de login errado
     */
        
    public void Login(String user, String pass) throws ListaVazia, LoginErrado {
        
        if(this.utilizadores.isEmpty()){
            throw new ListaVazia("Lista de Utilizadores VAZIA!");
        }
        else{
            for(Utilizador u1: this.utilizadores){
                if(u1.getUsername().equals(user) && u1.getPassword().equals(pass)){
                    System.out.println("Login Efetuado!");
                    this.user = u1;
                    return;
                }
            }
        }
        throw new LoginErrado("O Username ou a Password estão incorretos.");
    }
    
    
    /**
     * REGISTAR UTILIZADORES
     * @param ut - utilizador a registar
     * @throws UtilizadorRepetido - já existe um utilizador igual no sistema
     */
    public void addUtilizador(Utilizador ut) throws UtilizadorRepetido {
        
        for(Utilizador u : utilizadores)
            if(u.getUsername().equals(ut.getNome()))
                throw new UtilizadorRepetido("Username já existe");
        
        
        this.utilizadores.add(ut);
        
    } 
    
    
    /**
     * EDITAR PERFIL DE UTILIZADOR
     * @param antigoUsername - antigo username
     * @param username - novo username a alterar
     * @throws UtilizadorRepetido - já existe utilizador com o nome passado
     * @throws UsernameInexistente  - não existe utilizador com o nome passado
     */
    
    
    public void changeUsername(String antigoUsername, String username) throws UtilizadorRepetido, UsernameInexistente{
        for(Utilizador u: utilizadores)
            if(u.getUsername().equals(username))
                throw new UtilizadorRepetido("Username já existe");
        
        Utilizador util = this.utilizadorPorUsername(antigoUsername);
        
        
        for(Utilizador u: utilizadores)
            if(u.getUsername().equals(antigoUsername)){
                u.setUsername(username);
                utilizadores.remove(util);
                utilizadores.add(u);
                return;
            }
        
    }
    
    
    /**
     * ALTERAR HORAS DIARIAS DO UTILIZADOR
     * @param username - username do utilizador
     * @param horas - novas horas do utilizador
     * @throws UsernameInexistente - não existe utilizador com o nome passado
     */
    public void changeHoras(String username, int horas) throws UsernameInexistente{
                
        Utilizador util = this.utilizadorPorUsername(username);
        
        for(Utilizador u: utilizadores)
            if(u.getUsername().equals(username)){
                u.setnHorasDiarias(horas);
                utilizadores.remove(util);
                utilizadores.add(u);
                return;
            }
        
    }
    
    /**
     * ALTERAR PASSWORD
     * @param username - username do utilizador
     * @param password - password a alterar
     * @throws UsernameInexistente - não existe utilizador com o nome passado
     */
    public void changePassword(String username, String password) throws UsernameInexistente{
        
        Utilizador util = this.utilizadorPorUsername(username);
                
        for(Utilizador u: utilizadores)
            if(u.getUsername().equals(username)){
                u.setPassword(password);
                utilizadores.remove(util);
                utilizadores.add(u);
                return;
            }
        
    }
    
    /**
     * ALTERAR PERMISSOES DE UM UTILIZADOR
     * @param username - username do utilizador a alterar
     * @param role - nova role do utilizador
     * @throws UsernameInexistente - não existe utilizador com o nome passado
     */
    
    public void changeRole(String username, Role role) throws UsernameInexistente{
        
        Utilizador util = this.utilizadorPorUsername(username);
                
        for(int i = 0; i<utilizadores.size(); i++)
            if(utilizadores.get(i).getUsername().equals(username))
                this.utilizadores.get(i).setRole(role);
        
    }
    
    
    /**
     * DEVOLVER UTILIZDORES BASEADO EM ROLES
     * @param role - role do utilizador a usar o programa
     * @return - lista de utilizadores baseado nas permissões do utilizador principal
     */
    
    public ArrayList<Utilizador> utilizadoresPorRole(Role role){
        
        ArrayList<Utilizador> users = new ArrayList<>();
        
        if(role == Role.UserManager){
            for(Utilizador u : this.utilizadores)
                if(u.getRole() == Role.User)
                    users.add(u);
            return users;
        }else{
            return this.getUtilizadores();
        }
    }
    
    
    
    
    
    /**
     * GET USER POR USERNAME
     * @param username - username a procurar
     * @return - utilizador relativo ao username
     * @throws UsernameInexistente - se nao exister utilizador com o nome passado
     */
    
    
    public Utilizador utilizadorPorUsername(String username) throws UsernameInexistente{
        for(Utilizador u: this.utilizadores)
            if(u.getUsername().equals(username))
                return u;
        
        throw new UsernameInexistente("Username indicado não existe");
        
    }
    
    /**
     * ADICIONAR AO HISTORICO
     * @param acao acao a adicionar ao historico
     */
    
    public void addHistorico(String acao){
        this.user.addHistorico(acao);
    }
    
    
    
    /**
     * PROJETOS
     */
    
    /**
     * ADICIONAR PROJETOS À LISTA
     * @param nomeCliente - nome do cliente
     * @param nome - nome do projeto
     * @param preco - preco do projeto
     * @param dono - dono do projeto (username do utilizador)
     * @throws ProjetoRepetido - se já existir um projeto com o nome passado
     */
    
    public void addProjeto(String nomeCliente, String nome, float preco, String dono) throws ProjetoRepetido{
        
        for(Projeto p : projetos)
            if(p.getNome().equals(nome))
                throw new ProjetoRepetido("Já existe um projeto com o nome indicado");
        
        Projeto p = new Projeto(nomeCliente, nome, preco, dono);
        
        projetos.add(p);
        
    }
    
    /**
     * GET PROJETOS POR NOME
     * @param nome - nome do projeto
     * @return - projeto relativo ao nome passado
     */
    
    public Projeto getProjetoByName(String nome){
        
        for(Projeto p: this.projetos)
            if(p.getNome().equals(nome))
                return p;
        
        throw new IllegalArgumentException(String.format("Não existe nenhum projeto com o nome : %s", nome));
        
    }
    
    
    /**
     * GET POSSIVEIS CONVIDADOS
     * @param nome - nome do projeto
     * @return - utilizadores que podem ser convidados para o projeto
     */
    
    public ArrayList<String> getPessoasParaConvidar(String nome){
        ArrayList<String> lista = new ArrayList<>();
        Projeto p = this.getProjetoByName(nome);
        
        for(Utilizador u : utilizadores){
            if(!(p.getConvidados().contains(u.getUsername()) || p.getDono().equals(u.getUsername()))){
                lista.add(u.getUsername());
            }
        }
        return lista;
    }
    
    /**
     * ADICIONAR CONTIVE
     * @param username - username do utilizador a enviar convite
     * @param nomeProjeto - nome do projeto 
     */
    public void addConvite(String username, String nomeProjeto){
        for(int i = 0; i< utilizadores.size(); i++){
            if(utilizadores.get(i).getUsername().equals(username)){
                utilizadores.get(i).addConvite(nomeProjeto);
            }
        }
    }
    
    /**
     * REMOVER CONVITE
     * @param nomeProjeto nome do projeto a remover o convite 
     */
    public void removeConvite(String nomeProjeto){
        this.user.removeConvite(nomeProjeto);
    }
    
    
    /**
     * ADICIONAR CONVIDADO A UM PROJETO
     * @param nomeProjeto - nome do projeto a adicionar
     */
    public void addConvidado(String nomeProjeto){
        for(int i = 0; i<projetos.size(); i++){
            if(projetos.get(i).getNome().equals(nomeProjeto)){
                projetos.get(i).addConvidado(this.user.getUsername());
            }
        }
    }
    
    /**
     * PROJETOS REALTIVOS A UM UTILIZADOR
     * @param username -username do utilizador
     * @return - todos os projetos relativos a um utilizador
     */
    
    public ArrayList<Projeto> getProjetosByUsername(String username){
        
        ArrayList<Projeto> lista = new ArrayList<>();
        
        for(Projeto p:projetos)
            if(p.getDono().equals(username) || p.getConvidados().contains(username))
                lista.add(p);
            
        return lista;
        
    }
    
    
    /**
     * REMOVE UTILIZADOR DE PROJETO
     * @param username - username do utilizador
     * @param projeto - nome do projeto
     */
    
    public void removeUtilizadorDeProjeto(String username, String projeto){
        for(int i = 0; i<projetos.size();i++)
            if(this.projetos.get(i).getConvidados().contains(username)){
                this.projetos.get(i).removeConvidado(username);
                return;
            }
    }
    
    /**
     * GET PROJETO ATRAVÉS DE TAREFA
     * @param t - Tarefa
     * @return - Projeto que contem a tarefa
     */
    public Projeto getProjetoByTarefa(Tarefa t){
        Projeto p = null;
        
        for(Projeto projeto : projetos){
            if(projeto.getTarefas().contains(t)){
                p= projeto;
                break;
            }
        }
        
        
        return p;
    }
    
    
    
    
    
    
    /**
     * ADICIONAR TAREFA
     * @param descricao - descrição da tarefa
     * @param nome - nome da tarefa
     * @param dataInicio - data inciail da tarefa
     * @param preco - preco da tarefa
     * @throws nomeRepetido - Se houver uma tarefa existente com o mesmo nome lança exceçao
     */
    
    
    public void addTarefa(String descricao, String nome, Date dataInicio, float preco) throws nomeRepetido{
        
        Tarefa t = new Tarefa(descricao, nome, dataInicio, preco);
        this.user.addTarefa(t);
        
    }
    
    
   /**
    * REMOVE TAREFA
    * @param username - username do utilizador
    * @param nome - nome da tarefa a remover
    */
    
    
    
    public void removeTarefa(String username, String nome){
        
        Tarefa tarefa = this.getTarefaNome(username, nome);
        
        for(int i = 0; i < utilizadores.size(); i++){
            if(utilizadores.get(i).getUsername().equals(username)){
                utilizadores.get(i).removeTarefa(tarefa);
            }
        }
        
    }
    
    /**
     * GET TAREFA PELO NOME
     * @param username - username do utilizador
     * @param nome - nome da tarefa
     * @return tarefa
     */
    
    public Tarefa getTarefaNome(String username, String nome){
        Tarefa tarefa = null;
        
        for(Utilizador u : this.utilizadores){
            if(u.getUsername().equals(username)){
                for(Tarefa t : u.getTarefas()){
                    if(t.getNome().equals(nome)){
                        tarefa = t;
                        break;
                    }
                }
                break;
            }
        }
        
        return tarefa;
        
    }
    
    
    /**
     * ALTERA DESCRICAO
     * @param username - username do utilizador que altera a tarefa
     * @param nomeTarefa - nome da tarefa
     * @param novaDescricao - nova descricao da tarefa
     */
    
    public void alteraDescricaoTarefa(String username, String nomeTarefa, String novaDescricao){
        
        for(int i=0 ; i<utilizadores.size(); i++){
            if(utilizadores.get(i).getUsername().equals(username)){
                for(int j = 0; j< utilizadores.get(i).getTarefas().size();j++){
                    if(utilizadores.get(i).getTarefas().get(j).getNome().equals(nomeTarefa)){
                        utilizadores.get(i).getTarefas().get(j).setDescricao(novaDescricao);
                        return;
                    }
                }
                return;
            }
        }
        
    }
    
    
    
    /**
     * ALTERA PRECO TAREFA
     * @param username - username do utilizador que altera a tarefa
     * @param nomeTarefa - 
     * @param preco - novo preco da tarefa
     */
    
    public void alteraPrecoTarefa(String username, String nomeTarefa, float preco){
        
        for(int i=0 ; i<utilizadores.size(); i++){
            if(utilizadores.get(i).getUsername().equals(username)){
                for(int j = 0; j< utilizadores.get(i).getTarefas().size();j++){
                    if(utilizadores.get(i).getTarefas().get(j).getNome().equals(nomeTarefa)){
                        utilizadores.get(i).getTarefas().get(j).setPreco(preco);
                        return;
                    }
                }
                return;
            }
        }
        
    }
    
    
    /**
     * ADICIONAR TAEFA A PROJETO
     * @param username - username do utilizador que adiciona a tarefa
     * @param tarefaNome - nome da tarefa a adicionar
     * @param projeto - nome do projeto a alterar
     */
    
    public void addTarefaProjeto(String username, String tarefaNome, String projeto){
        Tarefa tarefa = this.getTarefaNome(username, tarefaNome);
        
        for(int i=0; i<projetos.size(); i++){
            if(projetos.get(i).getTarefas().contains(tarefa)){
                this.removeTarefaProjeto(username, tarefaNome, projeto);
                break;
            }
        }
        
        for(int i=0; i<projetos.size(); i++){
            if(projetos.get(i).getNome().equals(projeto)){
                if(tarefa.getPreco() == 0){
                    tarefa.setPreco(projetos.get(i).getPrecoHora());
                }
                
                projetos.get(i).addTarefa(tarefa);
            }
        }
    }
    
    /**
     * REMOVER TAREFA DO PROJETO EM QUESTÃO
     * @param username - nome do utilizador que remove a tarefa
     * @param tarefaNome - nome da tarefa a remover
     * @param projeto - nome do projeto onde está inserida a tarefa
     */
    
    public void removeTarefaProjeto(String username, String tarefaNome, String projeto){
        for(int i=0; i<projetos.size(); i++){
            if(projetos.get(i).getNome().equals(projeto)){
                Tarefa tarefa = this.getTarefaNome(username, tarefaNome);
                projetos.get(i).removeTarefa(tarefa);
            }
        }
    }
    
    
    
    
    /**
     * TAREFAS  TERMINADAS
     * @param dateInicio
     * @param dateFim
     * @return tarefas que foram terminadas 
     */
    
    public ArrayList<Tarefa> tarefasTerminadas(Utilizador user, Date dateInicio, Date dateFim){
        
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        
        for(Tarefa t: user.getTarefas()){
            if(t.getDataFim().after(dateInicio) && t.getDataFim().before(dateFim)){
                tarefas.add(t);
            }
        }
        
        return tarefas;
        
    }
    
    /**
     * TAREFAS DO UTILIZADOR
     * @param datainicio - data inicial
     * @param datafim - data final
     * @param terminado - boolean se a tarefa foi terminada
     * @param u - utilizador em questão
     * @return - tarefas do utilizador passado como parametro com base na data inicial final e se foram terminadas ou nao
     */
    
    
    
    public ArrayList<Tarefa> getTarefasRelatorioUtilizador(Date datainicio, Date datafim, boolean terminado, Utilizador u ){
        
        ArrayList<Tarefa> lista = new ArrayList<>();
        
        for(Tarefa t: u.getTarefas()){
            if(terminado){
                if(t.isFinalizada()){
                    if(t.getDataInicio().after(datainicio) && t.getDataFim().before(datafim)){
                        lista.add(t);
                    } 
                }
            }else{
                if(t.getDataInicio().after(datainicio) && t.getDataInicio().before(datafim)){
                    lista.add(t);
                }
            }
        }
        return lista;
        
    }
    
    /**
     * TAREFAS DO PROJETO
     * @param datainicio - data inicial
     * @param datafim - data final
     * @param terminado - boolean se a tarefa foi terminada
     * @param p - projeto em questão
     * @return - tarefas do projeto passado como parametro com base na data inicial final e se foram terminadas ou nao
     */
    
    
    public ArrayList<Tarefa> getTarefasRelatorioProjeto(Date datainicio, Date datafim, boolean terminado, Projeto p ){
        
        ArrayList<Tarefa> lista = new ArrayList<>();
        
        for(Tarefa t: p.getTarefas()){
            if(terminado){
                if(t.isFinalizada()){
                    if(t.getDataInicio().after(datainicio) && t.getDataFim().before(datafim)){
                        lista.add(t);
                    } 
                }
            }else{
                if(t.getDataInicio().after(datainicio) && t.getDataInicio().before(datafim)){
                    lista.add(t);
                }
            }
        }
        return lista;
        
    }
    
    
    
    
    
    
    
    
    
    /**
     * ESCRITA EM FICHEIROS
     * @param ficheiro - nome do ficheiro a escrever
     */
    
    
    public static void serializar(String ficheiro) {
        // Serializar um objeto para ficheiro
        try (FileOutputStream fileOut = new FileOutputStream(ficheiro); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(repo);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in " + ficheiro);
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
    
   /**
    * LEITURA DO FICHEIRO
    * @param ficheiro - nome do ficheiro a ler
    */
     
    public void desserializar(String ficheiro) {
        try (FileInputStream fileIn = new FileInputStream(ficheiro); ObjectInputStream in = new ObjectInputStream(fileIn);) {
            repo = (Repositorio) in.readObject();
            in.close();
            fileIn.close();
            System.out.printf("Ficheiro " + ficheiro + " lido com sucesso.");
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Cliente class not found. " + ex.getMessage());
        }
    }

    

    
    
    
}
