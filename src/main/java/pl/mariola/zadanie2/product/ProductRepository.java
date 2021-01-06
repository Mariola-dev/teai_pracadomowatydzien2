package pl.mariola.zadanie2.product;

import org.springframework.stereotype.Repository;
import pl.mariola.zadanie2.product.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Repository
public class ProductRepository {

  private static final int MAX_PRODUCT_SIZE = 13;
  private static final int MIN_RANDOM_PRICE = 50;
  private static final int MAX_RANDOM_PRICE = 301;
  private final List<Product> products;


  public ProductRepository() {
    products = preparePoducts();
  }


  private List<Product> preparePoducts() {
    return IntStream.range(1, MAX_PRODUCT_SIZE)
            .mapToObj(index -> new Product(productName(index), randomPrice()))
            .collect(Collectors.toList());
  }


  private String productName(int index) {
    return "product" + index;
  }


  private BigDecimal randomPrice() {
    int randomPrice = new Random().ints(MIN_RANDOM_PRICE, MAX_RANDOM_PRICE).findFirst().getAsInt();
    return new BigDecimal(randomPrice);
  }


  public List<Product> getAllProducts() {
    return products;
  }


  public List<Product> getRandomProducts(int size) {
    if (size > products.size()) {
      return products;
    }

    return new Random().ints(0, products.size())
            .distinct()
            .limit(size)
            .mapToObj(products::get)
            .collect(Collectors.toList());
  }
}
