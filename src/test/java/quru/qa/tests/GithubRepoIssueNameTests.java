package quru.qa.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.*;
import static io.qameta.allure.Allure.step;

public class GithubRepoIssueNameTests extends TestBase {
    private SelenideElement mainPageSearchField = $(".search-input-container"),
                            searchQueryInput = $("#query-builder-test");

    private final String REPOSITORY = "yupryanya/qa-guru-task-11-allure",
                         ISSUE_NAME = "Issue for name test";

    @Test
    @Tag("regression")
    @DisplayName("Issue с заданным именем существует в репозитории")
    public void issueWithRequiredNameShouldExistInRepo() {
        open("https://github.com");
        mainPageSearchField.click();
        searchQueryInput.sendKeys(REPOSITORY);
        searchQueryInput.submit();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText(ISSUE_NAME)).should(Condition.exist);
    }

    @Test
    @Tag("regression")
    @DisplayName("Issue с заданным именем существует в репозитории (степы с использованием лямбда)")
    public void issueWithRequiredNameShouldExistInRepoWithLambda() {
        step("Открываем главную страницу", () ->
                open("https://github.com")
        );
        step("Выполняем поиск по имени репозитория " + REPOSITORY, () -> {
            mainPageSearchField.click();
            searchQueryInput.sendKeys(REPOSITORY);
            searchQueryInput.submit();
        });
        step("Кликаем по ссылке " + REPOSITORY + " в результатах поиска", () ->
                $(linkText(REPOSITORY)).click()
        );
        step("Открываем таб Issues", () ->
                $("#issues-tab").click()
        );
        step("Проверяем что Issue с именем '" + ISSUE_NAME + "' существует", () ->
                $(withText(ISSUE_NAME)).should(Condition.exist)
        );
    }

    @Test
    @Tag("regression")
    @DisplayName("Issue с заданным именем существует в репозитории (с использованием аннотации @Step)")
    public void issueWithRequiredNameShouldExistInRepoAnnotated() {
        WebSteps steps = new WebSteps();
        steps.openPage()
                .searchWithMainSearchField(REPOSITORY)
                .clickOnRepositoryName(REPOSITORY)
                .openIssueTab()
                .checkIssueWithName(ISSUE_NAME);
    }
}
