package com.hady.automation;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

//public class FormSubmissionAutomation {
//
//    public static void main(String[] args) {
//        // Set the path to the chromedriver executable
//        System.setProperty("webdriver.chrome.driver", "/home/aariyan/PseudoCode/Library/chromedriver_linux64/chromedriver");
//
//        // Create a new instance of the Chrome driver
//        WebDriver driver = new ChromeDriver();
//
//        driver.get("https://orgbd.net/Account/Login");
//        WebElement userEmail = driver.findElement(By.name("UserName"));
//        userEmail.sendKeys("hady@rokomari.com");
//
//        WebElement userPass = driver.findElement(By.name("Password"));
//        userPass.sendKeys("Hady#123");
//
//
//
//        WebElement logInBtn = driver.findElement(By.name("Submit"));
//        logInBtn.click();
//        // Navigate to your website
//        driver.get("https://orgbd.net/Student/Admission/NewStudentAdmission");
//
//        // Data of 100 students (this is just an example with one student)
//        String[][] studentsData = {
//                {"John Doe"},
//                // ... add the rest of the students here
//        };
//
//        String[] array = {"Male", "Female"};
//
//        // Loop through the students data and fill the form
//        for (String[] student : studentsData) {
//            WebElement nameInput = driver.findElement(By.name("Name"));
//            nameInput.sendKeys(student);
//
//            WebElement phoneNumber = driver.findElement(By.name("MobNumber"));
//            phoneNumber.sendKeys("01732394777");
//
//            WebElement gender = driver.findElement(By.name("Gender"));
//            gender.sendKeys(array[0]);
//
//            WebElement religion = driver.findElement(By.name("Religion"));
//            religion.sendKeys("Islam");
//
//            WebElement classField = driver.findElement(By.name("StudentClass"));
//            classField.sendKeys("Model Test");
//
//            WebElement program = driver.findElement(By.id("Program"));
//            program.click();
//            program.sendKeys("Utkorsho SSC Final Preparation & Model Test");
//            program.click();
//
//            //program.sendKeys("187");
//
//            WebElement session = driver.findElement(By.name("Session"));
//            session.click();
//            session.sendKeys("2024");
//            session.click();
//
//
//            WebElement institution = driver.findElement(By.name("LastInstituteName"));
//            institution.click();
//            institution.sendKeys("NOTRE DAME");
//
//
//
//            institution.sendKeys("DAME COLLEGE MYMENSINGH [137031]");
//            institution.click();
//
//
//
//            WebElement version = driver.findElement(By.name("VersionOfStudy"));
//            version.sendKeys("Bangla");
//
//            WebElement branch = driver.findElement(By.name("OnlineBranch"));
//            branch.sendKeys("Online Branch");
//
//            WebElement ab = driver.findElement(By.name("Branch"));
//            //ab.click();
//            ab.sendKeys("Online Utkorsho");
//            //ab.click();
//
//            WebElement campus = driver.findElement(By.name("Campus"));
//            campus.click();
//            campus.sendKeys("Online Campus");
//            campus.click();
//
//
//
//            // nameInput.clear();
////            ageInput.clear();
////
////            nameInput.sendKeys(student[0]);
////            ageInput.sendKeys(student[1]);
////
////            if ("true".equals(student[2]) && !checkbox.isSelected()) {
////                checkbox.click();
////            } else if ("false".equals(student[2]) && checkbox.isSelected()) {
////                checkbox.click();
////            }
////
////            submitButton.click();
//
//            // Wait for a second before proceeding to the next student
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                throw new RuntimeException(e);
//            }
//        }
//
//        // Close the browser
//        //driver.quit();
//
//
//        /// Testing
//        // Initialize ChromeOptions
////        ChromeOptions options = new ChromeOptions();
////        // If you are using Chromium (not Chrome), uncomment the next line and set the path to your Chromium binary
////        // options.setBinary("/path/to/chromium");
////
////        // Create a new instance of the Chrome driver
////        WebDriver driver = new ChromeDriver(options);
////
////        // Navigate to Google's homepage
////        driver.get("https://www.google.com");
////
////        // Print the title of the page
////        System.out.println("Page title is: " + driver.getTitle());
////
////        // Close the browser
////        driver.quit();
//    }
//}

public class FormSubmissionAutomation {

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

            // Navigate to the student admission form page
            driver.get("https://orgbd.net/Student/Admission/NewStudentAdmission");

