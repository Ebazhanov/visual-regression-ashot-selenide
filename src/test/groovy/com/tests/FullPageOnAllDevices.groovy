package com.tests

import com.base.SelenideBaseTest
import com.base.pages.HomePage
import io.qameta.allure.Description
import io.qameta.allure.Feature
import org.testng.annotations.Test

@Feature("Desktop/Tablet/Mobile full screenshot comparison")
class FullPageOnAllDevices extends SelenideBaseTest {

    @Test
    @Description("Run test on Mobile emulator iPhone 7 in Chrome")
    void mobile() {
        new HomePage()
                .openHomePage()
                .closeCookiesPopup()
                .compareScreenshotHomePage("ActualHomeMobilePage", "OriginalHomeMobilePage", 10)
    }

    @Test
    @Description("Run test on Tablet emulator Ipad in Chrome")
    void tablet() {
        new HomePage()
                .openHomePage()
                .closeCookiesPopup()
                .compareScreenshotHomePage("ActualHomeTabletPage", "OriginalHomeTabletPage", 10)
    }

    @Test
    @Description("Run test on Desktop")
    void desktop() {
        new HomePage()
                .openHomePage()
                .closeCookiesPopup()
                .compareScreenshotHomePage("ActualHomeDesktopPage", "OriginalHomeDesktopPage", 10)
    }

}
