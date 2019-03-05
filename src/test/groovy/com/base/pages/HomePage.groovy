package com.base.pages

import com.base.tools.ScreenshotComparisonHelper
import com.base.tools.ScreenshotFileNameEnum
import com.base.utils.PropertiesReader
import com.codeborne.selenide.Condition
import io.qameta.allure.Step
import org.openqa.selenium.By
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
    compareFullScreenshotHomePage(ScreenshotFileNameEnum fileNameEnum,
                                  Set<By> ignoreElements, int withDiffSizeTrigger) {
        Screenshot actualScreenshot = new ScreenshotComparisonHelper().takeActualScreenshot(fileNameEnum.actual, ignoreElements)
        Screenshot expectedScreenshot = new ScreenshotComparisonHelper().getExpectedScreenshot(fileNameEnum.original)
        expectedScreenshot.setIgnoredAreas(actualScreenshot.getIgnoredAreas())
        assertFalse("Pixels of the shcreenshot is different",
                compareScreenshots(
                        actualScreenshot,
                        expectedScreenshot,
                        withDiffSizeTrigger,
                        fileNameEnum.actual,
                        fileNameEnum.original)
                        .hasDiff())
        return this
    }

}
