package pl.mariola.zadanie2.shop.variants;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.mariola.zadanie2.product.ProductRepository;
import pl.mariola.zadanie2.shop.AbstractShopService;
import pl.mariola.zadanie2.shop.CartService;
import pl.mariola.zadanie2.shop.VariantName;

import java.math.BigDecimal;
import java.util.Optional;

import static pl.mariola.zadanie2.shop.VariantName.START;

@Profile("start")
@Service
public class StartShopService extends AbstractShopService {


  public StartShopService(ProductRepository productRepository, CartService cartService) {
    super(productRepository, cartService);
  }


  @Override
  public Optional<BigDecimal> newTotalPrice() {
    return Optional.empty();
  }


  @Override
  public VariantName variantName() {
    return START;
  }


}
