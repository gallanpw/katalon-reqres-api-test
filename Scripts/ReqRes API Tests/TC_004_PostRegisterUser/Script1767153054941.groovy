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
println("TEST CASE: TC_004 - POST Register User")
println("=" * 50)

try {
    // Verify that email was extracted from previous test
    if (GlobalVariable.EXTRACTED_USER_EMAIL == null || GlobalVariable.EXTRACTED_USER_EMAIL.isEmpty()) {
        throw new Exception("Email not extracted from GET Single User.  Please run TC_002 first.")
    }
    
    println("Base URL: " + GlobalVariable.BASE_URL)
    println("Endpoint: " + GlobalVariable.ENDPOINT_REGISTER)
    println("Email (from TC_002): " + GlobalVariable. EXTRACTED_USER_EMAIL)
    
    // Send POST request
    response = WS.sendRequest(findTestObject('Object Repository/ReqRes/POST_RegisterUser'))
    
    // Get response status
    int statusCode = response.getStatusCode()
    println("Response Status Code:  " + statusCode)
    
    // Assert response status
    WS.verifyResponseStatusCode(response, 200)
    
    // Parse JSON response
    JsonSlurper jsonSlurper = new JsonSlurper()
    def responseData = jsonSlurper.parseText(response.getResponseText())
    
    println("Response Body:")
    println(response.getResponseText())
    
    // Verify registration successful
    assert responseData.id != null, "Response should contain 'id' field"
    assert responseData.token != null, "Response should contain 'token' field"
    
    println("Registered User ID: " + responseData.id)
    println("Token: " + responseData.token)
    
    KeywordUtil. logInfo("✓ POST Register User Test PASSED")
    println("=" * 50)
    
} catch (Exception e) {
    KeywordUtil.markFailedAndStop("✗ POST Register User Test FAILED: " + e.getMessage())
    println("Error: " + e.getMessage())
}