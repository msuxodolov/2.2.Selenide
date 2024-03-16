package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

class OrderingCardTest {

    @Test
    void orderOnlineBankCardTest() {
        open("http://localhost:9999");
        $("[data-test-id=city] [placeholder='Город']").setValue("Архангельск");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        String verificationDate = LocalDate.now().plusDays(3)         //Текущая дата плюс 3 дня
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));     //Формат даты день.месяц.год
        $("[data-test-id=date] input").setValue(verificationDate);
        $("[data-test-id=name] [name='name']").setValue("Иван Смирнов");
        $("[data-test-id=phone] [name='phone']").setValue("+79111731333");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id=notification]")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + verificationDate),
                        Duration.ofSeconds(15));
    }

    @Test
    void orderOnlineBankCardTwoTest() {
        open("http://localhost:9999");
        $("[data-test-id=city] [placeholder='Город']").setValue("Ар");
        $(Selectors.byText("Архангельск")).click();
        $("[data-test-id=date] input").click();
        int days = 4;
        for (int cycle = 0; cycle < days; cycle++) {
            $(".calendar").sendKeys(Keys.ARROW_RIGHT);
        }
        $(".calendar").sendKeys(Keys.ENTER);
        String verificationDate = LocalDate.now().plusDays(7)         //Текущая дата плюс 7 дней
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));     //Формат даты день.месяц.год
        $("[data-test-id=name] [name='name']").setValue("Иван Смирнов");
        $("[data-test-id=phone] [name='phone']").setValue("+79111731333");
        $("[data-test-id=agreement]").click();
        $("[role=button] .button__content").click();
        $("[data-test-id=notification]")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + verificationDate),
                        Duration.ofSeconds(15));
    }


}