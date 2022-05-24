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
 * @program: CreateWsByGitUrlTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-01-24 15:49
 **/
public class CreateWsByGitUrlTestCase extends AbstractTestCase {

    String proName = RandomStringUtils.randomAlphanumeric(8);
    String spaceKey = null;

    @Before
    public void tearUp() {

        createPorject(proName);
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        deleteProject(proName);
        deleteWorkspaceBySpacekey(spaceKey);
    }

    @Test
    public void testCreateWsByGitUrl() {
        wait.until(ExpectedConditions.presenceOfElementLocated(daWsManagerElementsLocater.createWorkspaceCard()));
        webDriver.findElement(daWsManagerElementsLocater.createWorkspaceCard()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(daWsManagerElementsLocater.createWsByGitUrlTab()));
        webDriver.findElement(daWsManagerElementsLocater.createWsByGitUrlTab()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(daWsManagerElementsLocater.gitUrlInput()));

        webDriver.findElement(daWsManagerElementsLocater.gitUrlInput()).sendKeys(
                "git@gitxxx.xxx.xxx:" + Steps.username + "/" + proName + ".git");

        Assert.assertEquals("com-card env-card seleted",
                webDriver.findElement(daWsManagerElementsLocater.firstEnvCard()).getAttribute("class"));

        webDriver.findElements(daWsManagerElementsLocater.createWorkspaceBtn()).get(1).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(daWsManagerElementsLocater.createWorkspaceCard()));
        Assert.assertEquals(Steps.username + "/" + proName,
                webDriver.findElement(daWsManagerElementsLocater.firstWsTitle()).getText());

        spaceKey = webDriver.findElement(daWsManagerElementsLocater.firstWsCard()).getAttribute("href").split("/")[4];

        Assert.assertEquals("git@gitxxx.xxx.xxx:" + Steps.username + "/" + proName + ".git",
                queryWsRemoteRepo(spaceKey));
    }

}
