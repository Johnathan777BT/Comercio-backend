package net.johnathan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.johnathan.model.Establecimientos;


@Repository
public interface EstablecimientosRepository extends JpaRepository<Establecimientos, Integer>{

	@Query(value="select * from establecimientos where FK_ID_COM =:id", nativeQuery=true)
	public List<Establecimientos> findByIdCom(int id);
	
}
