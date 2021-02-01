/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BLL.exceptions.NonexistentEntityException;
import BLL.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DAL.Codpostal;
import DAL.Utilizador;
import DAL.Consulta;
import DAL.Fatura;
import DAL.Paciente;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nuno
 */
public class PacienteJpaController implements Serializable {
    
    private static final String PERSISTENCE_UNIT_NAME = "ProjetoLibraryPU";

    public PacienteJpaController() {
        this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paciente paciente) throws PreexistingEntityException, Exception {
        if (paciente.getConsultaList() == null) {
            paciente.setConsultaList(new ArrayList<Consulta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Codpostal codPostal = paciente.getCodPostal();
            if (codPostal != null) {
                codPostal = em.getReference(codPostal.getClass(), codPostal.getCodPostal());
                paciente.setCodPostal(codPostal);
            }
            Utilizador idUser = paciente.getIdUser();
            if (idUser != null) {
                idUser = em.getReference(idUser.getClass(), idUser.getId());
                paciente.setIdUser(idUser);
            }
            List<Consulta> attachedConsultaList = new ArrayList<Consulta>();
            for (Consulta consultaListConsultaToAttach : paciente.getConsultaList()) {
                consultaListConsultaToAttach = em.getReference(consultaListConsultaToAttach.getClass(), consultaListConsultaToAttach.getId());
                attachedConsultaList.add(consultaListConsultaToAttach);
            }
            paciente.setConsultaList(attachedConsultaList);
            em.persist(paciente);
            if (codPostal != null) {
                codPostal.getPacienteList().add(paciente);
                codPostal = em.merge(codPostal);
            }
            if (idUser != null) {
                idUser.getPacienteList().add(paciente);
                idUser = em.merge(idUser);
            }
            for (Consulta consultaListConsulta : paciente.getConsultaList()) {
                Paciente oldIdPacOfConsultaListConsulta = consultaListConsulta.getIdPac();
                consultaListConsulta.setIdPac(paciente);
                consultaListConsulta = em.merge(consultaListConsulta);
                if (oldIdPacOfConsultaListConsulta != null) {
                    oldIdPacOfConsultaListConsulta.getConsultaList().remove(consultaListConsulta);
                    oldIdPacOfConsultaListConsulta = em.merge(oldIdPacOfConsultaListConsulta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPaciente(paciente.getId()) != null) {
                throw new PreexistingEntityException("Paciente " + paciente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paciente paciente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente persistentPaciente = em.find(Paciente.class, paciente.getId());
            Codpostal codPostalOld = persistentPaciente.getCodPostal();
            Codpostal codPostalNew = paciente.getCodPostal();
            Utilizador idUserOld = persistentPaciente.getIdUser();
            Utilizador idUserNew = paciente.getIdUser();
            List<Consulta> consultaListOld = persistentPaciente.getConsultaList();
            List<Consulta> consultaListNew = paciente.getConsultaList();
            if (codPostalNew != null) {
                codPostalNew = em.getReference(codPostalNew.getClass(), codPostalNew.getCodPostal());
                paciente.setCodPostal(codPostalNew);
            }
            if (idUserNew != null) {
                idUserNew = em.getReference(idUserNew.getClass(), idUserNew.getId());
                paciente.setIdUser(idUserNew);
            }
            List<Consulta> attachedConsultaListNew = new ArrayList<Consulta>();
            for (Consulta consultaListNewConsultaToAttach : consultaListNew) {
                consultaListNewConsultaToAttach = em.getReference(consultaListNewConsultaToAttach.getClass(), consultaListNewConsultaToAttach.getId());
                attachedConsultaListNew.add(consultaListNewConsultaToAttach);
            }
            consultaListNew = attachedConsultaListNew;
            paciente.setConsultaList(consultaListNew);
            paciente = em.merge(paciente);
            if (codPostalOld != null && !codPostalOld.equals(codPostalNew)) {
                codPostalOld.getPacienteList().remove(paciente);
                codPostalOld = em.merge(codPostalOld);
            }
            if (codPostalNew != null && !codPostalNew.equals(codPostalOld)) {
                codPostalNew.getPacienteList().add(paciente);
                codPostalNew = em.merge(codPostalNew);
            }
            if (idUserOld != null && !idUserOld.equals(idUserNew)) {
                idUserOld.getPacienteList().remove(paciente);
                idUserOld = em.merge(idUserOld);
            }
            if (idUserNew != null && !idUserNew.equals(idUserOld)) {
                idUserNew.getPacienteList().add(paciente);
                idUserNew = em.merge(idUserNew);
            }
            for (Consulta consultaListOldConsulta : consultaListOld) {
                if (!consultaListNew.contains(consultaListOldConsulta)) {
                    consultaListOldConsulta.setIdPac(null);
                    consultaListOldConsulta = em.merge(consultaListOldConsulta);
                }
            }
            for (Consulta consultaListNewConsulta : consultaListNew) {
                if (!consultaListOld.contains(consultaListNewConsulta)) {
                    Paciente oldIdPacOfConsultaListNewConsulta = consultaListNewConsulta.getIdPac();
                    consultaListNewConsulta.setIdPac(paciente);
                    consultaListNewConsulta = em.merge(consultaListNewConsulta);
                    if (oldIdPacOfConsultaListNewConsulta != null && !oldIdPacOfConsultaListNewConsulta.equals(paciente)) {
                        oldIdPacOfConsultaListNewConsulta.getConsultaList().remove(consultaListNewConsulta);
                        oldIdPacOfConsultaListNewConsulta = em.merge(oldIdPacOfConsultaListNewConsulta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = paciente.getId();
                if (findPaciente(id) == null) {
                    throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente paciente;
            try {
                paciente = em.getReference(Paciente.class, id);
                paciente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.", enfe);
            }
            Codpostal codPostal = paciente.getCodPostal();
            if (codPostal != null) {
                codPostal.getPacienteList().remove(paciente);
                codPostal = em.merge(codPostal);
            }
            Utilizador idUser = paciente.getIdUser();
            if (idUser != null) {
                idUser.getPacienteList().remove(paciente);
                idUser = em.merge(idUser);
            }
            List<Consulta> consultaList = paciente.getConsultaList();
            for (Consulta consultaListConsulta : consultaList) {
                consultaListConsulta.setIdPac(null);
                consultaListConsulta = em.merge(consultaListConsulta);
            }
            em.remove(paciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paciente> findPacienteEntities() {
        return findPacienteEntities(true, -1, -1);
    }

    public List<Paciente> findPacienteEntities(int maxResults, int firstResult) {
        return findPacienteEntities(false, maxResults, firstResult);
    }

    private List<Paciente> findPacienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paciente.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Paciente> findPacienteEntities(String nome){
        List<Paciente> pac = new ArrayList<>();
        
        for(Paciente p : findPacienteEntities())
            if(p.getNome().contains(nome)) pac.add(p);
        
        
        return pac;        
        
    }

    public Paciente findPaciente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paciente.class, id);
        } finally {
            em.close();
        }
    }

    public int getPacienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paciente> rt = cq.from(Paciente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Paciente findPacienteByUser(String username){
        UtilizadorJpaController uc = new UtilizadorJpaController();
        
        return uc.findUtilizadorNome(username).getPacienteList().get(0);
   
    }
    public List<Consulta> getConsultasPorRealizar(Paciente pac){
        List<Consulta> list =  new ArrayList<>();
        
        Short s = 0;
        
        Date d = new Date(); 
        for(Consulta c : pac.getConsultaList())
            if(c.getRealizado() == s && c.getData().after(d)) 
                list.add(c);
        
        
        return list;
    }

    public List<Consulta> getConsultasNaoRealizadas(Paciente pac){
        List<Consulta> list =  new ArrayList<>();
        
        Short s = 0;
        
        Date d = new Date(); 
        for(Consulta c : pac.getConsultaList())
            if(c.getRealizado() == s && c.getData().before(d)) 
                list.add(c);
        
        
        return list;
    }
    
    public List<Consulta> getConsultasRealizadasSemFatura(Paciente pac){
        List<Consulta> list =  new ArrayList<>();
        
        for(Consulta c : pac.getConsultaList()){
            try{
                c.getFatura();      
            }catch(NullPointerException e){
                list.add(c);
            }
        }
        
        
        return list;
    }
    
    public List<Fatura> getFaturas(Paciente pac){
        List<Fatura> list =  new ArrayList<>();
        
        for(Consulta c : pac.getConsultaList()){
            try{
                c.getFatura();
                list.add(c.getFatura());
            }catch(NullPointerException e){
                
            } 
        }
            
        
        
        return list;
    }
    
    
    
    
    
}
