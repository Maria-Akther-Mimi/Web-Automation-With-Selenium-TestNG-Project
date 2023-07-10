# Web(OrangeHRM)-Automation-With-Selenium-TestNG-Project


## Technology used:
- Java
- Intellij idea
- Allure

## Tool used in this Project
 - Selenium

## Framework used in this Project:
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
https://docs.google.com/spreadsheets/d/1Dd1rVF3xFnJ6j2MZ8QOLUWyCOfSwKKaa/edit?usp=drive_link&ouid=109073001623741005951&rtpof=true&sd=true

## How to run this project
- Clone this project
- Run Master suite (Regression testing) by: <br>
-  ```gradle clean test -PsuiteFile="MasterSuite.xml"```
- Run SmokeMaster suite (Smoke testing) by: ```gradle clean test -PsuiteFile="SmokeMasterSuite.xml"```
- Generate allure report by these command: <br>
  ```allure generate allure-results --clean -output```  
  ```allure serve allure-results```

## Allure Report
https://drive.google.com/file/d/1APaxYFvK9FGU-dyD7oILuI6gityKNdoG/view?usp=drive_link
https://drive.google.com/file/d/1-tjwKrVxvCLbQy6Ex8VXzM2k3g56xd5i/view?usp=drive_link

## Automation(TestNG) Output Video
https://drive.google.com/file/d/1AG4GKYUXVC5ITgKV_2JshIf3dpRuGIPy/view?usp=sharing
