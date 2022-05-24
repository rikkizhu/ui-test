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
 * @program: HorizontalSplitTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-15 15:41
 **/
public class HorizontalSplitTestCase extends AbstractTestCase {
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
    public void testHorizontalSplitFileTabs() throws InterruptedException {
        actions.doubleClick(webDriver.findElement(fileTreeElementsLocater.readmeFileLocater())).build().perform();
        webDriver.findElement(fileTreeElementsLocater.cliFolderLocater()).click();
        actions.doubleClick(webDriver.findElement(fileTreeElementsLocater.cliFolderLocater())).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("/cli/hello.py")));
        actions.doubleClick(webDriver.findElement(By.id("/cli/hello.py"))).build().perform();
        actions.doubleClick(webDriver.findElement(By.id("/cli/snake.py"))).build().perform();
        Thread.sleep(1000);

        Assert.assertEquals(5, numberOfElements(fileGroupElementLocater.tabsInGroupFile()));

        actions.contextClick(webDriver.findElement(fileGroupElementLocater.openedFileinFileGroup())).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fileGroupElementLocater.horizontalSplitBtn()));
        webDriver.findElement(fileGroupElementLocater.horizontalSplitBtn()).click();
        Thread.sleep(1000);

        Assert.assertTrue(isElementPresent(By.xpath("//div[@class='pane-container row resizable']")));
        Assert.assertEquals(4, numberOfElements(fileGroupElementLocater.tabsInGroupFile()));
        Assert.assertEquals("/cli/snake.py", webDriver.findElement(By.xpath
                ("//div[@id='tab_bar_tab_group_21']/ul[@class='tab-labels']/li[@class='tab-label active']"))
                .getAttribute("title"));
    }
}
