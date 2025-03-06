Feature: API Testing with REST Assured and TestNG

  Scenario: Validate GET Request Response
    Given I send a GET request to "https://echo.free.beeceptor.com/sample-request?author=beeceptor"
    Then The response status code should be 200
    And The response should contain "path", "ip", and "headers"

  Scenario: Validate POST Request with Order Data
    Given I send a POST request to "http://echo.free.beeceptor.com/sample-request?author=beeceptor" with the following data:
      | order_id      | 12345 |
      | customer_name | Jane Smith |
      | email         | janesmith@example.com |
      | phone         | 1-987-654-3210 |
      | street        | 456 Oak Street |
      | city          | Metropolis |
      | state         | NY |
      | zipcode       | 10001 |
      | country       | USA |
      | product1_id   | A101 |
      | product1_name | Wireless Headphones |
      | quantity1     | 1 |
      | price1        | 79.99 |
      | product2_id   | B202 |
      | product2_name | Smartphone Case |
      | quantity2     | 2 |
      | price2        | 15.99 |
      | payment_method | credit_card |
      | transaction_id | txn_67890 |
      | amount        | 111.97 |
      | currency      | USD |
      | shipping_method | standard |
      | shipping_cost | 5.99 |
      | estimated_delivery | 2024-11-15 |
      | order_status  | processing |
      | created_at    | 2024-11-07T12:00:00Z |
    Then The response status code should be 200
    And The response should match the sent order details
    And The response body should match the sent order details