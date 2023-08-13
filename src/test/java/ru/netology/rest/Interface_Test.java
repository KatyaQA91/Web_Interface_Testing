package ru.netology.rest;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class Interface_Test {
    @Test
    void shouldTest() {
        open("http://localhost:9999");
    }
}
