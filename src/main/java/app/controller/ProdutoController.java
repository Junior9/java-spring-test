package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<Produto> get(@PathVariable Long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("key", "Value 1");
		return new ResponseEntity<Produto>(produtoService.get(id),headers,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Produto>> getAll() {
		return new ResponseEntity<List<Produto>>(produtoService.lista(),HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Produto> save(@RequestBody Produto produto){
		return new ResponseEntity<Produto>(produtoService.salva(produto),HttpStatus.OK);
	}

}
