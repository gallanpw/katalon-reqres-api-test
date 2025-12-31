import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.TestObject.findPropertyValue
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import groovy.json.JsonSlurper as JsonSlurper
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

println('=' * 50)

println('TEST CASE: TC_001 - Get List Users')

println('=' * 50)

try {
    // Set test data
    GlobalVariable.PAGE_NUMBER = 1

    println('Base URL: ' + GlobalVariable.BASE_URL)

    println('Endpoint: ' + GlobalVariable.ENDPOINT_USERS_LIST)

    println('Page Number: ' + GlobalVariable.PAGE_NUMBER)

    // Send GET request
    response = WS.sendRequest(findTestObject('Object Repository/ReqRes/GET_ListUsers'))

    // Get response status
    int statusCode = response.getStatusCode()

    println('Response Status Code: ' + statusCode)

    // Assert response status
    WS.verifyResponseStatusCode(response, 200)

    // Parse JSON response
    JsonSlurper jsonSlurper = new JsonSlurper()

    def responseData = jsonSlurper.parseText(response.getResponseText())

    println('Response Body:')

    println(response.getResponseText())

    // Verify response contains data
    assert responseData.data != null : 'Response should contain \'data\' field'

    assert responseData.page == 1 : 'Page should be 1'

    assert responseData.data.size() > 0 : 'Data array should not be empty'

    // Store total records for reference
    int totalRecords = responseData.total

    int totalPages = responseData.total_pages

    println('Total Records: ' + totalRecords)

    println('Total Pages: ' + totalPages)

    println('Current Page Records: ' + responseData.data.size())

    KeywordUtil.logInfo('✓ GET List Users Test PASSED')

    println('=' * 50)
}
catch (Exception e) {
    KeywordUtil.markFailedAndStop('✗ GET List Users Test FAILED:  ' + e.getMessage())

    println('Error: ' + e.getMessage())
} 

