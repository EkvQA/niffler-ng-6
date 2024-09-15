package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

    private final SelenideElement archiveSwitcher = $("[data-test-id='archive-switcher']");
    private final SelenideElement archiveButtonSubmit = $("[data-test-id='archive-submit']");
    private final SelenideElement unarchiveButtonSubmit = $("[data-test-id='unarchive-submit']");
    private final SelenideElement successAlert = $("[class*='alert-success']");
    private final ElementsCollection categories = $$("[data-test-id='categories-list'] li");
    provate final SelenideElement arhiveButton = $("[data-test-id='archive-button']");

    public ProfilePage clickArchiveButton(String categoryName) {
        categories.find(text(categoryName)).$("[data-test-id='archive-button']").click();
        return this;
    }

    public ProfilePage clickUnarchiveCategoryButton(String categoryName) {
        categories.find(text(categoryName)).$("[data-test-id='unarchive-button']").click();
        return this;
    }

    public ProfilePage clickArchiveButtonSubmit() {
        archiveButtonSubmit.click();
        return this;
    }

    public ProfilePage clickUnarchiveButton() {
        unarchiveButtonSubmit.click();
        return this;
    }

    public ProfilePage checkSuccessAlert(String message) {
        successAlert.shouldHave(text(message));
        return this;
    }

    public ProfilePage checkCategoryInList(String categoryName) {
        categories.findBy(text(categoryName)).shouldBe(visible);
        return this;
    }

    public ProfilePage checkCategoryNotInList(String categoryName) {
        categories.filterBy(text(categoryName)).shouldHave(size(0));
        return this;
    }

    public ProfilePage clickArchiveSwitcher() {
        archiveSwitcher.click();
        return this;
    }

    public ProfilePage checkUnarchiveButtonCategory(String categoryName) {
        categories.find(text(categoryName)).$("[data-test-id='unarchive-button']").shouldBe(visible);
        return this;
    }

    public ProfilePage checkArchiveButtonCategory(String categoryName) {
        categories.find(text(categoryName));
        arhiveButton.shouldBe(visible);
        return this;
    }
}
