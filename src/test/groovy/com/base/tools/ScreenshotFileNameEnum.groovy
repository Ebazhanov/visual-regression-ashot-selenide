package com.base.tools

enum ScreenshotFileNameEnum {

    HomePageDesktop("ActualHomeDesktopPage.png", "ActualHomeDesktopPage.png"),
    HomePageTablet("ActualHomeTabletPage.png", "OriginalHomeTabletPage.png"),
    HomePageMobile("ActualHomeMobilePage.png", "OriginalHomeMobilePage.png");

    String actual
    String original

    ScreenshotFileNameEnum(String actual, String original) {
        this.actual = actual
        this.original = original
    }

}