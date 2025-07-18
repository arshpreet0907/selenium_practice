
public class Main {

      /// _29 HAF POM
        /**
         * We removed the Main folder inside src, since it is for development, we are performing testing here
         * so we will work inside the test folder
         * Resource files (Utilities, test data, resources) will go inside the resources folder
         * Test Base : common methods required for test cases kept in separate class here

         * Steps :
         * 0.1) We will start by adding the dependencies
               * 1) Selenium
               * 2) Apache Poi (poi, poi-ooxml)
               * 3) Commons-io
               * 4) Apache Commons
               * 5) Apache logging (log4j-core, log4j-api)
               * 6) Event Report
               * 7) TestNG
         * 0.2) make the folder structure :
               * 1) at project level :
                     * 1) logs
                     * 2) reports
                     * 3) screenshots
                     * 4) testData
                     * 5) TestNG.xml
               * 2) at java level
                     * 1) pageObjects
                     * 2) testBase
                     * 3) testCases
                     * 4) utilities
         * 1) Test Case : Account Registration (url : https://tutorialsninja.com/demo/)
                 * Page Object Classes : 2 (Home and Registration)
                 * Test Case : AccountRegistrationTest
         * */
        void _29(){}
      /// _30 HAF Logs Properties Cross Browser
        /**
         * 2) Adding logs to Test Cases (log4j2) : recording all events in the form of text
                 * steps :
                         * 1) add log4j2.xml in src/test/resources
                         * 2) update base class
                         * 3) add log statements to AccountRegistrationTest
                 * types of logs : application logs and system logs
                 * we also maintain the log files especially for debugging
                 * log levels : (increasing order of priority)
                         * ALL
                         * 1) Trace : all logs
                         * 2) Debug : all debug and higher levels
                         * 3) Info : all info and higher levels
                         * 4) Warn : all warn and higher levels
                         * 5) Error : all error and higher levels
                         * 6) Fetal : all fetal and higher levels
                         * OFF
                 * Appenders and Loggers :
                         * Appenders : where to generate the logs (console/file) : file is preferred
                         * Loggers : what type of logs generate (levels : ALL < Trace < Debug < Info < Warn < Error < Fetal < OFF)
                         * in the log4j2.xml file we specify the appenders and loggers
                 * in the xml file :
                         * in the property we specify the base path + logs folder to place the log files
                         * in rolling file we specify the name of file along with time stamp, so log files will be creating with time stamp in their name
                         * in appenders ref we specify where we like to put our data in either console or file
                         * in root we can specify any of the above logging levels
                         * if file size becomes more than specified it will create a new file with the over flowing logs
                 * in BaseClass we need to update to load the xml file :
                         * create Logger object () -> import org.apache.logging.log4j.Logger;
                         * instantiate the logger object using LoggerManager -> logger= LogManager.getLogger(this.getClass()); import org.apache.logging.log4j.LogManager;
                         * now use this logger object to call its methods and log specific levels and messages

         * 3) Run tests on desired browser/cross browser/parallel : xml files should be created at the project level
                 * steps :
                         * 1) Create testng.xml file to Run Test Cases and parameterize browser name and OS to BaseClass →setup() method.
                         * 2) Update BaseClass -setup() method, launch browser based on conditions.
                         * 3) Maintain separate xml to run tests multiple browsers parallelly.
                 * pass the parameter in BaseClass using xml file and using switch we can dynamically choose which browser to open
                 * os is changed using grid
                 * to run multiple test cases on different browsers we can make multiple tests in xml file and provide different parameters there along thread count at suite level along with parallel="tests"

         * 4) Read Common values from config.properties file.
                * steps :
                         * 1) Add config.properties file under src/test/resources.
                         * 2) Update BaseClass -setup() method, add script to load config.properties file.
         * use of properties file : common values for multiple test cases
         * */
        void _30(){}
      /// _31 HAF Data Driven Tests Apache POI
        /**
         * 5) Login Test Case :
                * steps :
                         * 1) Create and update page object classes. LoginPage, MyAccountPage - new classes HomePage update by adding login link element
                         * 2) Create Login Test
                         * 3) Add entry testng.xml
                 * we need to add the login element in HomePage class, make another PageObjectClass for login and logged in page, make another test class for the login test
                 * new PageObjectClasses : LoginPage, MyAccountPage
                 * new test class : TC002LoginTest -> click on login button, enter login details, verify the logged in My Account message

         * 6) Data Driven Login Test :
                 * in order to perform login tests for multiple login details -> valid/invalid we will perform data driven testing
                 * steps :
                         * 1) Prepare test data in Excel, place the Excel file inside the testData folder.
                         * 2) Create ExcelUtility class under utilities package.
                         * 3) Update Page Object class MyAccountPage, add logout link element
                         * 4) Create DataProviders class in utilities package to maintain data providers for data driven tests.
                         * 5) Create Login DataDriven Test under testCases package.
                         * 6) Add an Entry in testng.xml file
                 * the ExcelUtility file has methods : getRowCount(), getCellCount(), getCellData(), setCellData(), fillGreenColor(), fillRedColor()
                 * all the methods are nonstatic so we to create the object of class here
                 * we pass the file path in constructor and use it to perform operations on file
                 * now we make a DataProviders class in utilities with method which will read the Excel file, put it in 2D array and pass it to test case, we will use the ExcelUtility file here
                 * create another test class : TC003DataDrivenLoginTest to use data provider with valid and invalid data, keeping the login and logout order properly
         */
        void _31(){}
      /// _32 HAF Grouping Tests Extent Reports
        /**
         * 7) Grouping tests :
                * steps :
                        * 1) Add all test cases into specific group (sanity, regression, master etc.).
                        * 2) Also add BaseClass methods setup() & teardown() to all groups.
                        * 3) Create separate TestNG xml file(grouping.xml) to run groups and include groups which we want to execute.
                * all methods to be executed should be grouped including test,before and after,etc.
                * LoginTest : {Sanity, Master}, AccountRegistrationTest : {Regression, Master}, DataDrivenLoginTest : {DataDriven}
                * setup(Before Class) :  {Main}
                * tearDown(After Class) :  {Main}
                * here define tag can be used to make an inclusion group of classes (Union) and execute without giving another name to the Union of groups
                * to run methods that belong to two specified groups (Intersection) is not explicitly possible (Expressions might work but not in this case), can be done using combination of include and exclude tags

         * 8) Add Extent Reports to Project :
                 * Steps :
                         * 1) Create ExtentReportUtility utility class under utilities package.
                         * 2) Add captureScreen() method in BaseClass
                         * 3) Add ExtentReportUtility (Listener class) entry in testng.xml file.
                         * 4) Make sure WebDriver is static in BaseClass, we refer same driver instance in ExtentReportUtility
                 * Also we will add the screenshot feature on failure in base class we add a method to take test method name as input take screenshot and return the path of the screenshot
                 * In ExtentReportUtility we add the methods : onStart, onTestSuccess, on TestFailure, onTestSkipped, onFinish. adding the status, info, groups of each test case along with other functionalities
                 * adding the listener xml files
                 * we need to make the BaseClass web driver static because one instance is created when execution starts from setup,
                 * but when new BaseClass() is called a driver is used in captureScreenshot method, so if web driver is nonstatic a new driver can be made
                 * and conflict can be there, to avoid it make the web driver static so it is shared across all the instances of BaseClass under one execution
                 * intentionally fail any test to check if screenshot gets attached to the report

         * 9) Run Failed Test Cases :
                 * test-output->testng-failed.xml
                 * how :
                         * if in round 1 out of 100 test cases 90 got passes 10 failed, in round 2 we want ot execute only these 10 failed test cases
                         * capture the failed test cases
                         * when our xml file executes the test cases the failed test cases are stored automatically in a separate xml file in the test-output folder named testng-failed.xml
                         * it will cover the failed test cases of previous test round
                         * the entry will not be deleted till another fail happens till then it will not be removed even if the test got passed
                 * in our case test-output folder is not automatically generated so this xml file is also not generated
         */
        void _32(){}
        /**
         Error in XML file:
         The content of element type "test" must match "(method-selectors?,parameter*,groups?,packages?,classes?)".

         The error indicates that the XML elements inside <test> are not in the correct order according to the TestNG DTD.
         The required order is:

         method-selectors? (optional)
         parameter* (zero or more)
         groups? (optional)
         packages? (optional)
         classes? (optional)
         */

