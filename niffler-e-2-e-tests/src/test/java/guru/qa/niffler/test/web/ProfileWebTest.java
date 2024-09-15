package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.Category;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.page.LoginPage;
import guru.qa.niffler.page.MainPage;
import guru.qa.niffler.page.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfileWebTest {

    private static final Config CFG = Config.getInstance();

    @Category(
            username = "test",
            archived = false
    )
    @Test
    @DisplayName("Архивация категории: проверка успешной архивации категории")
    void categoryIsArchivedSuccessfully(CategoryJson category) {
        final String successMessage = "Category " + category.name() + " is archived";

        MainPage mainPage = Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("test", "1234567")
                .successfulLogin();

        mainPage.menuButtonClick()
                .profileButtonClick();

        new ProfilePage()
                .clickArchiveButton(category.name())
                .clickArchiveButtonSubmit()
                .checkSuccessAlert(successMessage)
                .checkCategoryNotInList(category.name())
                .clickArchiveSwitcher()
                .checkCategoryInList(category.name())
                .checkUnarchiveButtonCategory(category.name());
    }

    @Category(
            username = "test",
            archived = true
    )
    @Test
    @DisplayName("Разархивация категории: проверка успешной разархивации категории")
    void categoryIsUnarchivedSuccessfully(CategoryJson category) {
        final String successMessage = "Category " + category.name() + " is unarchived";

        MainPage mainPage = Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login("test", "1234567")
                .successfulLogin();

        mainPage.menuButtonClick()
                .profileButtonClick();

        new ProfilePage()
                .clickArchiveSwitcher()
                .checkCategoryInList(category.name())
                .clickUnarchiveCategoryButton(category.name())
                .clickUnarchiveButton()
                .checkSuccessAlert(successMessage)
                .checkArchiveButtonCategory(category.name());
    }
}
