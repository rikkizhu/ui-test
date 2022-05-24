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
 * @program: RestoreWsTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-01-31 11:25
 **/
public class RestoreWsTestCase extends AbstractTestCase {
    String wsName = RandomStringUtils.randomAlphanumeric(8);
    String spaceKey = null;

    @Before
    public void tearUp() {
        spaceKey = createWsWithoutRemoteRepo(wsName, Steps.AI_TemplateId);
        deleteWorkspaceBySpacekey(spaceKey);
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        deleteWorkspaceBySpacekey(spaceKey);
    }

    @Test
    public void testRestoreWs() {
        webDriver.navigate().refresh();
        wait.until(ExpectedConditions.textToBe(daWsManagerElementsLocater.firstDeletedWsTitle(),
                Steps.username + "/" + wsName));

        webDriver.findElement(daWsManagerElementsLocater.firstRestoreWsBtn()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(daWsManagerElementsLocater.restoreWsBtninPrompt()));
        webDriver.findElement(daWsManagerElementsLocater.restoreWsBtninPrompt()).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(daWsManagerElementsLocater.inactiveDashMask()));

        Assert.assertEquals(Steps.username + "/" + wsName,
                webDriver.findElement(daWsManagerElementsLocater.firstWsTitle()).getText());

        spaceKey = webDriver.findElement(daWsManagerElementsLocater.firstWsCard()).getAttribute("href").split("/")[4];

    }
}
