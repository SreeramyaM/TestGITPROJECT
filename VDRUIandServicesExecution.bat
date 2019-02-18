SET mypath=%~dp0
cd %mypath%
SET drivepath=%~d0
%drivepath%
set ProjectPath=%mypath%
set classpath=%ProjectPath%\lib\selenium-2.53.0\*;
echo %classpath%
call mvn clean install -Dtest=org.fwcms.cdc.vdr.VDRAllCountrySubmissionTest
timeout 5
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Author: Happiestminds Technologies
:: Created Date: 14th May 2012
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

:: Test case Driver batch file for running Test caes under a Test Suite
call C:\Framework\SOAAutomationFramework\setup.bat location result projfile TCCSV TSCSV generatedatefile resultlog report


:: Changing the directory to SOAP UI bin folder
cd \
C:
cd %location%

:: --------------------------------------------------------------------------------------------------------::
:: Executing testrunner batch file for specified test cases and test suite	- BLOCK START				   ::
:: --------------------------------------------------------------------------------------------------------::

:: For loop to read CSV file for test case execution
@echo off

echo.Calling_GenerateDate_Function
call :GenerateDate

call :execute "Y" "InsurancePurchaseCompleteFlow_UIAndServices" "insuranceServicesTestSuite"
call :executereport
call C:\Framework\SOAAutomationFramework\Reporter\emailnotification.bat
goto :eof

:: Function to Generate New Stay date Range ::

:GenerateDate
echo.Entering_GenerateDate_function
start %generatedatefile%
echo.Exiting_GenerateDate_function
goto :eof
:end

:: Function to execute specified testcases in CSV from command prompt ::

:execute
	Set "Flag=%~1"
	Set "Testcase=%~2"
	Set "Suite=%~3"

	if %Flag% == Y (
		echo %Suite%-%Testcase%: Execution started at %date%-%time%  >> %resultlog%
		testrunner.bat -s%Suite% -c%Testcase% -S -r -a -f %result% -I %projfile% 
	)
	if %Flag% == N (
		echo %Suite% - %Testcase% : Execution Skipped %date%-%time% >> %resultlog%
	)
	goto :eof
:end

:: Function to execute Reporter function ::

:executereport
echo.Entering_ReportGeneration_function
start %report%
echo.Exiting_ReportGeneration_function
:end
echo.emailNotification
::call C:\Framework\SOAAutomationFramework\Reporter\emailnotification.bat

:: --------------------------------------------------------------------------------------------------------::
:: Executing testrunner batch file for specified test cases under a test suite	- BLOCK END 				::
:: --------------------------------------------------------------------------------------------------------::