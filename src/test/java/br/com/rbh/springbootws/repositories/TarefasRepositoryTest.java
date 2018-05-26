package br.com.rbh.springbootws.repositories;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Iterators;

import br.com.rbh.springbootws.entities.Tarefa;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TarefasRepositoryTest {

	@Autowired
	private TarefasRepository repository;
	
	@Test
	public void test() {
		Iterable<Tarefa> tarefas = repository.findAll();
		
		Assert.assertEquals(2, Iterators.size(tarefas.iterator()));
	}

}
