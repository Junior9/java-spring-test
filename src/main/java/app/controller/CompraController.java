package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.model.Compra;
import app.service.CompraService;

@RestController
@RequestMapping("api/compra")
public class CompraController {
	
	@Autowired
	private CompraService compraService;

	@PostMapping("/save")
	public void save(@RequestBody Compra compra) {
		//return compraService.compra(compra);
		System.out.println("Psotdddd");
	}
	
	@GetMapping("teste")
	public void teste() {
		System.out.println("Teste");
	}
}
