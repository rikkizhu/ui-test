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
 * @program: xxxx.uitest.filetree.OpenFileTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-02 14:49
 **/
public class OpenFolerAndFileTestCase extends AbstractTestCase {
    String wsName = RandomStringUtils.randomAlphanumeric(8);
    String spaceKey = null;
    By folderLocater = By.xpath("//div[@id='/cli']/div[1]");
    By fileLocater = By.id("/cli/hello.py");
    By openedFileLocater = By.xpath("//div[@id='tab_bar_tab_group_2']/ul[@class='tab-labels']/li[@class='tab-label active']");

    @Before
    public void tearUp() {
        spaceKey = createWsWithoutRemoteRepo(wsName, Steps.PythonDemo_TemplateId);
    }

    @After
    public void tearDown() {
        deleteWorkspaceBySpacekey(spaceKey);
    }

    @Test
    public void testOpenFileTestCase() throws InterruptedException {
        enterLatestWs(spaceKey);
        webDriver.findElement(folderLocater).click();
        actions.doubleClick(webDriver.findElement(folderLocater)).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(fileLocater));
        actions.doubleClick(webDriver.findElement(fileLocater)).build().perform();
        Thread.sleep(2000);
        Assert.assertEquals("/cli/hello.py", webDriver.findElement(openedFileLocater).getAttribute("title"));
    }
}
