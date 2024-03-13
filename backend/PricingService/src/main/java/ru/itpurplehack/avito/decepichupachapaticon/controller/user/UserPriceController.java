package ru.itpurplehack.avito.decepichupachapaticon.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itpurplehack.avito.decepichupachapaticon.algo.AlgorithmModule;
import ru.itpurplehack.avito.decepichupachapaticon.jsonEntity.UserPriceResponse;
import ru.itpurplehack.avito.decepichupachapaticon.jsonEntity.UserRequest;

@RestController
public class UserPriceController {

    private final AlgorithmModule algorithmModule;

    @Autowired
    public UserPriceController(AlgorithmModule algorithmModule) {
        this.algorithmModule = algorithmModule;
    }

    @PostMapping("/user/price")
    public UserPriceResponse getPriceForUser(@RequestBody UserRequest userRequest) {
        //int price = algorithmModule.roadUpSearch(userRequest.getMicrocategoryId(), userRequest.getLocationId());
        //return new UserPriceResponse(price, userRequest.getLocationId(), userRequest.getMicrocategoryId(), matrix, segment);
    }
}
