## Full page Screenshot comparison with 'Ashot'

[![Awesome](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg)](https://github.com/pazone/ashot)

# Motivation: 
To find free tool to make full screenshots comparison on desktop + tablet/mobile!

## Examples:

* Simple example with all magic is [here](https://github.com/Ebazhanov/Ashot-Selenide/blob/master/src/test/groovy/com/tests/SimpleExamples.groovy)

* How it can be in your tests [here](https://github.com/Ebazhanov/Ashot-Selenide/blob/master/src/test/groovy/com/tests/DifferentDevices.groovy)

#### How to run:

`$ clean test`

`$ allureServe`

> **Default browser:** Chrome


# How you can update screenshots for Visual tests

-	to run failed tests by running `your testNG ... .xml` suit
-	search for the results in `//Resources/ActualScreenshot.png` folder, there should be appeared screenshot with `Actual ... png` in the name
-	once you found your screenshot > exchange part of the name from `Actual` to `Original`
-	if you still get screenshot with `Difference ... .png` double check maybe you have missed ignoreElement


The cool thing is the tests are 100% stable

FYI if you want to try something else look at the video [youtube](https://www.youtube.com/watch?v=ydK36JRiMOw)
