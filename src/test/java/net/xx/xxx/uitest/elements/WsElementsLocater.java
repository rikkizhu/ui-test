package net.xx.xxx.uitest.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @program: WsElementsLocater
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-01 10:57
 **/
public class WsElementsLocater {

    public WebDriver webDriver;

    public WsElementsLocater(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public By wsHeader() {
        return By.xpath("//div[@class='menu-bar-container']");
    }

    public By wsIframe() {
        return By.id("iframe");
    }

    public By enterWsMask() {
        return By.xpath("//div[@class='progress']");
    }

    public  By dashboardBtninMenuBar(){
        return By.xpath("//*[@id='MENUBAR']/div/div/div[1]/a/i");
    }
}
