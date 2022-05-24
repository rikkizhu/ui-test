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
 * @program: CreateWsByProjectTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-01-18 14:08
 **/
public class CreateWsByProjectTestCase extends AbstractTestCase {
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
    public void testCreateWsByProject() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(daWsManagerElementsLocater.createWorkspaceCard()));
        webDriver.findElement(daWsManagerElementsLocater.createWorkspaceCard()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(daWsManagerElementsLocater.projectOrTemplateBody()));
        Thread.sleep(1000);

        webDriver.findElement(daWsManagerElementsLocater.firstProjectOrTemplateCard()).click();
        wait.until(ExpectedConditions.attributeToBe(daWsManagerElementsLocater.firstProjectOrTemplateCard(),
                "class", "com-card project-card seleted"));

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
