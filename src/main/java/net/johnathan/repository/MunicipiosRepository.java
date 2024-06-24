package net.johnathan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.johnathan.model.Municipios;

@Repository
public interface MunicipiosRepository extends JpaRepository<Municipios, Integer> {

	
	@Query(value="select * from municipios where departamento_id =:id", nativeQuery=true)
	public List<Municipios> findByIdDep(int id);
	
}

