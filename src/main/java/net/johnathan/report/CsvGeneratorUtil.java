package net.johnathan.report;

import java.util.List;

import org.springframework.stereotype.Component;

import net.johnathan.DTO.ComercianteReportDTO;

@Component
public class CsvGeneratorUtil {

	 private static final String CSV_HEADER = "ID|Nombre|Departamento|Municipio|Telefono|Correo|FechaRegistro|Ingresos|NroEmpleados|NroEstablecimientos\n";

	    public String generateCsv(List<ComercianteReportDTO> comers) {
	        StringBuilder csvContent = new StringBuilder();
	        csvContent.append(CSV_HEADER);

	        for (ComercianteReportDTO comer : comers) {
	            csvContent.append(comer.getId_com()).append("|")
	                      .append(comer.getNombre()).append("|")
	                      .append(comer.getNombre_departamento()).append("|")
	                      .append(comer.getNombre_municipio()).append("|")
	                      .append(comer.getTelefono()).append("|")
	                      .append(comer.getCorreo_electronico()).append("|")
	                      .append(comer.getFecha_registro()).append("|")
	                      .append(comer.getIngresos()).append("|")
	                      .append(comer.getNro_empleados()).append("|")
	                      .append(comer.getNro_establecimiento()).append("\n")
	                      ;
	        }

	        return csvContent.toString();
	    }
	
}
