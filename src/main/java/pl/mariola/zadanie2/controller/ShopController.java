package pl.mariola.zadanie2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import pl.mariola.zadanie2.shop.Shop;

@Controller
public class ShopController {


  private final Shop shop;


  @Autowired
  public ShopController(Shop shop) {
    this.shop = shop;
  }


  @EventListener(ApplicationReadyEvent.class)
  public void get() {
    shop.prepareCart();
    shop.showCart();
  }

}
