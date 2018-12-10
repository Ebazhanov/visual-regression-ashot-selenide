package com.base.listeners

import com.codeborne.selenide.Screenshots
import com.google.common.io.Files
import io.qameta.allure.Attachment
import org.testng.ITestResult
import org.testng.TestListenerAdapter

class ScreenshotOnFailure extends TestListenerAdapter {

    @Override
    void onTestFailure(ITestResult iTestResult) {
        attachBrowserScreenshot()
    }

    @Attachment(value = "Page screenshot on failure", type = "image/png")
    byte[] attachBrowserScreenshot() throws IOException {
        File screenshot = Screenshots.takeScreenShotAsFile()
        return Files.toByteArray(screenshot)
    }

}
