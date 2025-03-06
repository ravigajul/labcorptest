package com.labcorp.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;
import com.labcorp.utils.RestUtils;
import io.cucumber.datatable.DataTable;
import java.util.Map;

public class ApiSteps {
    private Response response;
    private String requestBody;

    @Given("I send a GET request to {string}")
    public void sendGetRequest(String url) {
        response = RestUtils.getRequest(url);
    }

    @Then("The response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
    }

    @Then("The response should contain {string}, {string}, and {string}")
    public void validateResponseFields(String field1, String field2, String field3) {
        Assert.assertTrue(response.jsonPath().getMap("$").containsKey(field1));
        Assert.assertTrue(response.jsonPath().getMap("$").containsKey(field2));
        Assert.assertTrue(response.jsonPath().getMap("$").containsKey(field3));
    }

    @Given("I send a POST request to {string} with the following data:")
    public void sendPostRequest(String url, DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        // Construct JSON payload
        requestBody = "{\n" +
                "  \"order_id\": \"" + data.get("order_id") + "\",\n" +
                "  \"customer\": {\n" +
                "    \"name\": \"" + data.get("customer_name") + "\",\n" +
                "    \"email\": \"" + data.get("email") + "\",\n" +
                "    \"phone\": \"" + data.get("phone") + "\",\n" +
                "    \"address\": {\n" +
                "      \"street\": \"" + data.get("street") + "\",\n" +
                "      \"city\": \"" + data.get("city") + "\",\n" +
                "      \"state\": \"" + data.get("state") + "\",\n" +
                "      \"zipcode\": \"" + data.get("zipcode") + "\",\n" +
                "      \"country\": \"" + data.get("country") + "\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"product_id\": \"" + data.get("product1_id") + "\",\n" +
                "      \"name\": \"" + data.get("product1_name") + "\",\n" +
                "      \"quantity\": " + data.get("quantity1") + ",\n" +
                "      \"price\": " + data.get("price1") + "\n" +
                "    },\n" +
                "    {\n" +
                "      \"product_id\": \"" + data.get("product2_id") + "\",\n" +
                "      \"name\": \"" + data.get("product2_name") + "\",\n" +
                "      \"quantity\": " + data.get("quantity2") + ",\n" +
                "      \"price\": " + data.get("price2") + "\n" +
                "    }\n" +
                "  ],\n" +
                "  \"payment\": {\n" +
                "    \"method\": \"" + data.get("payment_method") + "\",\n" +
                "    \"transaction_id\": \"" + data.get("transaction_id") + "\",\n" +
                "    \"amount\": " + data.get("amount") + ",\n" +
                "    \"currency\": \"" + data.get("currency") + "\"\n" +
                "  }\n" +
                "}";

        response = RestUtils.postRequest(url, requestBody);
    }

    @Then("The response should match the sent order details")
    public void validatePostResponse() {
        // Extract the body from the response
        String responseBody = response.getBody().asString();

        // Validate the response body (order details)
        Assert.assertTrue(responseBody.contains("order_id"));
        Assert.assertTrue(responseBody.contains("customer"));
        Assert.assertTrue(responseBody.contains("items"));
        Assert.assertTrue(responseBody.contains("payment"));
    }

    @Then("The response body should match the sent order details")
    public void validatePostResponsedetails() {
        String expectedResponseBody = "{\n" +
                "  \"order_id\": \"12345\",\n" +
                "  \"customer\": {\n" +
                "    \"name\": \"Jane Smith\",\n" +
                "    \"email\": \"janesmith@example.com\",\n" +
                "    \"phone\": \"1-987-654-3210\",\n" +
                "    \"address\": {\n" +
                "      \"street\": \"456 Oak Street\",\n" +
                "      \"city\": \"Metropolis\",\n" +
                "      \"state\": \"NY\",\n" +
                "      \"zipcode\": \"10001\",\n" +
                "      \"country\": \"USA\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"product_id\": \"A101\",\n" +
                "      \"name\": \"Wireless Headphones\",\n" +
                "      \"quantity\": 1,\n" +
                "      \"price\": 79.99\n" +
                "    },\n" +
                "    {\n" +
                "      \"product_id\": \"B202\",\n" +
                "      \"name\": \"Smartphone Case\",\n" +
                "      \"quantity\": 2,\n" +
                "      \"price\": 15.99\n" +
                "    }\n" +
                "  ],\n" +
                "  \"payment\": {\n" +
                "    \"method\": \"credit_card\",\n" +
                "    \"transaction_id\": \"txn_67890\",\n" +
                "    \"amount\": 111.97,\n" +
                "    \"currency\": \"USD\"\n" +
                "  }\n" +
                "}";

        String responseBody = response.getBody().asString();

        // Validate exact match
        Assert.assertEquals(response.jsonPath().getString("parsedBody.order_id"), "12345");
        Assert.assertEquals(response.jsonPath().getString("parsedBody.customer.name"), "Jane Smith");
        Assert.assertEquals(response.jsonPath().getString("parsedBody.customer.email"), "janesmith@example.com");
        Assert.assertEquals(response.jsonPath().getString("parsedBody.customer.phone"), "1-987-654-3210");
        Assert.assertEquals(response.jsonPath().getString("parsedBody.customer.address.street"), "456 Oak Street");
        Assert.assertEquals(response.jsonPath().getString("parsedBody.customer.address.city"), "Metropolis");
        Assert.assertEquals(response.jsonPath().getString("parsedBody.customer.address.state"), "NY");
        Assert.assertEquals(response.jsonPath().getString("parsedBody.customer.address.zipcode"), "10001");
        Assert.assertEquals(response.jsonPath().getString("parsedBody.customer.address.country"), "USA");

        Assert.assertEquals(response.jsonPath().getString("parsedBody.items[0].product_id"), "A101");
        Assert.assertEquals(response.jsonPath().getString("parsedBody.items[0].name"), "Wireless Headphones");
        Assert.assertEquals(response.jsonPath().getInt("parsedBody.items[0].quantity"), 1);
        Assert.assertEquals(response.jsonPath().getFloat("parsedBody.items[0].price"), 79.99, 0.01);

        Assert.assertEquals(response.jsonPath().getString("parsedBody.items[1].product_id"), "B202");
        Assert.assertEquals(response.jsonPath().getString("parsedBody.items[1].name"), "Smartphone Case");
        Assert.assertEquals(response.jsonPath().getInt("parsedBody.items[1].quantity"), 2);
        Assert.assertEquals(response.jsonPath().getFloat("parsedBody.items[1].price"), 15.99, 0.01);

        Assert.assertEquals(response.jsonPath().getString("parsedBody.payment.method"), "credit_card");
        Assert.assertEquals(response.jsonPath().getString("parsedBody.payment.transaction_id"), "txn_67890");
        Assert.assertEquals(response.jsonPath().getFloat("parsedBody.payment.amount"), 111.97, 0.01);
        Assert.assertEquals(response.jsonPath().getString("parsedBody.payment.currency"), "USD");
    }

}