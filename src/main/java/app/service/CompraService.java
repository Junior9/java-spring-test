package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Compra;
import app.model.Pago;

@Service
public class CompraService {

	@Autowired
	private PagoService pagoService;
	private Pago pago;
	
	public Compra compra(Compra compra) {
		pago = pagoService.pago(compra.getUserId(), compra.getProdutos());
		
		if(pago.getRealizado()) {
			compra.setPago(pago);
			compra.setRealizada(Boolean.TRUE);
		}else {
			compra.setPago(new Pago(compra.getUserId(), 0D, Boolean.FALSE));
			compra.setRealizada(Boolean.FALSE);
		}
		return compra;
	}

}
