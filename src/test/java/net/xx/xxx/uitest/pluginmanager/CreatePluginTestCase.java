package net.xx.xxx.uitest.pluginmanager;

import net.xx.xxx.uitest.AbstractTestCase;
import net.xx.xxx.uitest.Steps;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @program: CreatePluginTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-01 14:59
 **/
public class CreatePluginTestCase extends AbstractTestCase {

    String pluginName = RandomStringUtils.randomAlphanumeric(8);
    String projectName = RandomStringUtils.randomAlphanumeric(8);
    String pluginDescription = RandomStringUtils.randomAlphanumeric(16);
    String spaceKey = null;
    String pluginId = null;


    @After
    public void tearDown() throws InterruptedException {
        pluginId = getFirstDevelopedPluginId(webDriver);
        deletePlugin(pluginId);
        Thread.sleep(1000);
        deleteWorkspaceBySpacekey(spaceKey);
        deleteProject(projectName);
    }

    @Test
    public void testCreatePlugin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pluginElementsLocater.createPluginCard()));
        webDriver.findElement(pluginElementsLocater.createPluginCard()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(pluginElementsLocater.pluginNameInputinCreate()));

        webDriver.findElement(pluginElementsLocater.pluginNameInputinCreate()).sendKeys(pluginName);
        webDriver.findElement(pluginElementsLocater.projectNameInput()).sendKeys(projectName);
        webDriver.findElement(pluginElementsLocater.firstPluginType()).click();
        webDriver.findElement(pluginElementsLocater.pluginDescriptionInput()).sendKeys(pluginDescription);
        webDriver.findElement(pluginElementsLocater.createPluginBtn()).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(daWsManagerElementsLocater.firstWsTitle()));
        Assert.assertEquals(Steps.username + "/" + projectName,
                webDriver.findElement(daWsManagerElementsLocater.firstWsTitle()).getText());

        spaceKey = webDriver.findElement(daWsManagerElementsLocater.firstWsCard()).getAttribute("href").split("/")[4];

        wait.until(ExpectedConditions.visibilityOfElementLocated(pluginElementsLocater.pluginManageTab()));
        webDriver.findElement(pluginElementsLocater.pluginManageTab()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(pluginElementsLocater.developedPlugin()));
        webDriver.findElement(pluginElementsLocater.developedPlugin()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(pluginElementsLocater.firstDevelopedPluginName()));

        Assert.assertEquals(pluginName, webDriver.findElement(pluginElementsLocater.firstDevelopedPluginName()).getText());
        Assert.assertEquals(pluginDescription, webDriver.findElement(pluginElementsLocater.firstDevelopedPluginDescription()).getText());
    }
}
