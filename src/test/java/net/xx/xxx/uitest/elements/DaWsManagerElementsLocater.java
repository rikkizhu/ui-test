package net.xx.xxx.uitest.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @program: DashboardWsManagerElements
 * @description:
 * @author: zhuruiqi
 * @create: 2019-01-15 14:27
 **/
public class DaWsManagerElementsLocater {
    public WebDriver webDriver;

    public DaWsManagerElementsLocater(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public By createWorkspaceCard() {
        return By.id("ws.createWorkspace");
    }

    public By createWorkspaceBtn() {
        return By.id("global.create");
    }

    public By projectOrTemplateBody() {
        return By.xpath("//div[@class='project-body']");
    }

    public By firstProjectOrTemplateCard() {
        return By.xpath("//div[@class='project-body']/div[1]");
    }

    public By firstWsTitle() {
        return By.xpath("//div[@class='card-box']/a[1]/div[@class='inner']/div[@class='title']");
    }

    public By firstEnvCard() {
        return By.xpath("//div[@class='board-content negative-margin env']/div[1]");
    }

    public By  firstWsCard() {
        return By.xpath("//div[@class='created']/div[@class='card-box']/a[1]");
    }

    public By createWsByTemplateTab() {
        return By.id("ws.templateProject");
    }

    public By proNameInput() {
        return By.xpath("//input[@name='projectName']");
    }

    public By createWsByGitUrlTab() {
        return By.id("ws.otherGitRepo");
    }

    public By gitUrlInput() {
        return By.xpath("//div[@class='repo-input']/input");
    }

    public By noRemoteRepoTab() {
        return By.id("ws.noRemoteRepo");
    }

    public By wsNameInput() {
        return By.xpath("//input[@name='workspaceName']");
    }

    public By deleteWsBtn() {
        return By.id("global.delete");
    }

    public By deleteWsBtninPrompt() {
        return By.xpath("//div[@class='dash-mask active']/div[@class='prompt']/div[@class='control']" +
                "/button[@class='com-button warn']/span[@id='global.delete']");
    }

    public By inactiveDashMask() {
        return By.xpath("//div[@class='dash-mask']");
    }

    public By firstDeletedWsTitle() {
        return By.xpath("//div[@class='deleted']/div[@class='card-box']/div[1]/div[@class='inner']/div[@class='title']");
    }

    public By firstRestoreWsBtn() {
        return By.xpath("//div[@class='deleted']/div[@class='card-box']/div[1]/div[@class='control']/div[@class='act']/span");
    }

    public By restoreWsBtninPrompt() {
        return By.xpath("//div[@class='dash-mask active']/div[@class='prompt']/div[@class='control']/button[@class='com-button primary']/span[@id='global.restore']");
    }

    public By stopWsBtn() {
        return By.id("global.stop");
    }

    public By stopWsBtninPrompt() {
        return By.xpath("//div[@class='dash-mask active']/div[@class='prompt']/div[@class='control']/button[@class='com-button warn']/span[@id='global.stop']");
    }

    public By firstWsStopBtn() {
        return By.xpath("//div[@class='created']/div[@class='card-box']/a[1]/div[@class='control']/div[1]");
    }

    public By pythonTemplateCard(){
        return By.xpath("//div[@class='project-body']/div[4]");
    }

    public By repoBtninWsCard(){
        return By.xpath("//div[@class=''badge]");
    }
}
