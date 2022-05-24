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
 * @program: CreateFileTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-11 10:43
 **/
public class CreateFileTestCase extends AbstractTestCase {
    String spaceKey = null;
    String wsName = RandomStringUtils.randomAlphanumeric(8);
    String fileName = RandomStringUtils.randomAlphanumeric(6);

    @Before
    public void tearUp() {
        spaceKey = createWsWithoutRemoteRepo(wsName, Steps.PythonDemo_TemplateId);
    }

    @After
    public void tearDown() {
        deleteWorkspaceBySpacekey(spaceKey);
    }

    @Test
    public void craeteFileByFolderContentMunu() throws InterruptedException {
        enterLatestWs(spaceKey);
        webDriver.findElement(fileTreeElementsLocater.cliFolderLocater()).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.cliFolderLocater())).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.contentMenuCreateFile()));
        webDriver.findElement(fileTreeElementsLocater.contentMenuCreateFile()).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.inputPathBox()));
        Assert.assertEquals("请输入新文件的路径", fileTreeElementsLocater.newFilePathMessage());
        Assert.assertEquals("/cli/untitled", webDriver.findElement(fileTreeElementsLocater.inputPathBox()).getAttribute("value"));

        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(fileName);
        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(fileTreeElementsLocater.cliFolderLocater()));
        actions.doubleClick(webDriver.findElement(fileTreeElementsLocater.cliFolderLocater())).build().perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("/cli/" + fileName)));
        Assert.assertTrue(webDriver.findElement(By.id("/cli/" + fileName)).isDisplayed());
    }

    @Test
    public void createFileByRootFolderContentMeNu() throws InterruptedException {
        enterLatestWs(spaceKey);
        webDriver.findElement(fileTreeElementsLocater.rootFolder()).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.rootFolder())).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.contentMenuCreateFile()));
        webDriver.findElement(fileTreeElementsLocater.contentMenuCreateFile()).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.inputPathBox()));
        Assert.assertEquals("请输入新文件的路径", fileTreeElementsLocater.newFilePathMessage());
        Assert.assertEquals("/untitled", webDriver.findElement(fileTreeElementsLocater.inputPathBox()).getAttribute("value"));

        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(fileName);
        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("/" + fileName)));
        Assert.assertTrue(webDriver.findElement(By.id("/" + fileName)).isDisplayed());
    }

    @Test
    public void createFileByFileContentMenu() throws InterruptedException {
        enterLatestWs(spaceKey);
        webDriver.findElement(fileTreeElementsLocater.readmeFileLocater()).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.readmeFileLocater())).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.contentMenuCreateFile()));
        webDriver.findElement(fileTreeElementsLocater.contentMenuCreateFile()).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.inputPathBox()));
        Assert.assertEquals("请输入新文件的路径", fileTreeElementsLocater.newFilePathMessage());
        Assert.assertEquals("/untitled", webDriver.findElement(fileTreeElementsLocater.inputPathBox()).getAttribute("value"));

        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(fileName);
        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("/" + fileName)));
        Assert.assertTrue(webDriver.findElement(By.id("/" + fileName)).isDisplayed());
    }

    @Test
    public void createFileByMenuBar() throws InterruptedException {
        enterLatestWs(spaceKey);
        webDriver.findElement(fileTreeElementsLocater.fileMenuBar()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.menuBarNewFileBtn()));
        webDriver.findElement(fileTreeElementsLocater.menuBarNewFileBtn()).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.inputPathBox()));
        Assert.assertEquals("请输入新文件的路径", fileTreeElementsLocater.newFilePathMessage());
        Assert.assertEquals("/untitled", webDriver.findElement(fileTreeElementsLocater.inputPathBox()).getAttribute("value"));

        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(fileName);
        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("/" + fileName)));
        Assert.assertTrue(webDriver.findElement(By.id("/" + fileName)).isDisplayed());
    }

    @Test
    public void createFileByTabGroup() throws InterruptedException {
        enterLatestWs(spaceKey);
        webDriver.findElement(fileTreeElementsLocater.addNewFileBtn()).click();
        webDriver.findElement(fileTreeElementsLocater.fileMenuBar()).click();
        webDriver.findElement(fileTreeElementsLocater.menuBarSaveBtn()).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.inputPathBox()));
        Assert.assertEquals("请输入新文件的路径", webDriver.findElement(fileTreeElementsLocater.newFilePathOtMsg()).getText());
        Assert.assertEquals("/未命名", webDriver.findElement(fileTreeElementsLocater.inputPathBox()).getAttribute("value"));


        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(fileName);
        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("/" + fileName)));
        Assert.assertTrue(webDriver.findElement(By.id("/" + fileName)).isDisplayed());
    }
}
