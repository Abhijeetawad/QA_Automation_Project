package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReports() {

        if (extent == null) {

            // Create folder if it doesn't exist
            String reportFolder = System.getProperty("user.dir") + "/test-output/ExtentReports";
            File folder = new File(reportFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Path for HTML report
            String reportPath = reportFolder + "/ExtentReport.html";

            // Create SparkReporter
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setReportName("QA Automation Report");
            sparkReporter.config().setDocumentTitle("Automation Test Results");

            // Set colorful theme
            sparkReporter.config().setTheme(Theme.DARK);  // Change to Theme.STANDARD for light theme

            // Create ExtentReports object
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Add system info
            extent.setSystemInfo("Project", "QA Automation Project");
            extent.setSystemInfo("Tester", "Abhijeet Awad");
            extent.setSystemInfo("Environment", "QA");
        }

        return extent;
    }
}
