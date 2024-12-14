package org.javaboy.vcher.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class ViewController {

    @RequestMapping("/")
    public String backEndIndex() {
        return "redirect:/index.html";
    }
}
