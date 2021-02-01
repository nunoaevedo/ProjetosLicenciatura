/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BLL.exceptions.NonexistentEntityException;
import BLL.exceptions.PreexistingEntityException;
import DAL.Administrador;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DAL.Codpostal;
import DAL.Utilizador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nuno
 */
public class AdministradorJpaController implements Serializable {
    
    private static final String PERSISTENCE_UNIT_NAME = "ProjetoLibraryPU";

    public AdministradorJpaController() {
        this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Administrador administrador) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Codpostal codPostal = administrador.getCodPostal();
            if (codPostal != null) {
                codPostal = em.getReference(codPostal.getClass(), codPostal.getCodPostal());
                administrador.setCodPostal(codPostal);
            }
            Utilizador idUser = administrador.getIdUser();
            if (idUser != null) {
                idUser = em.getReference(idUser.getClass(), idUser.getId());
                administrador.setIdUser(idUser);
            }
            em.persist(administrador);
            if (codPostal != null) {
                codPostal.getAdministradorList().add(administrador);
                codPostal = em.merge(codPostal);
            }
            if (idUser != null) {
                idUser.getAdministradorList().add(administrador);
                idUser = em.merge(idUser);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAdministrador(administrador.getId()) != null) {
                throw new PreexistingEntityException("Administrador " + administrador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Administrador administrador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrador persistentAdministrador = em.find(Administrador.class, administrador.getId());
            Codpostal codPostalOld = persistentAdministrador.getCodPostal();
            Codpostal codPostalNew = administrador.getCodPostal();
            Utilizador idUserOld = persistentAdministrador.getIdUser();
            Utilizador idUserNew = administrador.getIdUser();
            if (codPostalNew != null) {
                codPostalNew = em.getReference(codPostalNew.getClass(), codPostalNew.getCodPostal());
                administrador.setCodPostal(codPostalNew);
            }
            if (idUserNew != null) {
                idUserNew = em.getReference(idUserNew.getClass(), idUserNew.getId());
                administrador.setIdUser(idUserNew);
            }
            administrador = em.merge(administrador);
            if (codPostalOld != null && !codPostalOld.equals(codPostalNew)) {
                codPostalOld.getAdministradorList().remove(administrador);
                codPostalOld = em.merge(codPostalOld);
            }
            if (codPostalNew != null && !codPostalNew.equals(codPostalOld)) {
                codPostalNew.getAdministradorList().add(administrador);
                codPostalNew = em.merge(codPostalNew);
            }
            if (idUserOld != null && !idUserOld.equals(idUserNew)) {
                idUserOld.getAdministradorList().remove(administrador);
                idUserOld = em.merge(idUserOld);
            }
            if (idUserNew != null && !idUserNew.equals(idUserOld)) {
                idUserNew.getAdministradorList().add(administrador);
                idUserNew = em.merge(idUserNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = administrador.getId();
                if (findAdministrador(id) == null) {
                    throw new NonexistentEntityException("The administrador with id " + id + " no longer exists.");
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
            Administrador administrador;
            try {
                administrador = em.getReference(Administrador.class, id);
                administrador.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The administrador with id " + id + " no longer exists.", enfe);
            }
            Codpostal codPostal = administrador.getCodPostal();
            if (codPostal != null) {
                codPostal.getAdministradorList().remove(administrador);
                codPostal = em.merge(codPostal);
            }
            Utilizador idUser = administrador.getIdUser();
            if (idUser != null) {
                idUser.getAdministradorList().remove(administrador);
                idUser = em.merge(idUser);
            }
            em.remove(administrador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Administrador> findAdministradorEntities() {
        return findAdministradorEntities(true, -1, -1);
    }

    public List<Administrador> findAdministradorEntities(int maxResults, int firstResult) {
        return findAdministradorEntities(false, maxResults, firstResult);
    }

    private List<Administrador> findAdministradorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Administrador.class));
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

    public Administrador findAdministrador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Administrador.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdministradorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Administrador> rt = cq.from(Administrador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
