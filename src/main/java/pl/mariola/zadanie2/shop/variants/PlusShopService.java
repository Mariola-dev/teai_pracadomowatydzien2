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
import static pl.mariola.zadanie2.shop.VariantName.PLUS;

@Profile("plus")
@Service
public class PlusShopService extends StartShopService {

  protected static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
  protected static final int SCALE_2_PLACES = 2;

  @Value("${shop.product.vat-rate}")
  private Integer vatRate;


  public PlusShopService(ProductRepository productRepository, CartService cartService) {
    super(productRepository, cartService);
  }


  @Override
  public Optional<BigDecimal> newTotalPrice() {
    return Optional.of(applayVatToTotalPrice());
  }


  protected BigDecimal applayVatToTotalPrice() {
    return cartService.totalPrice().multiply(convertVatRateToMultiply()).setScale(SCALE_2_PLACES, HALF_UP);
  }


  private BigDecimal convertVatRateToMultiply() {
    return new BigDecimal(vatRate).add(ONE_HUNDRED).divide(ONE_HUNDRED, SCALE_2_PLACES, HALF_UP);
  }


  @Override
  public VariantName variantName() {
    return PLUS;
  }
}
