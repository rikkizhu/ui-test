package net.xx.xxx.uitest.filetree;

import net.xx.xxx.uitest.AbstractTestCase;
import net.xx.xxx.uitest.Steps;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;


/**
 * @program: DownloadTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-12 11:17
 **/
public class DownloadTestCase extends AbstractTestCase{
    String spaceKey = null;
    String wsName = RandomStringUtils.randomAlphanumeric(8);
    File downloadDir =null;

    @Before
    public void tearUp() {
        downloadDir = new File("./target/download");
        downloadDir.mkdir();
        spaceKey = createWsWithoutRemoteRepo(wsName, Steps.PythonDemo_TemplateId);
    }

    @After
    public void tearDown() {
        deleteGeneratedFile("./target/download");
        deleteWorkspaceBySpacekey(spaceKey);
    }

    @Test
    public void testDownloadFile() throws InterruptedException {
        enterLatestWs(spaceKey);
        webDriver.findElement(fileTreeElementsLocater.readmeFileLocater()).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.readmeFileLocater())).build().perform();
        webDriver.findElement(fileTreeElementsLocater.downloadBtn()).click();

        Thread.sleep(3000);

        Assert.assertTrue(isFileExists(downloadDir,"README.md"));
    }

    @Test
    public void testDownloadFolder() throws InterruptedException {
        enterLatestWs(spaceKey);
        webDriver.findElement(fileTreeElementsLocater.cliFolderLocater()).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.cliFolderLocater())).build().perform();
        webDriver.findElement(fileTreeElementsLocater.downloadBtn()).click();

        Thread.sleep(3000);

        Assert.assertTrue(isFileExists(downloadDir,"cli.tar.gz"));
    }

    @Test
    public void testDownloadRootDir() throws InterruptedException {
        enterLatestWs(spaceKey);
        webDriver.findElement(fileTreeElementsLocater.rootFolder()).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.rootFolder())).build().perform();
        webDriver.findElement(fileTreeElementsLocater.downloadBtn()).click();

        Thread.sleep(3000);

        Assert.assertTrue(isFileExists(downloadDir, "empty-template.tar.gz"));
    }
}
