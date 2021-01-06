package pl.mariola.zadanie2.shop;

import org.springframework.stereotype.Service;
import pl.mariola.zadanie2.product.entity.Product;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;

@Service
public class CartService {

  private static final Locale LOCALE_PL = new Locale("pl", "PL");
  private static final DecimalFormat CURRENCY_DECIMAL_FORMAT = (DecimalFormat) NumberFormat.getCurrencyInstance(LOCALE_PL);

  private final List<Product> productsInCart = new ArrayList<>();
  private Optional<BigDecimal> newTotalPrice = Optional.empty();


  public void addProductsToCart(List<Product> products) {
    productsInCart.addAll(products);
  }


  public List<Product> getProductsInCart() {
    return productsInCart;
  }


  public void changeTotalPrice(Optional<BigDecimal> newTotalPrice) {
    this.newTotalPrice = newTotalPrice;
  }


  public void showCart() {
    showProducts();
    showTotalPrice();
  }


  private void showProducts() {
    show("Products in Cart:");
    productsInCart.forEach(this::showProduct);
  }


  private void showProduct(Product product) {
    show(format("%s %s", product.getName(), priceWithCurrency(product.getPrice())));
  }


  private void showTotalPrice() {
    show(format("Sum: %s", priceWithCurrency(calculateTotalPrice())));
    show(format("Updated: %s", priceWithCurrency(totalPrice())));
  }


  public BigDecimal totalPrice() {
    return newTotalPrice.orElse(calculateTotalPrice());
  }


  private BigDecimal calculateTotalPrice() {
    return productsInCart.stream()
            .map(Product::getPrice)
            .reduce(ZERO, BigDecimal::add);
  }


  private String priceWithCurrency(BigDecimal price) {
    return CURRENCY_DECIMAL_FORMAT.format(price);
  }


  public void show(String productDataToShow) {
    System.out.println(productDataToShow);
  }


  public void clearCart() {
    productsInCart.clear();
    newTotalPrice = Optional.empty();
  }

}
