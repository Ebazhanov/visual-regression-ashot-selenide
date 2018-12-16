package com.tests

import com.base.SelenideBaseTest
import com.base.pages.HomePage
import io.qameta.allure.Description
import io.qameta.allure.Feature
import org.testng.annotations.Test

@Feature("Desktop/Tablet/Mobile full screenshot comparison")
class DifferentDevices extends SelenideBaseTest {

    private int DIFF_PIXELS = 20
    private String IGNORE_LOCATOR = ".mx-auto.col-sm-8.col-md-5.hide-sm"

    @Test
    @Description("Run test on Mobile emulator iPhone 7 in Chrome")
    void mobile() {
        new HomePage()
                .openHomePage()
                .closeCookiesPopup()
                .compareFullScreenshotHomePage(
                "ActualHomeMobilePage.png",
                "OriginalHomeMobilePage.png",
                IGNORE_LOCATOR,
                DIFF_PIXELS)
    }

    @Test
    @Description("Run test on Tablet emulator Ipad in Chrome")
    void tablet() {
        new HomePage()
                .openHomePage()
                .closeCookiesPopup()
                .compareFullScreenshotHomePage(
                "ActualHomeTabletPage.png",
                "OriginalHomeTabletPage.png",
                IGNORE_LOCATOR,
                DIFF_PIXELS)
    }

    @Test
    @Description("Run test on Desktop")
    void desktop() {
        new HomePage()
                .openHomePage()
                .closeCookiesPopup()
                .compareFullScreenshotHomePage(
                "ActualHomeDesktopPage.png",
                "OriginalHomeDesktopPage.png",
                IGNORE_LOCATOR,
                DIFF_PIXELS)
    }

}
