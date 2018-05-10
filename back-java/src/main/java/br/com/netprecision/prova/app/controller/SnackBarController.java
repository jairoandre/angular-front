package br.com.netprecision.prova.app.controller;

import br.com.netprecision.prova.app.entity.Order;
import br.com.netprecision.prova.app.entity.Product;
import br.com.netprecision.prova.app.model.OrderItemPayload;
import br.com.netprecision.prova.app.service.SnackBarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/snackbar")
public class SnackBarController {

  @Autowired
  private SnackBarService snackBarService;

  @RequestMapping(value = "/order", method = {RequestMethod.POST})
  public ResponseEntity<Long> createOrder() {
    return ResponseEntity.ok(snackBarService.createOrder());
  }

  @RequestMapping(value = "/order/{id}", method = {RequestMethod.GET})
  public ResponseEntity<Order> getOrder(@PathVariable("id") Long orderId) {
    return ResponseEntity.ok(snackBarService.getOrder(orderId));
  }

  @RequestMapping(value = "/order", method = {RequestMethod.PUT})
  public ResponseEntity<Byte> addProductOnOrder(@RequestBody OrderItemPayload payload) {
    return ResponseEntity.ok(snackBarService.addProductOnOrder(payload.getOrderId(), payload.getProductCode(), payload.getQuantity()));
  }

  @RequestMapping(value = "/order/{id}/total", method = {RequestMethod.GET})
  public ResponseEntity<Float> getTotal(@PathVariable("id") Long orderId) {
    return ResponseEntity.ok(snackBarService.computeOrderTotalPrice(orderId));
  }

  @RequestMapping(value = "/order/{id}/exchange", method = {RequestMethod.GET})
  public ResponseEntity<Float> getExchange(@PathVariable("id") Long orderId, @RequestParam("amount") Float amount) {
    return ResponseEntity.ok(snackBarService.closeOrder(orderId, amount));
  }

  @RequestMapping(value = "/products", method = {RequestMethod.GET})
  public ResponseEntity<List<Product>> getAllProducts() {
    return ResponseEntity.ok(snackBarService.getAllProducts());
  }


}
