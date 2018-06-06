package br.com.rbh.springbootws.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.rbh.springbootws.entities.Usuario;

@Repository
public interface UsuariosRepository extends CrudRepository<Usuario, Long> {

}
