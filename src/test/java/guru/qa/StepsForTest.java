package guru.qa;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class StepsForTest {

    @Step("Открываем гитхаб")
    public void openMainPage() {
        open("https://github.com/");
    }

    @Step("Поиск репозитория {repo}")
    public void searchForRepository(String repo) {
        $("[placeholder='Search GitHub']").setValue(repo).pressEnter();
    }

    @Step("Переходим в репозиторий {repo}")
    public void clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Открываем вкладку Issues")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue c номером {issue}")
    public void shouldSeeIssueWithNumber(int issue) {
        $(withText("#" + issue)).should(exist);
    }
}
