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
 * @program: CollapseFileTreeTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-15 11:46
 **/
public class CollapseFileTreeTestCase extends AbstractTestCase {
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
    public void testCollapseFileTree() throws InterruptedException {
        enterLatestWs(spaceKey);
        actions.doubleClick(webDriver.findElement(fileTreeElementsLocater.cliFolderLocater())).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("/cli/hello.py")));

        webDriver.findElement(fileTreeElementsLocater.collapseFileTreeBtn()).click();
        Thread.sleep(1500);
        Assert.assertEquals(0,numberOfElements(By.id("/cli/hello.py")));
    }
}
