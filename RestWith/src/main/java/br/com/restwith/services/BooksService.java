package br.com.restwith.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.restwith.converter.Converter;
import br.com.restwith.data.model.Books;
import br.com.restwith.data.vo.BooksVO;
import br.com.restwith.exception.ResourceNotFoundException2;
import br.com.restwith.repository.BooksRepository;

@Service
public class BooksService {
	
	private BooksRepository booksRepository;
	
	public BooksService(BooksRepository booksRepository) {
		this.booksRepository = booksRepository;
	}
	
	public BooksVO create(BooksVO booksVO) {
		// converte o vo para entidade 
		var entity = Converter.parse(booksVO, Books.class);
		// apos salva converte a entidade para VO. 
		var vo = Converter.parse(booksRepository.save(entity), BooksVO.class);
		return 	vo;	
	}
	
	public BooksVO update(BooksVO booksVO) {
		var entity = this.booksRepository.findById(booksVO.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No found id"));
		var vo = Converter.parse(this.booksRepository.saveAndFlush(entity), BooksVO.class);
		return 	vo;	
	}

	public Page<BooksVO> findAll(Pageable pageable) {
		var page = this.booksRepository.findAll(pageable);
		var vo = page.map(this::convertToPageVo);
		return vo;
	}

	private BooksVO convertToPageVo(Books booksEntity){
		return Converter.parse(booksEntity, BooksVO.class);
	}


	public BooksVO findById(Long id) {
		var books = this.booksRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found"));
		var vo = Converter.parse(books, BooksVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		Books books = this.booksRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException2("No found id"));
		this.booksRepository.delete(books);
	}
		

}
