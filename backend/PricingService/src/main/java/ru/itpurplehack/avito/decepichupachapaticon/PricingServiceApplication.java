package ru.itpurplehack.avito.decepichupachapaticon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PricingServiceApplication {
    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add("3"); a.add("6"); a.add("8");
        for(String c:a){
            if(c=="6"){
                a.remove(c);
                break;
            }
        }
        System.out.println(a);
    }

}
