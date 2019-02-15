package basic_teste_pay_shop.service;

import java.util.List;
import java.util.Optional;

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
import app.model.Produto;
import app.repository.ProdutoRepository;
import app.service.ProdutoService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootConfiguration.class)
public class ProdutoServiceTest {
	
	@Autowired
	private ProdutoService produtoService;
	
	@MockBean
	private ProdutoRepository produtoRepository;
	
	private Produto produto;
	private List<Produto> produtos;
	
	@Before
	public void init() {
		
	}
	
	@Test
	public void salva() {
		produto = new Produto(1L, "Pera", "Pera", 10D);
		Mockito.when(produtoRepository.save(produto))
			.thenReturn(new Produto(1L, "Pera", "Pera", 10D));
		
		Produto produtoSalvo = produtoService.salva(produto);
		Assert.assertThat(produtoSalvo, Matchers.notNullValue());
		Assert.assertThat(produtoSalvo.getNome(), Matchers.equalTo("Pera"));
	}
	
	
	@Test
	public void getProduto() {

		Mockito.when(produtoRepository.findById(1L))
				.thenReturn(Optional.of(new Produto(1L, "Sabao", "Melhor sabao",2D)));
	
		produto = produtoService.get(1L);
		Assert.assertThat(produto, Matchers.notNullValue());
		Assert.assertThat(produto.getId(),Matchers.equalTo(1L));
		Assert.assertThat(produto.getNome(),Matchers.equalTo("Sabao"));
	}
	@Test
	public void getProdutos() {

		Mockito.when(produtoRepository.findAll())
				.thenReturn(Lists.newArrayList(new Produto(),new Produto()) );
		
		produtos = produtoService.todos();
		Assert.assertThat(produtos.size(), Matchers.equalTo(2));
		
	}
}
