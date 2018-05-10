package br.com.netprecision.prova.app.service;

import br.com.netprecision.prova.app.entity.Order;
import br.com.netprecision.prova.app.entity.OrderItem;
import br.com.netprecision.prova.app.entity.Product;
import br.com.netprecision.prova.app.model.OrderItemPayload;
import br.com.netprecision.prova.app.repository.OrderRepository;
import br.com.netprecision.prova.app.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SnackBarService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ProductRepository productRepository;

  public long createOrder() {
    return orderRepository.save(new Order()).getId();
  }

  public Order getOrder(Long orderId) {
    Order order = orderRepository.findOne(orderId);
    order.getItems();
    return order;
  }

  public byte addProductOnOrder(Long orderId, String productCode, Integer quantity) {
    Order order = orderRepository.findOne(orderId);

    if (order == null)
      return -1;

    Product product = productRepository.findOneByCode(productCode);

    if (product == null)
      return -2;

    Optional<OrderItem> maybeItem = order.getItems().stream().filter(o -> o.getProduct().equals(product)).findFirst();

    if (maybeItem.isPresent()) {
      OrderItem currentItem = maybeItem.get();
      currentItem.setQuantity(currentItem.getQuantity() + quantity);
    } else {
      order.getItems().add(OrderItem.builder()
          .order(order)
          .product(product)
          .quantity(quantity)
          .build());
    }

    try {
      orderRepository.save(order);
      return 0;
    } catch (Exception e) {
      log.error(e.getMessage());
      return -9;
    }

  }

  public byte removeProductOnOrder(Long orderId, String productCode, Integer quantity) {
    Order order = orderRepository.findOne(orderId);

    if (order == null)
      return -1;

    Product product = productRepository.findOneByCode(productCode);

    if (product == null)
      return -2;

    Optional<OrderItem> maybeItem = order.getItems().stream().filter(o -> o.getProduct().equals(product)).findFirst();

    if (maybeItem.isPresent()) {
      OrderItem currentItem = maybeItem.get();
      currentItem.setQuantity(currentItem.getQuantity() - quantity);

      if (currentItem.getQuantity() < 0) {
        order.getItems().remove(currentItem);
      }

      try {
        orderRepository.save(order);
        return 0;
      } catch (Exception e) {
        log.error(e.getMessage());
        return -9;
      }

    } else {
      return -3;
    }
  }

  public float computeOrderTotalPrice(Long orderId) {
    Order order = orderRepository.findOne(orderId);

    if (order == null)
      return -1;

    return orderRepository.computeOrderTotalPrice(orderId);
  }

  public float closeOrder(Long orderId, Float paymentValue) {
    Order order = orderRepository.findOne(orderId);

    if (order == null)
      return -1;

    Float orderTotalPrice = orderRepository.computeOrderTotalPrice(orderId);

    if (orderTotalPrice > paymentValue)
      return -2;

    return paymentValue - orderTotalPrice;
  }

  private Float computeTotal(OrderItemPayload payload) {
    Product product = productRepository.findOneByCode(payload.getProductCode());
    if (product == null) {
      throw new RuntimeException("Product not found");
    }
    return product.getPrice().floatValue() * payload.getQuantity();
  }

  public float computeOrderTotalPrice(List<OrderItemPayload> orderItems) {
    try {
      return orderItems.stream().reduce(0f, (acc, oi) -> (acc == null) ? computeTotal(oi) : (acc + computeTotal(oi)), Float::sum);
    } catch (Exception e) {
      return -2;
    }
  }

  public List<Product> getAllProducts() {
    List<Product> array = new ArrayList<>();
    productRepository.findAll().forEach(array::add);
    return array;
  }


}
