package com.springboot.cleinteapp.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(value="error", required=false) String error, Model model,
			@RequestParam(value="logout", required=false)String logout, Principal principal, RedirectAttributes attribute) {
		if(error != null) {
			model.addAttribute("error","ERROR DE ACCESO: Usuario y/o contraseña incorrectos");
		}
		
		if(principal != null) {
			attribute.addFlashAttribute("warning", "ATENCION: Usted ya ha iniciado sesion");
			return "redirect:/";
		}
		
		if(logout != null) {
			model.addAttribute("success","ATENCION: Ha finalizado sesion con exito");
		}
		return "login";
	}
	
}
