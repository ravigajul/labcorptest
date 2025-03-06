Feature: LabCorp Job Search and Validation

  Scenario: Search and Validate a LabCorp Job Posting
    Given I open the LabCorp website
    When I navigate to the Careers page
    And I search for "QA Test Automation Developer"
    And I select the first job from the results
    Then I validate the job details
    And I click Apply Now and confirm details match
    And I return to Job Search