package net.xx.xxx.uitest.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @program: FileGroupElementLocater
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-15 14:35
 **/
public class FileGroupElementLocater {
    public WebDriver webDriver;

    public FileGroupElementLocater(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public By openedFileinFileGroup() {
        return By.xpath("//div[@id='tab_bar_tab_group_2']/ul[@class='tab-labels']/li[@class='tab-label active']");
    }
    public By closeFileTabBtn(){
        return By.id("tab.contextMenu.close");
    }

    public By tabsInGroupFile(){
        return By.xpath("//div[@id='tab_bar_tab_group_2']/ul[@class='tab-labels']/li");
    }
    public By fileTabLocater(String filePath){
        return By.xpath("//div[@id='tab_bar_tab_group_2']/ul[@class='tab-labels']/li[@title='"+filePath+"']");
    }
    public By closeOtherFileTabsBtn(){
        return By.id("tab.contextMenu.closeOthers");
    }

    public By closeAllFileTabsBtn(){
        return By.id("tab.contextMenu.closeAll");
    }
    public By verticalSplitBtn(){
        return By.id("tab.contextMenu.verticalSplit");
    }

    public By horizontalSplitBtn(){
        return By.id("tab.contextMenu.horizontalSplit");
    }
}