            for (UdvashAdmissionModel model: DataSet.BUNDLE_MODULE_1_listamount2500_30) {
                fillStudentForm(driver, wait, model);
                // Add a delay between form submissions if needed
                //Thread.sleep(1000);
                waitForReceiptAndNavigateBack(driver, wait);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            //driver.quit();
        }
    }

    private static void waitForReceiptAndNavigateBack(WebDriver driver, WebDriverWait wait) {
        // Wait for the receipt page URL which contains the ID
        wait.until(ExpectedConditions.urlMatches(".*GenerateMoneyReciept.*"));

        // Navigate back to the form page to start the next submission
        //driver.navigate().back();
        driver.get("https://orgbd.net/Student/Admission/NewStudentAdmission");

    }

    private static void fillStudentForm(WebDriver driver, WebDriverWait wait, UdvashAdmissionModel model) throws InterruptedException {
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


        Thread.sleep(1000);
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
// Submit the form
        driver.findElement(By.id("newAdmissionPaymentSubmitBtn")).click();
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

//    public static void selectReferrer(WebDriver driver, WebDriverWait wait) {
//        // Input the search query into the text field
//        WebElement referrerInputField = wait.until(ExpectedConditions.elementToBeClickable(By.id("ReferrerNameAutoComplete")));
//        referrerInputField.click();
//        referrerInputField.clear();
//        referrerInputField.sendKeys("694");
//
//        // Wait for the dropdown to appear
//        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.typeahead.dropdown-menu")));
//
//        // Click on the first item in the dropdown
//        WebElement firstItemInDropdown = dropdown.findElement(By.cssSelector("li a"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstItemInDropdown);
//    }
//
//    private static void setReferrerValue(WebDriver driver, String value, String text) {
//        // Use JavaScript to set the value of the autocomplete field and hidden input field
//        String script = "document.getElementById('ReferrerNameAutoComplete').value=arguments[0];" +
//                "document.getElementById('ReferrerName').value=arguments[1];";
//        ((JavascriptExecutor) driver).executeScript(script, text, value);
//
//        // Now trigger any change or selection events that the page relies on
//        script = "var input = document.getElementById('ReferrerNameAutoComplete');" +
//                "var event = new Event('change', { 'bubbles': true, 'cancelable': true });" +
//                "input.dispatchEvent(event);" +
//                "event = new Event('select', { 'bubbles': true, 'cancelable': true });" +
//                "input.dispatchEvent(event);";
//        ((JavascriptExecutor) driver).executeScript(script);
//    }
//
//    private static void selectAutocompleteOption(WebDriver driver, WebDriverWait wait, By inputFieldBy, String hint, String completeText) {
//        // Find the autocomplete input field and click on it to focus
//        WebElement inputField = driver.findElement(inputFieldBy);
//        inputField.click();
//
//        // Clear the field and send the search text
//        inputField.clear();
//        inputField.sendKeys(hint);
//
//        // Define a locator for the dropdown menu items
//        By optionsBy = By.xpath("//ul[@class='typeahead dropdown-menu']/li/a");
//
//        // Wait for the autocomplete options to appear
//        wait.until(ExpectedConditions.visibilityOfElementLocated(optionsBy));
//
//        // Find all the autocomplete options
//        List<WebElement> options = driver.findElements(optionsBy);
//
//        // Iterate through the options and click the one that matches the full text
//        for (WebElement option : options) {
//            // Use contains to find the option that has the text
//            if (option.getText().contains(completeText)) {
//                // Scroll the option into view
//                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
//                // Wait for the option to be clickable
//                wait.until(ExpectedConditions.elementToBeClickable(option));
//                option.click();
//                break;
//            }
//        }
//    }
//
//    private static void setDropdownValue(WebDriver driver, String value, String text) {
//        // Use JavaScript to set the value of the autocomplete field and hidden input field
//        String script = "document.getElementById('DiscountApprovedByAutoComplete').value=arguments[0];" +
//                "document.getElementById('DiscountApprovedBy').value=arguments[1];";
//        ((JavascriptExecutor) driver).executeScript(script, text, value);
//
//        // Now trigger any change or selection events that the page relies on
//        script = "var input = document.getElementById('DiscountApprovedByAutoComplete');" +
//                "var event = new Event('change', { 'bubbles': true, 'cancelable': true });" +
//                "input.dispatchEvent(event);" +
//                "event = new Event('select', { 'bubbles': true, 'cancelable': true });" +
//                "input.dispatchEvent(event);";
//        ((JavascriptExecutor) driver).executeScript(script);
//    }