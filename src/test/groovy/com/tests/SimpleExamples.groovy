package com.tests

import com.base.SelenideBaseTest
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
import static org.testng.AssertJUnit.assertFalse

class SimpleExamples extends SelenideBaseTest {

    String pathToResources = "src/test/resources/"

    @Test
    @Description("Part of the page image comparison")
    void desktopPartOfThePageImageComparison() {
        open("https://www.lieferando.de/")
        Screenshot actualScreenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportRetina(100, 0, 0, 2))
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(WebDriverRunner.getWebDriver(), $(".inner.steps-inner"))
        ImageIO.write(actualScreenshot.getImage(), "PNG", new File(pathToResources + "ActualPartOfThePageImage.png"))
        BufferedImage expectedImage = ImageIO.read(new File(pathToResources + "OriginalPartOfThePageImage.png"))
        ImageDiff diff = new ImageDiffer().makeDiff(expectedImage, actualScreenshot.getImage()).withDiffSizeTrigger(10)
        if (diff.hasDiff()) {
            ImageIO.write(diff.getMarkedImage(), "PNG", new File(pathToResources + "DifferencePartOfThePageImage.png"))
        }
        assertFalse("Screenshot has difference", diff.hasDiff())
    }

    @Test
    @Description("Full page comparison with one Ignore element")
    void desktopFullPageWithOneIgnoreElement() {
        open("https://www.visitberlin.de/en/arrival-car")
        $(".cc_btn.cc_btn_accept_all").waitUntil(Condition.visible, 1000).click()
        Screenshot actualScreenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportRetina(300, 0, 0, 2))
                .coordsProvider(new WebDriverCoordsProvider())
                .addIgnoredElement(By.cssSelector(".bleed-header__img.cover-img"))
                .takeScreenshot(WebDriverRunner.getWebDriver())
        ImageIO.write(actualScreenshot.getImage(), "PNG", new File(pathToResources + "ActualDesktopFullPageWithOneIgnoreElement.png"))
        Screenshot expectedImage = new Screenshot(ImageIO.read(new File(pathToResources + "OriginalDesktopFullPageWithOneIgnoreElement.png")))
        expectedImage.setIgnoredAreas(actualScreenshot.getIgnoredAreas())
        ImageDiff diff = new ImageDiffer().makeDiff(expectedImage, actualScreenshot).withDiffSizeTrigger(0)
        if (diff.hasDiff()) {
            ImageIO.write(diff.getMarkedImage(), "PNG", new File(pathToResources + "DifferenceDesktopFullPageWithOneIgnoreElement.png"))
        }
        assertFalse("Screenshot has difference", diff.hasDiff())
    }


    @Test
    @Description("Full page comparison with multiple Ignore elements")
    void desktopFullPageWithMultipleIgnoreElement() {
        open("https://www.drivy.de/")
        $(".js_cookies_banner_accept_full_form").waitUntil(Condition.visible, 1000).click()
        Set<By> setIgnoredElements = new HashSet()
        setIgnoredElements.add(By.cssSelector(".homepage_hero__usecases"))
        setIgnoredElements.add(By.cssSelector(".home_last_rented_section"))
        setIgnoredElements.add(By.cssSelector(".cobalt-Button.cobalt-Button--primary.homepage_hero__cta.js_order_form_submit"))
        Screenshot actualScreenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportRetina(300, 0, 0, 2))
                .coordsProvider(new WebDriverCoordsProvider())
                .ignoredElements(setIgnoredElements)
                .takeScreenshot(WebDriverRunner.getWebDriver())
        ImageIO.write(actualScreenshot.getImage(), "PNG", new File(pathToResources + "ActualFullPageWithMultipleIgnoreElement.png"))
        Screenshot expectedImage = new Screenshot(ImageIO.read(new File(pathToResources + "OriginalFullPageWithMultipleIgnoreElement.png")))
        expectedImage.setIgnoredAreas(actualScreenshot.getIgnoredAreas())
        ImageDiff diff = new ImageDiffer().makeDiff(expectedImage, actualScreenshot).withDiffSizeTrigger(20)
        if (diff.hasDiff()) {
            ImageIO.write(diff.getMarkedImage(), "PNG", new File(pathToResources + "DifferenceFullPageWithMultipleIgnoreElement.png"))
        }
        assertFalse("Screenshot has difference", diff.hasDiff())
    }

    @Test
    @Description("Full page comparison failed test")
    void desktopFullPageFailedTest() {
        open("https://www.bvg.de/en/")
        $(".button.cookie-confirm__button.cookie-confirm__accept").waitUntil(Condition.visible, 1000).click()
        Set<By> setIgnoredElements = new HashSet()
        setIgnoredElements.add(By.cssSelector(".traffic-info"))
        setIgnoredElements.add(By.cssSelector(".teaser"))
        setIgnoredElements.add(By.cssSelector(".call-center"))
        Screenshot actualScreenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportRetina(300, 0, 0, 2))
                .coordsProvider(new WebDriverCoordsProvider())
                .ignoredElements(setIgnoredElements)
                .takeScreenshot(WebDriverRunner.getWebDriver())
        ImageIO.write(actualScreenshot.getImage(), "PNG", new File(pathToResources + "ActualDesktopFullPageBvg.png"))
        Screenshot expectedImage = new Screenshot(ImageIO.read(new File(pathToResources + "OriginalDesktopFullPageBvg.png")))
        expectedImage.setIgnoredAreas(actualScreenshot.getIgnoredAreas())
        ImageDiff diff = new ImageDiffer().makeDiff(expectedImage, actualScreenshot).withDiffSizeTrigger(0)
        if (diff.hasDiff()) {
            ImageIO.write(diff.getMarkedImage(), "PNG", new File(pathToResources + "DifferenceDesktopFullPageBvg.png"))
        }
        assertFalse("Screenshot has difference", diff.hasDiff())
    }

}
