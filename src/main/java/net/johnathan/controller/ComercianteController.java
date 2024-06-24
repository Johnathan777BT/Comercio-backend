package net.johnathan.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URI;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;

import net.johnathan.DTO.ComercianteDTO;
import net.johnathan.DTO.ComercianteReportDTO;
import net.johnathan.model.Comerciantes;
import net.johnathan.report.CsvGeneratorUtil;
import net.johnathan.report.PdfUtils;
import net.johnathan.repository.ComerciantesRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/comerciantes")
public class ComercianteController {

	@Autowired private ComerciantesRepository repo;
	
	 @Autowired
	 private CsvGeneratorUtil csvGeneratorUtil;
	 
	 @Autowired
	 Patcher patcher;
	
	@PostMapping
	//@RolesAllowed("Admin")
	public ResponseEntity<Comerciantes> create(@RequestBody @Valid Comerciantes com) {
		
		System.out.println("nom:"+com.getNombre()  +" --"+
		com.isEstado() 		+"--idDep--"+com.getDepartamentos().getId_departamento() +
		"_idMun_"+com.getMunicipios().getId_municipio()+ "--"+ com.getFecha_registro());
		
		Comerciantes savedProduct = repo.save(com);
				
		//URI productURI = URI.create("/comerciantes/" + savedProduct.getId());
		return  ResponseEntity.ok().body(savedProduct);
	}
	
