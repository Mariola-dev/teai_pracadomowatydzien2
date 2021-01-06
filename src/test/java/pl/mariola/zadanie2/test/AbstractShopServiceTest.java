package pl.mariola.zadanie2.test;

import org.springframework.beans.factory.annotation.Autowired;
import pl.mariola.zadanie2.product.entity.Product;
import pl.mariola.zadanie2.shop.Shop;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public abstract class AbstractShopServiceTest {

  protected static final int SCALE_2_PLACES = 2;
  @Autowired
  protected Shop shop;


  protected BigDecimal sumPrice() {
    return shop.getProductsInCart().stream()
            .map(Product::getPrice)
            .reduce(ZERO, BigDecimal::add);
  }
}
