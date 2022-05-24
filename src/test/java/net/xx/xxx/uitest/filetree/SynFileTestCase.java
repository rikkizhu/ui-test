package net.xx.xxx.uitest.filetree;

import net.xx.xxx.uitest.AbstractTestCase;
import net.xx.xxx.uitest.Steps;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @program: SynFileTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-12 14:05
 **/
public class SynFileTestCase extends AbstractTestCase {
    String wsName = RandomStringUtils.randomAlphanumeric(8);
    String spaceKey = null;

    @Before
    public void tearUp() {
        spaceKey = createWsWithoutRemoteRepo(wsName, Steps.PythonDemo_TemplateId);
    }

    @After
    public void tearDown() {
        deleteWorkspaceBySpacekey(spaceKey);
    }

    @Test
    public void testSynFileByRootFolderContentMenu() throws InterruptedException {
        enterLatestWs(spaceKey);

        webDriver.findElement(fileTreeElementsLocater.rootFolder()).click();
        Assert.assertTrue(webDriver.findElement(fileTreeElementsLocater.focusDirArrow()).isDisplayed());

        webDriver.findElement(fileTreeElementsLocater.rootFolder()).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.rootFolder())).build().perform();
        webDriver.findElement(fileTreeElementsLocater.contentMenuSynMenu()).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(fileTreeElementsLocater.focusDirArrow(), 0));
    }

    @Test
    public void testSynFileByFolderContentMenu() throws InterruptedException {
        enterLatestWs(spaceKey);

        webDriver.findElement(fileTreeElementsLocater.cliFolderLocater()).click();
        Assert.assertTrue(webDriver.findElement(fileTreeElementsLocater.focusDirArrow()).isDisplayed());

        webDriver.findElement(fileTreeElementsLocater.cliFolderLocater()).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.cliFolderLocater())).build().perform();
        webDriver.findElement(fileTreeElementsLocater.contentMenuSynMenu()).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(fileTreeElementsLocater.focusDirArrow(), 0));
    }

    @Test
    public void testSynFiletree() throws InterruptedException {
        enterLatestWs(spaceKey);

        webDriver.findElement(fileTreeElementsLocater.synFileTreeBtn()).click();
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(fileTreeElementsLocater.folderArrows(), 4));
    }

}
