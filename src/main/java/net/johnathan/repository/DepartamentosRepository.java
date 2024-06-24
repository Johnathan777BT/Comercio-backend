package net.johnathan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.johnathan.model.Departamentos;


@Repository
public interface DepartamentosRepository extends JpaRepository<Departamentos, Integer> {

}
