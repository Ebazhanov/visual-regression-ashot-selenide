package com.base.tools

enum ScreenshotFileNameEnum {

    HomePageDesktop("ActualHomeDesktopPage.png", "ActualHomeDesktopPage.png"),
    HomePageMobile("ActualHomeMobilePage.png", "OriginalHomeMobilePage.png"),
    HomePageTablet("ActualHomeTabletPage.png", "OriginalHomeTabletPage.png");

    String actual
    String original

    ScreenshotFileNameEnum(String actual, String original) {
        this.actual = actual
        this.original = original
    }
    
}