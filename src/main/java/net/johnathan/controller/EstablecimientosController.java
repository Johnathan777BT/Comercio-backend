package net.johnathan.controller;

import java.net.URI;
import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import net.johnathan.model.Establecimientos;

import net.johnathan.repository.EstablecimientosRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/establecimientos")
public class EstablecimientosController {

	
	@Autowired private EstablecimientosRepository repo;
	
	@PostMapping
	//@RolesAllowed("Admin")
	public ResponseEntity<Establecimientos> create(@RequestBody @Valid Establecimientos est) {
		
		System.out.print(est.getNombre_establecimiento()+"-"+est.getNro_empleados()+ "-"+est.getIngresos()
		+ "-"+ est.getComerciantes().getId_com());
		
		 var save =  repo.save(est);
		URI _URI = URI.create("/establecimientos/" + save.getId_est());
		return  ResponseEntity.ok().body(save);
	}
	
	@GetMapping("/listar")
	//@RolesAllowed({"Admin", "AuxRegistro"})
	//@Secured({"ROL_Admin", "AuxRegistro"})
	public List<Establecimientos> list() {

		System.out.println("entro a listar establecimientos");
		
		return repo.findAll();
	}
	
	
	@GetMapping("/listaridcom/{id}")
	public List<Establecimientos> listByIdCom(@PathVariable int id){
		
		System.out.println("entro a listar establecimientos id com:"+id);
		
		return repo.findByIdCom(id);
	}
	
	@GetMapping("/listar/{id}")
	//@RolesAllowed({"Admin", "AuxRegistro"})
	//@Secured({"ROL_Admin", "AuxRegistro"})
	public ResponseEntity<Establecimientos> listById(@PathVariable("id") Integer Id) {

	
		if(!repo.findById(Id).isEmpty()) {
			
			System.out.println("entro a listar establecimiento");
			
			var est= repo.findById(Id).get();
			 
			return  ResponseEntity.ok().body(est);
			
		}else
		{
			return ResponseEntity.badRequest().body(null);
		}	
		
		
	}
	
	
	@PutMapping("/act/{id}")
	private ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody @Valid Establecimientos esta ) {
		
		if(!repo.findById(id).isEmpty()) {
			Establecimientos can = repo.findById(id).get();
	
			System.out.println("nombre est :" + esta.getNombre_establecimiento());
			can.setNombre_establecimiento(esta.getNombre_establecimiento());
			can.setIngresos(esta.getIngresos());
			can.setNro_empleados(esta.getNro_empleados());
			repo.save(can);
		
		}else {	
			return  ResponseEntity.badRequest().build();
		}
		
		return  ResponseEntity.noContent().build();
		
	}
	
	
	@DeleteMapping("/del/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		
		if(!repo.findById(id).isEmpty()) {
			Establecimientos est = repo.findById(id).get();
			
			repo.deleteById(id);
			
		}else {
			return   ResponseEntity.notFound().build();
		}
		
		return  ResponseEntity.noContent().build();//ResponseEntity.ok().body("Registro Eliminado correctamente");
	}
	
	
	
	
}
