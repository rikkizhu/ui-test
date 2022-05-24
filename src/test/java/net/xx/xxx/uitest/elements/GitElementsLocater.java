package net.xx.xxx.uitest.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @program: GitElementsLocater
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-14 10:31
 **/
public class GitElementsLocater {
    public WebDriver webDriver;

    public GitElementsLocater(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public By gitMenuBar() {
        return By.id("menuBarItems.git.main");
    }

    public By gitCommitBtn(){
        return By.id("menuBarItems.git.commit");
    }

    public By acceptGitCommit(){
        return By.id("git.commit");
    }

    public By pushBtn(){
        return By.id("menuBarItems.git.push");
    }

}
