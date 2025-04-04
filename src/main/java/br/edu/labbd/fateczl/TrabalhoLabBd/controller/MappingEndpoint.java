package br.edu.labbd.fateczl.TrabalhoLabBd.controller;

import java.sql.SQLException;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.AuthDao;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MappingEndpoint {

	
	@PostMapping("/checkLogin")
	public String AuthCheck(@RequestParam String rg, @RequestParam String password, Model model, HttpServletResponse response, @CookieValue(value = "user", defaultValue = "") String c) throws ClassNotFoundException, SQLException {
	    System.out.println("Usuário: " + rg);
	    System.out.println("Senha: " + password);
	    AuthDao db = new AuthDao();
	    int value = db.checkLogin(rg, password);
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
