package br.edu.labbd.fateczl.TrabalhoLabBd.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.labbd.fateczl.TrabalhoLabBd.model.Especialidade;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.EspecialidadeDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.GenericDAO;

@Controller
public class EspecialidadeController {
	
	/*SOLID: Responsabilidade unica: controller especifico para acoes de Especialidade*/
	
	GenericDAO gDAO = new GenericDAO();

	@PostMapping("/salvarEspecialidade")
	public String salvarEspecialidade(@RequestParam Map<String, String> params,Model model) throws ClassNotFoundException, SQLException {
	
		EspecialidadeDao eDao = new EspecialidadeDao(gDAO);
		Especialidade especialidade = new Especialidade();
		especialidade.setId(Integer.parseInt(params.get("id")));
		especialidade.setNome(params.get("nome"));
		try {
			eDao.inserir(especialidade);
		}catch(SQLException error) {

		}
		model.addAttribute("lista_especialidade",eDao.getAll());
		return "cadastro_especialidade";
	}	
	@PostMapping("/deleteEspecialidade")
	public String deleteEspecialidade(@RequestBody Especialidade especialidade, Model model) throws ClassNotFoundException, SQLException {
	
		try {
			
			EspecialidadeDao db = new EspecialidadeDao(gDAO);
			db.deleteById(especialidade.getId());
		}catch(Exception exception){
			model.addAttribute("erro","Existe um médico Vinculado a Especialidade!");
		}
		
		EspecialidadeDao db = new EspecialidadeDao(gDAO);
		model.addAttribute("lista_especialidade",db.getAll());		
		
		return "cadastro_especialidade";
	}
	
}
