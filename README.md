# Katalon Studio ReqRes API Test Suite

Comprehensive WebService API test automation for https://reqres.in using Katalon Studio.

## 📋 Overview

This test suite demonstrates API testing best practices with: 
- ✅ Global Variables for configuration management
- ✅ RESTful API testing for multiple HTTP methods (GET, PUT, POST)
- ✅ Response data extraction and reusability
- ✅ GitHub Actions CI/CD integration
- ✅ Automated test execution on push/PR events

## 📁 Project Structure

```
katalon-reqres-api-test/
├── GlobalVariable.groovy              # Global variables configuration
├── Object Repository/
│   └── ReqRes/
│       ├── GET_ListUsers.rs           # GET /api/users? page=1
│       ├── GET_SingleUser.rs          # GET /api/users/{id}
│       ├── PUT_UpdateUser.rs          # PUT /api/users/{id}
│       └── POST_RegisterUser.rs       # POST /api/register
├── Test Cases/
│   └── ReqRes API Tests/
│       ├── TC_001_GetListUsers.groovy
│       ├── TC_002_GetSingleUser.groovy
│       ├── TC_003_PutUpdateUser.groovy
│       └── TC_004_PostRegisterUser.groovy
├── Test Suites/
│   └── ReqResAPITestSuite.ts
├── Keywords/
│   └── APIHelper.groovy               # Helper methods for API testing
├── . github/
│   └── workflows/
│       └── katalon-tests. yml          # CI/CD workflow
└── README.md
```

## 🚀 Quick Start

### Prerequisites
- Katalon Studio 7.x or higher
- Java 11 or higher
- Git

### Installation

1. **Clone Repository**
```bash
git clone https://github.com/gallanpw/katalon-reqres-api-test.git
cd katalon-reqres-api-test
```

2. **Open in Katalon Studio**
   - Launch Katalon Studio
   - File → Open Project → Select this repository

3. **Run Test Suite**
   - Navigate to Test Suites folder
   - Right-click `ReqResAPITestSuite` → Run

## 📝 Test Cases

### TC_001: GET List Users
- **Endpoint**: `/api/users?page=1`
- **Method**: GET
- **Expected**:  Status 200, data array with users
- **Assertions**:
  - Status code is 200
  - Response contains 'data' field
  - Page number is 1
  - Data array is not empty

### TC_002: GET Single User
- **Endpoint**: `/api/users/{id}`
- **Method**: GET
- **Expected**: Status 200, single user object
- **Assertions**: 
  - Status code is 200
  - User data exists
  - User has email, first_name, last_name
  - Email extracted for TC_004
- **Global Variables Used**:
  - Sets:  `EXTRACTED_USER_EMAIL`, `EXTRACTED_USER_ID`, `EXTRACTED_USER_NAME`

### TC_003: PUT Update User
- **Endpoint**: `/api/users/{id}`
- **Method**: PUT
- **Body**:
```json
{
  "name": "John Updated",
  "job": "Senior QA Engineer"
}
```
- **Expected**: Status 200, updated fields returned
- **Assertions**: 
  - Status code is 200
  - Name is "John Updated"
  - Job is "Senior QA Engineer"
  - updatedAt field exists

### TC_004: POST Register User
- **Endpoint**: `/api/register`
- **Method**: POST
- **Body**:  Uses email from TC_002
```json
{
  "email":  "${EXTRACTED_USER_EMAIL}",
  "password": "QATest@123"
}
```
- **Expected**: Status 200, token received
- **Assertions**: 
  - Status code is 200
  - User ID received
  - Token received
- **Global Variables Used**:
  - Gets: `EXTRACTED_USER_EMAIL`

## 🔧 Global Variables

```groovy
// Base URL
BASE_URL = "https://reqres.in"

// Endpoints
ENDPOINT_USERS_LIST = "/api/users"
ENDPOINT_SINGLE_USER = "/api/users"
ENDPOINT_UPDATE_USER = "/api/users"
ENDPOINT_REGISTER = "/api/register"

// Test Data
USER_ID = "2"
PAGE_NUMBER = 1

// Response Variables
EXTRACTED_USER_EMAIL = ""
EXTRACTED_USER_ID = ""
EXTRACTED_USER_NAME = ""
```

## 🔄 Data Flow

```
TC_001: Get List Users
    ↓
TC_002: Get Single User
    ├→ Extract Email to EXTRACTED_USER_EMAIL
    ├→ Extract ID to EXTRACTED_USER_ID
    └→ Extract Name to EXTRACTED_USER_NAME
        ↓
TC_003: Update User
    └→ Updates user data
        ↓
TC_004: Register with Extracted Email
    └→ Uses EXTRACTED_USER_EMAIL from TC_002
        └→ Returns token if successful
```

## 🔐 Global Variable Usage

### Setting Global Variables
```groovy
GlobalVariable.USER_ID = "2"
GlobalVariable.PAGE_NUMBER = 1
```

### Reading Global Variables
```groovy
String email = GlobalVariable.EXTRACTED_USER_EMAIL
String userId = GlobalVariable.EXTRACTED_USER_ID
```

### Using in Request Bodies
```groovy
// In POST_RegisterUser.rs
{
  "email": "${EXTRACTED_USER_EMAIL}",
  "password": "QATest@123"
}
```

## 🐙 GitHub Actions CI/CD

Automated testing runs on:
- **Push** to main or develop branches
- **Pull Requests** to main branch
- **Schedule**:  Daily at 2 AM UTC

View workflow:  `.github/workflows/katalon-tests.yml`

## 📊 Test Execution & Reports

Tests generate HTML reports in `Reports/` directory:
- Test execution summary
- Detailed pass/fail information
- Response logs for each request
- Screenshots (if applicable)

## 🔍 Debugging

### Enable verbose logging: 
```groovy
KeywordUtil.logInfo("Debug message")
println("Console output")
```

### Check Global Variables:
```groovy
println("USER_ID: " + GlobalVariable. USER_ID)
println("EXTRACTED_USER_EMAIL: " + GlobalVariable.EXTRACTED_USER_EMAIL)
```

## ⚙️ Configuration

### Katalon Project Settings
- **Test Framework**: TestNG
- **API Format**: REST
- **Response Format**: JSON

### Request Timeouts
- Connection Timeout: 30 seconds
- Socket Timeout: 30 seconds

## 📖 API Documentation

Visit: https://reqres.in/

## 🐛 Troubleshooting

### Issue: Email not extracted in TC_004
**Solution**: Ensure TC_002 runs before TC_004

### Issue: Connection timeout
**Solution**: Check internet connection and reqres.in status

### Issue: GitHub Actions failed
**Solution**: Check workflow logs in GitHub Actions tab

## 👤 Author
Prepared by: gallanpw

## 📄 License
MIT License

## 🤝 Contributing
Pull requests are welcome! 