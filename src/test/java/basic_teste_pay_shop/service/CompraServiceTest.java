package basic_teste_pay_shop.service;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import app.SpringBootConfiguration;
import app.model.Compra;
import app.model.Pago;
import app.model.Produto;
import app.model.User;
import app.service.CompraService;
import app.service.PagoService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootConfiguration.class)
public class CompraServiceTest {
	
	@Autowired
	private CompraService compraService;
	
	@MockBean
	private PagoService pagoService;
	
	private Compra compra;
	private User usuario;
	private List<Produto> produtos;
	@Before
	public void init() {
		usuario = new User(1L);
		produtos = new ArrayList<Produto>();
	}
	
	@Test
	public void compraComListaVazia() {
		Mockito.when(pagoService.pago(Mockito.anyLong(), Mockito.anyListOf(Produto.class)))
			.thenReturn( new Pago(usuario.getId(),0D,Boolean.FALSE));
		
		compra = compraService.compra(new Compra(usuario.getId(),produtos)); 
		Assert.assertThat(compra.getRealizada(),Matchers.equalTo(Boolean.FALSE));
		Assert.assertThat(compra.getPago().getValor(),Matchers.equalTo(0D));
	}
	
	@Test
	public void compraComListaValida() {
		;
		Mockito.when(pagoService.pago(Mockito.anyLong(), Mockito.anyListOf(Produto.class)))
			.thenReturn( new Pago(usuario.getId(),45D,Boolean.TRUE));
		
		compra = compraService.compra(new Compra(usuario.getId(),Lists.newArrayList(new Produto(10D),new Produto(35D))));
		Assert.assertThat(compra.getRealizada(),Matchers.equalTo(Boolean.TRUE));
		Assert.assertThat(compra.getPago().getValor(),Matchers.equalTo(45D));
	}

}
