package br.edu.labbd.fateczl.TrabalhoLabBd.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.labbd.fateczl.TrabalhoLabBd.model.Cliente;
import br.edu.labbd.fateczl.TrabalhoLabBd.model.Consulta;
import br.edu.labbd.fateczl.TrabalhoLabBd.model.LoginRequest;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.AuthDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.ClienteDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.ConsultaDao;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class MappingPages {
	
	
	@GetMapping("/")
	public String home(Model model) throws ClassNotFoundException, SQLException {

		return "index";
	}
	
	
	//MÉDICO
	@GetMapping("/visualizar")
	public String visualizar_consulta(@CookieValue(value = "tipo", defaultValue = "desconhecido") String cookie) {
		if(!cookie.equals("medico")){
			return "404";
		}
		return "visualizar_consultas";
	}
	
	
	//CLIENTE
	@GetMapping("/consultas")
	public String consultas(@CookieValue(value = "tipo", defaultValue = "desconhecido") String cookie, HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		if(!cookie.equals("cliente")){
			return "404";
		}
		
		ConsultaDao db = new ConsultaDao();
		List<Consulta> consultas = db.getAllById();
		
		//Debug pra teste pode apagar dps, inclusive o parametro passado na função
		System.out.println(request.getHeader("Cookie"));
		
		model.addAttribute("lista_consulta", consultas);
		
		
		return "historico_consulta";
	}
	
	@GetMapping("/agendar")
	public String agendar(@CookieValue(value = "tipo", defaultValue = "desconhecido") String cookie, Model model) {
		if(!cookie.equals("cliente")){
			return "404";
		}
		List<String> lista = new ArrayList<String>();
		lista.add("Neurologista");
		lista.add("Ortopedista");
		model.addAttribute("listaEspecialidade",lista);
		return "agendar_consulta";
	}

}
