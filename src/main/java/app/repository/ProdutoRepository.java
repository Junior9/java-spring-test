package app.repository;

import org.springframework.data.repository.CrudRepository;

import app.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {
	
}
