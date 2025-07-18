public class JenkinsCISetupFinalSession{
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
}