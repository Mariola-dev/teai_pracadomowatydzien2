package pl.mariola.zadanie2.shop.variants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.mariola.zadanie2.product.ProductRepository;
import pl.mariola.zadanie2.shop.CartService;
import pl.mariola.zadanie2.shop.VariantName;

import java.math.BigDecimal;
import java.util.Optional;

import static java.math.RoundingMode.HALF_UP;
import static pl.mariola.zadanie2.shop.VariantName.PRO;

@Profile("pro")
@Service
public class ProShopService extends PlusShopService {

  @Value("${shop.product.discount}")
  private Integer discount;


  public ProShopService(ProductRepository productRepository, CartService cartService) {
    super(productRepository, cartService);
  }


  @Override
  public Optional<BigDecimal> newTotalPrice() {
    return Optional.of(applayDiscountToTotalPrice());
  }


  private BigDecimal applayDiscountToTotalPrice() {
    return applayVatToTotalPrice().multiply(convertDiscountToMultiply()).setScale(SCALE_2_PLACES, HALF_UP);
  }


  private BigDecimal convertDiscountToMultiply() {
    return ONE_HUNDRED.subtract(new BigDecimal(discount)).divide(ONE_HUNDRED, SCALE_2_PLACES, HALF_UP);
  }


  @Override
  public VariantName variantName() {
    return PRO;
  }
}
