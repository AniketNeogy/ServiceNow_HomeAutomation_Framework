# Selenium Testing Framework 

### Prerequisites : 

Below are a few prerequisites required before downloading and using this framework : 

- Install Java on your system and configure it in the system path.
- Install TestNG plugin for the IDE that you are going to use.
- Install Maven on your system and configure it in the system path
- The current framework supports parallel execution using Chrome and Firefox, hence if you want to execute tests in parallel ensure 
  both Google Chrome and Mozilla Firefox are installed on your system.

### Steps to import and run the project : 

Below are the steps required to download and run the project : 

- Download the zip file and unzip it in the desired location.
- Import the project into the IDE of your choice. Please Ensure that the IDE has TestNG plugin installed.
- Once the project is imported it should automatically build (provided Maven is installed). The framework is maven project as such in case it does not build automatically do a clean & update maven project(from IDE) Or maven clean install from CMD.
- Now there are two ways in which we can execute our test cases . First is Using the IDE, below are the steps involved to run the tests from IDE

	1. Open the TestNG.XML file. The TestNG.xml file is present at location "\\ServiceNow_HomeAutomation_Framework\src\test\resources"
	2. Right Click --> Run As TestNG Suite
	
Please Note : 
	The Current framework is designed for parallel execution with thread count of 2 and provide support for cross browser testing. So it will run one test tag in chrome and the other in firefox. 
	In case you want to disable the parallel execution please comment out a test tag corresponding to a browser and run the tests.

```	
TestNG.XML for single threaded execution (replace the TesNG.XML file with this to get single threaded execution on chrome browser only):  

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Extent Report Parallel Test Suite" parallel="tests" thread-count="1">
    <listeners>
        <listener class-name="utils.Listeners.TestListener"/>
        <listener class-name="utils.Listeners.AnnotationTransformer"/>
    </listeners>

    <test name="DemoTestChrome">
     <parameter name = "browser" value="chrome"/> 
        <classes>
            <class name="tests.ProductCheckoutTest"/>
        </classes>
    </test>
    
    <!-- <test name="DemoTestFirefox">
     <parameter name = "browser" value="firefox"/> 
        <classes>
            <class name="tests.ProductCheckoutTest"/>
        </classes>
    </test> -->
</suite>

```
	
- Now the second way is using maven : Below are the steps involved to run the tests from IDE
		
		1. Using CMD goto the project location "ServiceNow_HomeAutomation_Framework"
		2. Then execute the following command : mvn clean test
	
Please Note : 
	The current framework is configured in maven using the surefire plugin. As such it will execute the tests as per the TestNg the XML configuration.
	

### Framework Design and Features : 

Below are more of the major design patterns implemented in the project : 

- **Page Object Model** : All page classes are designed based on the POM model (A conscious effort was made to not use Page Factory due to its limitations
with respect to Locator issues (specially with regards to Stale Element Exception)


- **Singleton**  : The ExtentManager class is an implementation of Singleton design principle.


- **Builder Pattern** - All pages classes have been written by following builder pattern design principle. This helped us to write the tests in more readable and maintainable fashion. Below is a snippet of the Test cases written in builder pattern format : 

```
homePage
        .gotoMyStoreHomePage()
        .performGlobalSearch("printed")
        .verfiySearchResultsContainText("printed")
        .applySortingFilter("Price: Lowest first")
        .verifyItemsSortedLowestFirst()
        .selectProductAndNavigateToProductDetailsPage()
        .selectProductDetails(3, "M", "Green")
        .addProductToCard()
        .reviewCartAndProceedToCheckout()
        .verifyOrderSummaryAndProceed()
        .enterLoginCredentialsAndSignin("zarmanshah@malomiesed.com","Testing123")
        .addNewAddress("Aniket", "Neogy", "52 Downtown", "Denver", "Colorado", "11111", "United States", "8981833643", "AniketAddress")
        .chooseDeliveryAddressAndProceed()
        .verifyShippingDetailsAndProceed()
        .completePaymentViaBankWire()
        .verifyOrderSummaryBankWire();
```
- **Thread Safety**  : As already stated the framework supports cross browser parallel test execution. As such I have maintained a tread map in the 
 ExtentTestManager class, so that the extent test logs can be written into a single report in a thread safe manner.
 
 

Below are more of the major features provided by the framework  : 

- **Extent Reports** : This framework is build on top of TestNG as such we get basic TestNG reports. However we wanted to have a robust reporting mechanism in place. As such we decided to implement Extent Reports in our framework. All the extent report logging logic is handled with the help of TestNG listeners. Also we are able to capture screenshots and embed them into the reports in case of failure as well as when the entire test passes.
Classes to refer : ``utils.ExtentReports.ExtentManager.java, utils.ExtentReportsExtentTestManager.java, utils.Listeners.TestListener.java``
Extent Report Location : ``\\ServiceNow_HomeAutomation_Framework\ExtentReports\ExtentReportResults.html``



- **Retry Mechanism** : The framework leverages the IRetryAnalyzer interface in TestNG to implement retry mechanism for failed test cases. As per our current configuration the tests are retried 3times before failing. 
Classes to refer : ``RetryFailedTests.java, AnnotationTransformer.java``



- **Parallel Execution** : The Current framework is designed for parallel execution with thread count of 2 and provide support for cross browser testing. So it will run one test tag in chrome and the other in firefox. 



 - **No Driver Executable Maintenance Required** : In this framework we have used WebDriver Manager API as such no need to maintain browser executables.





 

	