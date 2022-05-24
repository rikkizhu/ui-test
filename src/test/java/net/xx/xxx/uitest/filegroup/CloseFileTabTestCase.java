package net.xx.xxx.uitest.filegroup;

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
 * @program: CloseFileTabTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-15 14:24
 **/
public class CloseFileTabTestCase extends AbstractTestCase {
    String wsName = RandomStringUtils.randomAlphanumeric(8);
    String spaceKey = null;

    @Before
    public void tearUp() throws InterruptedException {
        spaceKey = createWsWithoutRemoteRepo(wsName, Steps.PythonDemo_TemplateId);
        enterLatestWs(spaceKey);
    }

    @After
    public void tearDown() {
        deleteWorkspaceBySpacekey(spaceKey);
    }

    @Test
    public void testCloseFileTab() throws InterruptedException {
        actions.doubleClick(webDriver.findElement(fileTreeElementsLocater.readmeFileLocater())).build().perform();
        webDriver.findElement(fileTreeElementsLocater.cliFolderLocater()).click();
        actions.doubleClick(webDriver.findElement(fileTreeElementsLocater.cliFolderLocater())).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("/cli/hello.py")));
        actions.doubleClick(webDriver.findElement(By.id("/cli/hello.py"))).build().perform();
        actions.doubleClick(webDriver.findElement(By.id("/cli/snake.py"))).build().perform();
        Thread.sleep(1000);

        Assert.assertEquals(5,numberOfElements(fileGroupElementLocater.tabsInGroupFile()));

        actions.contextClick(webDriver.findElement(fileGroupElementLocater.openedFileinFileGroup())).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fileGroupElementLocater.closeFileTabBtn()));
        webDriver.findElement(fileGroupElementLocater.closeFileTabBtn()).click();

        Assert.assertEquals(4,numberOfElements(fileGroupElementLocater.tabsInGroupFile()));
        Assert.assertEquals("/cli/hello.py",webDriver.findElement(fileGroupElementLocater.openedFileinFileGroup()).getAttribute("title"));
        Assert.assertEquals(0,numberOfElements(fileGroupElementLocater.fileTabLocater("/cli/snake.py")));
    }
}
