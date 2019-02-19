package basic_teste_pay_shop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.SpringBootConfiguration;
import app.model.Produto;
import app.repository.ProdutoRepository;
import app.service.ProdutoService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootConfiguration.class)
public class ProdutoControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private ProdutoService produtoService;
	
	@MockBean
	private ProdutoRepository produtoRepository;
	
	private Produto produto;
	private List<Produto> produtos;
	private MockMvc mockMvc;
	private ObjectMapper om;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		om = new ObjectMapper();
	}
	
	@Test
	public void getProduto() throws Exception {
		
		Optional<Produto> opetional = Optional.of(new Produto(10D));
		
		Mockito.when(produtoRepository.findById(1L))
			.thenReturn(opetional);
		
		produto = produtoService.get(1L);

		MvcResult result = mockMvc.perform(
				get("/api/produto/1").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andReturn();
		
		String produtoJson = result.getResponse().getContentAsString();
		produto = om.readValue(produtoJson, Produto.class);
		Assert.assertThat(produto, Matchers.notNullValue());
	}
	
	@Test
	public void save() throws Exception {
		
		mockMvc.perform( 
					post("/api/produto/save")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.sessionAttr("produto", new Produto(1L, "Pasta", "Pasta boa", 10D))
						
				)
				//.andExpect(status().isOk())
				//.andExpect(model().attribute("produto.nome", Matchers.equalTo("Pasta")))
				;
	}
}
