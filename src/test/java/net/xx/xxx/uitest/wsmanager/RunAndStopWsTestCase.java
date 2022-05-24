package net.xx.xxx.uitest.wsmanager;

import net.xx.xxx.uitest.AbstractTestCase;
import net.xx.xxx.uitest.Steps;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @program: xxxx.uitest.wsmanager.StopWsTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-01 09:34
 **/
public class RunAndStopWsTestCase extends AbstractTestCase {
    String wsName = RandomStringUtils.randomAlphanumeric(8);
    String spaceKey = null;

    @Before
    public void tearUp() {
        createWsWithoutRemoteRepo(wsName, Steps.AI_TemplateId);
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        deleteWorkspaceBySpacekey(spaceKey);
    }

    @Test
    public void testStopWs() throws InterruptedException {
        webDriver.navigate().refresh();
        spaceKey = webDriver.findElement(daWsManagerElementsLocater.firstWsCard()).getAttribute("href").split("/")[4];
        webDriver.findElement(daWsManagerElementsLocater.firstWsCard()).click();
        String[] handles = new String[webDriver.getWindowHandles().size()];
        webDriver.getWindowHandles().toArray(handles);
        webDriver.switchTo().window(handles[1]);
        webDriver.switchTo().frame(webDriver.findElement(wsElementsLocater.wsIframe()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(wsElementsLocater.wsHeader()));

        Assert.assertEquals("Online", queryWsStatus(spaceKey));

        webDriver.switchTo().window(handles[0]);
        webDriver.navigate().refresh();
        webDriver.findElement(daWsManagerElementsLocater.stopWsBtn()).click();
        webDriver.findElement(daWsManagerElementsLocater.stopWsBtninPrompt()).click();

        Thread.sleep(1000);
        Assert.assertEquals("Offline", queryWsStatus(spaceKey));
    }
}
