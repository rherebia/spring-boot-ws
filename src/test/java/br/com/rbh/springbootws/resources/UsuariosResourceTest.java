package br.com.rbh.springbootws.resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.Optional;

import br.com.rbh.springbootws.entities.Usuario;
import br.com.rbh.springbootws.repositories.UsuariosRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(UsuariosResource.class)
public class UsuariosResourceTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UsuariosRepository repository;
	
	@Test
	public void testGetAll() throws Exception {
		Usuario usuario1 = new Usuario();
		usuario1.setId(1L);
		usuario1.setNome("Usuario Teste 1");
		
		Usuario usuario2 = new Usuario();
		usuario2.setId(2L);
		usuario2.setNome("Usuario Teste 2");
		
		given(repository.findAll()).willReturn(Arrays.asList(usuario1, usuario2));
		
		mvc.perform(get("/usuarios").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(content().string(containsString("Usuario Teste 1")))
			.andExpect(content().string(containsString("Usuario Teste 2")));
	}
	
	@Test
	public void testGetByIdUsuarioInexistente() throws Exception {
		given(repository.findById(1L)).willReturn(Optional.empty());
		
		mvc.perform(get("/usuarios/1"))
			.andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	
	@Test
	public void testGetByIdUsuarioExistente() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("Usuario Teste 1");
		
		given(repository.findById(anyLong())).willReturn(Optional.of(usuario));
		
		mvc.perform(get("/usuarios/1").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(content().string(containsString("Usuario Teste 1")));
	}
	
	@Test
	public void testUpdateUsuarioInexistente() throws Exception {
		given(repository.findById(anyLong())).willReturn(Optional.empty());
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("Usuario Teste 1");
		
		mvc.perform(put("/usuarios/1", usuario))
			.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}
	
	@Test
	public void testUpdateUsuarioExistente() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("Usuario Teste 1");
		
		given(repository.findById(anyLong())).willReturn(Optional.of(usuario));
		given(repository.save(any())).willReturn(usuario);
		
		mvc.perform(put("/usuarios/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":1,\"nome\":\"Usuario Teste 2\"}"))
			.andExpect(status().is(HttpStatus.OK.value()));
	}
	
	@Test
	public void testCreateUsuario() throws Exception {
		given(repository.save(any())).willReturn(any());
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("Usuario Teste 1");
		
		mvc.perform(post("/usuarios")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":1,\"nome\":\"Usuario Teste 2\"}"))
			.andExpect(status().is(HttpStatus.CREATED.value()));
	}
	
	@Test
	public void testRemoveUsuario() throws Exception {
		doNothing().when(repository).deleteById(anyLong());
		
		mvc.perform(delete("/usuarios/1"))
			.andExpect(status().is(HttpStatus.NO_CONTENT.value()));
	}
}
