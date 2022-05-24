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
 * @program: xxxx.uitest.filetree.RenameFileTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-11 16:58
 **/
public class RenameFileAndFolderTestCase extends AbstractTestCase {
    String spaceKey;
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
    public void testRenameFile() throws InterruptedException {
        enterLatestWs(spaceKey);
        webDriver.findElement(fileTreeElementsLocater.readmeFileLocater()).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.readmeFileLocater())).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.contentMenuRenameBtn()));
        webDriver.findElement(fileTreeElementsLocater.contentMenuRenameBtn()).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.inputPathBox()));
        Assert.assertEquals("请输入新文件路径/文件名", webDriver.findElement(fileTreeElementsLocater.newFileNameMsg()).getText());
        Assert.assertEquals("/README.md", webDriver.findElement(fileTreeElementsLocater.inputPathBox()).getAttribute("value"));

        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(fileName);
        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("/" + fileName)));
        Assert.assertTrue(webDriver.findElement(By.id("/" + fileName)).isDisplayed());
    }

    @Test
    public void testRenameFolder() throws InterruptedException {
        enterLatestWs(spaceKey);
        webDriver.findElement(fileTreeElementsLocater.cliFolderLocater()).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.cliFolderLocater())).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.contentMenuRenameBtn()));
        webDriver.findElement(fileTreeElementsLocater.contentMenuRenameBtn()).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.inputPathBox()));
        Assert.assertEquals("请输入新文件路径/文件名", webDriver.findElement(fileTreeElementsLocater.newFileNameMsg()).getText());
        Assert.assertEquals("/cli", webDriver.findElement(fileTreeElementsLocater.inputPathBox()).getAttribute("value"));

        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(fileName);
        webDriver.findElement(fileTreeElementsLocater.inputPathBox()).sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("/" + fileName)));
        Assert.assertTrue(webDriver.findElement(By.id("/" + fileName)).isDisplayed());
    }
}
