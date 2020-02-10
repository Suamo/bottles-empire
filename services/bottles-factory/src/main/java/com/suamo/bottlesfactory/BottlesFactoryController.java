package com.suamo.bottlesfactory;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BottlesFactoryController {

    @PostMapping("/factory")
    public void makeBottles(Model model) {

        System.out.println();
    }

}
