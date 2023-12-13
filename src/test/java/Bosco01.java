import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class Bosco01 {

    @BeforeEach
    void setUp() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 15000;
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        open("https://www.bosco.ru/");
        sleep(3000);
        $(".home-slider__button-link").click();
    }

    @Test
    void testPageTitle() {
        $("h1.section-title").shouldHave(Condition.text("Женский каталог"));
    }

    @Test
    void testBasicSearchResults() {
        $(".page-header__tools .page-header__toggle-search").click();
        $("input.digi-search-form__input").setValue("max mara");
        $("button.digi-search-form__submit").click();
        $("h1.digi-title").shouldHave(Condition.text("Результаты поиска по запросу «max mara»"));
    }

    @Test
    void testBasicAddingToCart() {

        open("https://www.bosco.ru/product/shuba-iz-iskusstvennogo-mekha-706446/");
        $(".product-details__size-select").click();
        $$(".input-select__option").find(Condition.text("46")).click();
        $(".js-product-details-to-cart").click();
        $x("//div[@class='page-header__cart-default']/..//span").shouldHave(Condition.text("1"));
    }

}
