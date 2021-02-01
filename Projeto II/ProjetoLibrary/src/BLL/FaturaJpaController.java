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
import DAL.Consulta;
import DAL.Fatura;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nuno
 */
public class FaturaJpaController implements Serializable {
    
    private static final String PERSISTENCE_UNIT_NAME = "ProjetoLibraryPU";

    public FaturaJpaController() {
        this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fatura fatura) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Consulta idCons = fatura.getIdCons();
            if (idCons != null) {
                idCons = em.getReference(idCons.getClass(), idCons.getId());
                fatura.setIdCons(idCons);
            }
            em.persist(fatura);
            if (idCons != null) {
                Fatura oldFaturaOfIdCons = idCons.getFatura();
                if (oldFaturaOfIdCons != null) {
                    oldFaturaOfIdCons.setIdCons(null);
                    oldFaturaOfIdCons = em.merge(oldFaturaOfIdCons);
                }
                idCons.setFatura(fatura);
                idCons = em.merge(idCons);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFatura(fatura.getId()) != null) {
                throw new PreexistingEntityException("Fatura " + fatura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fatura fatura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fatura persistentFatura = em.find(Fatura.class, fatura.getId());
            Consulta idConsOld = persistentFatura.getIdCons();
            Consulta idConsNew = fatura.getIdCons();
            if (idConsNew != null) {
                idConsNew = em.getReference(idConsNew.getClass(), idConsNew.getId());
                fatura.setIdCons(idConsNew);
            }
            fatura = em.merge(fatura);
            if (idConsOld != null && !idConsOld.equals(idConsNew)) {
                idConsOld.setFatura(null);
                idConsOld = em.merge(idConsOld);
            }
            if (idConsNew != null && !idConsNew.equals(idConsOld)) {
                Fatura oldFaturaOfIdCons = idConsNew.getFatura();
                if (oldFaturaOfIdCons != null) {
                    oldFaturaOfIdCons.setIdCons(null);
                    oldFaturaOfIdCons = em.merge(oldFaturaOfIdCons);
                }
                idConsNew.setFatura(fatura);
                idConsNew = em.merge(idConsNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fatura.getId();
                if (findFatura(id) == null) {
                    throw new NonexistentEntityException("The fatura with id " + id + " no longer exists.");
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
            Fatura fatura;
            try {
                fatura = em.getReference(Fatura.class, id);
                fatura.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fatura with id " + id + " no longer exists.", enfe);
            }
            Consulta idCons = fatura.getIdCons();
            if (idCons != null) {
                idCons.setFatura(null);
                idCons = em.merge(idCons);
            }
            em.remove(fatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fatura> findFaturaEntities() {
        return findFaturaEntities(true, -1, -1);
    }

    public List<Fatura> findFaturaEntities(int maxResults, int firstResult) {
        return findFaturaEntities(false, maxResults, firstResult);
    }

    private List<Fatura> findFaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fatura.class));
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
    
    public List<Fatura> findFaturaEntities(String nome) {
        List<Fatura> list = new ArrayList<>();
        
        String temp;
        
        for( Fatura f :findFaturaEntities() ){
            temp = f.getIdCons().getIdPac().getNome().concat(" ").concat(f.getIdCons().getIdPac().getApelido());
             if(temp.contains(nome)) list.add(f);     
        }
        return list;
        
    }

    public Fatura findFatura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fatura> rt = cq.from(Fatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public boolean existeFatura(int id){
        
        boolean existe;
        
        try{
            findFatura(id).getId();
            existe = true;
        }catch(NullPointerException e){
            existe = false;
        }
        
        return existe;
        
    }
    
    
    
    
    
    
}
