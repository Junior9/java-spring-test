package basic_teste_pay_shop.service;

import java.util.ArrayList;
import java.util.List;

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
import app.model.Pago;
import app.model.Produto;
import app.model.User;
import app.repository.PagoRepository;
import app.service.PagoService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootConfiguration.class)
public class PagoServiceTest {
	
	@Autowired
	private PagoService pagoService;
	
	@MockBean
	private PagoRepository pagoRepository;
	
	private List<Produto> produtos;
	private User usuario;
	private Pago pago;
	
	@Before
	public void init() {
		usuario = new User(1L);
		produtos = new ArrayList<Produto>();
	}
	
	@Test
	public void pagoConListaVazia() {
		pago  = pagoService.pago(usuario.getId(),produtos);
		Assert.assertThat(pago.getRealizado(),Matchers.equalTo(Boolean.FALSE));
		Assert.assertThat(pago.getValor(),Matchers.equalTo(0D));
		Assert.assertThat(pago.getUserId(),Matchers.nullValue());
	}
	
	@Test
	public void pagoConListaNull() {
		produtos.add(null);
		produtos.add(null);
		produtos.add(null);
		pago  = pagoService.pago(usuario.getId(),produtos);
		Assert.assertThat(pago.getRealizado(),Matchers.equalTo(Boolean.FALSE));
		Assert.assertThat(pago.getValor(),Matchers.equalTo(0D));
		Assert.assertThat(pago.getUserId(),Matchers.nullValue());
	}
	
	@Test
	public void pagoConListValidaConUsuarioNull() {
		produtos.add(new Produto(50D));
		
		Mockito.when(pagoRepository.save(Mockito.any(Pago.class)))
			.thenReturn(new Pago());
		
		pago  = pagoService.pago(null,produtos);
		Assert.assertThat(pago.getRealizado(),Matchers.equalTo(Boolean.FALSE));
		Assert.assertThat(pago.getValor(),Matchers.equalTo(0D));
		Assert.assertThat(pago.getUserId(),Matchers.nullValue());
	}
	
	@Test
	public void pagoConListaValida() {
		usuario = new User(1L);
		produtos.add(new Produto(50D));
		produtos.add(new Produto(50D));
		produtos.add(new Produto(25D));
		
		Mockito.when(pagoRepository.save(Mockito.any(Pago.class)))
				.thenReturn(new Pago(usuario.getId(),125D,Boolean.TRUE));
		
		pago = pagoService.pago(usuario.getId(),produtos);
		
		Assert.assertThat(pago.getRealizado(),Matchers.equalTo(Boolean.TRUE));
		Assert.assertThat(pago.getValor(), Matchers.equalTo(125D));
		Assert.assertThat(pago.getUserId(), Matchers.notNullValue());
	}
	
	@Test
	public void pagoConListaValidaUsuarioNull() {
		produtos.add(new Produto(50D));
		produtos.add(new Produto(50D));
		produtos.add(new Produto(25D));
		
		Mockito.when(pagoRepository.save(Mockito.any(Pago.class)))
				.thenReturn(new Pago(usuario.getId(),125D,Boolean.TRUE));
		
		pago = pagoService.pago(null,produtos);

		Assert.assertThat(pago.getRealizado(),Matchers.equalTo(Boolean.FALSE));
		Assert.assertThat(pago.getValor(), Matchers.equalTo(0D));
		Assert.assertThat(pago.getUserId(), Matchers.nullValue());
	}
	
	@Test
	public void pagoConListaValidaComProdutoNull() {
		produtos.add(new Produto(50D));
		produtos.add(new Produto(500D));
		produtos.add(null);
		produtos.add(new Produto(25D));
		
		Mockito.when(pagoRepository.save(Mockito.any(Pago.class)))
			.thenReturn(new Pago(usuario.getId(),575D,Boolean.TRUE));
		
		pago = pagoService.pago(usuario.getId(),produtos);

		Assert.assertThat(pago.getRealizado(),Matchers.equalTo(Boolean.TRUE));
		Assert.assertThat(pago.getValor(), Matchers.equalTo(575D));
		Assert.assertThat(pago.getUserId(), Matchers.notNullValue());
	}
}