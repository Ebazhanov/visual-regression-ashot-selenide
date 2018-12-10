package com.base

import com.base.listeners.ScreenshotOnFailure
import org.testng.annotations.*

import static com.base.utils.BrowserSelection.selectBrowser
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver

@Listeners(ScreenshotOnFailure)
class SelenideBaseTest {

    @BeforeClass(alwaysRun = true)
    @Parameters(["browser", "deviceName"])
    void setBrowserSettings(@Optional String browser, @Optional String deviceName) {
        selectBrowser(browser, deviceName)
    }

    @AfterClass(alwaysRun = true)
    void after() {
        clearBrowserCache()
        closeWebDriver()
    }

}
