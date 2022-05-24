package net.xx.xxx.uitest.pluginmanager;

import net.xx.xxx.uitest.AbstractTestCase;
import net.xx.xxx.uitest.Steps;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @program: PrePublishPluginTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-02 10:39
 **/
public class PrePublishPluginTestCase extends AbstractTestCase {
    String pluginName = RandomStringUtils.randomAlphanumeric(8);
    String repoName = RandomStringUtils.randomAlphanumeric(8);
    String remark = RandomStringUtils.randomAlphanumeric(16);
    String pluginId = null;
    String spaceKey = null;
    WebDriverWait webDriverWait = new WebDriverWait(webDriver, 90);

    @Before
    public void tearUp() throws InterruptedException {
        spaceKey = createPlugin(pluginName, repoName, Steps.pluginTypeId, Steps.pluginTemplateId, remark);
        Thread.sleep(3000);
    }

    @After
    public void tearDown() throws InterruptedException {
        deletePlugin(pluginId);
        Thread.sleep(2000);
        deleteWorkspaceBySpacekey(spaceKey);
        deleteProject(repoName);
    }

    @Test
    public void testPrePublishPlugin() throws InterruptedException {
        webDriver.navigate().to(Steps.getBaseUrl() + "/dashboard/plugin/mine");
        wait.until(ExpectedConditions.textToBe(pluginElementsLocater.firstDevelopedPluginName(), pluginName));
        webDriver.findElement(pluginElementsLocater.firstDevelopedPluginManageBtn()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(pluginElementsLocater.prePublishBtn()));
        webDriver.findElement(pluginElementsLocater.prePublishBtn()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(pluginElementsLocater.prePublishBtninPrompt()));
        webDriver.findElement(pluginElementsLocater.prePublishBtninPrompt()).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(pluginElementsLocater.prePublishingTip()));
        Assert.assertEquals("上次预发布正在构建中，请耐心等待...",
                webDriver.findElement(pluginElementsLocater.prePublishingTip()).getText());

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(pluginElementsLocater.cancelprePublishBtn()));
        Assert.assertEquals("当前处于预发布状态，如需使用正式版本，您可以",
                webDriver.findElement(pluginElementsLocater.prePublishSuccessTip()).getText());
        Assert.assertTrue(webDriver.findElement(pluginElementsLocater.nowPrePublishedTip()).isDisplayed());

        pluginId = getFirstDevelopedPluginId(webDriver);
        Assert.assertEquals(true, queryIsPrePublished(pluginId));

        webDriver.findElement(pluginElementsLocater.wsBtninPluginSettins()).click();
        String[] handles = new String[webDriver.getWindowHandles().size()];
        webDriver.getWindowHandles().toArray(handles);
        webDriver.switchTo().window(handles[1]);
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(wsElementsLocater.wsIframe()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(wsElementsLocater.wsHeader()));
        Thread.sleep(1000);
        Assert.assertTrue(webDriver.findElement(pluginElementsLocater.userPluginButton()).isDisplayed());
    }
}
