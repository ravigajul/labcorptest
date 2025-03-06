package com.labcorp.stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class LabCorpJobSearchSteps {
    WebDriver driver;
    WebDriverWait wait;

    String jobTitle, jobLocation, jobId, jobDesc, jobExperience, qualification, originalWindowHandle;

    @Given("I open the LabCorp website")
    public void i_open_the_labcorp_website() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();

        driver.get("https://www.labcorp.com/");
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(),
                "Lab Diagnostics & Drug Development, Global Life Sciences Leader | Labcorp");
    }

    @When("I navigate to the Careers page")
    public void i_navigate_to_the_careers_page() {
        WebElement careersLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Careers")));
        careersLink.click();
    }

    @When("I search for {string}")
    public void i_search_for(String jobKeyword) {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("typehead")));
        searchBox.sendKeys(jobKeyword);

        WebElement searchButton = driver.findElement(By.cssSelector("button#ph-search-backdrop"));
        searchButton.click();
    }

    @When("I select the first job from the results")
    public void i_select_the_first_job_from_the_results() {
        List<WebElement> jobList = wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.cssSelector("span[data-ph-id='ph-page-element-page11-Zk12Zp']")));
        Assert.assertTrue(jobList.size() > 0, "No job listings found!");

        WebElement firstJob = jobList.get(0);
        jobTitle = firstJob.getText();
        firstJob.click();
    }

    @Then("I validate the job details")
    public void i_validate_the_job_details() {
        jobLocation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".job-location")))
                .getText();
        jobTitle = driver.findElement(By.cssSelector("h1.job-title")).getText();
        jobId = driver.findElement(By.xpath("//b[contains(text(), 'Job ID')]")).getText();

        // Capture additional job details
        jobDesc = driver.findElement(By.xpath("(//p)[3]")).getText();
        jobExperience = driver
                .findElement(By.cssSelector(
                        ".job-page-external .col-lg-8 section:nth-child(3) .job-description .jd-info p:nth-child(6)"))
                .getText();
        qualification = driver
                .findElement(By.cssSelector(
                        ".job-page-external .col-lg-8 section:nth-child(3) .job-description .jd-info p:nth-child(8)"))
                .getText();

        // Assertions
        Assert.assertNotNull(jobTitle, "Job Title is missing!");
        Assert.assertNotNull(jobLocation, "Job Location is missing!");
        Assert.assertNotNull(jobId, "Job ID is missing!");
        Assert.assertTrue(jobDesc.contains("Experience in functional testing of web-based applications"),
                "Job description mismatch!");
        Assert.assertTrue(jobExperience.contains("5-7 years"), "Job Experience mismatch!");
        Assert.assertTrue(qualification.contains("B.Tech"), "Qualification mismatch!");
    }

    @Then("I click Apply Now and confirm details match")
    public void i_click_apply_now_and_confirm_details_match() {
        WebElement applyNowButton = wait
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.job-bottom-action a")));

        // Scroll into view a
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", applyNowButton);

        // capturing originalWindowHandle
        originalWindowHandle = driver.getWindowHandle();

        // Click using JavaScript to avoid element click interception issue
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", applyNowButton);

        // Switch to new tab
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        // Reconfirm details on Apply page
        String applyJobTitle = driver.getTitle();
        System.out.println(driver.getTitle());

        Assert.assertEquals(applyJobTitle, "Workday", "Title mismatch");

    }

    @Then("I return to Job Search")
    public void i_return_to_job_search() {
        driver.switchTo().window(originalWindowHandle);
        driver.quit();
    }
}