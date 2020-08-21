package br.com.restwith.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.restwith.converter.Converter;
import br.com.restwith.data.model.Person;
import br.com.restwith.data.vo.PersonVO;
import br.com.restwith.exception.ResourceNotFoundException;
import br.com.restwith.exception.ResourceNotFoundException2;
import br.com.restwith.repository.PersonRespository;

@Service   // spring cuide da injeção de dependecia dessa classe usando o autorad
public class PersonService {
	
	@Autowired
	private PersonRespository repsitory;
	
	public PersonVO create(PersonVO person) {
		var entity = Converter.parse(person, Person.class);
		var vo = Converter.parse(repsitory.save(entity), PersonVO.class);
		return 	vo;	
	}
	
	public PersonVO update(PersonVO person) {
		var entity = repsitory.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No found id"));
		var vo = Converter.parse(repsitory.saveAndFlush(entity), PersonVO.class);
		return 	vo;	
	}
	
	public PersonVO findById(Long id) {
		var entity =  repsitory.findById(id).orElseThrow(() -> new ResourceNotFoundException("No found id")) ;
		var vo = Converter.parse(entity, PersonVO.class);
		return vo;
	}

	public List<PersonVO> findAll() {
		var vo = Converter.parseList(repsitory.findAll(), PersonVO.class);
		return vo;
	}

	
	public void delete(Long id) {
		Person person = repsitory.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException2("No found id"));
		repsitory.delete(person);
	}
		
}
