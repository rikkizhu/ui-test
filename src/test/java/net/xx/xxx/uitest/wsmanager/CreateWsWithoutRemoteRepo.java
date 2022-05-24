package net.xx.xxx.uitest.wsmanager;

import net.xx.xxx.uitest.AbstractTestCase;
import net.xx.xxx.uitest.Steps;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @program: CreateWsWithoutRemoteRepo
 * @description:
 * @author: zhuruiqi
 * @create: 2019-01-28 11:45
 **/

public class CreateWsWithoutRemoteRepo extends AbstractTestCase {
    String wsName = RandomStringUtils.randomAlphanumeric(8);
    String spaceKey = null;

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        deleteWorkspaceBySpacekey(spaceKey);
    }

    @Test
    public void testCreateWsWithoutSource() {
        wait.until(ExpectedConditions.presenceOfElementLocated(daWsManagerElementsLocater.createWorkspaceCard()));
        webDriver.findElement(daWsManagerElementsLocater.createWorkspaceCard()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(daWsManagerElementsLocater.noRemoteRepoTab()));
        webDriver.findElement(daWsManagerElementsLocater.noRemoteRepoTab()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(daWsManagerElementsLocater.wsNameInput()));

        webDriver.findElement(daWsManagerElementsLocater.wsNameInput()).click();
        webDriver.findElement(daWsManagerElementsLocater.wsNameInput()).sendKeys(wsName);

        Assert.assertEquals("com-card template-card seleted",
                webDriver.findElement(daWsManagerElementsLocater.firstEnvCard()).getAttribute("class"));

        webDriver.findElements(daWsManagerElementsLocater.createWorkspaceBtn()).get(1).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(daWsManagerElementsLocater.createWorkspaceCard()));
        Assert.assertEquals(Steps.username + "/" + wsName,
                webDriver.findElement(daWsManagerElementsLocater.firstWsTitle()).getText());

        spaceKey = webDriver.findElement(daWsManagerElementsLocater.firstWsCard()).getAttribute("href").split("/")[4];

        Assert.assertEquals("git@gitxxx.xxx.xxx:xxxxxx/empty-template.git",
                queryWsRemoteRepo(spaceKey));
    }
}
