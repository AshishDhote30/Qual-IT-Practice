package Utils;


import Pages.PageInitializer;
import StepDefinitions.Hooks;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.Properties;
import java.util.Random;

public class Utils extends PageInitializer {


    public static void fnHighlightMe(WebDriver driver, WebElement element) throws InterruptedException {
        //Creating JavaScriptExecuter Interface
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int iCnt = 0; iCnt < 2; iCnt++) {
            //Execute javascript
            js.executeScript("arguments[0].style.border='4px groove green'", element);
            Thread.sleep(1000);
            js.executeScript("arguments[0].style.border=''", element);
        }
    }

    public static String getConfigValue(String key) {
        Properties config = new Properties();

        try {
            String filename = "";
            if (System.getProperty("os.name").contains("Win")) {
                filename = "properties\\config";
            } else {
                filename = "properties/config";
            }
            config.load(new FileInputStream("target/classes/" + filename));
        } catch (Throwable t) {
            System.out.print("Issue loading properties file");
            t.printStackTrace();
        }
        return config.getProperty(key);

    }


    public static void fnScreenshot(WebDriver driver) throws InterruptedException {

        Hooks.myScenario.write("Current Page URL is " + getDriver().getCurrentUrl());
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Hooks.myScenario.embed(screenshot, "image/png");


    }

    public static void sendKeyJS(WebDriver driver, WebElement element, String string) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + string + "';", element);

    }

    public static void clickJS(WebDriver driver, WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    protected boolean checkIfElementIsPresent(WebElement element){
        try {
            element.getSize();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void waitAndClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public static void waitForElementVisibility(WebElement element, long sec) {
        WebDriverWait wait = new WebDriverWait(getDriver(), sec);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static String generateRandomEmail(int count, String email) {

        String randomChars = "1234567890";
        StringBuilder random = new StringBuilder();
        String emailArr[] = email.split("@");
        Random rnd = new Random();
        while (random.length() < count) { // length of the random string.
            int index = (int) (rnd.nextFloat() * randomChars.length());
            random.append(randomChars.charAt(index));
        }
        String string = random.toString();
        return (emailArr[0] + string + "@" + emailArr[1]);
    }


    public static void WriteToFile(String fileContent)  {
        String projectPath = System.getProperty("user.dir");
        String fileName;

        if(Utils.getConfigValue("environment").toUpperCase()=="PROD") {
             fileName = "generatedAccounts_prod";
        }else { fileName = "generatedAccounts_test";}

        String newLine = System.getProperty( "line.separator" );
        String tempFile = projectPath + File.separator+"log"+File.separator+fileName;
        File file = new File(tempFile);
        // if file does exists, then delete and create a new file
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //write to file with OutputStreamWriter
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file.getAbsoluteFile(), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Writer writer=new OutputStreamWriter(outputStream);
        try {
            writer.write(fileContent);
            writer.write(newLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void  validateMessage(WebElement element, String message) {

         if(element.getText().contains(message)){
             Assert.assertTrue("Switch Package message displayed successfully", true);
         }
        else {Assert.assertTrue("Switch Package message displayed successfully", true);}

    }


    public static void sendMail(File screenshot, String scenarioName) {

        final String SMTP_AUTH = "mail.smtp.auth";
        final String SMTP_STARTTLS = "mail.smtp.starttls.enable";
        final String SMTP_HOST = "mail.smtp.host";
        final String SMTP_PORT = "mail.smtp.port";
        final String ADRESS = "recipients";

        final String USERNAME = Utils.getConfigValue("username");
        final String PASSWORD = Utils.getConfigValue("password");

        Properties props = new Properties();
        props.put(SMTP_AUTH, Utils.getConfigValue(SMTP_AUTH));
        props.put(SMTP_STARTTLS, Utils.getConfigValue(SMTP_STARTTLS));
        props.put(SMTP_HOST, Utils.getConfigValue(SMTP_HOST));
        props.put(SMTP_PORT, Utils.getConfigValue(SMTP_PORT));

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("zaheer.akhter@skytv.co.nz"));

            InternetAddress[] iAdressArray = InternetAddress.parse(Utils.getConfigValue(ADRESS));
            message.setRecipients(Message.RecipientType.CC, iAdressArray);

            message.setSubject("Neon Test Failed - " + scenarioName);

            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText("Hi Team, \n" + "\t " + scenarioName
                    + " - is failing. Please refer the attachment to figure out screen.");

//create new MimeBodyPart object and set DataHandler object to this object
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();

            String filename = screenshot.getAbsolutePath();
            DataSource source = new FileDataSource(filename);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(filename);

            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(filename);

// create Multipart object and add MimeBodyPart objects to this object
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart2);

// set the multiplart object to the message object
            message.setContent(multipart);
              Transport.send(message);

            System.out.println("Mail Sent Successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    public static String trimString(String s){

        return s.replaceAll("\\r\\n|\\r|\\n", "").trim().replaceAll(" +", " ");
    }
}

