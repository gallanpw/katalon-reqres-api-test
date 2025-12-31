import static com.kms. katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject. ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.TestObject.findPropertyValue

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com. kms.katalon.core. testcase.TestCaseFactory as TestCaseFactory
import com. kms.katalon.core. testobject.ObjectRepository as ObjectRepository
import com.kms. katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword. WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil
import groovy.json.JsonSlurper
import internal.GlobalVariable

println("=" * 50)
println("TEST CASE: TC_003 - PUT Update User")
println("=" * 50)

try {
    // Set test data - Update user with ID 2
    GlobalVariable.USER_ID = "2"
    
    println("Base URL: " + GlobalVariable.BASE_URL)
    println("Endpoint: " + GlobalVariable. ENDPOINT_UPDATE_USER)
    println("User ID: " + GlobalVariable.USER_ID)
    
    // Send PUT request
    response = WS.sendRequest(findTestObject('Object Repository/ReqRes/PUT_UpdateUser'))
    
    // Get response status
    int statusCode = response. getStatusCode()
    println("Response Status Code: " + statusCode)
    
    // Assert response status
    WS.verifyResponseStatusCode(response, 200)
    
    // Parse JSON response
    JsonSlurper jsonSlurper = new JsonSlurper()
    def responseData = jsonSlurper.parseText(response.getResponseText())
    
    println("Response Body:")
    println(response.getResponseText())
    
    // Verify update was successful
    assert responseData. name != null, "Response should contain 'name' field"
    assert responseData.job != null, "Response should contain 'job' field"
    assert responseData.updatedAt != null, "Response should contain 'updatedAt' field"
    
    // Verify updated values
    assert responseData.name == "John Updated", "Name should be 'John Updated'"
    assert responseData.job == "Senior QA Engineer", "Job should be 'Senior QA Engineer'"
    
    println("Updated Name: " + responseData.name)
    println("Updated Job: " + responseData.job)
    println("Updated At: " + responseData.updatedAt)
    
    KeywordUtil.logInfo("✓ PUT Update User Test PASSED")
    println("=" * 50)
    
} catch (Exception e) {
    KeywordUtil.markFailedAndStop("✗ PUT Update User Test FAILED: " + e. getMessage())
    println("Error:  " + e.getMessage())
}