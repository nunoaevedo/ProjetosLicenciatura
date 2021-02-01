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
import DAL.Administrador;
import DAL.Codpostal;
import java.util.ArrayList;
import java.util.List;
import DAL.Rececionista;
import DAL.Medico;
import DAL.Paciente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nuno
 */
public class CodpostalJpaController implements Serializable {
    
    private static final String PERSISTENCE_UNIT_NAME = "ProjetoLibraryPU";

    public CodpostalJpaController() {
        this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Codpostal codpostal) throws PreexistingEntityException, Exception {
        if (codpostal.getAdministradorList() == null) {
            codpostal.setAdministradorList(new ArrayList<Administrador>());
        }
        if (codpostal.getRececionistaList() == null) {
            codpostal.setRececionistaList(new ArrayList<Rececionista>());
        }
        if (codpostal.getMedicoList() == null) {
            codpostal.setMedicoList(new ArrayList<Medico>());
        }
        if (codpostal.getPacienteList() == null) {
            codpostal.setPacienteList(new ArrayList<Paciente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Administrador> attachedAdministradorList = new ArrayList<Administrador>();
            for (Administrador administradorListAdministradorToAttach : codpostal.getAdministradorList()) {
                administradorListAdministradorToAttach = em.getReference(administradorListAdministradorToAttach.getClass(), administradorListAdministradorToAttach.getId());
                attachedAdministradorList.add(administradorListAdministradorToAttach);
            }
            codpostal.setAdministradorList(attachedAdministradorList);
            List<Rececionista> attachedRececionistaList = new ArrayList<Rececionista>();
            for (Rececionista rececionistaListRececionistaToAttach : codpostal.getRececionistaList()) {
                rececionistaListRececionistaToAttach = em.getReference(rececionistaListRececionistaToAttach.getClass(), rececionistaListRececionistaToAttach.getId());
                attachedRececionistaList.add(rececionistaListRececionistaToAttach);
            }
            codpostal.setRececionistaList(attachedRececionistaList);
            List<Medico> attachedMedicoList = new ArrayList<Medico>();
            for (Medico medicoListMedicoToAttach : codpostal.getMedicoList()) {
                medicoListMedicoToAttach = em.getReference(medicoListMedicoToAttach.getClass(), medicoListMedicoToAttach.getId());
                attachedMedicoList.add(medicoListMedicoToAttach);
            }
            codpostal.setMedicoList(attachedMedicoList);
            List<Paciente> attachedPacienteList = new ArrayList<Paciente>();
            for (Paciente pacienteListPacienteToAttach : codpostal.getPacienteList()) {
                pacienteListPacienteToAttach = em.getReference(pacienteListPacienteToAttach.getClass(), pacienteListPacienteToAttach.getId());
                attachedPacienteList.add(pacienteListPacienteToAttach);
            }
            codpostal.setPacienteList(attachedPacienteList);
            em.persist(codpostal);
            for (Administrador administradorListAdministrador : codpostal.getAdministradorList()) {
                Codpostal oldCodPostalOfAdministradorListAdministrador = administradorListAdministrador.getCodPostal();
                administradorListAdministrador.setCodPostal(codpostal);
                administradorListAdministrador = em.merge(administradorListAdministrador);
                if (oldCodPostalOfAdministradorListAdministrador != null) {
                    oldCodPostalOfAdministradorListAdministrador.getAdministradorList().remove(administradorListAdministrador);
                    oldCodPostalOfAdministradorListAdministrador = em.merge(oldCodPostalOfAdministradorListAdministrador);
                }
            }
            for (Rececionista rececionistaListRececionista : codpostal.getRececionistaList()) {
                Codpostal oldCodPostalOfRececionistaListRececionista = rececionistaListRececionista.getCodPostal();
                rececionistaListRececionista.setCodPostal(codpostal);
                rececionistaListRececionista = em.merge(rececionistaListRececionista);
                if (oldCodPostalOfRececionistaListRececionista != null) {
                    oldCodPostalOfRececionistaListRececionista.getRececionistaList().remove(rececionistaListRececionista);
                    oldCodPostalOfRececionistaListRececionista = em.merge(oldCodPostalOfRececionistaListRececionista);
                }
            }
            for (Medico medicoListMedico : codpostal.getMedicoList()) {
                Codpostal oldCodPostalOfMedicoListMedico = medicoListMedico.getCodPostal();
                medicoListMedico.setCodPostal(codpostal);
                medicoListMedico = em.merge(medicoListMedico);
                if (oldCodPostalOfMedicoListMedico != null) {
                    oldCodPostalOfMedicoListMedico.getMedicoList().remove(medicoListMedico);
                    oldCodPostalOfMedicoListMedico = em.merge(oldCodPostalOfMedicoListMedico);
                }
            }
            for (Paciente pacienteListPaciente : codpostal.getPacienteList()) {
                Codpostal oldCodPostalOfPacienteListPaciente = pacienteListPaciente.getCodPostal();
                pacienteListPaciente.setCodPostal(codpostal);
                pacienteListPaciente = em.merge(pacienteListPaciente);
                if (oldCodPostalOfPacienteListPaciente != null) {
                    oldCodPostalOfPacienteListPaciente.getPacienteList().remove(pacienteListPaciente);
                    oldCodPostalOfPacienteListPaciente = em.merge(oldCodPostalOfPacienteListPaciente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCodpostal(codpostal.getCodPostal()) != null) {
                throw new PreexistingEntityException("Codpostal " + codpostal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Codpostal codpostal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Codpostal persistentCodpostal = em.find(Codpostal.class, codpostal.getCodPostal());
            List<Administrador> administradorListOld = persistentCodpostal.getAdministradorList();
            List<Administrador> administradorListNew = codpostal.getAdministradorList();
            List<Rececionista> rececionistaListOld = persistentCodpostal.getRececionistaList();
            List<Rececionista> rececionistaListNew = codpostal.getRececionistaList();
            List<Medico> medicoListOld = persistentCodpostal.getMedicoList();
            List<Medico> medicoListNew = codpostal.getMedicoList();
            List<Paciente> pacienteListOld = persistentCodpostal.getPacienteList();
            List<Paciente> pacienteListNew = codpostal.getPacienteList();
            List<Administrador> attachedAdministradorListNew = new ArrayList<Administrador>();
            for (Administrador administradorListNewAdministradorToAttach : administradorListNew) {
                administradorListNewAdministradorToAttach = em.getReference(administradorListNewAdministradorToAttach.getClass(), administradorListNewAdministradorToAttach.getId());
                attachedAdministradorListNew.add(administradorListNewAdministradorToAttach);
            }
            administradorListNew = attachedAdministradorListNew;
            codpostal.setAdministradorList(administradorListNew);
            List<Rececionista> attachedRececionistaListNew = new ArrayList<Rececionista>();
            for (Rececionista rececionistaListNewRececionistaToAttach : rececionistaListNew) {
                rececionistaListNewRececionistaToAttach = em.getReference(rececionistaListNewRececionistaToAttach.getClass(), rececionistaListNewRececionistaToAttach.getId());
                attachedRececionistaListNew.add(rececionistaListNewRececionistaToAttach);
            }
            rececionistaListNew = attachedRececionistaListNew;
            codpostal.setRececionistaList(rececionistaListNew);
            List<Medico> attachedMedicoListNew = new ArrayList<Medico>();
            for (Medico medicoListNewMedicoToAttach : medicoListNew) {
                medicoListNewMedicoToAttach = em.getReference(medicoListNewMedicoToAttach.getClass(), medicoListNewMedicoToAttach.getId());
                attachedMedicoListNew.add(medicoListNewMedicoToAttach);
            }
            medicoListNew = attachedMedicoListNew;
            codpostal.setMedicoList(medicoListNew);
            List<Paciente> attachedPacienteListNew = new ArrayList<Paciente>();
            for (Paciente pacienteListNewPacienteToAttach : pacienteListNew) {
                pacienteListNewPacienteToAttach = em.getReference(pacienteListNewPacienteToAttach.getClass(), pacienteListNewPacienteToAttach.getId());
                attachedPacienteListNew.add(pacienteListNewPacienteToAttach);
            }
            pacienteListNew = attachedPacienteListNew;
            codpostal.setPacienteList(pacienteListNew);
            codpostal = em.merge(codpostal);
            for (Administrador administradorListOldAdministrador : administradorListOld) {
                if (!administradorListNew.contains(administradorListOldAdministrador)) {
                    administradorListOldAdministrador.setCodPostal(null);
                    administradorListOldAdministrador = em.merge(administradorListOldAdministrador);
                }
            }
            for (Administrador administradorListNewAdministrador : administradorListNew) {
                if (!administradorListOld.contains(administradorListNewAdministrador)) {
                    Codpostal oldCodPostalOfAdministradorListNewAdministrador = administradorListNewAdministrador.getCodPostal();
                    administradorListNewAdministrador.setCodPostal(codpostal);
                    administradorListNewAdministrador = em.merge(administradorListNewAdministrador);
                    if (oldCodPostalOfAdministradorListNewAdministrador != null && !oldCodPostalOfAdministradorListNewAdministrador.equals(codpostal)) {
                        oldCodPostalOfAdministradorListNewAdministrador.getAdministradorList().remove(administradorListNewAdministrador);
                        oldCodPostalOfAdministradorListNewAdministrador = em.merge(oldCodPostalOfAdministradorListNewAdministrador);
                    }
                }
            }
            for (Rececionista rececionistaListOldRececionista : rececionistaListOld) {
                if (!rececionistaListNew.contains(rececionistaListOldRececionista)) {
                    rececionistaListOldRececionista.setCodPostal(null);
                    rececionistaListOldRececionista = em.merge(rececionistaListOldRececionista);
                }
            }
            for (Rececionista rececionistaListNewRececionista : rececionistaListNew) {
                if (!rececionistaListOld.contains(rececionistaListNewRececionista)) {
                    Codpostal oldCodPostalOfRececionistaListNewRececionista = rececionistaListNewRececionista.getCodPostal();
                    rececionistaListNewRececionista.setCodPostal(codpostal);
                    rececionistaListNewRececionista = em.merge(rececionistaListNewRececionista);
                    if (oldCodPostalOfRececionistaListNewRececionista != null && !oldCodPostalOfRececionistaListNewRececionista.equals(codpostal)) {
                        oldCodPostalOfRececionistaListNewRececionista.getRececionistaList().remove(rececionistaListNewRececionista);
                        oldCodPostalOfRececionistaListNewRececionista = em.merge(oldCodPostalOfRececionistaListNewRececionista);
                    }
                }
            }
            for (Medico medicoListOldMedico : medicoListOld) {
                if (!medicoListNew.contains(medicoListOldMedico)) {
                    medicoListOldMedico.setCodPostal(null);
                    medicoListOldMedico = em.merge(medicoListOldMedico);
                }
            }
            for (Medico medicoListNewMedico : medicoListNew) {
                if (!medicoListOld.contains(medicoListNewMedico)) {
                    Codpostal oldCodPostalOfMedicoListNewMedico = medicoListNewMedico.getCodPostal();
                    medicoListNewMedico.setCodPostal(codpostal);
                    medicoListNewMedico = em.merge(medicoListNewMedico);
                    if (oldCodPostalOfMedicoListNewMedico != null && !oldCodPostalOfMedicoListNewMedico.equals(codpostal)) {
                        oldCodPostalOfMedicoListNewMedico.getMedicoList().remove(medicoListNewMedico);
                        oldCodPostalOfMedicoListNewMedico = em.merge(oldCodPostalOfMedicoListNewMedico);
                    }
                }
            }
            for (Paciente pacienteListOldPaciente : pacienteListOld) {
                if (!pacienteListNew.contains(pacienteListOldPaciente)) {
                    pacienteListOldPaciente.setCodPostal(null);
                    pacienteListOldPaciente = em.merge(pacienteListOldPaciente);
                }
            }
            for (Paciente pacienteListNewPaciente : pacienteListNew) {
                if (!pacienteListOld.contains(pacienteListNewPaciente)) {
                    Codpostal oldCodPostalOfPacienteListNewPaciente = pacienteListNewPaciente.getCodPostal();
                    pacienteListNewPaciente.setCodPostal(codpostal);
                    pacienteListNewPaciente = em.merge(pacienteListNewPaciente);
                    if (oldCodPostalOfPacienteListNewPaciente != null && !oldCodPostalOfPacienteListNewPaciente.equals(codpostal)) {
                        oldCodPostalOfPacienteListNewPaciente.getPacienteList().remove(pacienteListNewPaciente);
                        oldCodPostalOfPacienteListNewPaciente = em.merge(oldCodPostalOfPacienteListNewPaciente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = codpostal.getCodPostal();
                if (findCodpostal(id) == null) {
                    throw new NonexistentEntityException("The codpostal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Codpostal codpostal;
            try {
                codpostal = em.getReference(Codpostal.class, id);
                codpostal.getCodPostal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The codpostal with id " + id + " no longer exists.", enfe);
            }
            List<Administrador> administradorList = codpostal.getAdministradorList();
            for (Administrador administradorListAdministrador : administradorList) {
                administradorListAdministrador.setCodPostal(null);
                administradorListAdministrador = em.merge(administradorListAdministrador);
            }
            List<Rececionista> rececionistaList = codpostal.getRececionistaList();
            for (Rececionista rececionistaListRececionista : rececionistaList) {
                rececionistaListRececionista.setCodPostal(null);
                rececionistaListRececionista = em.merge(rececionistaListRececionista);
            }
            List<Medico> medicoList = codpostal.getMedicoList();
            for (Medico medicoListMedico : medicoList) {
                medicoListMedico.setCodPostal(null);
                medicoListMedico = em.merge(medicoListMedico);
            }
            List<Paciente> pacienteList = codpostal.getPacienteList();
            for (Paciente pacienteListPaciente : pacienteList) {
                pacienteListPaciente.setCodPostal(null);
                pacienteListPaciente = em.merge(pacienteListPaciente);
            }
            em.remove(codpostal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Codpostal> findCodpostalEntities() {
        return findCodpostalEntities(true, -1, -1);
    }

    public List<Codpostal> findCodpostalEntities(int maxResults, int firstResult) {
        return findCodpostalEntities(false, maxResults, firstResult);
    }

    private List<Codpostal> findCodpostalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Codpostal.class));
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

    public Codpostal findCodpostal(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Codpostal.class, id);
        } finally {
            em.close();
        }
    }

    public int getCodpostalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Codpostal> rt = cq.from(Codpostal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Boolean existeCodpostal(String id) {
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Codpostal.findByCodPostal");
        q.setParameter("codPostal", id);
        Boolean encontrou = false;
        
        for(Object o : q.getResultList()){
            if(((Codpostal)o).getCodPostal().equals(id)) encontrou = true;
        }
        
        return encontrou;
        
    }
   
    
}
