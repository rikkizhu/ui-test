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
 * @program: DeleteWsTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-01-28 16:44
 **/

public class DeleteWsTestCase extends AbstractTestCase {
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
    public void testDeleteWs() {
        wait.until(ExpectedConditions.textToBe(daWsManagerElementsLocater.firstWsTitle(),
                Steps.username + "/" + wsName));

        webDriver.findElement(daWsManagerElementsLocater.firstWsCard()).findElement(daWsManagerElementsLocater.deleteWsBtn()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(daWsManagerElementsLocater.deleteWsBtninPrompt()));
        webDriver.findElement(daWsManagerElementsLocater.deleteWsBtninPrompt()).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(daWsManagerElementsLocater.inactiveDashMask()));

        Assert.assertEquals(Steps.username + "/" + wsName,
                webDriver.findElement(daWsManagerElementsLocater.firstDeletedWsTitle()).getText());

        spaceKey = webDriver.findElement(daWsManagerElementsLocater.firstWsCard()).getAttribute("href").split("/")[4];


    }

}