      /// _33 HAF Run Tests on Selenium Grid
        /**
         * 10) Run Tests on Selenium Grid :
                 * it is required when we need to run our test cases on different operating systems and browsers
                 * so that we can know our test run okay on multiple environment
                 * Grid Setup:
                         * Download selenium-server-4.34.0.jar and place it somewhere (we have places in lib folder).
                         * Run below command in command prompt to start Selenium Grid
                                * java -jar selenium-server-4.34.0.jar standalone
                         * URL to see sessions: http://localhost:4444/
                 * steps :
                         * 1) Add execution_env=local/remote in config.properties file under resources folder.
                         * 2) Update setup() method in the BaseClass (capture execution environment from config.properties file then add required capabilities of OS & Browser in conditions).
                 * Grid components :
                         * 1) HUB : from where we are executing the test cases and controlling everything
                         * 2) Node :
                                 * remote machine, on which machines, operating systems and browsers we want to execute the test cases
                                 * we can run on different systems which is very difficult to maintain since we need to just run tests
                                 * we can create multiple virtual machines in our system
                                 * we can also use docker concept since creating VMs is a tedious task
                                 * we can download docker images for different operating systems from docker hub
                                 * from image we create container, having its grid setup can act as a node
                         * we need to attach node to hub, then hub can recognise the nodes in grid environment
                         * then we specify on which operating system and browser we wish to execute our test cases
                         * the requirement is that the entire grid setup should be connected to one network : hub and nodes
                         * standalone setup : same system can act as hub and node
                         * distributed setup : hub and nodes are on different systems
                 * Standalone Setup (Single machine):
                         * 1. Download selenium-server-4.34.0.jar and place it somewhere.
                         * 2. Run below command to start Selenium Grid
                                 * java -jar selenium-server-4.34.0.jar standalone
                         * 3. URL to see sessions: http://localhost:4444/
                 * Hub & Node Setup (Multiple machines): (without docker) (also for Virtual Machines)
                         * 1. Download selenium-server-4.34.0.jar and place it somewhere in both (hub & node) the machines.
                         * 2. Run below command to make machine as hub java -jar selenium-server-4.34.0.jar hub
                         * 3. Run below command to make machine as node java -jar selenium-server-4.34.0.jar node --hub http://<hub-ip>:4444
                         * 4. URL to see sessions: http://localhost:4444/
                 * this also works if we run commands on different command prompts on same machine and operating system
                         * download the latest grid version and change the command accordingly
                 * Grid Dashboard on browser :
                         * when we open the url provided by the command prompt after running the command to start grid we see a dashboard
                         * here under stereotypes we see logos of browsers with some number on top which tells how many sessions we can run parallelly
                 * after all set what to change in code to configure grid in our framework :
                         * make hub url as a String, make DesiredCapabilities object, there set the Platform(Platform.WIN10) and BrowserName("chrome"), or we change accordingly
                         * make the web driver object from RemoteWebDriver class and in constructor pass the url string as new URL(url) and the capabilities object
                         * use this driver object as previously and it runs fine
                         * in order to decide where we want to execute the test either on local machine or on grid we can add a key value pair (execution_env=local/remote) in properties file
                         * and based on it we can decide how to instantiate the web driver object.
         * we are having trouble in running the tests parallelly, sequential execution of different tests in xml file is alright but when we make parallel = true and thread-count =2 at suite then unexpected things occur
         */
        void _33(){}

