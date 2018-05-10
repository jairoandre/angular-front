package br.com.netprecision.prova.app.repository;

import br.com.netprecision.prova.app.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

  @Query(value =
      "select sum(p.price * oi.quantity) from orders o " +
          "inner join o.items as oi " +
          "inner join oi.product as p " +
          "where o.id = ?1 " +
          "group by o.id")
    // @Query("select o.id from Order o where o.id = ?1")
  float computeOrderTotalPrice(Long id);
}
