package org.example._5;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Using CSS Selector we can only navigate the DOM in top to down direction
 * but in X Path we can navigate in any direction using X Path AXES
 * we can go top to down, bottom up, sideways, etc.

 * Types of nodes/elements:
        * 1) Self node : node under consideration
        * 2) Parent node : node containing self node
        * 3) Child node : nodes contained within self node
        * 4) Ancestor node : parent of parent node and beyond
        * 5) Sibling node : nodes with same parent node, preceding and following sibling
        * 6) descendant node : child of child node and beyond

 * We know in DOM every node always has at most one parent.

 * we can use 1 based indexing while locating element. e.g. : when a node has more than one element, we need nth child then use tag_name[n].
 * Locating the nodes using different types of nodes :
        * 1) Parent node :
                    * 1) <x path of context element>/parent::parent_tag_name
                            * we move from self node to its parent, 'parent' is a keyword, '::' is compulsory
                    * 2) <x path of context element>/parent::*
                            * parent_tag_name can be replaced by regular expression '*'
                    * 3) <x path of context element>/..
                            * this is also a valid expression to move to parent node

        * 2) Child node :
                    * 1) <x path of context element>/child::child_tag_name
                            * using the child keyword we can get the child element, we can replace child_tag_name with regular expression '*'
                    * 2) <x path of context element>/child_tag_name
                            * similar to chaining x path, we can replace child_tag_name with regular expression '*'

        * 3) Descendant node : it looks all the way in the hierarchy
                     * 1) <x path of context element>/descendant::descendant_tag_name
                            * using the descendant keyword we can get the immediate descendant element by specifying at which tag to stop and return
                     * 2) <x path of context element>//descendant_tag_name
                            * // lets to jump directly to tag specified under the self node

        * 4) Grandchild node :
                     * 1) <x path of context element>/child_tag_name/grandchild_tag_name
                            * using chaining to get to grandchild
                     * 2) <x path of context element>/'*'/grandchild_tag_name

        * 5) Ancestor node : starting from html it tries to match the given tag name in the hierarchy, in top to down fashion, returns the first element found
                     * 1) <x path of context element>/ancestor::ancestor_tag_name
                            * using the ancestor keyword, we can get to immediate ancestor element with specified tag
                     * 2) <x path of context element>/ancestor::*
                            * this will return the grandfather element of self node

        * 6) Following node : it contains all the nodes coming after the self node, parent may or may not be same
                     * 1) <x path of context element>/following::following_tag_name
                            * it continues exploring till it finds the specified tag in top to down approach
                     * 2) <x path of context element>/following::*
                            * it returns the element right after the self node, may or may not be child or sibling

        * 7) Preceding node : it contains all the nodes coming before the self node, parent may or may not be same
                     * 1) <x path of context element>/preceding::preceding_tag_name
                            * it continues exploring till it finds the specified tag in bottom up approach
                     * 2) <x path of context element>/preceding::*
                            * it returns the element right before the self node, may or may not be a parent or sibling

        * 8) Following sibling node :
                     * 1) <x path of context element>/following-sibling::following_tag_name
                            * returns the immediate next sibling
                     * 2) <x path of context element>/following-sibling::*

        * 9) Preceding sibling node :
                     * 1) <x path of context element>/preceding-sibling::preceding-sibling_tag_name
                            * returns the immediate before sibling
                     * 2) <x path of context element>/preceding-sibling::*

        * 10) Using multiple X Paths : X_path_1|X_path_2...|X_path_n
                     * 1) findElement : checks X_path 1 to n till it finds a valid element and returns it
                     * 2) findElements : checks all the X_path and returns all the valid elements

 * Using Operators
        * we can use various operators to compare numeric value in attributes and inner text.
          We have addition (+), subtraction (-), multiplication (*), division (div), equal (=),
          not equal (!), less than (<), less than or equal (<), greater than (>), greater than
          or equal (>=), and, or, mod operators.

 * we need to locate element(s) with deal price greater than 100
        * //span[contains(@class,'dealPrice Text') and text()>100]
 * */
