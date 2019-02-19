package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Produto;
import app.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	public List<Produto> lista() {
		return (List<Produto>) repository.findAll();
	}

	public Produto get(Long id) {
		return repository.findById(id).get(); 
		//return new Produto(1L, "Chock", "My best choc", 5D);
	}

	public List<Produto> todos() {
		return (List<Produto>) repository.findAll();
	}

	public Produto salva(Produto produto) {
		return repository.save(produto);
	}

}
