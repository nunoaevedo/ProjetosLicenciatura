/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BLL.ConsultaJpaController;
import BLL.PacienteJpaController;
import DAL.Consulta;
import DAL.Paciente;
import DAL.Utilizador;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Nuno
 */


public class consultasNaoRealizadasController extends AbstractController {
    
    public consultasNaoRealizadasController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        
        
        HttpSession session = request.getSession(true);
        
        Utilizador u  = ((Utilizador)session.getAttribute("currentSessionUser"));
        Paciente p = u.getPacienteList().get(0);
        
        PacienteJpaController pc = new PacienteJpaController();
        
        
        List<Consulta> c = new ArrayList<>(); 
        c = pc.getConsultasNaoRealizadas(p);
                    
            
        return new ModelAndView("consultasNaoRealizadas", "cons", c);
            
        
    }
    
    
}
