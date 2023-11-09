package com.hady.automation;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CleanCode {
    private WebDriver driver;
    private WebDriverWait wait;

    public CleanCode(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    private void waitForReceiptAndNavigateBack() {
        // Wait for the receipt page URL which contains the ID
        wait.until(ExpectedConditions.urlContains("GenerateMoneyReciept"));

        // Navigate back to the form page to start the next submission
        driver.navigate().to("https://orgbd.net/Student/Admission/NewAdmission");
    }

    public void submitAllStudentsData() {
        for (UdvashAdmissionModel model : DataSet.BUNDLE_MODULE_1_listamount2501_1) {
            try {
                fillStudentForm(model);
                // Click the submit button
                driver.findElement(By.id("newAdmissionPaymentSubmitBtn")).click();
                waitForReceiptAndNavigateBack();
            } catch (Exception e) {
                System.err.println("An error occurred while submitting form for student: " + e.toString());
                e.printStackTrace();
                // Log error and continue with the next student or handle accordingly
            }
        }
    }

    private void fillStudentForm(UdvashAdmissionModel model) throws InterruptedException {

        System.out.println("DRIVER: "+driver); // Should not print 'null'

        WebElement nameInput = driver.findElement(By.name("Name"));
        nameInput.sendKeys(model.getName());

        WebElement phoneNumber = driver.findElement(By.name("MobNumber"));
        phoneNumber.sendKeys(model.getNumber());

        Select genderDropdown = new Select(driver.findElement(By.name("Gender")));
        genderDropdown.selectByVisibleText("Male");

        Select religionDropdown = new Select(driver.findElement(By.name("Religion")));
        religionDropdown.selectByVisibleText("Islam");

        WebElement classField = driver.findElement(By.name("StudentClass"));
        classField.sendKeys("Ten");

        // Assuming "Program" is a select dropdown
        WebElement program = driver.findElement(By.id("Program"));
        program.click();
        //program.sendKeys("Utkorsho SSC Final Preparation & Model Test");
        program.sendKeys("Utkorsho Free Class");
        program.click();

        WebElement sessionDropdown = driver.findElement(By.id("Session"));
        sessionDropdown.click();
        sessionDropdown.sendKeys("2024");
        sessionDropdown.click();

        selectAutocompleteOption(driver, wait, By.name("LastInstituteName"), "137031","NOTRE DAME COLLEGE MYMENSINGH [137031]");

        Select versionDropdown = new Select(driver.findElement(By.name("VersionOfStudy")));
        versionDropdown.selectByVisibleText("Bangla"); /// Till This Everything is working

        /**
         This is for Utkorsho SSC Final Preparation and model test
         */
//        Select branchDropdown = new Select(driver.findElement(By.name("OnlineBranch")));
//        branchDropdown.selectByVisibleText(student[8]);
//
//        Select campusDropdown = new Select(driver.findElement(By.name("Campus")));
//        campusDropdown.selectByVisibleText(student[9]);


        /**
         This is for Utkorsho SSC Final Preparation and model test
         */
        // Assuming student[8] is the text for the branch you want to select.
//        WebElement branchDropdown = driver.findElement(By.name("OnlineBranch"));
//        branchDropdown.click(); // to expand the dropdown
//        branchDropdown.sendKeys(student[8]); // to filter or input the text
//        branchDropdown.sendKeys(Keys.RETURN); // to select the filtered option

        /**
         This is for Utkorsho SSC Final Preparation and model test
         */
//        Select branchD = new Select(driver.findElement(By.id("Branch")));
//        branchD.selectByVisibleText("Online Utkorsho");


        WebElement branchDropdown = driver.findElement(By.name("Branch"));
        branchDropdown.click(); // to expand the dropdown
        branchDropdown.sendKeys("Online Utkorsho"); // to filter or input the text
        branchDropdown.sendKeys(Keys.RETURN);

//        Select campusDropdown = new Select(driver.findElement(By.id("Campus")));
//        campusDropdown.selectByVisibleText("Online Campus");

        WebElement campusDropdown = driver.findElement(By.id("Campus"));
        campusDropdown.click();
        campusDropdown.sendKeys("Online Campus");
        campusDropdown.click();
/**
 This is for Utkorsho SSC Final Preparation and model test
 */
        // Find the checkbox using one of its attributes
//        WebElement checkBox = driver.findElement(By.cssSelector("input.course-name-check[data-course-id='1351']"));
//        if (!checkBox.isSelected()) {
//            checkBox.click();
//        }


        WebElement checkBox = driver.findElement(By.cssSelector("input.course-name-check[data-course-id='1363']"));
        if (!checkBox.isSelected()) {
            checkBox.click();
        }

        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nextButton = w.until(ExpectedConditions.elementToBeClickable(By.id("newAdmissionNextBtn")));

        nextButton.click();


        //Thread.sleep(1000);
        WebElement discountAmount = driver.findElement(By.name("spDiscountAmount"));
        discountAmount.sendKeys("0");

//        WebElement note = driver.findElement(By.name("referrerenceNote"));
//        note.sendKeys("Migration From Utkorsho");

        WebElement receivableAmount = driver.findElement(By.name("receivedAmount"));
        receivableAmount.sendKeys("0");

//        By discountApprovedByInput = By.id("DiscountApprovedByAutoComplete");
//        String searchText = "694"; // Replace with your search text
//        setDropdownValue(driver, "694", "8801708166087 - (0694) - Ratul");
//
//
//        Select type = new Select(driver.findElement(By.name("RefererList")));
//        type.selectByVisibleText("Team Member");
//
//        //selectReferrer(driver, wait, "3979", "3979 - Hady - 8801321143477");
//        setReferrerValue(driver, "3979","3979 - Hady - 8801321143477");



    }


    public static void main(String[] args) {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "/home/aariyan/PseudoCode/Library/chromedriver_linux64/chromedriver");

        // Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();

        // Define a wait that can be reused for elements to be ready
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://orgbd.net/Account/Login");
            WebElement userEmail = driver.findElement(By.name("UserName"));
            userEmail.sendKeys("hady@rokomari.com");

            WebElement userPass = driver.findElement(By.name("Password"));
            userPass.sendKeys("Hady#123");

            WebElement logInBtn = driver.findElement(By.name("Submit"));
            logInBtn.click();

//            // Navigate to the student admission form page
//            driver.get("https://orgbd.net/Student/Admission/NewStudentAdmission");
//
//            // Example student data
//            String[][] studentsData = {
//                    //{"John Doe", "01732394777", "Male", "Islam", "Model Test", "2024", "NOTRE DAME COLLEGE MYMENSINGH [137031]", "Bangla", "Online Branch", "Online Campus"},
//                    {"John Doe", "01732394777", "Male", "Islam", "Ten", "2024", "NOTRE DAME COLLEGE MYMENSINGH [137031]", "Bangla", "Online Branch", "Online Campus"},
//                    // ... add the rest of the students here
//            };
//
//            for (String[] student : studentsData) {
//                fillStudentForm(driver, wait, student);
//                // Add a delay between form submissions if needed
//                Thread.sleep(1000);
//            }

            new CleanCode(driver).submitAllStudentsData();


        } catch (Exception e) {
            System.out.println("MAIN EXCEPTION: "+e.toString());
            e.printStackTrace();
        } finally {
            // Close the browser
            //driver.quit();
        }
    }


    private static void selectAutocompleteOption(WebDriver driver, WebDriverWait wait, By inputFieldBy, String hint, String completeText) {
        // Find the autocomplete input field and click on it to focus
        WebElement inputField = driver.findElement(inputFieldBy);
        inputField.click();

        // Clear the field and send the search text
        inputField.clear();
        inputField.sendKeys(hint);

        // Define a locator for the dropdown menu items
        By optionsBy = By.xpath("//ul[@class='typeahead dropdown-menu']/li/a");

        // Wait for the autocomplete options to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionsBy));

        // Find all the autocomplete options
        List<WebElement> options = driver.findElements(optionsBy);

        // Iterate through the options and click the one that matches the full text
        for (WebElement option : options) {
            // Use contains to find the option that has the text
            if (option.getText().contains(completeText)) {
                // Scroll the option into view
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
                // Wait for the option to be clickable
                wait.until(ExpectedConditions.elementToBeClickable(option));
                option.click();
                break;
            }
        }
    }

}