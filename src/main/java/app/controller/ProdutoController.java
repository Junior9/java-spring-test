package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.model.Produto;
import app.service.ProdutoService;

@RestController
@RequestMapping("api/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping("/{id}")
	public Produto get(@PathVariable Long id) {
		return produtoService.get(id);
	}
	
	
}
