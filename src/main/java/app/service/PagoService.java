package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Pago;
import app.model.Produto;
import app.repository.PagoRepository;

@Service
public class PagoService {
	
	@Autowired
	private PagoRepository pagoRepository;

	public Pago pago(Long idUser, List<Produto> produtos) {
		if(idUser !=null && validaProdutos(produtos)) {
			Double total = getTotalValor(produtos);
			if(total > 0D) {
				return pagoRepository.save(new Pago(idUser,total,Boolean.TRUE));
			}			
		}	
		return new Pago();
	}
	
	
	private Double getTotalValor(List<Produto> produtos) {
		Double total = 0D;
		for (Produto produto : produtos) {
			if(produto != null) {
				total += produto.getValor(); 
			}
		}
		return total;
	}


	private Boolean validaProdutos(List<Produto> produtos) {
		if(produtos != null && !produtos.isEmpty()) {
			return Boolean.TRUE;
		}	
		return Boolean.FALSE;
	}
}
