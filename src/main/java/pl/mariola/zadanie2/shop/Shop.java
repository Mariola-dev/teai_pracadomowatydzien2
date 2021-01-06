package pl.mariola.zadanie2.shop;


import pl.mariola.zadanie2.product.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface Shop {

  void prepareCart();

  void showCart();

  void clearCart();

  List<Product> getProductsInCart();

  BigDecimal totalPrice();
}
