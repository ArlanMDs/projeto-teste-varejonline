package com.projetoteste.arlan.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projetoteste.arlan.entity.Orcamento;
import com.projetoteste.arlan.entity.PedidoDto;
import com.projetoteste.arlan.repository.OrcamentoRepository;
import com.projetoteste.arlan.repository.PedidoRepository;
import com.projetoteste.arlan.services.OrcamentoService;

@Controller
public class OrcamentoController {

	@Autowired
	private OrcamentoService orcamentoService;

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	OrcamentoRepository orcamentoRepository;

	@GetMapping("/criarOrcamento")
	public String index(Model model) {
		model.addAttribute("orcamento", orcamentoService.createOrcamento());
		return "criarOrcamento";
	}

	@PostMapping("/criarOrcamento")
	public String save(@Valid Orcamento orcamento, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("errorMessage", "Os dados contém erros.");
		} else {

			model.addAttribute("orcamento", orcamentoService.saveOrcamento(orcamento));
			model.addAttribute("successMessage", "Orcamento salvo com sucesso!");
		}

		return "criarOrcamento";
	}

	@GetMapping("/editarOrcamento/{id}")
	public String getForm(Model model, @PathVariable(required = false, name = "id") Long id) {

		model.addAttribute("orcamento", orcamentoRepository.findById(id));
		PedidoDto pedidoDto = new PedidoDto();
		pedidoDto.setPedidos(pedidoRepository.getPedidosByOrcamentoId(id));
		model.addAttribute("pedidoDto", pedidoDto);
		return "editarOrcamento";
	}

	@PostMapping("/editarOrcamento")
	public String edit(@Valid Orcamento orcamento, BindingResult bindingResult, RedirectAttributes redirAttrs,
			Model model) {
		PedidoDto pedidoDto = new PedidoDto();
		pedidoDto.setPedidos(pedidoRepository.getPedidosByOrcamentoId(orcamento.getId()));
		model.addAttribute("pedidoDto", pedidoDto);

		if (bindingResult.hasErrors()) {
			redirAttrs.addFlashAttribute("errorMessage", "Os dados contém erros.");
		} else {

			model.addAttribute("orcamento", orcamentoService.editOrcamento(orcamento));
			redirAttrs.addFlashAttribute("successMessage", "Orcamento salvo com sucesso!");
		}

		return "redirect:/editarOrcamento/" + orcamento.getId();
	}

	@RequestMapping(value = { "orcamentos" }, method = RequestMethod.GET)
	public String listOrcamentos(ModelMap model) {
		List<Orcamento> orcamentos = orcamentoService.findAllOrcamentos();
		model.addAttribute("orcamentos", orcamentos);
		model.addAttribute("metaTitle", "All Users");
		return "orcamentos";
	}

	@PostMapping("/addPedido")
	public String addPedido(Orcamento orcamento) {
		orcamentoService.addPedido(orcamento);
		return "criarOrcamento :: pedidos";
	}

	@PostMapping("/removePedido")
	public String removePedido(Orcamento orcamento, @RequestParam("removeDynamicRow") Integer pedidoIndex) {
		orcamentoService.removePedido(orcamento, pedidoIndex);
		return "criarOrcamento :: pedidos";
	}

	@RequestMapping(value = "/orcamentoDelete/{id}", method = RequestMethod.GET)
	public String deleteOrcamento(RedirectAttributes redirAttrs, Model model, @PathVariable(required = true, name = "id") Long id) {

		try {
			if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString()
					.contains("[GERENTE]")) {
				orcamentoRepository.deleteById(id);
				redirAttrs.addFlashAttribute("successMessage", "Orcamento excluído com sucesso!");
			}else
				redirAttrs.addFlashAttribute("errorMessage", "Apenas Gerentes podem excluir orçamentos.");


		} catch (Exception e) {
			// TODO: handle exception
		}

		return "redirect:/orcamentos";
	}

	@GetMapping("/search")
	public String search(Model model) {
		return "search";
	}

	@RequestMapping(value = "/orcamentos/{surname}", method = RequestMethod.GET)
	public String showGuestList(Model model, @PathVariable("surname") String surname) {
		model.addAttribute("orcamentos", orcamentoRepository.findByNomeCliente(surname));

		return "search :: resultsList";
	}

	@GetMapping("/tabs")
	public String tabs(Model model) {
		return "tabs";
	}

	@GetMapping("/progress")
	public String progress(Model model) {
		return "progress";
	}

	@GetMapping("/jquery")
	public String jquery(Model model) {
		return "jquery";
	}

	@GetMapping("/jquery2")
	public String jquery2(Model model) {
		return "jquery2";
	}
}
