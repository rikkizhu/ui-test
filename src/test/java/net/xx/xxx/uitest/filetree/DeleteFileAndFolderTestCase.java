package net.xx.xxx.uitest.filetree;

import net.xx.xxx.uitest.AbstractTestCase;
import net.xx.xxx.uitest.Steps;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @program: DeleteFileAndFolderTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-12 09:35
 **/
public class DeleteFileAndFolderTestCase extends AbstractTestCase {
    String spaceKey = null;
    String wsName = RandomStringUtils.randomAlphanumeric(8);

    @Before
    public void tearUp() {
        spaceKey = createWsWithoutRemoteRepo(wsName, Steps.PythonDemo_TemplateId);
    }

    @After
    public void tearDown() {
        deleteWorkspaceBySpacekey(spaceKey);
    }

    @Test
    public void testDeleteFile() throws InterruptedException {
        enterLatestWs(spaceKey);
        Assert.assertTrue(webDriver.findElement(By.id("/README.md")).isDisplayed());

        webDriver.findElement(fileTreeElementsLocater.readmeFileLocater()).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.readmeFileLocater())).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.deleteFileBtn()));
        webDriver.findElement(fileTreeElementsLocater.deleteFileBtn()).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.acceptDeleteBtn()));
        webDriver.findElement(fileTreeElementsLocater.acceptDeleteBtn()).click();

        Thread.sleep(1500);
        Assert.assertFalse(isElementPresent(fileTreeElementsLocater.readmeFileLocater()));
    }

    @Test
    public void testDeleteFolder() throws InterruptedException {
        enterLatestWs(spaceKey);
        Assert.assertTrue(webDriver.findElement(By.id("/cli")).isDisplayed());

        webDriver.findElement(fileTreeElementsLocater.cliFolderLocater()).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.cliFolderLocater())).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.deleteFileBtn()));
        webDriver.findElement(fileTreeElementsLocater.deleteFileBtn()).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.acceptDeleteBtn()));
        webDriver.findElement(fileTreeElementsLocater.acceptDeleteBtn()).click();

        Thread.sleep(1500);
        Assert.assertFalse(isElementPresent(fileTreeElementsLocater.cliFolderLocater()));
    }
}
