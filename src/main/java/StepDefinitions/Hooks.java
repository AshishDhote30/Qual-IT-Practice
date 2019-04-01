package StepDefinitions;

import Pages.PageInitializer;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class Hooks extends PageInitializer {

    public static Scenario myScenario;
    //public static boolean isNewFeature = false;
    //Result result;


   /* @Before
    public void start(Scenario scenario) {
        Setup();
        DOMConfigurator.configure("log4j.xml");
        Log.info("Logs Initiated");

        // For App dynamics

        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
        String rawFeatureName = scenario.getId().split(";")[0].replace("-", " ").replace("service tests", "");

        String runningTestFeatureName = rawFeatureName.substring(0, 1).toUpperCase() + rawFeatureName.substring(1).trim();
        if (GenericFunctions.CurrentTestResult.getProjectName() == null || GenericFunctions.CurrentTestResult.getProjectName().equals("")) {
            System.out.println("Starting Test Execution.");
            isNewFeature = true;
        } else if (GenericFunctions.CurrentTestResult.getProjectName().equals(runningTestFeatureName)) {
            isNewFeature = false;
        } else {
            isNewFeature = true;
        }

        if (isNewFeature) {
            GenericFunctions.CurrentTestResult = new ReportTestResult();
            GenericFunctions.CurrentTestResult.setProjectId(GenericFunctions.DetailedProjectId);
            GenericFunctions.CurrentTestResult.setProjectName(runningTestFeatureName);
            GenericFunctions.CurrentTestResult.setPlatform(GenericFunctions.TestPlatform.toUpperCase());
            GenericFunctions.CurrentTestResult.setEnvironment(GenericFunctions.TestEnvironment.toUpperCase());
            GenericFunctions.CurrentFeatureStartTime = new Date();
            GenericFunctions.CurrentTestResult.setStartTime(DateFormat.format(GenericFunctions.CurrentFeatureStartTime));
            GenericFunctions.CurrentTestResult.setTotalTestCount(1);
            GenericFunctions.CurrentTestResult.setFailTestCount(0);
            GenericFunctions.CurrentTestResult.setPassTestCount(0);
            GenericFunctions.TestResults.add(GenericFunctions.CurrentTestResult);
        } else {
            GenericFunctions.CurrentTestResult.setTotalTestCount(GenericFunctions.CurrentTestResult.getTotalTestCount() + 1);
        }

       //  BaseClass.currentScenario = scenario.getName();
         // System.out.println("Executing Test Scenario: " + BaseClass.currentScenario);
        GenericFunctions.FullTestCount += 1;
    }
*/

    @Before
    public void beforeScenario(Scenario scenario) {

        Setup();
        myScenario = scenario;
    }

    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario ) {

        WebDriver driver = getDriver();

        // File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         //Utils.sendMail(scrFile, scenario.getName());

     try {
            scenario.write("Current Page URL is " + getDriver().getCurrentUrl());
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }


       /* if(!scenario.isFailed() && NeonSignUpPage.getUniqueEmail()!=""){

            Utils.WriteToFile( scenario.getName()+ "=" + NeonSignUpPage.getUniqueEmail());
            NeonSignUpPage.clearUniqueEmail();
        }*/
/*

        if (getDriver().toString().contains("Remote")) {
            String jobID = ((RemoteWebDriver) driver).getSessionId().toString();
            SauceREST client = new SauceREST("zaheerakhter", "0e5f59a1-fc53-4f13-9d90-ccbaecce7a15");
            Map<String, Object> sauceJob = new HashMap<String, Object>();
            sauceJob.put("name", "Test method: " + scenario.getName() + " " + "Browser: " + getBrowserName());

            if (scenario.isFailed()) {
                //TestUtils.takeScreenshot(scenario.getName() + "_Failed_" + LocalDateTime.now());
                client.jobFailed(jobID);
            } else {
                client.jobPassed(jobID);
            }
            client.updateJobInfo(jobID, sauceJob);
        }

        if (scenario.isFailed()) {
            GenericFunctions.FailTestCount += 1;
            GenericFunctions.CurrentTestResult.setFailTestCount(GenericFunctions.CurrentTestResult.getFailTestCount() + 1);
        }
        else {
            GenericFunctions.PassTestCount += 1;
            GenericFunctions.CurrentTestResult.setPassTestCount(GenericFunctions.CurrentTestResult.getPassTestCount() + 1);
        }
        GenericFunctions.CurrentTestResult.setRunStatus("Total: " + GenericFunctions.CurrentTestResult.getTotalTestCount() + " | Pass: " + GenericFunctions.CurrentTestResult.getPassTestCount() + " | Fail: " + GenericFunctions.CurrentTestResult.getFailTestCount());
        GenericFunctions.CurrentTestResult.setRunDuration((new Date().getTime() - GenericFunctions.CurrentFeatureStartTime.getTime()) / 1000);
        GenericFunctions.TestResults.set((GenericFunctions.TestResults.size()-1), GenericFunctions.CurrentTestResult);
*/


        driver.quit();

    }

}
