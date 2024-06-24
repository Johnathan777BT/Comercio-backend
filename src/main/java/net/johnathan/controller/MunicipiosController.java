package net.johnathan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.johnathan.model.Municipios;

import net.johnathan.repository.MunicipiosRepository;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/municipios")
public class MunicipiosController {


	@Autowired private MunicipiosRepository repo;
	
	@GetMapping("/listar")
	//@RolesAllowed({"Admin", "AuxRegistro"})
	//@Secured({"ROL_Admin", "AuxRegistro"})
	public List<Municipios> list() {

		System.out.println("entro a listar municipios");
		
		return repo.findAll();
	}
	
	@GetMapping("/listar/{id}")
	public List<Municipios> listByDep(@PathVariable int id){
		
		System.out.println("entro a listar municipios id dep:"+id);
		
		return repo.findByIdDep(id);
	}
	
	
}
