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
import DAL.Medico;
import DAL.Paciente;
import DAL.Fatura;
import DAL.Artigo;
import DAL.Consulta;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nuno
 */
public class ConsultaJpaController implements Serializable {
    
    private static final String PERSISTENCE_UNIT_NAME = "ProjetoLibraryPU";

    public ConsultaJpaController() {
        this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Consulta consulta) throws PreexistingEntityException, Exception {
        if (consulta.getArtigoList() == null) {
            consulta.setArtigoList(new ArrayList<Artigo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medico idMed = consulta.getIdMed();
            if (idMed != null) {
                idMed = em.getReference(idMed.getClass(), idMed.getId());
                consulta.setIdMed(idMed);
            }
            Paciente idPac = consulta.getIdPac();
            if (idPac != null) {
                idPac = em.getReference(idPac.getClass(), idPac.getId());
                consulta.setIdPac(idPac);
            }
            Fatura fatura = consulta.getFatura();
            if (fatura != null) {
                fatura = em.getReference(fatura.getClass(), fatura.getId());
                consulta.setFatura(fatura);
            }
            List<Artigo> attachedArtigoList = new ArrayList<Artigo>();
            for (Artigo artigoListArtigoToAttach : consulta.getArtigoList()) {
                artigoListArtigoToAttach = em.getReference(artigoListArtigoToAttach.getClass(), artigoListArtigoToAttach.getId());
                attachedArtigoList.add(artigoListArtigoToAttach);
            }
            consulta.setArtigoList(attachedArtigoList);
            em.persist(consulta);
            if (idMed != null) {
                idMed.getConsultaList().add(consulta);
                idMed = em.merge(idMed);
            }
            if (idPac != null) {
                idPac.getConsultaList().add(consulta);
                idPac = em.merge(idPac);
            }
            if (fatura != null) {
                Consulta oldIdConsOfFatura = fatura.getIdCons();
                if (oldIdConsOfFatura != null) {
                    oldIdConsOfFatura.setFatura(null);
                    oldIdConsOfFatura = em.merge(oldIdConsOfFatura);
                }
                fatura.setIdCons(consulta);
                fatura = em.merge(fatura);
            }
            for (Artigo artigoListArtigo : consulta.getArtigoList()) {
                artigoListArtigo.getConsultaList().add(consulta);
                artigoListArtigo = em.merge(artigoListArtigo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findConsulta(consulta.getId()) != null) {
                throw new PreexistingEntityException("Consulta " + consulta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Consulta consulta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Consulta persistentConsulta = em.find(Consulta.class, consulta.getId());
            Medico idMedOld = persistentConsulta.getIdMed();
            Medico idMedNew = consulta.getIdMed();
            Paciente idPacOld = persistentConsulta.getIdPac();
            Paciente idPacNew = consulta.getIdPac();
            Fatura faturaOld = persistentConsulta.getFatura();
            Fatura faturaNew = consulta.getFatura();
            List<Artigo> artigoListOld = persistentConsulta.getArtigoList();
            List<Artigo> artigoListNew = consulta.getArtigoList();
            if (idMedNew != null) {
                idMedNew = em.getReference(idMedNew.getClass(), idMedNew.getId());
                consulta.setIdMed(idMedNew);
            }
            if (idPacNew != null) {
                idPacNew = em.getReference(idPacNew.getClass(), idPacNew.getId());
                consulta.setIdPac(idPacNew);
            }
            if (faturaNew != null) {
                faturaNew = em.getReference(faturaNew.getClass(), faturaNew.getId());
                consulta.setFatura(faturaNew);
            }
            List<Artigo> attachedArtigoListNew = new ArrayList<Artigo>();
            for (Artigo artigoListNewArtigoToAttach : artigoListNew) {
                artigoListNewArtigoToAttach = em.getReference(artigoListNewArtigoToAttach.getClass(), artigoListNewArtigoToAttach.getId());
                attachedArtigoListNew.add(artigoListNewArtigoToAttach);
            }
            artigoListNew = attachedArtigoListNew;
            consulta.setArtigoList(artigoListNew);
            consulta = em.merge(consulta);
            if (idMedOld != null && !idMedOld.equals(idMedNew)) {
                idMedOld.getConsultaList().remove(consulta);
                idMedOld = em.merge(idMedOld);
            }
            if (idMedNew != null && !idMedNew.equals(idMedOld)) {
                idMedNew.getConsultaList().add(consulta);
                idMedNew = em.merge(idMedNew);
            }
            if (idPacOld != null && !idPacOld.equals(idPacNew)) {
                idPacOld.getConsultaList().remove(consulta);
                idPacOld = em.merge(idPacOld);
            }
            if (idPacNew != null && !idPacNew.equals(idPacOld)) {
                idPacNew.getConsultaList().add(consulta);
                idPacNew = em.merge(idPacNew);
            }
            if (faturaOld != null && !faturaOld.equals(faturaNew)) {
                faturaOld.setIdCons(null);
                faturaOld = em.merge(faturaOld);
            }
            if (faturaNew != null && !faturaNew.equals(faturaOld)) {
                Consulta oldIdConsOfFatura = faturaNew.getIdCons();
                if (oldIdConsOfFatura != null) {
                    oldIdConsOfFatura.setFatura(null);
                    oldIdConsOfFatura = em.merge(oldIdConsOfFatura);
                }
                faturaNew.setIdCons(consulta);
                faturaNew = em.merge(faturaNew);
            }
            for (Artigo artigoListOldArtigo : artigoListOld) {
                if (!artigoListNew.contains(artigoListOldArtigo)) {
                    artigoListOldArtigo.getConsultaList().remove(consulta);
                    artigoListOldArtigo = em.merge(artigoListOldArtigo);
                }
            }
            for (Artigo artigoListNewArtigo : artigoListNew) {
                if (!artigoListOld.contains(artigoListNewArtigo)) {
                    artigoListNewArtigo.getConsultaList().add(consulta);
                    artigoListNewArtigo = em.merge(artigoListNewArtigo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = consulta.getId();
                if (findConsulta(id) == null) {
                    throw new NonexistentEntityException("The consulta with id " + id + " no longer exists.");
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
            Consulta consulta;
            try {
                consulta = em.getReference(Consulta.class, id);
                consulta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The consulta with id " + id + " no longer exists.", enfe);
            }
            Medico idMed = consulta.getIdMed();
            if (idMed != null) {
                idMed.getConsultaList().remove(consulta);
                idMed = em.merge(idMed);
            }
            Paciente idPac = consulta.getIdPac();
            if (idPac != null) {
                idPac.getConsultaList().remove(consulta);
                idPac = em.merge(idPac);
            }
            Fatura fatura = consulta.getFatura();
            if (fatura != null) {
                fatura.setIdCons(null);
                fatura = em.merge(fatura);
            }
            List<Artigo> artigoList = consulta.getArtigoList();
            for (Artigo artigoListArtigo : artigoList) {
                artigoListArtigo.getConsultaList().remove(consulta);
                artigoListArtigo = em.merge(artigoListArtigo);
            }
            em.remove(consulta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Consulta> findConsultaEntities() {
        return findConsultaEntities(true, -1, -1);
    }

    public List<Consulta> findConsultaEntities(int maxResults, int firstResult) {
        return findConsultaEntities(false, maxResults, firstResult);
    }

    private List<Consulta> findConsultaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Consulta.class));
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
    
    public List<Consulta> findConsultaEntities(String nome) {
        
        List<Consulta> list = new ArrayList<>();
        
        String nomeAp;
        
        for(Consulta c : findConsultaEntities()){
            nomeAp = c.getIdPac().getNome().concat(" ").concat(c.getIdPac().getApelido());
            if(nomeAp.contains(nome)) list.add(c);
            
        }
        return list;
        
    }
    

    public Consulta findConsulta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Consulta.class, id);
        } finally {
            em.close();
        }
    }

    public int getConsultaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Consulta> rt = cq.from(Consulta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Consulta> getConsultasOrdenadas(){
        List<Consulta> c = new ArrayList<>(); 
        
        c = findConsultaEntities();
        
        c.sort(Comparator.comparing(Consulta::getData));
        
        
        return c;
        
    }
    
    public Boolean consSemFatura(){
        
        boolean encontrado = false;
        
        for(Consulta c : findConsultaEntities()){
            if(c.getRealizado() == 1){
                try{
                    c.getFatura().getId();
                }catch(NullPointerException e){
                    encontrado =true;
                }
            }
            
            
            
        }
        
        return encontrado;
        
    }
    
    public Boolean consSemFatura(Consulta cons){
        
        boolean encontrado = false;
        
        try{
            cons.getFatura().getId();
        }catch(NullPointerException e){
            encontrado = true;
        }
        
        return encontrado;
        
    }
    
    public double getPrecoBase(Consulta cons){
        
        double base = 0;
        
        base += Float.parseFloat(cons.getIdMed().getIdEsp().getPreco().toString());
        
        for(Artigo a : cons.getArtigoList()){
            base = base + Float.parseFloat(a.getPreco().toString());
            
        }
        
        
        return base;
    }
    
    
    
    
}
