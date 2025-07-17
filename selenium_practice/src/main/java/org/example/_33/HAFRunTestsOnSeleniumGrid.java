public class HAFRunTestsOnSeleniumGrid{
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
}