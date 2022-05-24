package net.xx.xxx.uitest.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @program: PluginElementsLocater
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-01 15:00
 **/
public class PluginElementsLocater {
    public WebDriver webDriver;

    public PluginElementsLocater(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public By createPluginCard() {
        return By.id("plugin.createCSPlugin");
    }

    public By pluginNameInputinCreate() {
        return By.xpath("//div[@class='dash-create-plugin']/div[2]/div[@class='board-content']/input");
    }

    public By projectNameInput() {
        return By.xpath("//div[@class='dash-create-plugin']/div[3]/div[@class='board-content']/input");
    }

    public By firstPluginType() {
        return By.xpath("//div[@class='plugin-type']/div[1]");
    }

    public By pluginDescriptionInput() {
        return By.xpath("//div[@class='dash-create-plugin']/div[5]/div[@class='board-content']/textarea");
    }

    public By createPluginBtn() {
        return By.xpath("//div[@class='dash-create-plugin']/div[6]/div[@class='board-content']/button[@class='com-button primary']/span[@id='global.create']");
    }

    public By pluginManageTab() {
        return By.xpath("//div[@class='dash-sidebar']/div[@class='nav']/a[2]/span[@id='global.plugin']");
    }

    public By developedPlugin() {
        return By.id("global.mine");
    }

    public By firstDevelopedPluginName() {
        return By.xpath("//div[@class='dash-mine plugin-list']/div[2]/div[@class='top']/span[@class='name']");
    }

    public By firstDevelopedPluginDescription() {
        return By.xpath("//div[@class='dash-mine plugin-list']/div[2]/div[@class='desc']/span");
    }

    public By firstDevelopedPluginManageBtn() {
        return By.xpath("//div[@class='dash-mine plugin-list']/div[2]/div[@class='control']/div[1]/a[1]/span[@id='global.manage']");
    }

    public By pluginIdinMyDevelopedPlugin() {
        return By.xpath("//div[@class='breadcrumb']/span[3]");
    }

    public By pluginSettingTab() {
        return By.xpath("//span[@id='plugin.pluginSet']");
    }

    public By pluginNameInputinSettings() {
        return By.xpath("//div[@class='dash-pluginset']/div[@class='panel']/div[2]/div[2]/input");
    }

    public By pluginNameDescriptioninSettings() {
        return By.xpath("//div[@class='dash-pluginset']/div[@class='panel']/div[3]/div[2]/textarea");
    }

    public By pluginSteetingsSaveBtn() {
        return By.xpath("//span[@id='global.save']");
    }


    public By pluginModifiedSuccessInfo() {
        return By.xpath("//div[@class='xxx-notification xxx-notification-topRight']/span/div/div/div/div");
    }

    public By prePublishBtn() {
        return By.xpath("//span[@id='plugin.preStat0Click']");
    }

    public By prePublishBtninPrompt() {
        return By.xpath("//span[@id='plugin.preStat0Action']");
    }

    public By prePublishingTip() {
        return By.xpath("//span[@id='plugin.preStat1']");
    }

    public By cancelprePublishBtn() {
        return By.xpath("//span[@id='plugin.preStat2Click']");
    }

    public By prePublishSuccessTip() {
        return By.xpath("//span[@id='plugin.preStat2']");
    }

    public By nowPrePublishedTip() {
        return By.xpath("//span[@id='plugin.nowPrePublish']");
    }

    public By wsBtninPluginSettins() {
        return By.xpath("//div[@class='top']/div[2]/a[2]/span[@id='global.workspace']");
    }

    public By userPluginButton() {
        return By.xpath("//div[@class='status-bar-plugin-area']/button");
    }
}
