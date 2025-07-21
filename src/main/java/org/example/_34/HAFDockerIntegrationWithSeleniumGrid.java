package org.example._34;
public class HAFDockerIntegrationWithSeleniumGrid{
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
}