import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTests {

    @BeforeAll
    public static void setup() {
        Configuration.browser = "chrome"; // Указываем, что тесты будут выполняться в браузере Chrome
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver(); // Закрываем браузер после каждого теста
    }

    @Test
    public void testWikipediaSearch() {
        open("https://www.wikipedia.org");
        $("#searchInput").setValue("Java").pressEnter();
        $("#firstHeading").shouldHave(text("Java"));
    }

    @Test
    public void testReturnToMainPageLink() {
        open("https://www.wikipedia.org/wiki/Special:Code");

        // Проверяем наличие элемента с ссылкой на "Заглавную страницу"
        $(".mw-indicator-with-pageview").$("a[title='Заглавная страница']").shouldNot(exist);
    }

    @Test
    public void testArticleParagraphs() {
        open("https://www.wikipedia.org/wiki/Java");
        $$("#mw-content-text p").shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    @Test
    public void testArticleContent() {
        open("https://www.wikipedia.org/wiki/Java");
        $("#firstHeading").should(exist);
    }

    @Test
    public void testChangeLanguage() {
        open("https://www.wikipedia.org");
        $("#js-link-box-ru").click();
        $("html[lang='ru']").shouldBe(visible);
    }
}