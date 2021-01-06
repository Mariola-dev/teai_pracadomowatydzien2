package pl.mariola.zadanie2.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import pl.mariola.zadanie2.product.ProductRepository;
import pl.mariola.zadanie2.product.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

public abstract class AbstractShopService implements Shop {

  private ProductRepository productRepository;
  protected CartService cartService;

  @Value("${shop.product.random-products-size}")
  private int randomProductsSize;

  protected abstract Optional<BigDecimal> newTotalPrice();

  protected abstract VariantName variantName();


  @Autowired
  public AbstractShopService(ProductRepository productRepository, CartService cartService) {
    this.productRepository = productRepository;
    this.cartService = cartService;
  }


  @Override
  public void prepareCart() {
    cartService.addProductsToCart(productRepository.getRandomProducts(randomProductsSize));
    cartService.changeTotalPrice(newTotalPrice());
  }


  @Override
  public void showCart() {
    showVariantName();
    showAllProductsSize();
    cartService.showCart();
  }


  private void showVariantName() {
    cartService.show(format("Shop version %s", variantName()));
  }


  private void showAllProductsSize() {
    cartService.show(format("All products size: %s", productRepository.getAllProducts().size()));
  }


  @Override
  public void clearCart() {
    cartService.clearCart();
  }


  @Override
  public List<Product> getProductsInCart() {
    return cartService.getProductsInCart();
  }


  @Override
  public BigDecimal totalPrice() {
    return cartService.totalPrice();
  }
}
