package com.springboot.cleinteapp.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.cleinteapp.model.entity.Ciudad;
import com.springboot.cleinteapp.model.entity.Cliente;
import com.springboot.cleinteapp.model.service.ICiudadeService;
import com.springboot.cleinteapp.model.service.IClienteService;


@Controller
@RequestMapping("/views/clientes")
@SessionAttributes("cliente") 
public class ClienteController {

	@Autowired
	private IClienteService iclienteService;
	
	@Autowired
	private ICiudadeService ciudadService;
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@GetMapping("/")
	public String listarCliente(Model model) {
		
		List<Cliente> listadoCliente = iclienteService.listarTodos();
		
		model.addAttribute("titulo", "Lista de Clientes");
		model.addAttribute("clientes",listadoCliente);
		return "/views/clientes/listar";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/crear")
	public String crear(Model model) {
		
		Cliente cliente = new Cliente();
		List<Ciudad> listCiudades = ciudadService.ListaCiudades();
		
		model.addAttribute("titulo","Formulario Cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudades", listCiudades);
		
		return "/views/clientes/frmCrear";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Cliente cliente, BindingResult result,
			Model model,RedirectAttributes attribute, SessionStatus status) {
		List<Ciudad> listCiudades = ciudadService.ListaCiudades();
		
		if(result.hasErrors()) {
			model.addAttribute("titulo","Formulario Cliente");
			model.addAttribute("cliente",cliente);
			model.addAttribute("ciudades",listCiudades);
			System.out.println("Hubo errores");
			return "/views/clientes/frmCrear";
		}
		
		iclienteService.guardar(cliente);
		
		
		System.out.println("Cliente Guardado");
		attribute.addFlashAttribute("success","Editado con exito");
		return "redirect:/views/clientes/";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	 @GetMapping("/edit/{id}")
	 public String editar(@PathVariable("id") Long idCliente, Model model, RedirectAttributes attribute) {
		 Cliente cliente = null;
		 if(idCliente > 0) {
			  cliente = iclienteService.buscarPorId(idCliente);
			  
			  if(cliente == null) {
				  attribute.addFlashAttribute("error","Error: el Id no existe");
				  return "redirect:/views/clientes/";	
			  }
		 }else {
			 attribute.addFlashAttribute("error","Error: con el ID del cliente");
			  return "redirect:/views/clientes/"; 
		 }
		 
		 List<Ciudad> listCiudades = ciudadService.ListaCiudades();
		 
		 model.addAttribute("titulo", "Formulario: Editar");
		 model.addAttribute("cliente", cliente);
		 model.addAttribute("ciudades", listCiudades);
		 
		 attribute.addFlashAttribute("warning","Editado con exito");
		 return "views/clientes/frmCrear";
	 }
	 
	@Secured("ROLE_ADMIN")
	 @GetMapping("/delete/{id}")
	 public String delete(@PathVariable("id") Long idCliente,Model model, RedirectAttributes attribute) {
		 Cliente cliente = null;
		 
		 if(idCliente > 0 ) {
			 
			 cliente = iclienteService.buscarPorId(idCliente);
			 
			 if(cliente == null) {
				 attribute.addFlashAttribute("danger","Error: en el id");
				 return "redirect:/views/clientes/";
			 }
		 }else {
			 	attribute.addFlashAttribute("danger","Error: en el id");
			 	return "redirect:/views/clientes/";
		 }
		 
		 iclienteService.eliminar(idCliente);
		 attribute.addFlashAttribute("warning","Eliminado con exito");
		 
		 return "redirect:/views/clientes/";
	 }
	
}