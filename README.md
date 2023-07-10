# OrangeHRM_Automation_Using_TestNG

## Technology used:
- Java
- Intellij idea
- Allure

## Framework used:
  - TestNG

## Scenario
1. Login as a admin to https://opensource-demo.orangehrmlive.com/  
2. Go to PIM menu and create a new employee. Save the employee firstname, lastname, employeeid, username and password into JSONArray file. Generate random password which meets following criteria:  
For a strong password, please use a hard to guess combination of text with upper and lower case characters, symbols and numbers. Assert if employee is created successfully.  

3. Now go to the dashboard again and search by the employee id to check if the employee is found  
4. Now go to the Directory menu and search by employee name and check if the employee is found  
5. Logout the session.  
6. Now login with the newly created employee creds  
7. Assert your full name is showing besides the profile icon.  
8. Go to my info  
9. Scroll down and select Gender and Blood Type as O+ and save it. Then logout the user.  
10. Create a smoke suite configuration which will run only following features (positive cases only):  
- Login to admin  
- search by the employee id if found  
- logout admin and login to the employee id you created last  
- Update the blood Group as AB-  
- Logout the user

## Test Cases
https://docs.google.com/spreadsheets/d/1t9Tk007j3RzmURG6j99aO_2LvmsKvgYc06PFjAnPqSM/edit?usp=sharing

## How to run this project
- Clone this project
- To run Master suite (regression testing) hit this command ```gradle clean test -PsuiteFile="MasterSuite.xml"```
- To run SmokeMaster suite (smoke testing) hit this command ```gradle clean test -PsuiteFile="SmokeMasterSuite.xml"```
- To generate allure report hit these command  
  ```allure generate allure-results --clean -output```  
  ```allure serve allure-results```

## Allure Report
![Allure_Overview](https://github.com/fariha28345/OrangeHRM_Automation_Using_TestNG/assets/50767962/6daa8116-7cfb-42c8-9bbb-e358ddfea122)
![Allure_behaivor](https://github.com/fariha28345/OrangeHRM_Automation_Using_TestNG/assets/50767962/e85d63ce-58ce-4780-b62f-ae13233177ae)

## Automation Output Video
https://github.com/fariha28345/OrangeHRM_Automation_Using_TestNG/assets/50767962/0b243e58-963a-464b-9815-6205106a3490

