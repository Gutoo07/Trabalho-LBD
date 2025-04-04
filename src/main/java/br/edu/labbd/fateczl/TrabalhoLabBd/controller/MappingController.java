package br.edu.labbd.fateczl.TrabalhoLabBd.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.labbd.fateczl.TrabalhoLabBd.model.Cliente;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.ClienteDao;


@Controller
public class MappingController {
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/cliente")
	public String cliente() throws ClassNotFoundException, SQLException {
		ClienteDao db = new ClienteDao();
		List<Cliente> list = db.listar();
		System.out.println(list.toString());
		return "cliente";
	}
	
	@GetMapping("/agendar")
	public String agendar(Model model) {
		List<String> lista = new ArrayList<String>();
		lista.add("Neurologista");
		lista.add("Ortopedista");
		model.addAttribute("listaEspecialidade",lista);
		return "agendar_consulta";
	}

}
