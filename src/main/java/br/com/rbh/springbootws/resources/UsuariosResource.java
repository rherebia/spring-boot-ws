package br.com.rbh.springbootws.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rbh.springbootws.entities.Usuario;
import br.com.rbh.springbootws.repositories.UsuariosRepository;

@RequestMapping("usuarios")
@RestController
public class UsuariosResource {
	
	@Autowired
	private UsuariosRepository repository;

	@GetMapping
	public ResponseEntity<?> getAll() {
		Iterable<Usuario> usuarios = repository.findAll();
		
		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Optional<Usuario> usuario = repository.findById(id);
		
		if (!usuario.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
		Optional<Usuario> usuarioBanco = repository.findById(id);
		
		if (usuarioBanco.isPresent()) {
			Usuario u = usuarioBanco.get();
			
			u.setNome(usuario.getNome());
			
			repository.save(u);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Não existe usuário com id " + id, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> partialUpdate(@RequestBody Usuario usuario) {
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Usuario usuario) {
		repository.save(usuario);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		repository.deleteById(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
