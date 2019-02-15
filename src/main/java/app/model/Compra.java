package app.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compra {
	private Long id;
	private Long userId;
	private Pago pago;
	private List<Produto> produtos;
	private Boolean realizada;
	
	public Compra(Long userId, List<Produto> produtos) {
		this.userId = userId;
		this.produtos = produtos;
		this.realizada = Boolean.FALSE;
	}
}
