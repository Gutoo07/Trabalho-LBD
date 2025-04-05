package br.edu.labbd.fateczl.TrabalhoLabBd.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.labbd.fateczl.TrabalhoLabBd.model.Especialidade;
import br.edu.labbd.fateczl.TrabalhoLabBd.model.Medico;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.AuthDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.ConsultaDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.EspecialidadeDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.GenericDAO;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.MedicoDao;
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
	@PostMapping("/crudMedicoPost")
	public String crudMedicoPost(@RequestParam Map<String, String> params, HttpServletResponse response, Model model) throws SQLException, ClassNotFoundException {
		MedicoDao mDao = new MedicoDao(gDAO);
		EspecialidadeDao eDao = new EspecialidadeDao(gDAO);
		
		String saida = "";
		String erro = "";
		String acao = params.get("botao");		
		String rg = params.get("rg");
		String nome = params.get("nome");
		String telefone = params.get("telefone");
		String valorConsulta = params.get("valorConsulta");
		String periodo = params.get("periodo");
		String especialidade = params.get("especialidade");
		String senha = params.get("senha");
		
		
		Medico medico = new Medico();
		List<Medico> medicos = new ArrayList<>();
		if (!acao.equalsIgnoreCase("Listar")) {
			medico.setRg(rg);

		}
		if (acao.equalsIgnoreCase("Inserir") || acao.equalsIgnoreCase("Atualizar")) {
			medico.setNome(nome);
			medico.setNome(nome);
			medico.setTelefone(telefone);
			medico.setPeriodo(periodo);
			medico.setValor_consulta(Double.parseDouble(valorConsulta));
			
			Especialidade especialidadeAux = new Especialidade();
			especialidadeAux = eDao.getByNome(especialidade);			
			medico.setEspecialidade(especialidadeAux);
			medico.setSenha(senha);
		}
		
		try {
			if (acao.equalsIgnoreCase("Inserir")) {
				saida = mDao.sp_medico("I", medico);
			}
			if (acao.equalsIgnoreCase("Atualizar")) {
				saida = mDao.sp_medico("U", medico);
			}
			if (acao.equalsIgnoreCase("Excluir")) {
				saida = mDao.excluir(medico);
			}
			if (acao.equalsIgnoreCase("Buscar")) {
				//medico = mDao.buscar(medico);
			}
			if (acao.equalsIgnoreCase("Listar")) {
				medicos = mDao.getAll();
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("saida", saida);
			model.addAttribute("erro", erro);
			model.addAttribute("medico", medico);
			model.addAttribute("medicos", medicos);
		}
		return "cadastrar_medico";
	}
	@GetMapping("/crudMedicoGet")
	public String crudMedicoGet(@RequestParam Map<String, String> params, ModelMap model) throws SQLException, ClassNotFoundException {
		String acao = params.get("acao");
		String rg = params.get("rg");
		
		MedicoDao mDao = new MedicoDao(gDAO);
		Medico medico = new Medico();
		List<Medico> medicos = new ArrayList<>();
		String erro = "";
		try {
			if (rg != null) {
				medico.setRg(rg);
				if (acao.equals("excluir")) {
					mDao.excluir(medico);
					medicos = mDao.getAll();
					medico = null;
				} else if (acao.equals("editar")) {
					medico = mDao.getById(rg);
					medicos = null;
					EspecialidadeDao eDao = new EspecialidadeDao(gDAO);
					List<Especialidade> lista = eDao.getAll();
					model.addAttribute("listaEspecialidade", lista);					
				} else {
					medico = null;
					medicos = null;
				}				
			}
		} catch (SQLException | ClassNotFoundException e ){
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("medico", medico);
			model.addAttribute("medicos", medicos);
		}
		return "cadastrar_medico";
	}
		
}
