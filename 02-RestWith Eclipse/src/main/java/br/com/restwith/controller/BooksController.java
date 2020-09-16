package br.com.restwith.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.restwith.data.vo.BooksVO;
import br.com.restwith.services.BooksService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = " API BOOKS ", description = "API-BOOKS", tags = "API BOOKS")
@RestController
@RequestMapping("api/books/v1")
public class BooksController {
	
	private BooksService booksService;

	public BooksController(BooksService booksService) {
		this.booksService = booksService;
	}
	
	@ApiOperation(value = "Lista de Books")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" } )
	public List<BooksVO> findAll() {
		List<BooksVO> booksVOs = booksService.findAll();
		booksVOs.stream()
				.forEach(b -> b.add(linkTo(methodOn(BooksController.class).findById(b.getKey())).withSelfRel()));
		return booksVOs;
	}
	
	@ApiOperation(value = "Busca por ID")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" } )
	public BooksVO findById(@PathVariable(value = "id") Long id) {
		BooksVO vo = booksService.findById(id);
		vo.add(linkTo(methodOn(BooksController.class).findById(vo.getKey())).withSelfRel());
		return null;
		
	}

	@ApiOperation(value = "Salva Books")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public BooksVO create(@RequestBody BooksVO books) {
		BooksVO booksVo = booksService.create(books);
		booksVo.add(linkTo(methodOn(BooksController.class).findById(booksVo.getKey())).withSelfRel());
		return booksVo;
	}
	
	@ApiOperation(value = "Atualizar Books")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public BooksVO update(@RequestBody BooksVO books) {
		BooksVO booksVo = booksService.update(books);
		booksVo.add(linkTo(methodOn(BooksController.class).findById(booksVo.getKey())).withSelfRel());
		return booksVo;
	}
	
	@ApiOperation(value = "Deletar um Books")
	@DeleteMapping(value="/{id}", produces = {"application/json","application/xml", "application/x-yaml"})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
		booksService.delete(id);
		return ResponseEntity.ok().build();
	}
	

}
