import static com. kms.katalon.core. checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.TestObject.findPropertyValue

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.model. FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com. kms.katalon.core. testobject.RequestObject
import com.kms.katalon. core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon. core.util.KeywordUtil
import groovy.json.JsonSlurper
import internal.GlobalVariable

println("=" * 50)
println("TEST CASE: TC_002 - Get Single User")
println("=" * 50)

try {
    // Set test data - Get user with ID 2
    GlobalVariable. USER_ID = "2"
    
    println("Base URL: " + GlobalVariable.BASE_URL)
    println("Endpoint: " + GlobalVariable.ENDPOINT_SINGLE_USER)
    println("User ID: " + GlobalVariable. USER_ID)
    
    // Send GET request
    response = WS.sendRequest(findTestObject('Object Repository/ReqRes/GET_SingleUser'))
    
    // Get response status
    int statusCode = response.getStatusCode()
    println("Response Status Code: " + statusCode)
    
    // Assert response status
    WS.verifyResponseStatusCode(response, 200)
    
    // Parse JSON response
    JsonSlurper jsonSlurper = new JsonSlurper()
    def responseData = jsonSlurper.parseText(response. getResponseText())
    
    println("Response Body:")
    println(response.getResponseText())
    
    // Verify response contains user data
    assert responseData.data != null, "Response should contain 'data' field"
    assert responseData.data.id != null, "User should have ID"
    assert responseData.data.email != null, "User should have email"
    assert responseData.data.first_name != null, "User should have first_name"
    assert responseData. data.last_name != null, "User should have last_name"
    
    // Extract and store email for later use in POST register
    GlobalVariable.EXTRACTED_USER_EMAIL = responseData.data.email
    GlobalVariable.EXTRACTED_USER_ID = responseData.data.id. toString()
    GlobalVariable.EXTRACTED_USER_NAME = responseData.data.first_name + " " + responseData. data.last_name
    
    println("Extracted Email: " + GlobalVariable.EXTRACTED_USER_EMAIL)
    println("Extracted User ID: " + GlobalVariable. EXTRACTED_USER_ID)
    println("Extracted User Name: " + GlobalVariable.EXTRACTED_USER_NAME)
    
    KeywordUtil.logInfo("✓ GET Single User Test PASSED")
    println("=" * 50)
    
} catch (Exception e) {
    KeywordUtil.markFailedAndStop("✗ GET Single User Test FAILED: " + e.getMessage())
    println("Error: " + e.getMessage())
}