      /// _34 HAF Docker Integration with SeleniumGrid
        /**
         * 11) Run tests on Docker with Selenium Environment :
                 * Docker : containerisation
                 * for multiple machine execution :
                         * 1) Physical machines
                         * 2) Virtual Machines
                 * above 2 are too tedious and difficult to maintain, to solve this we use docker images and containers to set up the different environments
                 * we can get these images from docker hub website (repository), each image can have different OS, using image we can make container
                 * these containers do not share hardware unlike VMs
                 * we do everything using docker commands
                 * we first need to install docker in our system
                 * after installation :
                         * check docker version using command in command prompt: docker version, docker-compose version
                         * apart from command prompt we can also interact with the UI windows application on our system
                 * Docker Commands :
                         * Basic :
                                 * 1) docker version : tells whether properly installed or not, both client and server version
                                 * 2) docker -v : gives the docker version number
                                 * 3) docker info : gives all information about the docker
                                 * 4) docker --help : gives all possible docker commands
                                 * 5) docker login : tries to log in to docker website
                         * Images :
                                 * 1) docker images : images already existing (repository, tag, image id,  created, size)
                                 * 2) docker pull image_name : downloads the specified image from docker hub, image_name can be specified accordingly
                                 * 3) docker rmi image_id : deletes the docker image, need to specify the image id properly, rmi = remove image
                         * Containers : need image to create container
                                 * 1) docker ps : which containers are already running (container id, image, command, created, status, ports, names), some may be present but not running
                                 * 2) docker run image_name : this creates the container for the particular image, if present it just creates the container, if image not present then it first downloads the image then creates the container, created containers do not run by default
                                 * 3) docker start container_id : it makes the container running
                                 * 4) docker stop container_id : it makes the container stop
                                 * 5) docker rm container_id : it removes the container, we cannot remove the running container, stop it first
                                 * 6) docker run -it image_name : it enables us to interact with container and also makes a new container and runs it
                         * Others :
                                 * 1) docker stats : tells about docker container memory usage and other stats
                                 * 2) docker system df : tells disk usage of docker images, containers, local volumes, build cache : total, active, size, reclaimable
                                 * 3) docker system prune -f : force full stopping/remove of all docker containers

                 * here we will install 3 images (image names) :
                         * 1) hub : selenium/hub
                         * 2) linux-firefox : selenium/node-firefox
                         * 3) linux-chrome : selenium/node-chrome
                 * all 3 should be part of one network
                 * 1 hub and 2 nodes
                 * run images using following commands :
                         * Create a Docker Network
                                 * docker network create grid
                                         * grid is the network name
                                         * returns ID
                         * hub :
                                 * docker run -d -p 4442-4444:4442-4444 --net grid --name selenium-hub selenium/hub
                                 * docker run -d -p 4442-4444:4442-4444 --net grid --name selenium-hub selenium/hub:latest
                                         * 4442-4444:4442-4444 is the porting mechanism meaning assign port in the given range
                                         * grid is the name of network
                                         * selenium-hub is the name of node
                                         * selenium/hub is the image name
                                         * returns ID
                         * linux-chrome :
                                 * docker run -d --net grid -e SE EVENT_BUS_HOST=selenium-hub -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-chrome
                                 * docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub --shm-size="2g" -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-chrome:latest
                                         * grid is the network name
                                         * selenium/node-chrome is the image name
                                         * --shm-size="2g" : ?
                                         * returns ID
                         * linux-firefox :
                                 * docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-firefox
                                 * docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub --shm-size="2g" -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-firefox:latest
                                         * returns ID
                         * remove the network :
                                 * docker network rm grid
                 * stop containers after running tests
         * through this a network gets created, inside it a hub and two nodes of chrome and firefox are made and these containers also starts running
         * now grid environment is set up successfully now we can run our grid environment and see the nodes running, just open the http://localhost:4444/ui and hub along with nodes will be visible
         * create a xml file with parameters, listeners and everything we need, make sure that the properties file has execution_env=remote and we have a switch case for linux : Platform.LINUX
         * by default it will follow headless execution, report will be generated test wise
         * Problem with this approach :
                 * need to start and stop the hub and nodes manually each time one by one
                 * it can again be a tedious task to it every time
         * Solution :
                 * docker-compose.yaml file :
                 * it is a configuration file in which we can specify all the hub and nodes
                 * it is simple and shortcut way : need not to download images, start and stop containers manually
                 * can be placed at project level along with xml files or inside the resources folder
                 * we can add any number of nodes by just replacing the image name
                 * 1) create docker-compose.yaml file :
                         * specifying version, services (docker images to run), networks
                         * for each image we specify what we wrote in the command individually (image, ports, environment, networks)
                 * 2) run docker-compose.yaml file :
                         * docker-compose up
                 * 3) to check hub and nodes running state :
                         * http://localhost:4444/grid/console
                 * 4) to increase number of nodes
                         * docker-compose scale chrome=3
                 * 5) to stop the grid and cleanup the created container run :
                         * docker-compose down
         * Once we run this file our hub and nodes are operational, we can view them on the previous grid url, and then simply run our xml file the sessions will be created and executed as usual
         */
        void _34(){}
      /// _35 HAF Git & GitHub Workflow
        /**
         * 12) Run Tests using Maven pom.xml, Command Prompt, run.bat file
                 * 1) Maven pom.xml :
                         * Test cases should also be able to run in remote environment as well
                         * we run using pom.xml, till now we used to add dependencies.
                         * pom.xml :
                                 * dependencies : download required dependency jars for projects
                                 * plugins : to compile and run the project
                                         * 1) maven compiler plugin : to compile to code
                                         * 2) maven surefire plugin : to run the project
                         * to run project from outside of ide.
                         * Apache Maven Compiler Plugin : normally used by devops teams, to build from development (in build lifecycle), it is a build creation tool
                         * Apache Maven Compiler Plugin -> Plugin Documentation -> Usage
                         * compiler plugin : https://maven.apache.org/plugins/maven-compiler-plugin/plugin-info.html
                         * surefire plugin : https://maven.apache.org/surefire/maven-surefire-plugin/examples/testng.html
                         * read it to know more ways to perform testing using surefire plugin
                         * inside the project tag at same level as dependencies tag add build tag as follows :
                         <build>
                                 <pluginManagement>
                                         <plugins>
                                                 <plugin>
                                                         <groupId>org.apache.maven.plugins</groupId>
                                                         <artifactId>maven-compiler-plugin</artifactId>
                                                         <version>3.14.0</version>
                                                 </plugin>
                                                 <plugin>
                                                         <groupId>org.apache.maven.plugins</groupId>
                                                         <artifactId>maven-surefire-plugin</artifactId>
                                                         <version>3.5.3</version>
                                                         <configuration>
                                                                 <suiteXmlFiles>
                                                                         <!-- here we specify the xml to run -->
                                                                         <suiteXmlFile>master.xml</suiteXmlFile>
                                                                 </suiteXmlFiles>
                                                         </configuration>
                                                 </plugin>
                                         </plugins>
                                 </pluginManagement>
                         </build>
                         * we can put multiple xml files as well inside the suiteXmlFiles place another suiteXmlFile tag
                         * inside the pom.xml right click -> run as -> maven test
                         * we used the build tag to configure the plugins only, we are making any builds
                         * making builds : a process, a consolidated product, integrated software, packaged software
                 * 2) Command prompt :
                         * maven has 2 levels :
                                 * 1) within ide as extension
                                 * 2) OS level
                         * we need to install maven at operating system level
                         * Install Maven steps :
                                 * 1) Download Maven : https://maven.apache.org/download.cgi : 	apache-maven-3.9.11-bin.zip
                                 * 2) extract zip and place it accordingly(e.g. program files)
                                 * 3) copy the bin path and paste it in Environment Variables path section in System Variables
                                 * 4) verify installation use command : mvn -version
                                 * 5) At least java should be installed before maven
                         * to run using command prompt :
                                 * 1) open project location in cmd containing the pom.xml file (verify using dir command)
                                 * 2) run command : mvn test/mvn clean test(clean up the previous execution files)
                                 * 3) make sure pom.xml file has properly configured compiler and surefire plugins with right xml file to run
                 * 3) run.bat file :
                         * instead of using the command prompt command everytime we can just make a bat file and execute it
                         * it is a shell scripting file
                         * inside the project folder make the run.bat file (name can be customized) : windows batch file
                         * inside the bat file put the commands executed on cmd, save and exit :
                                 * 1) cd path_to_project_folder_containing_pom.xml_file
                                 * 2) mvn test
                         * double click on the bat file, it will execute the commands specified and run the tests
                         * again make sure pom.xml file has the right plugins and xml file configured

         * 13) Push the code to Git and Github repository :
                 * from here CI(continuous integration) part starts, using git, github and jenkins
                 * Technical teams :
                         * 1) development : multiple developer work on same feature, have their own local repository(git) on top of workspace(directory), at the end of day all changes need to be make part of local repository(commit) then push the code to global repository/remote repository(github) using website link
                         * 2) devops : (activities)
                                 * 1) build creation : using maven (maven goals : compile code, run code, test code, package, install), using process automation
                                 * 2) run automation : take the test scripts from testing team global repository, using Jenkins
                                 * 3) build certification : at the end this is generated
                                 * nightly builds : at night build is certified
                         * 3) testing : do similar to developers, make changes all day, at the end commit to local repository and then push it to global repository
                 * we will focus on testing team work :
                         * to commit tests to local repository
                         * to push to global repository
                         * to configure project inside Jenkins
                 * continuously jenkins will take the test cases from global repository and execute them
                 * Prerequisites :
                         * 1) install git
                                 * download git for particular operating system
                                 * install the exe file
                                 * verify git install by having a git command prompt (git bash,git gui)
                                         * git --version
                         * 2) make account in github
                                 * go to git hub website
                                 * create account fill all information
                                 * login into your account -> profile
                                 * create remote repository :
                                         * go to profile
                                         * go to repository
                                         * create new fill all details and click create
                                         * in the code section get the repository url
                 * workflow :
                         * directory phases :
                                 * working directory ->(add command) -> staging area -> (commit command) -> git repository -> (push command) -> github repository
                         * file phases :
                                 * Untracked file -> (add command) -> tracked file -> (commit command) -> commited file -> (push command) -> remote file
                         * project is created inside the working directory/workspace
                         * we have control over untracked and tracked files but commited files are permanent
                         * refer to images
                         * steps : (Round 1)
                                 * 1) create a new local git repository at same workspace/project location:
                                         * git init
                                 * 2) Provide user info to git repository
                                         * git config --global user.name "Arshpreet Singh"
                                         * git config --global user.email "arshpreet0907singh@gmail.com"
                                 * 3) Adding files/folders to staging/indexing
                                         * git add -A : add all the files and folders to staging
                                         * git add filename : particular file
                                         * git add *.java : all java files
                                         * git add folder_name : particular folder
                                                 * git add .        # Stages new_file.txt and modified_file.txt only
                                                 * git add -A       # Stages all three: new, modified, AND deleted files
                                                 * git add -u       # Stages only modified_file.txt and deleted_file.txt
                                 * Check status of files being tracked :
                                         * git status : files green are being tracked
                                 * 4) Commit the code into local(git) repository :
                                         * git commit -m "commit message"
                                 * 5) Establish connection between local and global repository before first push
                                         * git remote add origin "remote repository url"
                                 * 6) Push the code into remote repository :
                                         * git push origin master
                                 * create token for login :
                                 * github profile -> settings -> developer settings -> personal access tokens -> tokens classic -> generate new token -> provide password -> provide note, expiration duration and select privileges (repo, admin.org, delete_repo, user, etc) for operations allowed, click on generate token, copy the token id and paste it to sign in window
                         * this all has to be done for round 1
                         * for round 2 steps :
                                 * check git status to know which files got created/changed/deleted(displayed with red)
                                 * 1) add the changed files
                                 * 2) commit the changes
                                 * 3) push the changes
                         * when some other person has push the changes to remote repository :
                                 * pull the new code to our local repository (when project already present in local as well as remote)
                                         * git pull "git_repo_url"   # first pull
                                         * git pull                  # from 2nd pull onwards
                                 * clone the remote repository (when remote repository does not exists locally, done only once)
                                         * git clone "git_repo_url"
                         * When multiple people are working on remote repository we maintain branches of git repository
                         * from the main branch we create branches and work separately we can push code there, then all branches can be merged together, if there are conflicts we can resolve them, it is called conflict resolution process
         */
        void _35(){}
      /// _36 HAF Jenkins CI Setup (Final Session)
        /**
         * 14) Run tests using Jenkins :
                 * Devops : CI/CD process as infinity logo
                         * CI (Continuous Integration): developing, testing and building the project on every commit
                         * CD (Continuous Delivery/Deployment) : deploying the successful builds for the users
                 * Jenkins : used for CI/CD pipeline process automation
                         * it is in devops team domain and a web based application
                         * Pipeline : code -> build -> automation testing -> deploy
                 * most of times sanity, regression, etc. tests will be executed on jenkins itself on headless mode by default
                 * Setup Jenkins :
                         * 1) download jenkins from website :
                                 * 1) 1 single .war file (generic java package) (web archiving file) : run through command prompt and stop when task is done, learning purpose
                                 * 2) installer .exe file : always up and running no need to stop
                         * 2) using .war file :
                                 * 1) initial setup on first execution :
                                         * go to folder where .war file is located
                                         * run the command
                                                 * java -jar jenkins.war
                                         * when executed for the first time a password will be generated copy and save it somewhere
                                         * wait until following message is visible :  Jenkins is fully up and running
                                         * minimize the command prompt and open the url : http://localhost:8080, 8080 is the default port number for jenkins
                                         * copy paste the administration password here, it will work to unlock the jenkins only once, later we create admin and user accounts
                                         * click on install suggested plugins, it install the plugins wait for some time
                                         * create the admin user name and password to open jenkins later
                                         * now jenkins is available on localhost port number 8080
                                         * the jenkins dashboard will be visible here
                                 * 2) One time configurations :
                                         * plugins :
                                                 * goto dashboard -> manage jenkins -> plugins
                                                 * goto installed plugins and verify the following plugins : git, maven
                                                 * if not installed goto available plugins search the name -> click install check box and click install button
                                         * specify java, git, maven locations :
                                                 * goto manage jenkins -> tools -> jdk installation, git installation, maven installation
                                                 * jdk : add jdk -> provide jdk name(any) and location(e.g. C:\Program Files\Java\jdk-17)
                                                 * git : add jdk -> provide git name(any) and location of exe file(e.g. C:\Users\arshpreet.singh\AppData\Local\Programs\Git\bin\git.exe)
                                                 * maven : add jdk -> provide maven name(any) and location (e.g. D:\training\selenium\IdeaProjects\apache-maven-3.9.11-bin\apache-maven-3.9.11)
                 * Running github project on Jenkins :
                         * 1) Create new idem on Dashboard :
                                 * give name and select maven project
                                 * now we are on configuration page :
                                         * add optional description
                                         * in Source Code Management add git and provide the github project url, make the branch same as repository (main/master)
                                         * in Build root POM : pom.xml, Goals and options : test (we ran pom.xml using mvn test on command prompt, here it is configured for maven so only test command)
                                         * provide email address for failed builds and other details
                                         * Click on save button, it will apply the changes
                         * 2) then a new entry is created in our main dashboard open it and click on build now so that it pulls the code from github and starts execution from the pom.xml file
                         * 3) we can find the code fetched by the jenkins inside, any changes made by the test execution will be reflected here
                                 * /var/lib/jenkins/workspace/[YOUR_JOB_NAME]/
                                 * C:\Users\[USERNAME]\.jenkins\workspace\[YOUR_JOB_NAME]\
                 * Running local project on Jenkins :
                         * using local pom.xml file
                         * steps :
                                 * 1) create new item from dashboard, give it a new name and select maven project, click ok
                                 * 2) in configure do not select any source code management
                                 * 3) in builds provide the full path of the pom.xml file, in goals and options put "test", it acts as mvn test command
                                 * 4) this will replicate the run.bat file behaviour, click on apply then save
                                 * 5) click on build now and it will run the project
                         * in our case it failed so we went to opencart_local folder inside C:\Users\[USERNAME]\.jenkins\workspace\[YOUR_JOB_NAME]\
                         * opencart_local is our job name, then copy pasted the entire project files there, in pom path we placed pom.xml and clicked on build now
                 * Running run.bat file on Jenkins :
                         * we made the run.bat file to run the pom.xml file we just need to run this inside jenkins (make sure maven is installed on system)
                         * create new item from dashboard, put the name and select freestyle project(common for all types of projects)
                         * here goto build steps and click on Execute Windows batch command, specify the full location of our run.bat file
                                 * D:\training\selenium\HAF\run.bat
                         * click apply and save, then click on build now
                 * In real time : we prefer Github project execution
                         * devops team own jenkins, testing team runs the jenkins in local bowser through url from devops machine
                         * here we cannot run local projects : all the java, maven, git paths configured are of devops local machine
                         * so using Github url we can run the projects
         */
        void _36(){}

}