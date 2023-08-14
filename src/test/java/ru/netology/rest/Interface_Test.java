package ru.netology.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class Interface_Test {

    @BeforeEach

    public void openHost() {
        open("http://localhost:9999");
    }

    @Test
    void successfulBidTest() {

        $("[data-test-id=name] input").val("Иванов Иван");
        $("[data-test-id=phone] input").val("+79850000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();

        $("[data-test-id = 'order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void doubleSurnameTest() {

        $("[data-test-id=name] input").val("Иванов-Петров Иван");
        $("[data-test-id=phone] input").val("+79850000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();

        $("[data-test-id = 'order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void notAgreementTest() {

        $("[data-test-id=name] input").val("Иванов Иван");
        $("[data-test-id=phone] input").val("+79850000000");
        $(By.className("button")).click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void notPhoneTest() {
        $("[data-test-id=name] input").val("Иванов Иван");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void notNameTest() {

        $("[data-test-id=phone] input").val("+79850000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();

        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }


    @Test
    void nameInEnglishTest() {

        $("[data-test-id=name] input").val("Ivaanov Ivan");
        $("[data-test-id=phone] input").val("+79850000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();

        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shortPhoneNumberTest() {

        $("[data-test-id=name] input").val("Иванов Иван");
        $("[data-test-id=phone] input").val("+7985000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();

        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void longPhoneNumberTest() {

        $("[data-test-id=name] input").val("Иванов Иван");
        $("[data-test-id=phone] input").val("+798500000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();

        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void nameWithOneEnglishLetterTest() {

        $("[data-test-id=name] input").val("Iванов Иван");
        $("[data-test-id=phone] input").val("+79850000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();

        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithNumberTest() {

        $("[data-test-id=name] input").val("Иванов2 Иван");
        $("[data-test-id=phone] input").val("+79850000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();

        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithSymbolTest() {

        $("[data-test-id=name] input").val("Ив@нов Иван");
        $("[data-test-id=phone] input").val("+79850000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();

        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void TheValueOfThePhoneFromTheEightTest() {

        $("[data-test-id=name] input").val("Иванов Иван");
        $("[data-test-id=phone] input").val("898500000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();

        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void phoneМalueЦithoutЗlusTest() {

        $("[data-test-id=name] input").val("Иванов Иван");
        $("[data-test-id=phone] input").val("798500000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();

        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}
