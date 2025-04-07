package br.edu.labbd.fateczl.TrabalhoLabBd.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.labbd.fateczl.TrabalhoLabBd.model.Material;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.GenericDAO;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.MaterialDao;

@Controller
public class MaterialController {
	
	/*SOLID: Responsabilidade unica: controller especifico para acoes de Material*/
	
	GenericDAO gDAO = new GenericDAO();

	@PostMapping("/salvarMaterial")
	public String salvarMaterial(@RequestParam Map<String, String> params,Model model) throws ClassNotFoundException, SQLException {
	
		MaterialDao eDao = new MaterialDao(gDAO);
		Material especialidade = new Material();
		especialidade.setId(Integer.parseInt(params.get("id")));
		especialidade.setNome(params.get("nome"));
		especialidade.setValor(Double.parseDouble(params.get("valor")));
		try {
			eDao.inserir(especialidade);
		}catch(SQLException error) {
		
		}
		model.addAttribute("lista_material",eDao.getAll());
		return "cadastro_material";
	}	
	@PostMapping("/deleteMaterial")
	public String deleteMaterial(@RequestBody Material material, Model model) throws ClassNotFoundException, SQLException {

	    try {
	        MaterialDao dao = new MaterialDao(gDAO);
	        dao.deleteById(material.getId());
	    } catch (Exception e) {
	        model.addAttribute("erro", "Não foi possível excluir o material. Verifique se ele está vinculado a outro registro.");
	    }

	    MaterialDao dao = new MaterialDao(gDAO);
	    model.addAttribute("lista_material", dao.getAll());

	    return "cadastro_material";
	}	
}
