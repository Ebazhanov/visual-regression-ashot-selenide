package com.base.utils

import com.codeborne.selenide.Configuration
import io.github.bonigarcia.wdm.ChromeDriverManager
import io.github.bonigarcia.wdm.DriverManagerType
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities

import static com.codeborne.selenide.WebDriverRunner.setWebDriver

class BrowserSelection {

    static void selectBrowser(String browser, String deviceName) throws Exception {
        def driver
        if (browser == "Chrome" || deviceName == null) {
            Configuration.browser = "Chrome"
            //Configuration.startMaximized = "Chrome"
            Configuration.browserSize = "1024x768"
            //Configuration.headless = true
            ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup()
        } else if (deviceName != null && !deviceName.isEmpty()) {
            Configuration.browser = "Chrome"
            ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup()
            Map<String, String> mobileEmulation = new HashMap<String, String>()
            mobileEmulation.put("deviceName", deviceName)
            Map<String, Object> chromeOptions = new HashMap<String, Object>()
            chromeOptions.put("mobileEmulation", mobileEmulation)
            DesiredCapabilities capabilities = DesiredCapabilities.chrome()
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions)
            driver = new ChromeDriver(capabilities)
            setWebDriver(driver)
        } else {
            throw new IllegalStateException("Browser " + browser + " not supported in tests")
        }
    }

}
