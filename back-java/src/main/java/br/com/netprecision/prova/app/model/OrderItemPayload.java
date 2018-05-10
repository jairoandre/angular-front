package br.com.netprecision.prova.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemPayload {

  @JsonProperty("order_id")
  private Long orderId;

  @JsonProperty("product_code")
  private String productCode;

  private Integer quantity;
}
