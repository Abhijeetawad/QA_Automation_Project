package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReports() {

        if (extent == null) {

            String reportPath = System.getProperty("user.dir")
                    + "\\test-output\\ExtentReport.html";

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(reportPath);

            sparkReporter.config().setReportName("QA Automation Report");
            sparkReporter.config().setDocumentTitle("Automation Test Results");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Project", "QA Automation Project");
            extent.setSystemInfo("Tester", "Abhijeet Awad");
            extent.setSystemInfo("Environment", "QA");
        }

        return extent;
    }
}
