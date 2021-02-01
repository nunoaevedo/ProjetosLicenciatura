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
import DAL.Medico;
import DAL.Rececionista;
import DAL.Utilizador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nuno
 */
public class RececionistaJpaController implements Serializable {
    
    private static final String PERSISTENCE_UNIT_NAME = "ProjetoLibraryPU";
    
    public RececionistaJpaController() {
        this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rececionista rececionista) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Codpostal codPostal = rececionista.getCodPostal();
            if (codPostal != null) {
                codPostal = em.getReference(codPostal.getClass(), codPostal.getCodPostal());
                rececionista.setCodPostal(codPostal);
            }
            Utilizador idUser = rececionista.getIdUser();
            if (idUser != null) {
                idUser = em.getReference(idUser.getClass(), idUser.getId());
                rececionista.setIdUser(idUser);
            }
            em.persist(rececionista);
            if (codPostal != null) {
                codPostal.getRececionistaList().add(rececionista);
                codPostal = em.merge(codPostal);
            }
            if (idUser != null) {
                idUser.getRececionistaList().add(rececionista);
                idUser = em.merge(idUser);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRececionista(rececionista.getId()) != null) {
                throw new PreexistingEntityException("Rececionista " + rececionista + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rececionista rececionista) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rececionista persistentRececionista = em.find(Rececionista.class, rececionista.getId());
            Codpostal codPostalOld = persistentRececionista.getCodPostal();
            Codpostal codPostalNew = rececionista.getCodPostal();
            Utilizador idUserOld = persistentRececionista.getIdUser();
            Utilizador idUserNew = rececionista.getIdUser();
            if (codPostalNew != null) {
                codPostalNew = em.getReference(codPostalNew.getClass(), codPostalNew.getCodPostal());
                rececionista.setCodPostal(codPostalNew);
            }
            if (idUserNew != null) {
                idUserNew = em.getReference(idUserNew.getClass(), idUserNew.getId());
                rececionista.setIdUser(idUserNew);
            }
            rececionista = em.merge(rececionista);
            if (codPostalOld != null && !codPostalOld.equals(codPostalNew)) {
                codPostalOld.getRececionistaList().remove(rececionista);
                codPostalOld = em.merge(codPostalOld);
            }
            if (codPostalNew != null && !codPostalNew.equals(codPostalOld)) {
                codPostalNew.getRececionistaList().add(rececionista);
                codPostalNew = em.merge(codPostalNew);
            }
            if (idUserOld != null && !idUserOld.equals(idUserNew)) {
                idUserOld.getRececionistaList().remove(rececionista);
                idUserOld = em.merge(idUserOld);
            }
            if (idUserNew != null && !idUserNew.equals(idUserOld)) {
                idUserNew.getRececionistaList().add(rececionista);
                idUserNew = em.merge(idUserNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rececionista.getId();
                if (findRececionista(id) == null) {
                    throw new NonexistentEntityException("The rececionista with id " + id + " no longer exists.");
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
            Rececionista rececionista;
            try {
                rececionista = em.getReference(Rececionista.class, id);
                rececionista.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rececionista with id " + id + " no longer exists.", enfe);
            }
            Codpostal codPostal = rececionista.getCodPostal();
            if (codPostal != null) {
                codPostal.getRececionistaList().remove(rececionista);
                codPostal = em.merge(codPostal);
            }
            Utilizador idUser = rececionista.getIdUser();
            if (idUser != null) {
                idUser.getRececionistaList().remove(rececionista);
                idUser = em.merge(idUser);
            }
            em.remove(rececionista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rececionista> findRececionistaEntities() {
        return findRececionistaEntities(true, -1, -1);
    }

    public List<Rececionista> findRececionistaEntities(int maxResults, int firstResult) {
        return findRececionistaEntities(false, maxResults, firstResult);
    }

    private List<Rececionista> findRececionistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rececionista.class));
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

    public Rececionista findRececionista(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rececionista.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Rececionista> findRececionistaEntities(String nome){
        List<Rececionista> recList = new ArrayList<>();
        
        for(Rececionista r : findRececionistaEntities())
            if(r.getNome().contains(nome)) recList.add(r);
        
        
        return recList;        
        
    }

    public int getRececionistaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rececionista> rt = cq.from(Rececionista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Boolean existeRec(int id){
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Rececionista.findById");
        q.setParameter("id", id);
        
        return !q.getResultList().isEmpty();
    }
    
    
    public Rececionista findRecByUser(String username){
        
        EntityManager em = getEntityManager();
        UtilizadorJpaController uc = new UtilizadorJpaController();
        Utilizador u = new Utilizador();
        
        u = uc.findUtilizadorNome(username);
        
        return u.getRececionistaList().get(0);
    }
    
    
}
