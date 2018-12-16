package com.base.pages

import com.base.tools.ScreenshotComparisonHelper
import com.base.utils.PropertiesReader
import com.codeborne.selenide.Condition
import io.qameta.allure.Step
import ru.yandex.qatools.ashot.Screenshot

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
    compareFullScreenshotHomePage(String actualName, String expectedName,
                                  String ignoreElement, int withDiffSizeTrigger) {
        Screenshot actualScreenshot = new ScreenshotComparisonHelper().takeActualScreenshot(actualName, ignoreElement)
        Screenshot expectedScreenshot = new ScreenshotComparisonHelper().getExpectedScreenshot(expectedName)
        expectedScreenshot.setIgnoredAreas(actualScreenshot.getIgnoredAreas())
        assertFalse("Pixels of the shcreenshot is different",
                compareScreenshots(
                        actualScreenshot,
                        expectedScreenshot,
                        withDiffSizeTrigger,
                        actualName,
                        expectedName)
                        .hasDiff())
        return this
    }

    @Step
    closeCookiesPopup() {
        $(".optanon-allow-all.accept-cookies-button").waitUntil(Condition.visible, 1000).click()
        return this
    }

}
