package net.xx.xxx.uitest.filetree;

import net.xx.xxx.uitest.AbstractTestCase;
import net.xx.xxx.uitest.Steps;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @program: CreateFolderTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-11 14:54
 **/
public class CreateFolderTestCase extends AbstractTestCase{
    String spaceKey = null;
    String wsName = RandomStringUtils.randomAlphanumeric(8);
    String folderName = RandomStringUtils.randomAlphanumeric(6);


    @Before
    public void tearUp() {
        spaceKey = createWsWithoutRemoteRepo(wsName, Steps.PythonDemo_TemplateId);
    }

    @After
    public void tearDown() {
        deleteWorkspaceBySpacekey(spaceKey);
    }

    @Test
    public void testCreateFolderByFolderContentMenu() throws InterruptedException {
        enterLatestWs(spaceKey);
        webDriver.findElement(fileTreeElementsLocater.cliFolderLocater()).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.cliFolderLocater())).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.contentMenuNewFolderBtn()));
        webDriver.findElement(fileTreeElementsLocater.contentMenuNewFolderBtn()).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.inputPathBox()));
        Assert.assertEquals("请输入新文件夹的路径", webDriver.findElement(fileTreeElementsLocater.newFolderPathMessage()).getText());
        Assert.assertEquals("/cli/untitled", webDriver.findElement(fileTreeElementsLocater.inputPathBox()).getAttribute("value"));

        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(folderName);
        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(fileTreeElementsLocater.cliFolderLocater()));
        actions.doubleClick(webDriver.findElement(fileTreeElementsLocater.cliFolderLocater())).build().perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("/cli/" + folderName)));
        Assert.assertTrue(webDriver.findElement(By.id("/cli/" + folderName)).isDisplayed());
    }

    @Test
    public void testCreateFolderByRootFolderContentMenu() throws InterruptedException {
        enterLatestWs(spaceKey);
        webDriver.findElement(fileTreeElementsLocater.rootFolder()).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.rootFolder())).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.contentMenuNewFolderBtn()));
        webDriver.findElement(fileTreeElementsLocater.contentMenuNewFolderBtn()).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.inputPathBox()));
        Assert.assertEquals("请输入新文件夹的路径", webDriver.findElement(fileTreeElementsLocater.newFolderPathMessage()).getText());
        Assert.assertEquals("/untitled", webDriver.findElement(fileTreeElementsLocater.inputPathBox()).getAttribute("value"));

        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(folderName);
        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("/" + folderName)));
        Assert.assertTrue(webDriver.findElement(By.id("/" + folderName)).isDisplayed());
    }

    @Test
    public void testCreateFolderByFileContentMenu() throws InterruptedException {
        enterLatestWs(spaceKey);
        webDriver.findElement(fileTreeElementsLocater.readmeFileLocater()).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.readmeFileLocater())).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.contentMenuNewFolderBtn()));
        webDriver.findElement(fileTreeElementsLocater.contentMenuNewFolderBtn()).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.newFolderPathMessage()));
        Assert.assertEquals("请输入新文件夹的路径", webDriver.findElement(fileTreeElementsLocater.newFolderPathMessage()).getText());
        Assert.assertEquals("/untitled", webDriver.findElement(fileTreeElementsLocater.inputPathBox()).getAttribute("value"));

        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(folderName);
        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("/" + folderName)));
        Assert.assertTrue(webDriver.findElement(By.id("/" + folderName)).isDisplayed());
    }


    @Test
    public void testCreateFolderByMenuBar() throws InterruptedException {
        enterLatestWs(spaceKey);
        webDriver.findElement(fileTreeElementsLocater.fileMenuBar()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.menuBarNewFolderBtn()));
        webDriver.findElement(fileTreeElementsLocater.menuBarNewFolderBtn()).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.newFolderPathMessage()));
        Assert.assertEquals("请输入新文件夹的路径", webDriver.findElement(fileTreeElementsLocater.newFolderPathMessage()).getText());
        Assert.assertEquals("/untitled", webDriver.findElement(fileTreeElementsLocater.inputPathBox()).getAttribute("value"));

        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(folderName);
        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("/" + folderName)));
        Assert.assertTrue(webDriver.findElement(By.id("/" + folderName)).isDisplayed());
    }
}
