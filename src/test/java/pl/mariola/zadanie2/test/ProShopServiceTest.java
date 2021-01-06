package pl.mariola.zadanie2.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"shop.product.vat-rate=6", "shop.product.discount=15"})
@ActiveProfiles("pro")
public class ProShopServiceTest extends AbstractShopServiceTest {

  private static final BigDecimal VAT_RATE = new BigDecimal("1.06");
  private static final BigDecimal DISCOUNT = new BigDecimal("0.85");


  @Test
  public void test() {
    assertNotNull(shop);
    assertEquals(priceWithVatRateAndDiscount(), shop.totalPrice());
  }


  private BigDecimal priceWithVatRateAndDiscount() {
    return sumPrice().multiply(VAT_RATE).multiply(DISCOUNT).setScale(SCALE_2_PLACES, HALF_UP);
  }

}