public class XPathAxes {
    public static void main(String[] args) {
        WebDriver driver= new ChromeDriver();
        List<WebElement> element_list;
        try{

        driver.get("https://www.amazon.in/");
//        driver.manage().window().maximize();
        System.out.println(driver.getTitle());
        WebElement element;
        /// 1) locating parent node
//         element=driver.findElement(By.xpath("//div[@id=\"nav-packard-glow-loc-icon\"]/parent::a"));
//         element=driver.findElement(By.xpath("//div[@id=\"nav-packard-glow-loc-icon\"]/parent::*"));
         element=driver.findElement(By.xpath("//div[@id=\"nav-packard-glow-loc-icon\"]/.."));
        System.out.println("1) locating parent node \n value of role : "+element.getAttribute("role")+"\n");


        /// 2) locating child node
//        element=driver.findElement(By.xpath("//a[@aria-label='Starting ₹199 | Bedsheets']/child::div[2]"));
//        element=driver.findElement(By.xpath("//a[@aria-label='Starting ₹199 | Bedsheets']/child::*"));
//        element=driver.findElement(By.xpath("//a[@aria-label='Starting ₹199 | Bedsheets']/div[2]"));
        element=driver.findElement(By.xpath("//a[@aria-label='Starting ₹199 | Bedsheets']/*"));
        System.out.println("2) locating child node : \nvalue of class : "+element.getAttribute("class")+"\n");

        /// 3) locating descendant node
//        element=driver.findElement(By.xpath("//div[@id='nav-fill-search']/descendant::form"));
//        element=driver.findElement(By.xpath("//div[@id='nav-fill-search']//form"));
        element=driver.findElement(By.xpath("//div[@id='nav-fill-search']//label"));
//        System.out.println("3) locating descendant node:\nvalue of id : "+element.getAttribute("id")+"\n");
        System.out.println("3) locating descendant node:\nvalue of for : "+element.getAttribute("for")+"\n");
//            System.out.println("3) locating descendant node:\nvalue of inner text : "+element.getText()+"\n");  // won't work if style="display:none"
            System.out.println("3) locating descendant node:\nvalue of inner text : "+element.getAttribute("textContent")+"\n"); // this works


        /// 4) locating grandchild node
//        element=driver.findElement(By.xpath("//div[@id='nav-xshop-container']/div/ul"));
        element=driver.findElement(By.xpath("//div[@id='nav-xshop-container']/*/ul"));
        
        System.out.println("4) locating grandchild node: value for class : \n "+element.getAttribute("class")+"\n");

        /// 5) locating ancestor node  // go again
//        element=driver.findElement(By.xpath("//a[@href='/fresh?ref_=nav_cs_fresh']/ancestor::div"));
//        element=driver.findElement(By.xpath(""));
//        System.out.println("5) locating ancestor node:\n value or id : "+element.getAttribute("id")+"\n");
//        System.out.println("5) locating ancestor node:\n size of list : "+element_list.size()+"\n");
            /// to get immediate parent use *[1]



        /// 6) locating following node
        element=driver.findElement(By.xpath("//h2[text()='Revamp your home in style']/following::a"));
        element=driver.findElement(By.xpath("//h2[text()='Revamp your home in style']/following::*"));
        System.out.println("6) locating following node:\nvalue of class is :  "+element.getAttribute("class")+"\n");


        /// 7) locating preceding node // go again
//        element=driver.findElement(By.xpath("//h2[text()='Revamp your home in style']/preceding::span"));
        element=driver.findElement(By.xpath("//h2[text()='Revamp your home in style']/preceding::*"));
        System.out.println("7) locating preceding node:\n value of class is : "+element.getTagName()+"\n");


        /// 8) locating following sibling node
        element=driver.findElement(By.xpath("//div[@id='sis_pixel_r2']/following-sibling::div"));
        //
//        element=driver.findElement(By.xpath("//div[@id='sis_pixel_r2']/following-sibling::*"));
        System.out.println("8) locating following sibling node:\nvalue of id is :  "+element.getTagName()+"\n");


        /// 9) locating preceding sibling node
        element=driver.findElement(By.xpath("//div[@id='sis_pixel_r2']/preceding-sibling::div"));
//        element=driver.findElement(By.xpath("//div[@id='sis_pixel_r2']/preceding-sibling::*"));
        System.out.println("2) locating preceding sibling node:\nvalue of id is :  "+element.getAttribute("data-a-state")+"\n");


        /// 10) locating nodes using multiple X Paths
        String mul_x_path="//*[@id='desktop-banner']|//*[@id=\"gw-layout\"]";
        element=driver.findElement(By.xpath(mul_x_path));
        element_list=driver.findElements(By.xpath(mul_x_path));
        System.out.println("10) locating multiple X Paths node:\nvalue of id is : "+element.getAttribute("id")+"\n");
        for (WebElement ele:element_list)
            System.out.println("10) locating multiple X Paths node:\nvalue of id is : "+ele.getAttribute("id"));







        }
        finally {
            driver.quit();
        }
        
    }
}
