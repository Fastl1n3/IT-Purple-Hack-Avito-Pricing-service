package ru.itpurplehack.avito.decepichupachapaticon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itpurplehack.avito.decepichupachapaticon.priceReturnService.PriceReturnService;

@SpringBootApplication
public class PricingServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PricingServiceApplication.class, args);
        /*PriceReturnService priceReturnService = context.getBean("priceReturnService", PriceReturnService.class);
        priceReturnService.fillTables();*/ //TODO расскоментить при заполненных таблицах DMatrices_info и BMatrices_info
    }

}