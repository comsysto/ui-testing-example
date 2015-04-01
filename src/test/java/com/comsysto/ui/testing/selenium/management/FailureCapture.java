package com.comsysto.ui.testing.selenium.management;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FailureCapture {

    private final static String REPORTS_PATH = "target/selenium-reports/";

    private final ITestResult result;
    private WebDriver webDriver;
    private final File surefireReports;

    public FailureCapture(ITestResult result, WebDriver webDriver) {
        this.result = result;
        this.webDriver = webDriver;

        surefireReports = new File(REPORTS_PATH);
        surefireReports.mkdirs();

    }

    public void createScreenshot() {
        File pictureFile = new File(surefireReports, createFileName(result, "failure.", ".png"));
        System.out.println("Writing test failed screenshot at " + pictureFile.getAbsolutePath());

        try (FileOutputStream out = new FileOutputStream(pictureFile)) {
            out.write(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES));
        } catch (IOException e) {
            System.out.println("Screenshot couldn't been saved: " + e.toString());
        }
    }

    public void createHtmlOutput() {

        File htmlFile = new File(surefireReports, createFileName(result, "failure.",  ".html"));
        System.out.println("Writing test failed html output at " + htmlFile.getAbsolutePath());

        try (FileOutputStream out = new FileOutputStream(htmlFile)) {
            out.write(webDriver.getPageSource().getBytes());
        } catch (IOException e) {
            System.out.println("Html output couldn't been saved: " + e.toString());
        }
    }

    private String createFileName(ITestResult result, String prefix, String fileType) {

        String methodName = result.getMethod().getMethodName();
        String fileName = prefix + methodName;

        // cut, so screenshotName isn't too long
        int maxNameLength = 150 - REPORTS_PATH.length() - fileType.length();

        if (fileName.length() > maxNameLength) {
            fileName = fileName.substring(0, maxNameLength) + "[...]";
        }

        return fileName + fileType;
    }
}
