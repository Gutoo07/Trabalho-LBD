package br.edu.labbd.fateczl.TrabalhoLabBd.controller;

import java.sql.SQLException;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.AuthDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.ConsultaDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.EspecialidadeDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.GenericDAO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MappingEndpoint {
	
	GenericDAO gDAO = new GenericDAO();
	
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
	@PostMapping("/agendarConsulta")
	public String agendarConsulta(@RequestParam String dia, @RequestParam String hora, @RequestParam String especialidade, @RequestParam boolean particularBoolean, Model model,
								@RequestParam String codigo_autorizacao, HttpServletResponse response,@CookieValue(value = "user", defaultValue = "") String rg)
								throws ClassNotFoundException, SQLException {
		
		System.out.println("Agendar consulta de "+especialidade+" para "+rg);
		EspecialidadeDao eDao = new EspecialidadeDao(gDAO);
		ConsultaDao cDao = new ConsultaDao(gDAO);

		int especialidadeId = (int) eDao.getIdByNome(especialidade);
		String saida = "";
		String erro = "";
		try {
			saida = cDao.sp_consulta(rg, especialidadeId, dia, hora, particularBoolean, codigo_autorizacao);
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("saida", saida);
			model.addAttribute("erro", erro);			
		}
;		return "agendar_consulta";
	}
		
}
