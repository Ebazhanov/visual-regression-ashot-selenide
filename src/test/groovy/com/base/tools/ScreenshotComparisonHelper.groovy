package com.base.tools

import com.codeborne.selenide.WebDriverRunner
import io.qameta.allure.Allure
import org.openqa.selenium.By
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

    private String pathToResources = "src/test/resources/"

    Screenshot takeActualScreenshot(String screenshotName, String ignoreElement) {
        Screenshot takeScreenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportRetina(300, 0, 0, 2))
                .coordsProvider(new WebDriverCoordsProvider())
                .addIgnoredElement(By.cssSelector(ignoreElement))
                .takeScreenshot(WebDriverRunner.getWebDriver())
        ImageIO.write(takeScreenshot.getImage(), "PNG", new File(pathToResources + screenshotName));
        return takeScreenshot
    }

    Screenshot getExpectedScreenshot(String screenshotName) {
        Screenshot expectedScreenshot = new Screenshot(ImageIO.read(new File(pathToResources + screenshotName)));
        return expectedScreenshot
    }

    ImageDiff compareScreenshots(Screenshot actual, Screenshot expected, int withDiffSizeTrigger, String actualFileName, String originalFileName) {
        ImageDiff diff = new ImageDiffer().makeDiff(expected, actual).withDiffSizeTrigger(withDiffSizeTrigger)
        if (diff.hasDiff()) {
            ImageIO.write(diff.getMarkedImage(), "PNG", new File(pathToResources + "Difference" + actualFileName))
            Path content = Paths.get(pathToResources + "Difference" + actualFileName)
            InputStream difference = Files.newInputStream(content)
            Allure.addAttachment("Difference", difference)
            Allure.addAttachment("Actual", readScreenshotFromResources(actualFileName))
            Allure.addAttachment("Original", readScreenshotFromResources(originalFileName))
        } else {
            Allure.addAttachment("Actual", readScreenshotFromResources(actualFileName))
            Allure.addAttachment("Original", readScreenshotFromResources(originalFileName))
        }
        return diff
    }

    private readScreenshotFromResources(String fileName) {
        Path path = Paths.get(pathToResources + fileName)
        InputStream actualResult = Files.newInputStream(path)
        return actualResult
    }

}
