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
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.labbd.fateczl.TrabalhoLabBd.model.Cliente;
import br.edu.labbd.fateczl.TrabalhoLabBd.model.Consulta;
import br.edu.labbd.fateczl.TrabalhoLabBd.model.Especialidade;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.ClienteDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.ConsultaDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.EspecialidadeDao;
import br.edu.labbd.fateczl.TrabalhoLabBd.persistence.GenericDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ClienteController {
	
	private GenericDAO gDAO = new GenericDAO();

	//CLIENTE
	@GetMapping("/consultas")
	public String consultas(@CookieValue(value = "tipo", defaultValue = "desconhecido") String tipo,@CookieValue(value = "user", defaultValue = "desconhecido") String rg, HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException {
		if(!tipo.equals("cliente")){
			return "404";
		}
		ConsultaDao db = new ConsultaDao(gDAO);
		List<Consulta> consultas = db.sp_ver_consultas(rg);
		
		model.addAttribute("lista_consulta", consultas);
		return "historico_consulta";
	}
	
	@GetMapping("/agendar")
	public String agendar(@CookieValue(value = "tipo", defaultValue = "desconhecido") String tipo, Model model) throws ClassNotFoundException, SQLException {
		if(!tipo.equals("cliente")){
			return "404";
		}
		EspecialidadeDao eDao = new EspecialidadeDao(gDAO);
		List<Especialidade> lista = eDao.getAll();

		model.addAttribute("listaEspecialidade",lista);
		return "agendar_consulta";
	}
	@PostMapping("/agendarConsulta")
	public String agendarConsulta(@RequestParam String dia, @RequestParam String hora, @RequestParam String especialidade, @RequestParam boolean particularBoolean, Model model,
								@RequestParam String codigo_autorizacao, HttpServletResponse response,@CookieValue(value = "user", defaultValue = "") String rg)
								throws ClassNotFoundException, SQLException {
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
		return "agendar_consulta";
	}
	@PostMapping("/crudClientePost")
	public String crudClientePost(@RequestParam Map<String, String> params, HttpServletResponse response, Model model) throws SQLException, ClassNotFoundException {
		ClienteDao cDao = new ClienteDao(gDAO);
		
		String saida = "";
		String erro = "";
		String acao = params.get("botao");		
		String rg = params.get("rg");
		String nome = params.get("nome");
		String telefone = params.get("telefone");
		String dt_nasc = params.get("dataNascimento");
		String senha = params.get("senha");
		
		
		Cliente cliente = new Cliente();
		List<Cliente> clientes = new ArrayList<>();
		if (!acao.equalsIgnoreCase("Listar")) {
			cliente.setRg(rg);
		}
		if (acao.equalsIgnoreCase("Inserir") || acao.equalsIgnoreCase("Atualizar")) {
			cliente.setNome(nome);
			cliente.setTelefone(telefone);
			cliente.setDt_nasc(LocalDate.parse(dt_nasc));
			cliente.setSenha(senha);
		}
		
		try {
			if (acao.equalsIgnoreCase("Inserir")) {
				saida = cDao.inserir(cliente);
			}
			if (acao.equalsIgnoreCase("Atualizar")) {
				saida = cDao.atualizar(cliente);
			}
			if (acao.equalsIgnoreCase("Excluir")) {
				saida = cDao.excluir(cliente);
			}
			if (acao.equalsIgnoreCase("Buscar")) {
				cliente = cDao.buscar(cliente);
			}
			if (acao.equalsIgnoreCase("Listar")) {
				clientes = cDao.listar();
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("saida", saida);
			model.addAttribute("erro", erro);
			model.addAttribute("cliente", cliente);
			model.addAttribute("clientes", clientes);
		}
		return "cadastrar_cliente";
	}
	@GetMapping("/crudClienteGet")
	public String crudClienteGet(@RequestParam Map<String, String> params, ModelMap model) throws SQLException, ClassNotFoundException {
		String acao = params.get("acao");
		String rg = params.get("rg");
		
		ClienteDao cDao = new ClienteDao(gDAO);
		Cliente cliente = new Cliente();
		List<Cliente> clientes = new ArrayList<>();
		String erro = "";
		try {
			if (rg != null) {
				cliente.setRg(rg);
				if (acao.equals("excluir")) {
					cDao.excluir(cliente);
					clientes = cDao.listar();
					cliente = null;
				} else if (acao.equals("editar")) {
					cliente = cDao.buscar(cliente);
					clientes = null;
				} else {
					cliente = null;
					clientes = null;
				}				
			}
		} catch (SQLException | ClassNotFoundException e ){
			erro = e.getMessage();
		} finally {
			model.addAttribute("erro", erro);
			model.addAttribute("cliente", cliente);
			model.addAttribute("clientes", clientes);
		}
		return "cadastrar_cliente";
	}
}
