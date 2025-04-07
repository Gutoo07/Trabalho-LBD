package br.edu.labbd.fateczl.TrabalhoLabBd.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.labbd.fateczl.TrabalhoLabBd.model.Cliente;
import br.edu.labbd.fateczl.TrabalhoLabBd.model.Especialidade;
import br.edu.labbd.fateczl.TrabalhoLabBd.model.LoginRequest;
import br.edu.labbd.fateczl.TrabalhoLabBd.model.Material;
import br.edu.labbd.fateczl.TrabalhoLabBd.model.Medico;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.AuthDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.ClienteDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.ConsultaDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.EspecialidadeDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.GenericDAO;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.MaterialDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.MedicoDao;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
	
	/*SOLID: Responsabilidade unica: controller especifico para Login*/
	
	GenericDAO gDAO = new GenericDAO();
	
	@PostMapping("/checkLogin")
	public String AuthCheck(@RequestParam String rg, @RequestParam String password, Model model, HttpServletResponse response, @CookieValue(value = "user", defaultValue = "") String c) throws ClassNotFoundException, SQLException {
	    AuthDao db = new AuthDao();
	    LoginRequest loginRequest = new LoginRequest(rg,password);
	    int value = db.checkLogin(loginRequest);
	    
	    System.out.println(value);
	    System.out.println(c.isEmpty());
	    
	    if(value == 1) {
		    Cookie cookie_user = new Cookie("user",rg);
		    Cookie cookie_pass = new Cookie("tipo","cliente");
		    cookie_user.setMaxAge(3600);
		    cookie_pass.setMaxAge(3600);
		    response.addCookie(cookie_user);
		    response.addCookie(cookie_pass);
	    }else if(value == 2) {
		    Cookie cookie_user = new Cookie("user",rg);
		    Cookie cookie_pass = new Cookie("tipo","medico");
		    cookie_user.setMaxAge(3600);
		    cookie_pass.setMaxAge(3600);
		    response.addCookie(cookie_user);
		    response.addCookie(cookie_pass);	    	
	    }
	    model.addAttribute("value",value);
	    
	    return "index";   
	}
}