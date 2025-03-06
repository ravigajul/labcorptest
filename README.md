# labcorptest
QA Automation Coding Test 
Once complete please upload the test project(s) on GitHub for review.
Also, ensure the project can be run during the next interview session. Questions will be based on the implementation of the Test Solution(s).
Develop a test method in Selenium to browse from www.labcorp.com through the Careers link to a specific LabCorp job listing confirming information on the job posting. 
The project should be written in C# along with Selenium and Specflow (or) Java, Selenium with Cucumber
The structure of the test should be written in BDD format as well. 
Include the proper wait methods, to at least three different “By” types when searching elements, and that it is tested in Chrome. 
Please be sure to accomplish the following tasks:
Start the test by opening a browser to www.labcorp.com 
Find and click Careers link
Search for any position (that is currently active on the site) example “QA Test Automation Developer” 
Select and browse to the position 
Add assertions to confirm
Job Title
Job Location
Job Id 
Any 3 other assertions of your choice (could be text in the requirements / introduction)
Example: (Text in quotes “” from below example should be updated by candidate to match that in the position chosen for the automation solution):

Confirm first sentence of third paragraph under Description/Introduction as “The right candidate for this role will participate in the test automation technology development and best practice models.” 
Confirm second bullet point under Management Support as “Prepare test plans, budgets, and schedules.” 
Confirm third requirement as “5+ years of experience in QA automation development and scripting.” 
Confirm first suggested automation tool to be familiar with contains “Selenium” 
Click Apply Now and confirm Job Title, Job Location, Job ID and another text of choice to match the previous page in the proceeding page. 
Click to Return to Job Search


Automate the below using REST Assured. Use Java with Selenium and Cucumber. The structure of the test should be written in BDD format as well.
GET Request
Endpoint: Use the following URL to retrieve the response: https://echo.free.beeceptor.com/sample-request?author=beeceptor
Validation Requirements: Ensure the response includes and validates fields such as path, ip, and all headers.
POST Request
Endpoint: Use the following URL to send the POST request: http://echo.free.beeceptor.com/sample-request?author=beeceptor
Validation Requirements: Verify the accuracy of customer information, payment details, and product information.
Payload:
{
  "order_id": "12345",
  "customer": {
    "name": "Jane Smith",
    "email": janesmith@example.com,
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