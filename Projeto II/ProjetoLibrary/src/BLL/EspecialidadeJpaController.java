/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BLL.exceptions.NonexistentEntityException;
import BLL.exceptions.PreexistingEntityException;
import DAL.Especialidade;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DAL.Medico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nuno
 */
public class EspecialidadeJpaController implements Serializable {
    
    private static final String PERSISTENCE_UNIT_NAME = "ProjetoLibraryPU";

    public EspecialidadeJpaController() {
        this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Especialidade especialidade) throws PreexistingEntityException, Exception {
        if (especialidade.getMedicoList() == null) {
            especialidade.setMedicoList(new ArrayList<Medico>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Medico> attachedMedicoList = new ArrayList<Medico>();
            for (Medico medicoListMedicoToAttach : especialidade.getMedicoList()) {
                medicoListMedicoToAttach = em.getReference(medicoListMedicoToAttach.getClass(), medicoListMedicoToAttach.getId());
                attachedMedicoList.add(medicoListMedicoToAttach);
            }
            especialidade.setMedicoList(attachedMedicoList);
            em.persist(especialidade);
            for (Medico medicoListMedico : especialidade.getMedicoList()) {
                Especialidade oldIdEspOfMedicoListMedico = medicoListMedico.getIdEsp();
                medicoListMedico.setIdEsp(especialidade);
                medicoListMedico = em.merge(medicoListMedico);
                if (oldIdEspOfMedicoListMedico != null) {
                    oldIdEspOfMedicoListMedico.getMedicoList().remove(medicoListMedico);
                    oldIdEspOfMedicoListMedico = em.merge(oldIdEspOfMedicoListMedico);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEspecialidade(especialidade.getId()) != null) {
                throw new PreexistingEntityException("Especialidade " + especialidade + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Especialidade especialidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Especialidade persistentEspecialidade = em.find(Especialidade.class, especialidade.getId());
            List<Medico> medicoListOld = persistentEspecialidade.getMedicoList();
            List<Medico> medicoListNew = especialidade.getMedicoList();
            List<Medico> attachedMedicoListNew = new ArrayList<Medico>();
            for (Medico medicoListNewMedicoToAttach : medicoListNew) {
                medicoListNewMedicoToAttach = em.getReference(medicoListNewMedicoToAttach.getClass(), medicoListNewMedicoToAttach.getId());
                attachedMedicoListNew.add(medicoListNewMedicoToAttach);
            }
            medicoListNew = attachedMedicoListNew;
            especialidade.setMedicoList(medicoListNew);
            especialidade = em.merge(especialidade);
            for (Medico medicoListOldMedico : medicoListOld) {
                if (!medicoListNew.contains(medicoListOldMedico)) {
                    medicoListOldMedico.setIdEsp(null);
                    medicoListOldMedico = em.merge(medicoListOldMedico);
                }
            }
            for (Medico medicoListNewMedico : medicoListNew) {
                if (!medicoListOld.contains(medicoListNewMedico)) {
                    Especialidade oldIdEspOfMedicoListNewMedico = medicoListNewMedico.getIdEsp();
                    medicoListNewMedico.setIdEsp(especialidade);
                    medicoListNewMedico = em.merge(medicoListNewMedico);
                    if (oldIdEspOfMedicoListNewMedico != null && !oldIdEspOfMedicoListNewMedico.equals(especialidade)) {
                        oldIdEspOfMedicoListNewMedico.getMedicoList().remove(medicoListNewMedico);
                        oldIdEspOfMedicoListNewMedico = em.merge(oldIdEspOfMedicoListNewMedico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = especialidade.getId();
                if (findEspecialidade(id) == null) {
                    throw new NonexistentEntityException("The especialidade with id " + id + " no longer exists.");
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
            Especialidade especialidade;
            try {
                especialidade = em.getReference(Especialidade.class, id);
                especialidade.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The especialidade with id " + id + " no longer exists.", enfe);
            }
            List<Medico> medicoList = especialidade.getMedicoList();
            for (Medico medicoListMedico : medicoList) {
                medicoListMedico.setIdEsp(null);
                medicoListMedico = em.merge(medicoListMedico);
            }
            em.remove(especialidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Especialidade> findEspecialidadeEntities() {
        return findEspecialidadeEntities(true, -1, -1);
    }

    public List<Especialidade> findEspecialidadeEntities(int maxResults, int firstResult) {
        return findEspecialidadeEntities(false, maxResults, firstResult);
    }

    private List<Especialidade> findEspecialidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Especialidade.class));
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

    public Especialidade findEspecialidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Especialidade.class, id);
        } finally {
            em.close();
        }
    }
    
    public Especialidade findEspecialidade(String nome) {
        EntityManager em = getEntityManager();
        
        Query q = em.createNamedQuery("Especialidade.findByNome");
        q.setParameter("nome", nome);
        
        Especialidade esp = new Especialidade();
        
        for(Object o : q.getResultList()){
            esp = ((Especialidade)o);
        }     
        return esp;
    }
    
    public List<Especialidade> findEspecialidadeEntities(String nome){
        List<Especialidade> espList = new ArrayList<>();
        
        for(Especialidade e: findEspecialidadeEntities() )
            if(e.getNome().toLowerCase().contains(nome.toLowerCase())) espList.add(e);
        
        return espList;
        
       
        
        
    }
    

    public int getEspecialidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Especialidade> rt = cq.from(Especialidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Boolean existeEsp(String nomeEsp){
        
        Especialidade esp = new Especialidade();
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Especialidade.findByNome");
        q.setParameter("nome", nomeEsp);
        Boolean encontrou = false;
        
        for(Object o : q.getResultList()){
            if(((Especialidade)o).getNome().equals(nomeEsp)) encontrou = true;
        }
        
        return encontrou;
        
    }
    
    
}
