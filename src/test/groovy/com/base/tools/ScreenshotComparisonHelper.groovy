package com.base.tools

import com.codeborne.selenide.WebDriverRunner
import io.qameta.allure.Allure
import ru.yandex.qatools.ashot.AShot
import ru.yandex.qatools.ashot.Screenshot
import ru.yandex.qatools.ashot.comparison.ImageDiff
import ru.yandex.qatools.ashot.comparison.ImageDiffer
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider
import ru.yandex.qatools.ashot.shooting.ShootingStrategies

import javax.imageio.ImageIO
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class ScreenshotComparisonHelper {

    Screenshot takeActualScreenshot(String screenshotName) {
        Screenshot takeScreenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportRetina(100, 0, 0, 2))
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(WebDriverRunner.getWebDriver())
        ImageIO.write(takeScreenshot.getImage(), "PNG", new File("src/test/resources/" + screenshotName + ".png"));
        return takeScreenshot
    }

    Screenshot expected(String screenshotName) {
        Screenshot expectedScreenshot = new Screenshot(ImageIO.read(new File("src/test/resources/" + screenshotName + ".png")));
        return expectedScreenshot
    }

    ImageDiff compareScreenshots(Screenshot actual, Screenshot expected, int withDiffSizeTrigger, String actualFileName, String originalFileName) {
        ImageDiff diff = new ImageDiffer().makeDiff(expected, actual).withDiffSizeTrigger(withDiffSizeTrigger)
        if (diff.hasDiff()) {
            ImageIO.write(diff.getMarkedImage(), "PNG", new File("src/test/resources/Difference" + actualFileName + ".png"))
            Path content = Paths.get("src/test/resources/Difference" + actualFileName + ".png")
            InputStream difference = Files.newInputStream(content)
            Allure.addAttachment("Difference", difference)
            Allure.addAttachment("Original", readScreenshotFromResources(originalFileName))
            Allure.addAttachment("Actual", readScreenshotFromResources(actualFileName))
        } else {
            Allure.addAttachment("Original", readScreenshotFromResources(originalFileName))
            Allure.addAttachment("Actual", readScreenshotFromResources(actualFileName))
        }
        return diff
    }

    private readScreenshotFromResources(String fileName) {
        Path path = Paths.get("src/test/resources/" + fileName + ".png")
        InputStream actualResult = Files.newInputStream(path)
        return actualResult
    }

}
