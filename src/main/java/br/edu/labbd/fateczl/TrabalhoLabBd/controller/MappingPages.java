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
import br.edu.labbd.fateczl.TrabalhoLabBd.model.Especialidade;
import br.edu.labbd.fateczl.TrabalhoLabBd.model.LoginRequest;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.AuthDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.ClienteDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.ConsultaDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.EspecialidadeDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.GenericDAO;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.MaterialDao;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class MappingPages {
	
	/*SOLID: Responsabilidade unica: controller especifico para mapeamento de paginas*/
	
	private GenericDAO gDAO = new GenericDAO();
	
	@GetMapping("/")
	public String home(Model model) throws ClassNotFoundException, SQLException {
		return "index";
	}
	
	//MÉDICO
	@GetMapping("/visualizar")
	public String visualizar_consulta(@CookieValue(value = "tipo", defaultValue = "desconhecido") String tipo, @CookieValue(value = "user", defaultValue = "") String rg, HttpServletRequest request, Model model) throws SQLException, ClassNotFoundException 		{
		if(!tipo.equals("medico")){
			return "404";
		}
		ConsultaDao db = new ConsultaDao(gDAO);
		List<Consulta> consultas = db.sp_ver_consultas(rg);
		model.addAttribute("lista_consulta", consultas);
		return "visualizar_consultas";
	}
	@GetMapping("medicos")
	public String cadastrarMedico(@CookieValue(value = "tipo", defaultValue = "desconhecido") String tipo, @CookieValue(value = "user", defaultValue = "desconhecido") String rg, HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		if(!tipo.equals("medico")){
			return "404";
		}
		
		EspecialidadeDao eDao = new EspecialidadeDao(gDAO);
		List<Especialidade> lista = eDao.getAll();
		model.addAttribute("listaEspecialidade",lista);
		
		return "cadastrar_medico";
	}
	@GetMapping("clientes")
	public String cadastrarCliente (@CookieValue(value = "tipo", defaultValue = "desconhecido") String tipo, @CookieValue(value = "user", defaultValue = "desconhecido") String rg, HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		if(!tipo.equals("medico")){
			return "404";
		}
		return "cadastrar_cliente";
	}
	
	@GetMapping("especialidades")
	public String cadastrarEspecialidade (@CookieValue(value = "tipo", defaultValue = "desconhecido") String tipo, @CookieValue(value = "user", defaultValue = "desconhecido") String rg, HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		if(!tipo.equals("medico")){
			return "404";
		}
		
		EspecialidadeDao db = new EspecialidadeDao(gDAO);
		model.addAttribute("lista_especialidade",db.getAll());
		
		
		return "cadastro_especialidade";
	}
	
	@GetMapping("material")
	public String cadastrarMaterial (@CookieValue(value = "tipo", defaultValue = "desconhecido") String tipo, @CookieValue(value = "user", defaultValue = "desconhecido") String rg, HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		if(!tipo.equals("medico")){
			return "404";
		}
		
		MaterialDao db = new MaterialDao(gDAO);
		model.addAttribute("lista_material",db.getAll());
		
		
		return "cadastro_material";
	}
	
	
}
