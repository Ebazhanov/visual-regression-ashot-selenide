package com.tests

import com.base.SelenideBaseTest
import com.base.pages.HomePage
import com.codeborne.selenide.Condition
import com.codeborne.selenide.WebDriverRunner
import io.qameta.allure.Description
import org.openqa.selenium.By
import org.testng.annotations.Test
import ru.yandex.qatools.ashot.AShot
import ru.yandex.qatools.ashot.Screenshot
import ru.yandex.qatools.ashot.comparison.ImageDiff
import ru.yandex.qatools.ashot.comparison.ImageDiffer
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider
import ru.yandex.qatools.ashot.shooting.ShootingStrategies

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

import static com.codeborne.selenide.Selenide.$
import static com.codeborne.selenide.Selenide.open
import static com.codeborne.selenide.Selenide.switchTo
import static org.testng.AssertJUnit.assertFalse

class SimpleExamples extends SelenideBaseTest {

    String pathToResources = "src/test/resources/"

    @Test
    @Description("Part of the page image comparison")
    void desktopPartOfThePageImageComparison() {
        open("https://www.berlin.de/")
        Screenshot actualScreenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportRetina(100, 0, 0, 2))
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(WebDriverRunner.getWebDriver(), $(".channelsection.land"))
        ImageIO.write(actualScreenshot.getImage(), "PNG", new File(pathToResources + "ActualPartOfThePageImage.png"))
        BufferedImage expectedImage = ImageIO.read(new File(pathToResources + "OriginalPartOfThePageImage.png"))
        ImageDiff diff = new ImageDiffer().makeDiff(expectedImage, actualScreenshot.getImage()).withDiffSizeTrigger(10)
        ImageIO.write(diff.getMarkedImage(), "PNG", new File(pathToResources + "DifferencePartOfThePageImage.png"))
        assertFalse(diff.hasDiff())
    }

    @Test
    @Description("Full page comparison with one Ignore element")
    void desktopFullPageWithOneIgnoreElement() {
        open("https://www.glassdoor.de/")
        $("._evidon-accept-button").waitUntil(Condition.visible, 1000).click()
        Screenshot actualScreenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportRetina(100, 0, 0, 2))
                .coordsProvider(new WebDriverCoordsProvider())
                .addIgnoredElement(By.cssSelector(".hphSlider.featured-employers"))
                .takeScreenshot(WebDriverRunner.getWebDriver())
        ImageIO.write(actualScreenshot.getImage(), "PNG", new File(pathToResources + "ActualDesktopFullPageWithOneIgnoreElement.png"))
        Screenshot expectedImage = new Screenshot(ImageIO.read(new File(pathToResources + "OriginalDesktopFullPageWithOneIgnoreElement.png")))
        expectedImage.setIgnoredAreas(actualScreenshot.getIgnoredAreas())
        ImageDiff diff = new ImageDiffer().makeDiff(expectedImage, actualScreenshot).withDiffSizeTrigger(0)
        ImageIO.write(diff.getMarkedImage(), "PNG", new File(pathToResources + "DifferenceDesktopFullPageWithOneIgnoreElement.png"))
        assertFalse(diff.hasDiff())
    }


    @Test
    @Description("Full page comparison with multiple Ignore elements")
    void desktopFullPageWithMultipleIgnoreElement() {
        open("https://www.drivy.de/")
        $(".js_cookies_banner_accept_full_form").waitUntil(Condition.visible, 1000).click()
        Set<By> setIgnoredElements = new HashSet()
        setIgnoredElements.add(By.cssSelector(".homepage_hero__usecases"))
        setIgnoredElements.add(By.cssSelector(".car_card__header"))
        Screenshot actualScreenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportRetina(100, 0, 0, 2))
                .coordsProvider(new WebDriverCoordsProvider())
                .ignoredElements(setIgnoredElements)
                .takeScreenshot(WebDriverRunner.getWebDriver())
        ImageIO.write(actualScreenshot.getImage(), "PNG", new File(pathToResources + "ActualFullPageWithMultipleIgnoreElement.png"))
        Screenshot expectedImage = new Screenshot(ImageIO.read(new File(pathToResources + "OriginalFullPageWithMultipleIgnoreElement.png")))
        expectedImage.setIgnoredAreas(actualScreenshot.getIgnoredAreas())
        ImageDiff diff = new ImageDiffer().makeDiff(expectedImage, actualScreenshot).withDiffSizeTrigger(10)
        ImageIO.write(diff.getMarkedImage(), "PNG", new File(pathToResources + "DifferenceFullPageWithMultipleIgnoreElement.png"))
        assertFalse(diff.hasDiff())
    }

    @Test
    @Description("Full page comparison failed test")
    void desktopFullPage() {
        open("https://www.bvg.de/en/")
        $(".button.cookie-confirm__button.cookie-confirm__accept").waitUntil(Condition.visible, 1000).click()
        Screenshot actualScreenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportRetina(100, 0, 0, 2))
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(WebDriverRunner.getWebDriver())
        ImageIO.write(actualScreenshot.getImage(), "PNG", new File(pathToResources + "ActualDesktopFullPageBvg.png"))
        Screenshot expectedImage = new Screenshot(ImageIO.read(new File(pathToResources + "OriginalDesktopFullPageBvg.png")))
        ImageDiff diff = new ImageDiffer().makeDiff(expectedImage, actualScreenshot).withDiffSizeTrigger(0)
        ImageIO.write(diff.getMarkedImage(), "PNG", new File(pathToResources + "DifferenceDesktopFullPageBvg.png"))
        assertFalse(diff.hasDiff())
    }

}
