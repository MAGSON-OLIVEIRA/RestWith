package br.com.restwith.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<PagedResources<BooksVO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "5") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction,
			PagedResourcesAssembler assembler) {

		var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "title"));

		Page<BooksVO> booksVOs = booksService.findAll(pageable);
		booksVOs.stream()
				.forEach(b -> b.add(linkTo(methodOn(BooksController.class).findById(b.getKey())).withSelfRel()));
		return new ResponseEntity<>(assembler.toResource(booksVOs), OK) ;
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
