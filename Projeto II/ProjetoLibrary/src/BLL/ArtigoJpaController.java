/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BLL.exceptions.NonexistentEntityException;
import BLL.exceptions.PreexistingEntityException;
import DAL.Artigo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DAL.Consulta;
import DAL.Especialidade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nuno
 */
public class ArtigoJpaController implements Serializable {
    
    private static final String PERSISTENCE_UNIT_NAME = "ProjetoLibraryPU";

    public ArtigoJpaController() {
        this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Artigo artigo) throws PreexistingEntityException, Exception {
        if (artigo.getConsultaList() == null) {
            artigo.setConsultaList(new ArrayList<Consulta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Consulta> attachedConsultaList = new ArrayList<Consulta>();
            for (Consulta consultaListConsultaToAttach : artigo.getConsultaList()) {
                consultaListConsultaToAttach = em.getReference(consultaListConsultaToAttach.getClass(), consultaListConsultaToAttach.getId());
                attachedConsultaList.add(consultaListConsultaToAttach);
            }
            artigo.setConsultaList(attachedConsultaList);
            em.persist(artigo);
            for (Consulta consultaListConsulta : artigo.getConsultaList()) {
                consultaListConsulta.getArtigoList().add(artigo);
                consultaListConsulta = em.merge(consultaListConsulta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArtigo(artigo.getId()) != null) {
                throw new PreexistingEntityException("Artigo " + artigo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Artigo artigo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Artigo persistentArtigo = em.find(Artigo.class, artigo.getId());
            List<Consulta> consultaListOld = persistentArtigo.getConsultaList();
            List<Consulta> consultaListNew = artigo.getConsultaList();
            List<Consulta> attachedConsultaListNew = new ArrayList<Consulta>();
            for (Consulta consultaListNewConsultaToAttach : consultaListNew) {
                consultaListNewConsultaToAttach = em.getReference(consultaListNewConsultaToAttach.getClass(), consultaListNewConsultaToAttach.getId());
                attachedConsultaListNew.add(consultaListNewConsultaToAttach);
            }
            consultaListNew = attachedConsultaListNew;
            artigo.setConsultaList(consultaListNew);
            artigo = em.merge(artigo);
            for (Consulta consultaListOldConsulta : consultaListOld) {
                if (!consultaListNew.contains(consultaListOldConsulta)) {
                    consultaListOldConsulta.getArtigoList().remove(artigo);
                    consultaListOldConsulta = em.merge(consultaListOldConsulta);
                }
            }
            for (Consulta consultaListNewConsulta : consultaListNew) {
                if (!consultaListOld.contains(consultaListNewConsulta)) {
                    consultaListNewConsulta.getArtigoList().add(artigo);
                    consultaListNewConsulta = em.merge(consultaListNewConsulta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = artigo.getId();
                if (findArtigo(id) == null) {
                    throw new NonexistentEntityException("The artigo with id " + id + " no longer exists.");
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
            Artigo artigo;
            try {
                artigo = em.getReference(Artigo.class, id);
                artigo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The artigo with id " + id + " no longer exists.", enfe);
            }
            List<Consulta> consultaList = artigo.getConsultaList();
            for (Consulta consultaListConsulta : consultaList) {
                consultaListConsulta.getArtigoList().remove(artigo);
                consultaListConsulta = em.merge(consultaListConsulta);
            }
            em.remove(artigo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Artigo> findArtigoEntities() {
        return findArtigoEntities(true, -1, -1);
    }

    public List<Artigo> findArtigoEntities(int maxResults, int firstResult) {
        return findArtigoEntities(false, maxResults, firstResult);
    }

    private List<Artigo> findArtigoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Artigo.class));
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
    
    public List<Artigo> findArtigoEntities(String nome) {
        List<Artigo> artList = new ArrayList<>();
        
        for(Artigo a: findArtigoEntities() )
            if(a.getNome().toLowerCase().contains(nome.toLowerCase())) artList.add(a);
        
        return artList;
        
        
    }
    

    public Artigo findArtigo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Artigo.class, id);
        } finally {
            em.close();
        }
    }
    
    public Artigo findArtigo(String nome){
        Artigo art = new Artigo();
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Artigo.findByNome");
        q.setParameter("nome", nome);
        
        for(Object o : q.getResultList()){
            art = ((Artigo)o);
        }
        
        return art;
    }

    public int getArtigoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Artigo> rt = cq.from(Artigo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Boolean existeArt(String nomeArt){
        
        Artigo art = new Artigo();
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Artigo.findByNome");
        q.setParameter("nome", nomeArt);
        Boolean encontrou = false;
        
        for(Object o : q.getResultList()){
            if(((Artigo)o).getNome().equals(nomeArt)) encontrou = true;
        }
        
        return encontrou;
        
    }
    
}
