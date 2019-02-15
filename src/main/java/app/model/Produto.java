package app.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto {
	
	@Id
	private Long id;
	private String nome;
	private String descricao;
	private Double valor;
	
	public Produto(Double valor) {
		this.valor = valor;
	}
}
