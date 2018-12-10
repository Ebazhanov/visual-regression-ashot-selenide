package com.base.pages

import com.base.tools.ScreenshotComparisonHelper
import com.base.utils.PropertiesReader
import com.codeborne.selenide.Condition
import io.qameta.allure.Step

import static com.codeborne.selenide.Selenide.$
import static com.codeborne.selenide.Selenide.open
import static org.testng.AssertJUnit.assertFalse

class HomePage extends ScreenshotComparisonHelper {

    @Step
    openHomePage() {
        open(PropertiesReader.getConfigKey("SITE_URL"))
        return this
    }

    @Step
    compareScreenshotHomePage(String actualName, String expectedName, int withDiffSizeTrigger) {
        assertFalse("Pixels of the shcreenshot is different",
                compareScreenshots(
                        takeActualScreenshot(actualName),
                        expected(expectedName), withDiffSizeTrigger, actualName, expectedName).hasDiff())
        return this
    }

    @Step
    closeCookiesPopup() {
        $(".buttonholder").waitUntil(Condition.visible, 1000).click()
        return this
    }

}
