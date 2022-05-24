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

import java.io.File;
import java.io.IOException;

/**
 * @program: xxxx.uitest.filetree.uploadFileTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-12 10:26
 **/
public class UploadFileTestCase extends AbstractTestCase {
    String spaceKey = null;
    String filePath = null;
    String wsName = RandomStringUtils.randomAlphanumeric(8);
    String fileName = RandomStringUtils.randomAlphanumeric(6);

    @Before
    public void tearUp() throws IOException {
        filePath = generateFile(fileName, "testupload");
        spaceKey = createWsWithoutRemoteRepo(wsName, Steps.PythonDemo_TemplateId);
    }

    @After
    public void tearDown() {
        deleteGeneratedFile("./target/upload");
        deleteWorkspaceBySpacekey(spaceKey);
    }

    @Test
    public void testUploadFileByFolderContentMenu() throws InterruptedException {
        enterLatestWs(spaceKey);
        webDriver.findElement(fileTreeElementsLocater.cliFolderLocater()).click();
        webDriver.findElement(fileTreeElementsLocater.uploadFileElement()).sendKeys(new File(filePath).getAbsolutePath());
        actions.doubleClick(webDriver.findElement(fileTreeElementsLocater.cliFolderLocater())).build().perform();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("/cli/" + fileName)));
        Assert.assertTrue(webDriver.findElement(By.id("/cli/" + fileName)).isDisplayed());
    }

    @Test
    public void testUploadFileByRootDirContentMenu() throws InterruptedException {
        enterLatestWs(spaceKey);
        webDriver.findElement(fileTreeElementsLocater.rootFolder()).click();
        webDriver.findElement(fileTreeElementsLocater.uploadFileElement()).sendKeys(new File(filePath).getAbsolutePath());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("/" + fileName)));
        Assert.assertTrue(webDriver.findElement(By.id("/" + fileName)).isDisplayed());
    }
}
