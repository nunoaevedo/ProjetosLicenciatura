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
import DAL.Especialidade;
import DAL.Utilizador;
import DAL.Consulta;
import DAL.Medico;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nuno
 */
public class MedicoJpaController implements Serializable {
    
    private static final String PERSISTENCE_UNIT_NAME = "ProjetoLibraryPU";

    public MedicoJpaController() {
        this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Medico medico) throws PreexistingEntityException, Exception {
        if (medico.getConsultaList() == null) {
            medico.setConsultaList(new ArrayList<Consulta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Codpostal codPostal = medico.getCodPostal();
            if (codPostal != null) {
                codPostal = em.getReference(codPostal.getClass(), codPostal.getCodPostal());
                medico.setCodPostal(codPostal);
            }
            Especialidade idEsp = medico.getIdEsp();
            if (idEsp != null) {
                idEsp = em.getReference(idEsp.getClass(), idEsp.getId());
                medico.setIdEsp(idEsp);
            }
            Utilizador idUser = medico.getIdUser();
            if (idUser != null) {
                idUser = em.getReference(idUser.getClass(), idUser.getId());
                medico.setIdUser(idUser);
            }
            List<Consulta> attachedConsultaList = new ArrayList<Consulta>();
            for (Consulta consultaListConsultaToAttach : medico.getConsultaList()) {
                consultaListConsultaToAttach = em.getReference(consultaListConsultaToAttach.getClass(), consultaListConsultaToAttach.getId());
                attachedConsultaList.add(consultaListConsultaToAttach);
            }
            medico.setConsultaList(attachedConsultaList);
            em.persist(medico);
            if (codPostal != null) {
                codPostal.getMedicoList().add(medico);
                codPostal = em.merge(codPostal);
            }
            if (idEsp != null) {
                idEsp.getMedicoList().add(medico);
                idEsp = em.merge(idEsp);
            }
            if (idUser != null) {
                idUser.getMedicoList().add(medico);
                idUser = em.merge(idUser);
            }
            for (Consulta consultaListConsulta : medico.getConsultaList()) {
                Medico oldIdMedOfConsultaListConsulta = consultaListConsulta.getIdMed();
                consultaListConsulta.setIdMed(medico);
                consultaListConsulta = em.merge(consultaListConsulta);
                if (oldIdMedOfConsultaListConsulta != null) {
                    oldIdMedOfConsultaListConsulta.getConsultaList().remove(consultaListConsulta);
                    oldIdMedOfConsultaListConsulta = em.merge(oldIdMedOfConsultaListConsulta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMedico(medico.getId()) != null) {
                throw new PreexistingEntityException("Medico " + medico + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Medico medico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medico persistentMedico = em.find(Medico.class, medico.getId());
            Codpostal codPostalOld = persistentMedico.getCodPostal();
            Codpostal codPostalNew = medico.getCodPostal();
            Especialidade idEspOld = persistentMedico.getIdEsp();
            Especialidade idEspNew = medico.getIdEsp();
            Utilizador idUserOld = persistentMedico.getIdUser();
            Utilizador idUserNew = medico.getIdUser();
            List<Consulta> consultaListOld = persistentMedico.getConsultaList();
            List<Consulta> consultaListNew = medico.getConsultaList();
            if (codPostalNew != null) {
                codPostalNew = em.getReference(codPostalNew.getClass(), codPostalNew.getCodPostal());
                medico.setCodPostal(codPostalNew);
            }
            if (idEspNew != null) {
                idEspNew = em.getReference(idEspNew.getClass(), idEspNew.getId());
                medico.setIdEsp(idEspNew);
            }
            if (idUserNew != null) {
                idUserNew = em.getReference(idUserNew.getClass(), idUserNew.getId());
                medico.setIdUser(idUserNew);
            }
            List<Consulta> attachedConsultaListNew = new ArrayList<Consulta>();
            for (Consulta consultaListNewConsultaToAttach : consultaListNew) {
                consultaListNewConsultaToAttach = em.getReference(consultaListNewConsultaToAttach.getClass(), consultaListNewConsultaToAttach.getId());
                attachedConsultaListNew.add(consultaListNewConsultaToAttach);
            }
            consultaListNew = attachedConsultaListNew;
            medico.setConsultaList(consultaListNew);
            medico = em.merge(medico);
            if (codPostalOld != null && !codPostalOld.equals(codPostalNew)) {
                codPostalOld.getMedicoList().remove(medico);
                codPostalOld = em.merge(codPostalOld);
            }
            if (codPostalNew != null && !codPostalNew.equals(codPostalOld)) {
                codPostalNew.getMedicoList().add(medico);
                codPostalNew = em.merge(codPostalNew);
            }
            if (idEspOld != null && !idEspOld.equals(idEspNew)) {
                idEspOld.getMedicoList().remove(medico);
                idEspOld = em.merge(idEspOld);
            }
            if (idEspNew != null && !idEspNew.equals(idEspOld)) {
                idEspNew.getMedicoList().add(medico);
                idEspNew = em.merge(idEspNew);
            }
            if (idUserOld != null && !idUserOld.equals(idUserNew)) {
                idUserOld.getMedicoList().remove(medico);
                idUserOld = em.merge(idUserOld);
            }
            if (idUserNew != null && !idUserNew.equals(idUserOld)) {
                idUserNew.getMedicoList().add(medico);
                idUserNew = em.merge(idUserNew);
            }
            for (Consulta consultaListOldConsulta : consultaListOld) {
                if (!consultaListNew.contains(consultaListOldConsulta)) {
                    consultaListOldConsulta.setIdMed(null);
                    consultaListOldConsulta = em.merge(consultaListOldConsulta);
                }
            }
            for (Consulta consultaListNewConsulta : consultaListNew) {
                if (!consultaListOld.contains(consultaListNewConsulta)) {
                    Medico oldIdMedOfConsultaListNewConsulta = consultaListNewConsulta.getIdMed();
                    consultaListNewConsulta.setIdMed(medico);
                    consultaListNewConsulta = em.merge(consultaListNewConsulta);
                    if (oldIdMedOfConsultaListNewConsulta != null && !oldIdMedOfConsultaListNewConsulta.equals(medico)) {
                        oldIdMedOfConsultaListNewConsulta.getConsultaList().remove(consultaListNewConsulta);
                        oldIdMedOfConsultaListNewConsulta = em.merge(oldIdMedOfConsultaListNewConsulta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = medico.getId();
                if (findMedico(id) == null) {
                    throw new NonexistentEntityException("The medico with id " + id + " no longer exists.");
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
            Medico medico;
            try {
                medico = em.getReference(Medico.class, id);
                medico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medico with id " + id + " no longer exists.", enfe);
            }
            Codpostal codPostal = medico.getCodPostal();
            if (codPostal != null) {
                codPostal.getMedicoList().remove(medico);
                codPostal = em.merge(codPostal);
            }
            Especialidade idEsp = medico.getIdEsp();
            if (idEsp != null) {
                idEsp.getMedicoList().remove(medico);
                idEsp = em.merge(idEsp);
            }
            Utilizador idUser = medico.getIdUser();
            if (idUser != null) {
                idUser.getMedicoList().remove(medico);
                idUser = em.merge(idUser);
            }
            List<Consulta> consultaList = medico.getConsultaList();
            for (Consulta consultaListConsulta : consultaList) {
                consultaListConsulta.setIdMed(null);
                consultaListConsulta = em.merge(consultaListConsulta);
            }
            em.remove(medico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Medico> findMedicoEntities() {
        return findMedicoEntities(true, -1, -1);
    }

    public List<Medico> findMedicoEntities(int maxResults, int firstResult) {
        return findMedicoEntities(false, maxResults, firstResult);
    }

    private List<Medico> findMedicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Medico.class));
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

    public Medico findMedico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Medico.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Medico> rt = cq.from(Medico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Boolean existeMed(int id){
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Medico.findById");
        q.setParameter("id", id);
        
        return !q.getResultList().isEmpty();
    }
    
    
    public Medico findMedByUser(String username){
        
        UtilizadorJpaController uc = new UtilizadorJpaController();
        Utilizador u = new Utilizador();
        
        u = uc.findUtilizadorNome(username);
        
        return u.getMedicoList().get(0);
    }
    
    public List<Medico> ProcuraMed(String nome){
        List<Medico> meds = new ArrayList<>();
        
        for(Medico m : findMedicoEntities())
            if(m.getNome().contains(nome)) meds.add(m);
        
        return meds;        
        
    }
    
    public Consulta getConsultaAtual(Medico med){
        
        Consulta cons = new Consulta();
        
        Calendar c1 = new Calendar.Builder().build();
        Date min = new Date();
        Date max = new Date();
        Date atual = new Date();
        
        for(Consulta c : med.getConsultaList()){
            min = c.getData();
            c1.setTime(c.getData());
            c1.add(Calendar.MINUTE, 30);
            max = c1.getTime();
            if(atual.after(min) && atual.before(max) && c.getRealizado() == 0) cons = c;
        
        }    
            
        return cons;
        
        
        
    }
    
    public Boolean existeMedComEsp(Especialidade esp){
        
        Boolean encontrou = false;
        
        for(Medico m : findMedicoEntities())
            if(m.getIdEsp().equals(esp))
                encontrou=true;
        
        
        return encontrou;
    }
    
    public List<Medico> findMedicoByEsp(Especialidade esp){
        List<Medico> meds = new ArrayList<>();
        
        for(Medico m : findMedicoEntities())
            if(m.getIdEsp().equals(esp)) meds.add(m);
        
        return meds;        
        
    }
    
    public List<Medico> findMedicoByEsp(Especialidade esp, String nome){
        List<Medico> meds = new ArrayList<>();
        
        for(Medico m : findMedicoByEsp(esp))
            if(m.getNome().contains(nome)) meds.add(m);
        
        return meds;        
        
    }
    
    public Boolean existeConsultaData(Medico med, Date data){
        Boolean existe = false;
        Calendar cal = new Calendar.Builder().build();
        cal.setTime(data);
        Calendar cal1 = new Calendar.Builder().build();
        cal1.setTime(data);
        cal.add(Calendar.MINUTE, -30);  
        cal1.add(Calendar.MINUTE, 30);        
        Date min = cal.getTime();
        Date max = cal1.getTime();
        
        for(Consulta c : med.getConsultaList())
            if((c.getData().after(min) && c.getData().before(max)) ) 
                existe = true;
            
        
        
        return existe;
        
    }
    
    public Boolean existeConsultaData(Consulta cons, Medico med, Date data){
        Boolean existe = false;
        Calendar cal = new Calendar.Builder().build();
        cal.setTime(data);
        Calendar cal1 = new Calendar.Builder().build();
        cal1.setTime(data);
        cal.add(Calendar.MINUTE, -30);  
        cal1.add(Calendar.MINUTE, 30);        
        Date min = cal.getTime();
        Date max = cal1.getTime();
        
        for(Consulta c : med.getConsultaList())
            if(cons.getId() != c.getId())
                if((c.getData().after(min) && c.getData().before(max)) ) 
                    existe = true;
            
        
        
        return existe;
        
    }
    
    
    
    
    
    
}
