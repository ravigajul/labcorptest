# QA Automation Coding Test

## Overview

This repository contains the implementation of the QA Automation test solution based on the requirements provided. The test project was developed using Selenium with C# and SpecFlow or Java with Selenium and Cucumber.

The main goal of the test is to navigate through [www.labcorp.com](https://www.labcorp.com), specifically from the Careers page to a job listing, confirm specific details from the job posting, and then submit the application. The test ensures the job title, job location, job ID, and other critical details match the expected values.

The solution also covers API testing using **REST Assured** to perform both GET and POST requests to external endpoints, validating the response body and headers.

## Test Solution Requirements

### Web Automation Test

1. **Start the test** by opening the Chrome browser and navigating to [www.labcorp.com](https://www.labcorp.com).
2. **Find and click** the "Careers" link on the homepage.
3. **Search for an active position** (e.g., "QA Test Automation Developer") from the available jobs.
4. **Select and browse to the position**.
5. **Assertions**:
   - Job Title
   - Job Location
   - Job ID
   - Additional assertions of your choice (e.g., text in the requirements/description)
     - Example: Confirm the first sentence of the third paragraph under Description/Introduction: `"The right candidate for this role will participate in the test automation technology development and best practice models."`
     - Confirm the second bullet point under Management Support: `"Prepare test plans, budgets, and schedules."`
     - Confirm the third requirement: `"5+ years of experience in QA automation development and scripting."`
     - Confirm that the first suggested automation tool contains "Selenium".
6. **Click "Apply Now"** and verify:
   - Job Title
   - Job Location
   - Job ID
   - Another text of your choice to match the previous page.
7. **Click to return** to the job search page.

### API Automation Test (REST Assured)

1. **GET Request**:
   - Endpoint: `https://echo.free.beeceptor.com/sample-request?author=beeceptor`
   - Validation Requirements: Validate the response includes the fields such as `path`, `ip`, and all headers.

2. **POST Request**:
   - Endpoint: `http://echo.free.beeceptor.com/sample-request?author=beeceptor`
   - Validation Requirements: Verify the accuracy of customer information, payment details, and product information.
   - Payload:

```json
{
  "order_id": "12345",
  "customer": {
    "name": "Jane Smith",
    "email": "janesmith@example.com",
    "phone": "1-987-654-3210",
    "address": {
      "street": "456 Oak Street",
      "city": "Metropolis",
      "state": "NY",
      "zipcode": "10001",
      "country": "USA"
    }
  },
  "items": [
    {
      "product_id": "A101",
      "name": "Wireless Headphones",
      "quantity": 1,
      "price": 79.99
    },
    {
      "product_id": "B202",
      "name": "Smartphone Case",
      "quantity": 2,
      "price": 15.99
    }
  ],
  "payment": {
    "method": "credit_card",
    "transaction_id": "txn_67890",
    "amount": 111.97,
    "currency": "USD"
  },
  "shipping": {
    "method": "standard",
    "cost": 5.99,
    "estimated_delivery": "2024-11-15"
  },
  "order_status": "processing",
  "created_at": "2024-11-07T12:00:00Z"
}