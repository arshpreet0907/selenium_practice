package org.example._35;
public class HAFGitGitHubWorkflow{
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
}