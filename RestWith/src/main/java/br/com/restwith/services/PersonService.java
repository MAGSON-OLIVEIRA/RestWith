package br.com.restwith.services;

import br.com.restwith.converter.Converter;
import br.com.restwith.data.model.Person;
import br.com.restwith.data.vo.PersonVO;
import br.com.restwith.exception.ResourceNotFoundException;
import br.com.restwith.exception.ResourceNotFoundException2;
import br.com.restwith.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service   // spring cuide da injeção de dependecia dessa classe usando o autorad
public class PersonService {
	
	@Autowired
	private PersonRepository repsitory;
	
	public PersonVO create(PersonVO person) {
		var entity = Converter.parse(person, Person.class);
		var vo = Converter.parse(repsitory.save(entity), PersonVO.class);
		return 	vo;	
	}
	
	public PersonVO update(PersonVO person) {
		var entity = repsitory.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No found id"));
		var vo = Converter.parse(repsitory.saveAndFlush(entity), PersonVO.class);
		return 	vo;	
	}
	
	public PersonVO findById(Long id) {
		var entity =  repsitory.findById(id).orElseThrow(() -> new ResourceNotFoundException("No found id")) ;
		var vo = Converter.parse(entity, PersonVO.class);
		return vo;
	}

	@Transactional // guaranti acid operação.
	public PersonVO disablePerson(Long id) {
		repsitory.disablePerson(id);
		var entity =  repsitory.findById(id).orElseThrow(() -> new ResourceNotFoundException("No found id")) ;
		var vo = Converter.parse(entity, PersonVO.class);
		return vo;
	}

	public Page<PersonVO> findAll(Pageable pageable) {
		var page = repsitory.findAll(pageable); // pega  a lista do page
		var vo = page.map(this::convertToPageVo);
		return vo;
	}

    public Page<PersonVO> findAllByName(String firstName , Pageable pageable) {
		var page = repsitory.findAllByName(firstName,pageable); // pega  a lista do page
		var vo = page.map(this::convertToPageVo);
		return vo;
	}

	private PersonVO convertToPageVo(Person entityPage){
		return Converter.parse(entityPage, PersonVO.class);
	}
	
	public void delete(Long id) {
		Person person = repsitory.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException2("No found id"));
		repsitory.delete(person);
	}
		
}
