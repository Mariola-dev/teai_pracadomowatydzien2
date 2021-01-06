package pl.mariola.zadanie2.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"shop.product.random-products-size=5"})
@ActiveProfiles("start")
public class StartShopServiceTest extends AbstractShopServiceTest {

  private static final int EXPECTED_PRODUCTS_SIZE_IN_CART = 5;


  @Test
  public void test() {
    assertNotNull(shop);
    assertEquals(EXPECTED_PRODUCTS_SIZE_IN_CART, shop.getProductsInCart().size());
    assertEquals(sumPrice(), shop.totalPrice());
  }


}
