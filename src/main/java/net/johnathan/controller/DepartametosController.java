package net.johnathan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.johnathan.model.Departamentos;
import net.johnathan.repository.DepartamentosRepository;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/departamentos")
public class DepartametosController {

	@Autowired private DepartamentosRepository repo;
	
	@GetMapping("/listar")
	//@RolesAllowed({"Admin", "AuxRegistro"})
	//@Secured({"ROL_Admin", "AuxRegistro"})
	public List<Departamentos> list() {

		System.out.println("entro a listar departamentos");
		
		return repo.findAll();
	}
	
}

