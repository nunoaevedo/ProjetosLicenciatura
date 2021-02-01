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
import java.util.ArrayList;
import java.util.List;
import DAL.Rececionista;
import DAL.Medico;
import DAL.Paciente;
import DAL.Utilizador;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nuno
 */
public class UtilizadorJpaController implements Serializable {

    private static final String PERSISTENCE_UNIT_NAME = "ProjetoLibraryPU";
        
    public UtilizadorJpaController() {
        this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    private EntityManagerFactory emf = null;

    public  EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Utilizador utilizador) throws PreexistingEntityException, Exception {
        if (utilizador.getAdministradorList() == null) {
            utilizador.setAdministradorList(new ArrayList<Administrador>());
        }
        if (utilizador.getRececionistaList() == null) {
            utilizador.setRececionistaList(new ArrayList<Rececionista>());
        }
        if (utilizador.getMedicoList() == null) {
            utilizador.setMedicoList(new ArrayList<Medico>());
        }
        if (utilizador.getPacienteList() == null) {
            utilizador.setPacienteList(new ArrayList<Paciente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Administrador> attachedAdministradorList = new ArrayList<Administrador>();
            for (Administrador administradorListAdministradorToAttach : utilizador.getAdministradorList()) {
                administradorListAdministradorToAttach = em.getReference(administradorListAdministradorToAttach.getClass(), administradorListAdministradorToAttach.getId());
                attachedAdministradorList.add(administradorListAdministradorToAttach);
            }
            utilizador.setAdministradorList(attachedAdministradorList);
            List<Rececionista> attachedRececionistaList = new ArrayList<Rececionista>();
            for (Rececionista rececionistaListRececionistaToAttach : utilizador.getRececionistaList()) {
                rececionistaListRececionistaToAttach = em.getReference(rececionistaListRececionistaToAttach.getClass(), rececionistaListRececionistaToAttach.getId());
                attachedRececionistaList.add(rececionistaListRececionistaToAttach);
            }
            utilizador.setRececionistaList(attachedRececionistaList);
            List<Medico> attachedMedicoList = new ArrayList<Medico>();
            for (Medico medicoListMedicoToAttach : utilizador.getMedicoList()) {
                medicoListMedicoToAttach = em.getReference(medicoListMedicoToAttach.getClass(), medicoListMedicoToAttach.getId());
                attachedMedicoList.add(medicoListMedicoToAttach);
            }
            utilizador.setMedicoList(attachedMedicoList);
            List<Paciente> attachedPacienteList = new ArrayList<Paciente>();
            for (Paciente pacienteListPacienteToAttach : utilizador.getPacienteList()) {
                pacienteListPacienteToAttach = em.getReference(pacienteListPacienteToAttach.getClass(), pacienteListPacienteToAttach.getId());
                attachedPacienteList.add(pacienteListPacienteToAttach);
            }
            utilizador.setPacienteList(attachedPacienteList);
            em.persist(utilizador);
            for (Administrador administradorListAdministrador : utilizador.getAdministradorList()) {
                Utilizador oldIdUserOfAdministradorListAdministrador = administradorListAdministrador.getIdUser();
                administradorListAdministrador.setIdUser(utilizador);
                administradorListAdministrador = em.merge(administradorListAdministrador);
                if (oldIdUserOfAdministradorListAdministrador != null) {
                    oldIdUserOfAdministradorListAdministrador.getAdministradorList().remove(administradorListAdministrador);
                    oldIdUserOfAdministradorListAdministrador = em.merge(oldIdUserOfAdministradorListAdministrador);
                }
            }
            for (Rececionista rececionistaListRececionista : utilizador.getRececionistaList()) {
                Utilizador oldIdUserOfRececionistaListRececionista = rececionistaListRececionista.getIdUser();
                rececionistaListRececionista.setIdUser(utilizador);
                rececionistaListRececionista = em.merge(rececionistaListRececionista);
                if (oldIdUserOfRececionistaListRececionista != null) {
                    oldIdUserOfRececionistaListRececionista.getRececionistaList().remove(rececionistaListRececionista);
                    oldIdUserOfRececionistaListRececionista = em.merge(oldIdUserOfRececionistaListRececionista);
                }
            }
            for (Medico medicoListMedico : utilizador.getMedicoList()) {
                Utilizador oldIdUserOfMedicoListMedico = medicoListMedico.getIdUser();
                medicoListMedico.setIdUser(utilizador);
                medicoListMedico = em.merge(medicoListMedico);
                if (oldIdUserOfMedicoListMedico != null) {
                    oldIdUserOfMedicoListMedico.getMedicoList().remove(medicoListMedico);
                    oldIdUserOfMedicoListMedico = em.merge(oldIdUserOfMedicoListMedico);
                }
            }
            for (Paciente pacienteListPaciente : utilizador.getPacienteList()) {
                Utilizador oldIdUserOfPacienteListPaciente = pacienteListPaciente.getIdUser();
                pacienteListPaciente.setIdUser(utilizador);
                pacienteListPaciente = em.merge(pacienteListPaciente);
                if (oldIdUserOfPacienteListPaciente != null) {
                    oldIdUserOfPacienteListPaciente.getPacienteList().remove(pacienteListPaciente);
                    oldIdUserOfPacienteListPaciente = em.merge(oldIdUserOfPacienteListPaciente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUtilizador(utilizador.getId()) != null) {
                throw new PreexistingEntityException("Utilizador " + utilizador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Utilizador utilizador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Utilizador persistentUtilizador = em.find(Utilizador.class, utilizador.getId());
            List<Administrador> administradorListOld = persistentUtilizador.getAdministradorList();
            List<Administrador> administradorListNew = utilizador.getAdministradorList();
            List<Rececionista> rececionistaListOld = persistentUtilizador.getRececionistaList();
            List<Rececionista> rececionistaListNew = utilizador.getRececionistaList();
            List<Medico> medicoListOld = persistentUtilizador.getMedicoList();
            List<Medico> medicoListNew = utilizador.getMedicoList();
            List<Paciente> pacienteListOld = persistentUtilizador.getPacienteList();
            List<Paciente> pacienteListNew = utilizador.getPacienteList();
            List<Administrador> attachedAdministradorListNew = new ArrayList<Administrador>();
            for (Administrador administradorListNewAdministradorToAttach : administradorListNew) {
                administradorListNewAdministradorToAttach = em.getReference(administradorListNewAdministradorToAttach.getClass(), administradorListNewAdministradorToAttach.getId());
                attachedAdministradorListNew.add(administradorListNewAdministradorToAttach);
            }
            administradorListNew = attachedAdministradorListNew;
            utilizador.setAdministradorList(administradorListNew);
            List<Rececionista> attachedRececionistaListNew = new ArrayList<Rececionista>();
            for (Rececionista rececionistaListNewRececionistaToAttach : rececionistaListNew) {
                rececionistaListNewRececionistaToAttach = em.getReference(rececionistaListNewRececionistaToAttach.getClass(), rececionistaListNewRececionistaToAttach.getId());
                attachedRececionistaListNew.add(rececionistaListNewRececionistaToAttach);
            }
            rececionistaListNew = attachedRececionistaListNew;
            utilizador.setRececionistaList(rececionistaListNew);
            List<Medico> attachedMedicoListNew = new ArrayList<Medico>();
            for (Medico medicoListNewMedicoToAttach : medicoListNew) {
                medicoListNewMedicoToAttach = em.getReference(medicoListNewMedicoToAttach.getClass(), medicoListNewMedicoToAttach.getId());
                attachedMedicoListNew.add(medicoListNewMedicoToAttach);
            }
            medicoListNew = attachedMedicoListNew;
            utilizador.setMedicoList(medicoListNew);
            List<Paciente> attachedPacienteListNew = new ArrayList<Paciente>();
            for (Paciente pacienteListNewPacienteToAttach : pacienteListNew) {
                pacienteListNewPacienteToAttach = em.getReference(pacienteListNewPacienteToAttach.getClass(), pacienteListNewPacienteToAttach.getId());
                attachedPacienteListNew.add(pacienteListNewPacienteToAttach);
            }
            pacienteListNew = attachedPacienteListNew;
            utilizador.setPacienteList(pacienteListNew);
            utilizador = em.merge(utilizador);
            for (Administrador administradorListOldAdministrador : administradorListOld) {
                if (!administradorListNew.contains(administradorListOldAdministrador)) {
                    administradorListOldAdministrador.setIdUser(null);
                    administradorListOldAdministrador = em.merge(administradorListOldAdministrador);
                }
            }
            for (Administrador administradorListNewAdministrador : administradorListNew) {
                if (!administradorListOld.contains(administradorListNewAdministrador)) {
                    Utilizador oldIdUserOfAdministradorListNewAdministrador = administradorListNewAdministrador.getIdUser();
                    administradorListNewAdministrador.setIdUser(utilizador);
                    administradorListNewAdministrador = em.merge(administradorListNewAdministrador);
                    if (oldIdUserOfAdministradorListNewAdministrador != null && !oldIdUserOfAdministradorListNewAdministrador.equals(utilizador)) {
                        oldIdUserOfAdministradorListNewAdministrador.getAdministradorList().remove(administradorListNewAdministrador);
                        oldIdUserOfAdministradorListNewAdministrador = em.merge(oldIdUserOfAdministradorListNewAdministrador);
                    }
                }
            }
            for (Rececionista rececionistaListOldRececionista : rececionistaListOld) {
                if (!rececionistaListNew.contains(rececionistaListOldRececionista)) {
                    rececionistaListOldRececionista.setIdUser(null);
                    rececionistaListOldRececionista = em.merge(rececionistaListOldRececionista);
                }
            }
            for (Rececionista rececionistaListNewRececionista : rececionistaListNew) {
                if (!rececionistaListOld.contains(rececionistaListNewRececionista)) {
                    Utilizador oldIdUserOfRececionistaListNewRececionista = rececionistaListNewRececionista.getIdUser();
                    rececionistaListNewRececionista.setIdUser(utilizador);
                    rececionistaListNewRececionista = em.merge(rececionistaListNewRececionista);
                    if (oldIdUserOfRececionistaListNewRececionista != null && !oldIdUserOfRececionistaListNewRececionista.equals(utilizador)) {
                        oldIdUserOfRececionistaListNewRececionista.getRececionistaList().remove(rececionistaListNewRececionista);
                        oldIdUserOfRececionistaListNewRececionista = em.merge(oldIdUserOfRececionistaListNewRececionista);
                    }
                }
            }
            for (Medico medicoListOldMedico : medicoListOld) {
                if (!medicoListNew.contains(medicoListOldMedico)) {
                    medicoListOldMedico.setIdUser(null);
                    medicoListOldMedico = em.merge(medicoListOldMedico);
                }
            }
            for (Medico medicoListNewMedico : medicoListNew) {
                if (!medicoListOld.contains(medicoListNewMedico)) {
                    Utilizador oldIdUserOfMedicoListNewMedico = medicoListNewMedico.getIdUser();
                    medicoListNewMedico.setIdUser(utilizador);
                    medicoListNewMedico = em.merge(medicoListNewMedico);
                    if (oldIdUserOfMedicoListNewMedico != null && !oldIdUserOfMedicoListNewMedico.equals(utilizador)) {
                        oldIdUserOfMedicoListNewMedico.getMedicoList().remove(medicoListNewMedico);
                        oldIdUserOfMedicoListNewMedico = em.merge(oldIdUserOfMedicoListNewMedico);
                    }
                }
            }
            for (Paciente pacienteListOldPaciente : pacienteListOld) {
                if (!pacienteListNew.contains(pacienteListOldPaciente)) {
                    pacienteListOldPaciente.setIdUser(null);
                    pacienteListOldPaciente = em.merge(pacienteListOldPaciente);
                }
            }
            for (Paciente pacienteListNewPaciente : pacienteListNew) {
                if (!pacienteListOld.contains(pacienteListNewPaciente)) {
                    Utilizador oldIdUserOfPacienteListNewPaciente = pacienteListNewPaciente.getIdUser();
                    pacienteListNewPaciente.setIdUser(utilizador);
                    pacienteListNewPaciente = em.merge(pacienteListNewPaciente);
                    if (oldIdUserOfPacienteListNewPaciente != null && !oldIdUserOfPacienteListNewPaciente.equals(utilizador)) {
                        oldIdUserOfPacienteListNewPaciente.getPacienteList().remove(pacienteListNewPaciente);
                        oldIdUserOfPacienteListNewPaciente = em.merge(oldIdUserOfPacienteListNewPaciente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = utilizador.getId();
                if (findUtilizador(id) == null) {
                    throw new NonexistentEntityException("The utilizador with id " + id + " no longer exists.");
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
            Utilizador utilizador;
            try {
                utilizador = em.getReference(Utilizador.class, id);
                utilizador.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The utilizador with id " + id + " no longer exists.", enfe);
            }
            List<Administrador> administradorList = utilizador.getAdministradorList();
            for (Administrador administradorListAdministrador : administradorList) {
                administradorListAdministrador.setIdUser(null);
                administradorListAdministrador = em.merge(administradorListAdministrador);
            }
            List<Rececionista> rececionistaList = utilizador.getRececionistaList();
            for (Rececionista rececionistaListRececionista : rececionistaList) {
                rececionistaListRececionista.setIdUser(null);
                rececionistaListRececionista = em.merge(rececionistaListRececionista);
            }
            List<Medico> medicoList = utilizador.getMedicoList();
            for (Medico medicoListMedico : medicoList) {
                medicoListMedico.setIdUser(null);
                medicoListMedico = em.merge(medicoListMedico);
            }
            List<Paciente> pacienteList = utilizador.getPacienteList();
            for (Paciente pacienteListPaciente : pacienteList) {
                pacienteListPaciente.setIdUser(null);
                pacienteListPaciente = em.merge(pacienteListPaciente);
            }
            em.remove(utilizador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Utilizador> findUtilizadorEntities() {
        return findUtilizadorEntities(true, -1, -1);
    }

    public List<Utilizador> findUtilizadorEntities(int maxResults, int firstResult) {
        return findUtilizadorEntities(false, maxResults, firstResult);
    }

    private List<Utilizador> findUtilizadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Utilizador.class));
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

    public Utilizador findUtilizador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Utilizador.class, id);
        } finally {
            em.close();
        }
    }

    public int getUtilizadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Utilizador> rt = cq.from(Utilizador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
    public Boolean loginAdministrador(String user, String pass){
        
        EntityManager em = getEntityManager();
        
        Utilizador util = new Utilizador();
        Query q = em.createNamedQuery("Utilizador.findByUsername");
        q.setParameter("username", user);
        
        if(q.getResultList().isEmpty())return false;
        
        for(Object o : q.getResultList()){           
            util.setUsername(((Utilizador)o).getUsername());
            util.setPassword(((Utilizador)o).getPassword());
            util.setTipoUser(((Utilizador)o).getTipoUser());
        }
        
        return util.getPassword().equals(pass) && util.getTipoUser() == 0;
        
        
    }
    
    public Boolean loginMedico(String user, String pass){
        
        EntityManager em = getEntityManager();
        
        Utilizador util = new Utilizador();
        Query q = em.createNamedQuery("Utilizador.findByUsername");
        q.setParameter("username", user);
        
        if(q.getResultList().isEmpty())return false;
        
        for(Object o : q.getResultList()){           
            util.setUsername(((Utilizador)o).getUsername());
            util.setPassword(((Utilizador)o).getPassword());
            util.setTipoUser(((Utilizador)o).getTipoUser());
        }
        
        return util.getPassword().equals(pass) && util.getTipoUser() == 1;
        
        
    }
    
    
    public Boolean loginRececionista(String user, String pass){
        
        EntityManager em = getEntityManager();
        
        Utilizador util = new Utilizador();
        Query q = em.createNamedQuery("Utilizador.findByUsername");
        q.setParameter("username", user);
        
        if(q.getResultList().isEmpty())return false;
        
        for(Object o : q.getResultList()){           
            util.setUsername(((Utilizador)o).getUsername());
            util.setPassword(((Utilizador)o).getPassword());
            util.setTipoUser(((Utilizador)o).getTipoUser());
        }
        
        return util.getPassword().equals(pass) && util.getTipoUser() == 2;
        
        
    }
    
    public Boolean loginPaciente(String user, String pass){
        
        EntityManager em = getEntityManager();
        
        try{
            Utilizador util = new Utilizador();
            Query q = em.createNamedQuery("Utilizador.findByUsername");
            q.setParameter("username", user);


            if(q.getResultList().isEmpty())return false;

            for(Object o : q.getResultList()){           
                util.setUsername(((Utilizador)o).getUsername());
                util.setPassword(((Utilizador)o).getPassword());
                util.setTipoUser(((Utilizador)o).getTipoUser());
            }

            return util.getPassword().equals(pass) && util.getTipoUser() == 3;
        }finally{
            em.close();
        }
        
        
        
    }
    
    
    public Boolean existeUser(String user){
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Utilizador.findByUsername");
        q.setParameter("username", user);
        
        
        //retorna true se existir utilizador
        return !q.getResultList().isEmpty();
    }  
    
    public Utilizador findUtilizadorNome(String nome) {
        EntityManager em = getEntityManager();
        try {
            
            Utilizador utili = new Utilizador();
            Query q = em.createNamedQuery("Utilizador.findByUsername");
            q.setParameter("username", nome);

            for(Object o : q.getResultList() ){
                utili = ((Utilizador)o);
            }

            return utili;
        }finally{
            em.close();
        }
        
    }
    
    
}
