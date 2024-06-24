package net.johnathan.repository;


import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.johnathan.model.Comerciantes;


@Repository
public interface ComerciantesRepository   extends JpaRepository<Comerciantes, Integer> {

	
	@Query(value="select comerciantes.ID_COM, comerciantes.NOMBRE, departamentos.NOMBRE_DEPARTAMENTO ,   TELEFONO, CORREO_ELECTRONICO, FECHA_REGISTRO, ESTADO "
			+ " , SUM(establecimientos.INGRESOS) as ingresos, count(establecimientos.FK_ID_COM) as nroest "
			+ " from comerciantes  left join establecimientos on comerciantes.ID_COM = establecimientos.FK_ID_COM "
			+ " inner join departamentos on departamentos.departamento_id = comerciantes.departamento_id "
			+ " GROUP BY comerciantes.ID_COM, comerciantes.NOMBRE, departamentos.NOMBRE_DEPARTAMENTO, TELEFONO, CORREO_ELECTRONICO, FECHA_REGISTRO, ESTADO", 
			countQuery = "select count(*) "
					+ " from comerciantes  left join establecimientos on comerciantes.ID_COM = establecimientos.FK_ID_COM "
					+ "inner join departamentos on departamentos.departamento_id = comerciantes.departamento_id",
			nativeQuery=true)
	public Page<Object[]> getList(Pageable pageable);
	
	
	@Query(value="select comerciantes.ID_COM, comerciantes.NOMBRE, departamentos.NOMBRE_DEPARTAMENTO , municipios.NOMBRE_MUNICIPIO,  NVL(comerciantes.TELEFONO,0) as TELEFONO, NVL(comerciantes.CORREO_ELECTRONICO,' ') as CORREOELECTRONICO, comerciantes.FECHA_REGISTRO, comerciantes.ESTADO "
			+ "			 , NVL(SUM(establecimientos.INGRESOS) ,0) as ingresos, NVL(sum(establecimientos.NRO_EMPLEADOS),0)  as nro_empleados , "
			+ "          count(establecimientos.FK_ID_COM) as Nroest "
			+ "			 from comerciantes  left join establecimientos on comerciantes.ID_COM = establecimientos.FK_ID_COM "
			+ "			 inner join departamentos on departamentos.departamento_id = comerciantes.departamento_id "
			+ "          left join municipios on comerciantes.id_municipio = municipios.id_municipio "
			+ "			 GROUP BY comerciantes.ID_COM, comerciantes.NOMBRE, departamentos.NOMBRE_DEPARTAMENTO, comerciantes.TELEFONO, comerciantes.CORREO_ELECTRONICO, comerciantes.FECHA_REGISTRO, comerciantes.ESTADO,"
			+ "           municipios.NOMBRE_MUNICIPIO ", 
			nativeQuery=true)
	public List<Object[]> getListAll();
	
	@Query(value="select comerciantes.ID_COM, comerciantes.NOMBRE, departamentos.NOMBRE_DEPARTAMENTO , municipios.NOMBRE_MUNICIPIO,  NVL(comerciantes.TELEFONO,0) as TELEFONO, NVL(comerciantes.CORREO_ELECTRONICO,' ') as CORREOELECTRONICO, comerciantes.FECHA_REGISTRO, comerciantes.ESTADO "
			+ "			 , NVL(SUM(establecimientos.INGRESOS) ,0) as ingresos, NVL(sum(establecimientos.NRO_EMPLEADOS),0)  as nro_empleados , "
			+ "          count(establecimientos.FK_ID_COM) as Nroest "
			+ "			 from comerciantes  left join establecimientos on comerciantes.ID_COM = establecimientos.FK_ID_COM "
			+ "			 inner join departamentos on departamentos.departamento_id = comerciantes.departamento_id "
			+ "          left join municipios on comerciantes.id_municipio = municipios.id_municipio where comerciantes.ID_COM=?"
			+ "			 GROUP BY comerciantes.ID_COM, comerciantes.NOMBRE, departamentos.NOMBRE_DEPARTAMENTO, comerciantes.TELEFONO, comerciantes.CORREO_ELECTRONICO, comerciantes.FECHA_REGISTRO, comerciantes.ESTADO,"
			+ "           municipios.NOMBRE_MUNICIPIO ", 
			nativeQuery=true)
	public List<Object[]> getListById(Integer id);
	
	
	@Modifying
	@Transactional
	@Query(value="{call sp_create_comerciantes(:p_id,  :p_estado, :p_nombre, :p_fecha, :p_dep_id, :p_mun_id)}", nativeQuery = true)
	public void addComerciante(int p_id, Integer p_estado, String p_nombre, Date p_fecha, Integer p_dep_id, Integer  p_mun_id  );
	
	

	
	@Modifying
	@Transactional
	@Query(value="{call modificarcomerciantes(:p_id, :p_nombre, :p_mun_id, :p_dep_id, :tel, :correo,  :p_estado , :cod, :respuesta )}", nativeQuery = true)
	public boolean modificarcomerciantes(int p_id, String p_nombre, Integer  p_mun_id,  Integer p_dep_id , String tel, String correo,  int p_estado, int cod,String respuesta  );
	
	 
	
	
}
