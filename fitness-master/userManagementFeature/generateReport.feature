Feature: Generate PDF Report

  As a user
  I want to generate a PDF report
  So that I can view and share structured data in a portable format

  Scenario: Generate a PDF report with dynamic data
    Given the report file path is "output/reports/sample_report.pdf"
    And the report title is "Sample Report"
    And the report data contains:
      | ID | Name     | Score |
      | 1  | osama    | 85    |
      | 2  | saif     | 90    |
      | 3  | Charlie  | 88    |
    When I generate the PDF report
    Then the PDF file "output/reports/sample_report.pdf" should exist
    And the PDF file should contain the text "Sample Report"
    And the PDF file should contain the text "osama"

  Scenario: Generate a PDF report for popular programs
    Given the report file path is "output/reports/popular_programs_report.pdf"
    And the report title is "Popular Programs Report"
    And the report data contains:
      | Program ID | Program Name    | Enrolled Users |
      | 3          | Yoga Basics     | 75             |
      | 4          | Cardio Blast    | 60             |
      | 1          | Weight Loss     | 50             |
      | 2          | Muscle Gain     | 45             |
    When I generate the PDF report
    Then the PDF file "output/reports/popular_programs_report.pdf" should exist
    And the PDF file should contain the text "Popular Programs Report"
    And the PDF file should contain the text "Yoga Basics"
    And the PDF file should contain the text "75"