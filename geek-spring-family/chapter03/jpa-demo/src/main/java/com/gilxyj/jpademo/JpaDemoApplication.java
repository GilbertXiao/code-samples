package com.gilxyj.jpademo;

import com.gilxyj.jpademo.model.Coffee;
import com.gilxyj.jpademo.model.CoffeeOrder;
import com.gilxyj.jpademo.repository.CoffeeOrderRepository;
import com.gilxyj.jpademo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class JpaDemoApplication  implements ApplicationRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CoffeeOrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
         initOrders();
    }

    private void initOrders() {
        Coffee espresso = Coffee.builder().name("espresso")
                .price(Money.parse("USD 23.87"))
                .build();

        log.info("Coffee: {}", espresso);
        coffeeRepository.save(espresso);


        Coffee latte = Coffee.builder().name("latte")
                .price(Money.of(CurrencyUnit.EUR, 30.0)).build();
        coffeeRepository.save(latte);
        log.info("Coffee: {}", latte);

        Iterable<Coffee> allCoffee = coffeeRepository.findAll();

        log.info("All Coffee:{}", allCoffee);

        CoffeeOrder order = CoffeeOrder.builder()
                .customer("Li Lei")
                .items(Collections.singletonList(espresso))
                .state(0)
                .build();
        orderRepository.save(order);
        log.info("Order: {}", order);

        order = CoffeeOrder.builder()
                .customer("Li Lei")
                .items(Arrays.asList(espresso, latte))
                .state(0)
                .build();
        orderRepository.save(order);
        log.info("Order: {}", order);

        Iterable<CoffeeOrder> allOrder = orderRepository.findAll();
        log.info("Order All: {}", allOrder);

    }
}
