# Assignment 5 – CI, Unit, and Integration Testing
**Kenner Fitt – SE 333**

![Build Status](https://github.com/kennerfitt/Assignment5_Code/actions/workflows/SE333_CI.yml/badge.svg)

---

## Overview
This project implements Continuous Integration (CI) using GitHub Actions with automated unit and integration testing for both Amazon and Barnes & Noble modules.

---

## Tests Implemented
- **BarnesAndNobleTest.java** – specification-based and structural-based tests.
- **AmazonUnitTest.java** – unit-level tests for Amazon.
- **AmazonIntegrationTest.java** – integration-level tests verifying combined module behavior.

All tests were executed successfully using Maven and verified through GitHub Actions.

---

## GitHub Actions Workflow
Workflow name: **SE333_CI**

Steps performed:
1. Checkout the repository
2. Setup Java 21 (Temurin)
3. Run Checkstyle
4. Run Maven tests
5. Generate and upload artifacts:
    - `checkstyle-report`
    - `jacoco-report`

---

## Reports
Artifacts produced:
- **Jacoco Report:** Provides test coverage results.
- **Checkstyle Report:** Lists Java style and formatting errors.

These artifacts were successfully uploaded as part of the workflow execution and verified in the GitHub Actions run.

---

## Results
- All tests passed successfully.
- Workflow completed without build errors.
- Jacoco and Checkstyle artifacts were uploaded successfully.

---

## Screenshots Included
1. Successful GitHub Actions run showing green check.
2. Artifacts uploaded section (checkstyle-report and jacoco-report).
3. Jacoco report HTML coverage table (`index.html`).
4. Checkstyle XML summary file.

---

### Repository Link
[https://github.com/kennerfitt/Assignment5_Code](https://github.com/kennerfitt/Assignment5_Code)