	 @PersistenceContext
	  private EntityManager em;

	
	@PutMapping("/act/{id}")
	private ResponseEntity<String> modificarcomerciantes(@PathVariable("id") Integer id, @RequestBody @Valid Comerciantes comer ) {
		
		if(!repo.findById(id).isEmpty()) {
			Comerciantes can = repo.findById(id).get();
	
			System.out.println("grDep id:" + comer.getDepartamentos().getId_departamento()+
					"fecha_reg: "+comer.getFecha_registro());
			can.setNombre(comer.getNombre());
			can.setCorreo_electronico(comer.getCorreo_electronico());
			can.setDepartamentos(comer.getDepartamentos());
			can.setMunicipios(comer.getMunicipios());
			can.setEstado(comer.isEstado());
			can.setTelefono(comer.getTelefono());
			can.setFecha_registro(comer.getFecha_registro());
			repo.save(can);
		
		}else {	
			return  ResponseEntity.badRequest().build();
		}
		
		return  ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/listar")
	//@RolesAllowed({"Admin", "AuxRegistro"})
	//@Secured({"ROL_Admin", "AuxRegistro"})
	public Page<Comerciantes> list(Pageable pageable) {

		System.out.println("entro a listar comerciantes");
		
		return repo.findAll(pageable);
	}
	
	
	 @PatchMapping("/actestado/{id}")
	 public ResponseEntity<Comerciantes> patchInternApi(@PathVariable("id") Integer id,@RequestBody Comerciantes incompleteIntern){

	   //RETRIEVE THE ASSOCIATED INTERN FROM DATABASE THROUGH ITS ID
	   System.out.print("recibi id: "+id);
	   if(repo.findById(id).isEmpty()) {
		   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	   }
	   Comerciantes existingIntern=repo.findById(id).get();
	   try {
		    //SEND BOTH THE EXISTING INTERN AND THE INCOMPLETE INTERN TO THE INTERNPATCHER
		    patcher.internPatcher(existingIntern, incompleteIntern);
		    //SAVE THE UPDATED EXISTING INTERN
		    
		    repo.save(existingIntern);
		   }catch (Exception e){
		      e.printStackTrace();
		   }
		  return ResponseEntity.status(HttpStatus.OK).body(existingIntern);

	 }
	
	@GetMapping("/listardto")
	//@RolesAllowed({"Admin", "AuxRegistro"})
	//@Secured({"ROL_Admin", "AuxRegistro"})
	public List<ComercianteDTO> listDto(@PageableDefault(page=0, size=5)  Pageable pageable) {

		System.out.println("entro a listar comerciantes");
		List<ComercianteDTO> values = new ArrayList<>();
		var results = repo.getList(pageable);
		System.out.println(results.getSize());
		ComercianteDTO[] com = new  ComercianteDTO[results.getSize()+1];
		
		 int x=0; int i=0;
		 for (Object[] r : results) {
			 System.out.println("-a-"+ r[i]+"-b-"+r[i+1]+"-c-"+r[i+2]+"-d-"+r[i+3]+"-e-"+r[i+4]
					+"-f-" +r[i+5]+ "-g-"+r[i+6]+"-h-"+r[i+7]+"-i-"+r[i+8]);

			 Timestamp dateTime= (Timestamp)r[i+5];
			 
				Date variblefec = Date.valueOf(dateTime.toLocalDateTime().toLocalDate());
					 
			 com[x] = new ComercianteDTO((BigDecimal) r[i],  (String) r[i+1], 
					 (String) r[i+2], (String) r[i+3], (String) r[i+4], (java.sql.Date) variblefec,
					 (BigDecimal) r[i+6], 
					 (BigDecimal) r[i+7], (BigDecimal) r[i+8]);
		
			 values.add(com[x]); x++;
		 }
		 
		return  values;
	}
	
	
	@GetMapping("/listar/csv")
    public void generateCsvFile(HttpServletResponse response) {
    	List<ComercianteReportDTO> values = new ArrayList<>();
		
    	var results = repo.getListAll();
		System.out.println(results.size());
		ComercianteReportDTO[] com = new  ComercianteReportDTO[results.size()+1];
		
		 int x=0; int i=0;
		 for (Object[] r : results) {
			 System.out.println("-a-"+ r[i]+"-b-"+r[i+1]+"-c-"+r[i+2]+"-d-"+r[i+3]+"-e-"+r[i+4]
					+"-f-" +r[i+5]+ "-g-"+r[i+6]+"-h-"+r[i+7]+"-i-"+r[i+8]+"-j-"+ r[i+9]+"-k-"+ r[i+10]);

			 
			 com[x] = new ComercianteReportDTO((BigDecimal) r[i],  (String) r[i+1], 
					 (String) r[i+2],  (String) r[i+3],  (String) r[i+4], (String) r[i+5], (java.sql.Timestamp) r[i+6], (BigDecimal) r[i+7], 
					 (BigDecimal) r[i+8], (BigDecimal) r[i+9],  (BigDecimal) r[i+10]);
		
			 values.add(com[x]); x++;
	
			 
		 }
		 
		  response.addHeader("Content-Type", "application/csv");
		  response.addHeader("Content-Disposition", "attachment; filename=export.csv");
		   try {
		       PrintWriter out = response.getWriter();		     
		       out.write(csvGeneratorUtil.generateCsv(values));
		       out.write("\n");		     
		       out.flush();
		       out.close();
		   } catch (IOException ix) {
		       throw new RuntimeException("There was an error while retrieving CSV data", ix);
		   } 

    }
	
	 @GetMapping("/listar/pdf/{id}")
	 public ResponseEntity<byte[]> exportPdf(@PathVariable Integer id) throws IOException, DocumentException {
	       
	    	List<ComercianteReportDTO> values = new ArrayList<>();
			
	    	var results = repo.getListById(id);
			System.out.println(results.size());
			ComercianteReportDTO[] com = new  ComercianteReportDTO[results.size()+1];
			
			 int x=0; int i=0;
			 for (Object[] r : results) {
				 System.out.println("-a-"+ r[i]+"-b-"+r[i+1]+"-c-"+r[i+2]+"-d-"+r[i+3]+"-e-"+r[i+4]
						+"-f-" +r[i+5]+ "-g-"+r[i+6]+"-h-"+r[i+7]+"-i-"+r[i+8]+"-j-"+ r[i+9]+"-k-"+ r[i+10]);

				 
				 com[x] = new ComercianteReportDTO((BigDecimal) r[i],  (String) r[i+1], 
						 (String) r[i+2],  (String) r[i+3],  (String) r[i+4], (String) r[i+5], (java.sql.Timestamp) r[i+6], (BigDecimal) r[i+7], 
						 (BigDecimal) r[i+8], (BigDecimal) r[i+9],  (BigDecimal) r[i+10]);
			
				 values.add(com[x]); x++;
		
				 
			 }      
	        ByteArrayOutputStream pdfStream = PdfUtils.generatePdfStream(values);  
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF);
	        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=pdf_report.pdf");
	        headers.setContentLength(pdfStream.size());   
	        return new ResponseEntity<>(pdfStream.toByteArray(), headers, HttpStatus.OK);
	    }
	
	@DeleteMapping("/del/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		
		if(!repo.findById(id).isEmpty()) {
			Comerciantes can = repo.findById(id).get();
			
			repo.deleteById(id);
		}else {
			return  ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping("/listar/{id}")
	//@RolesAllowed({"Admin", "AuxRegistro"})
	//@Secured({"ROL_Admin", "AuxRegistro"})
	public ResponseEntity<Comerciantes> listById(@PathVariable("id") Integer Id) {
	
		if(!repo.findById(Id).isEmpty()) {
			
			System.out.println("entro a listar Comerciantes");
			
			var est= repo.findById(Id).get();
			 
			return  ResponseEntity.ok().body(est);
			
		}else
		{
			return ResponseEntity.badRequest().body(null);
		}			
		
	}
	
}
