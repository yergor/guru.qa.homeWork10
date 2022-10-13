package guru.qa;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class CheckNameIssue {

    private static final String REPOSITORY = "yergor/guru.qa.homeWork8";
    private static final int ISSUE = 2;

    @BeforeAll
    static void configure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    public void simpleTest() {
        open("https://github.com/");
        $("[placeholder='Search GitHub']").setValue("yergor/guru.qa.homeWork8").pressEnter();
        $(linkText("yergor/guru.qa.homeWork8")).click();
        $("#issues-tab").click();
        $(withText("#2")).should(exist);

    }

    @Test
    public void testhWithLambda() {
        step("Открываем гитхаб", () -> {
            open("https://github.com/");
        });
        step("Поиск репозитория" + REPOSITORY, () -> {
            $("[placeholder='Search GitHub']").setValue(REPOSITORY).pressEnter();
        });
        step("Переходим в репозиторий" + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем вкладку Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue c номером " + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(exist);
        });
    }

    @Test
    public void testWithSteps() {
        StepsForTest steps = new StepsForTest();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }
}
