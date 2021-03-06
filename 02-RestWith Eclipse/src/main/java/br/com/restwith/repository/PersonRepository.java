package br.com.restwith.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.restwith.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
