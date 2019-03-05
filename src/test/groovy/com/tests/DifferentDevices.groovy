package com.tests

import com.base.SelenideBaseTest
import com.base.pages.HomePage
import com.base.tools.ScreenshotFileNameEnum
import io.qameta.allure.Description
import io.qameta.allure.Feature
import org.openqa.selenium.By
import org.testng.annotations.Test

@Feature("Desktop/Tablet/Mobile full screenshot comparison")
class DifferentDevices extends SelenideBaseTest {

    private int DIFF_PIXELS = 20

    private static Set<By> ignoreElements() {
        Set<By> setIgnoredElements = new HashSet<>()
        setIgnoredElements.add(By.cssSelector(".CustomerRatings__content"))
        setIgnoredElements.add(By.cssSelector(".LegalText__container"))
        return setIgnoredElements
    }

    @Test
    @Description("Run test on Desktop")
    void desktop() {
        new HomePage()
                .openHomePage()
                .compareFullScreenshotHomePage(
                ScreenshotFileNameEnum.HomePageDesktop,
                ignoreElements(),
                DIFF_PIXELS)
    }

    @Test
    @Description("Run test on Tablet emulator Ipad in Chrome")
    void tablet() {
        new HomePage()
                .openHomePage()
                .compareFullScreenshotHomePage(
                ScreenshotFileNameEnum.HomePageTablet,
                ignoreElements(),
                DIFF_PIXELS)
    }

    @Test
    @Description("Run test on Mobile emulator iPhone 7 in Chrome")
    void mobile() {
        new HomePage()
                .openHomePage()
                .compareFullScreenshotHomePage(
                ScreenshotFileNameEnum.HomePageMobile,
                ignoreElements(),
                DIFF_PIXELS)
    }

}
