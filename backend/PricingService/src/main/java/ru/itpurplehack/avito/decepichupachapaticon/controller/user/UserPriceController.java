package ru.itpurplehack.avito.decepichupachapaticon.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itpurplehack.avito.decepichupachapaticon.priceReturnService.PriceReturnService;
import ru.itpurplehack.avito.decepichupachapaticon.jsonEntity.UserPriceResponse;
import ru.itpurplehack.avito.decepichupachapaticon.jsonEntity.UserRequest;

@RestController
public class UserPriceController {

    private final PriceReturnService priceReturnService;

    @Autowired
    public UserPriceController(PriceReturnService priceReturnService) {
        this.priceReturnService = priceReturnService;
    }

    @PostMapping("/user/price")
    public UserPriceResponse getPriceForUser(@RequestBody UserRequest userRequest) {
        return priceReturnService.searchInMatrix(userRequest.getMicrocategoryId(), userRequest.getLocationId(),userRequest.getUserId());
    }
}
