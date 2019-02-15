package app.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Pago {

	@Id
	private Long id;
	private Long userId;
	private Double valor;
	private Boolean realizado;
	
	
	public Pago() {
		this.valor = 0D;
		this.realizado = Boolean.FALSE;
	}

	public Pago(Long userId, Double valor, Boolean realizado) {
		this.userId = userId;
		this.valor = valor;
		this.realizado = realizado;
	}

}
