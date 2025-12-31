// package com.example

/**
 * Global Variables for ReqRes API Testing
 * Katalon Studio 8.x
 */
class GlobalVariable {
    // Base URL
    static String BASE_URL = "https://reqres.in"
    
    // API Endpoints
    static String ENDPOINT_USERS_LIST = "/api/users"
    static String ENDPOINT_SINGLE_USER = "/api/users"
    static String ENDPOINT_UPDATE_USER = "/api/users"
    static String ENDPOINT_REGISTER = "/api/register"
    
    // Test Data Variables
    static String USER_ID = "2"
    static int PAGE_NUMBER = 1
    
    // Response Variables (untuk data dari response)
    static String EXTRACTED_USER_EMAIL = ""
    static String EXTRACTED_USER_ID = ""
    static String EXTRACTED_USER_NAME = ""
    
    // Assertion Messages
    static String SUCCESS_MESSAGE = "Test Passed"
    static String FAILURE_MESSAGE = "Test Failed"
}