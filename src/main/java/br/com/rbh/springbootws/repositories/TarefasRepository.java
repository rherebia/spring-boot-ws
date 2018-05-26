package br.com.rbh.springbootws.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.rbh.springbootws.entities.Tarefa;

@RepositoryRestResource(collectionResourceRel = "tarefas", path = "tarefas")
public interface TarefasRepository extends PagingAndSortingRepository<Tarefa, Long> {

}
