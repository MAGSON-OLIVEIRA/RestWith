package br.com.restwith.controller;

import br.com.restwith.data.vo.PersonVO;
import br.com.restwith.services.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.OK;

@Api(value = " API Person ", description = "API-PERSON", tags = "API PERSON")
@RestController
@RequestMapping("api/person/v1")
public class PersonController {
	
	@Autowired  // spring gerencia a instancia do PersonService
	private PersonService service;

	@Autowired
	PagedResourcesAssembler<PersonVO> assembler;


	@ApiOperation(value = "Lista de Person")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction,
			PagedResourcesAssembler assembler) {

		var sorDirection = "desc".equalsIgnoreCase(direction)? Sort.Direction.DESC : Sort.Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorDirection, "firstName"));

		Page<PersonVO> persons = service.findAll(pageable);
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));

		PagedResources<?> resources = assembler.toResource(persons);

		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@ApiOperation(value = "Lista de Person")
	@GetMapping(value = "/personbyname/{firstName}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findByName(@PathVariable(value = "firstName") String firstName,
															   @RequestParam(value = "page", defaultValue = "0") int page,
															   @RequestParam(value = "limit", defaultValue = "12") int limit,
															   @RequestParam(value = "direction", defaultValue = "asc") String direction) {

		var sorDirection = "desc".equalsIgnoreCase(direction)? Sort.Direction.DESC : Sort.Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorDirection, "firstName"));

		Page<PersonVO> persons = service.findAllByName(firstName,pageable);
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));

		PagedResources<?> resources = assembler.toResource(persons);

		return new ResponseEntity<>(resources, HttpStatus.OK);
	}


	@ApiOperation(value = "Buscar de Person por ID")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO findById(@PathVariable(value = "id") Long id) {
		PersonVO person = service.findById(id);
		person.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return person;
	}
	
	@ApiOperation(value = "Salvar Person")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO create(@RequestBody PersonVO person) {
		PersonVO personVo = service.create(person);
		personVo.add(linkTo(methodOn(PersonController.class).findById(personVo.getKey())).withSelfRel());
		return personVo;
	}
	
	@ApiOperation(value = "Atualizar Person")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO person) {
		PersonVO personVo =  service.update(person);
		personVo.add(linkTo( methodOn(PersonController.class).findById(personVo.getKey())).withSelfRel());
		return personVo;
	}
	
	@ApiOperation(value = "Deletar um Person")
	@DeleteMapping(value="/{id}", produces = {"application/json","application/xml", "application/x-yaml"})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
		service.delete(id);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Disable Person por ID")
	@PatchMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO disablePerson(@PathVariable(value = "id") Long id) {
		PersonVO person = service.disablePerson(id);
		person.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return person;
	}
}
