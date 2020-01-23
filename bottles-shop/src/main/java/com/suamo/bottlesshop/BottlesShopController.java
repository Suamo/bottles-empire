package com.suamo.bottlesshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BottlesShopController {

    @GetMapping
    public String list() {
        return "bottle1, bottle2";
    }

}
