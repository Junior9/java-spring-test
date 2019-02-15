package basic_teste_pay_shop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.SpringBootConfiguration;
import app.model.Compra;
import app.model.Pago;
import app.model.Produto;
import app.model.User;
import app.service.CompraService;
import app.service.PagoService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootConfiguration.class)
public class CompraControllerTest {
	
	@Autowired
	private CompraService compraService;
	@Autowired
	private WebApplicationContext context;
	@MockBean
	private PagoService pagoService;
	
	private List<Produto> produtos;
	private MockMvc mockMvc;
	private Compra compra;
	private ObjectMapper om;
	private User usuario;
	
	@Before
	public void init() {
		produtos = new ArrayList<Produto>();
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		om = new ObjectMapper();
		usuario = new User(1L);
	}
	
	@Test
	public void save() throws Exception {
		
		Mockito.when(pagoService.pago(usuario.getId(),  Lists.newArrayList(new Produto(10D))))
			.thenReturn(new Pago(1L, 10D, Boolean.TRUE));
		
		compra = compraService.compra(new Compra(usuario.getId(), Lists.newArrayList(new Produto(10D))));
		String compraJson = om.writeValueAsString(compra);
		
		MvcResult result = mockMvc.perform(
				post("/api/compra/save").content(compraJson).content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andReturn();
		
		Assert.assertThat(compra.getRealizada(), Matchers.equalTo(Boolean.TRUE));

		/*
		String resultContent = result.getResponse().getContentAsString();
		om.readValue(resultContent, Compra.class);
		Assert.assertThat(compra.getRealizada(), Matchers.equalTo(Boolean.TRUE));*/
	}
}
