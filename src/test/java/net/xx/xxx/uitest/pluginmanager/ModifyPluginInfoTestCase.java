package net.xx.xxx.uitest.pluginmanager;

import net.xx.xxx.uitest.AbstractTestCase;
import net.xx.xxx.uitest.Steps;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @program: ModifyPluginInfoTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-01 17:14
 **/
public class ModifyPluginInfoTestCase extends AbstractTestCase {

    String pluginName = RandomStringUtils.randomAlphanumeric(8);
    String repoName = RandomStringUtils.randomAlphanumeric(8);
    String remark = RandomStringUtils.randomAlphanumeric(16);
    String modifiedPluginName = RandomStringUtils.randomAlphanumeric(8);
    String modifiedDescription = RandomStringUtils.randomAlphanumeric(16);
    String pluginId = null;
    String spaceKey = null;

    @Before
    public void tearUp() {
        spaceKey = createPlugin(pluginName, repoName, Steps.pluginTypeId, Steps.pluginTemplateId, remark);
    }

    @After
    public void tearDown() throws InterruptedException {
        pluginId = getFirstDevelopedPluginId(webDriver);
        deletePlugin(pluginId);
        Thread.sleep(2000);
        deleteWorkspaceBySpacekey(spaceKey);
        deleteProject(repoName);
    }

    @Test
    public void testModifyPluginInfo() {
        webDriver.navigate().to(Steps.getBaseUrl() + "/dashboard/plugin/mine");
        wait.until(ExpectedConditions.textToBe(pluginElementsLocater.firstDevelopedPluginName(), pluginName));
        webDriver.findElement(pluginElementsLocater.firstDevelopedPluginManageBtn()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(pluginElementsLocater.pluginSettingTab()));
        webDriver.findElement(pluginElementsLocater.pluginSettingTab()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(pluginElementsLocater.pluginNameInputinSettings()));

        webDriver.findElement(pluginElementsLocater.pluginNameInputinSettings()).clear();
        webDriver.findElement(pluginElementsLocater.pluginNameInputinSettings()).sendKeys(modifiedPluginName);
        webDriver.findElement(pluginElementsLocater.pluginNameDescriptioninSettings()).clear();
        webDriver.findElement(pluginElementsLocater.pluginNameDescriptioninSettings()).sendKeys(modifiedDescription);
        webDriver.findElement(pluginElementsLocater.pluginSteetingsSaveBtn()).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(pluginElementsLocater.pluginModifiedSuccessInfo()));
        Assert.assertEquals(modifiedPluginName, webDriver.findElement(pluginElementsLocater.pluginNameInputinSettings()).getAttribute("value"));
        Assert.assertEquals(modifiedDescription, webDriver.findElement(pluginElementsLocater.pluginNameDescriptioninSettings()).getAttribute("value"));
    }
}
