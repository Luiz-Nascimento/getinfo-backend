package com.getinfo.contratos.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @GetMapping
    public String home() {
        return "home";
    }
}
