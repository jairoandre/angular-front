package br.com.netprecision.prova.app.repository;

import br.com.netprecision.prova.app.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

  Product findOneByCode(String code);
}
