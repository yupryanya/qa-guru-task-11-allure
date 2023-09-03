package quru.qa.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    private SelenideElement mainPageSearchField = $(".search-input-container"),
            searchQueryInput = $("#query-builder-test");

    @Step("Открываем главную страницу")
    public WebSteps openPage() {
        open("https://github.com");
        return this;
    }

    @Step("Выполняем поиск по имени репозитория {repo}")
    public WebSteps searchWithMainSearchField(String repo) {
        mainPageSearchField.click();
        searchQueryInput.sendKeys(repo);
        searchQueryInput.submit();
        return this;
    }

    @Step("Кликаем по ссылке {repo} в результатах поиска")
    public WebSteps clickOnRepositoryName(String repo) {
        $(linkText(repo)).click();
        return this;
    }

    @Step("Открываем таб Issues")
    public WebSteps openIssueTab() {
        $("#issues-tab").click();
        return this;
    }

    @Step("Проверяем что Issue с именем '{issue}' существует")
    public void checkIssueWithName(String issue) {
        $(withText(issue)).should(Condition.exist);
    }
}
