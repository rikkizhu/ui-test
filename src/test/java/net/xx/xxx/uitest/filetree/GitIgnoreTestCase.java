package net.xx.xxx.uitest.filetree;

import net.xx.xxx.uitest.AbstractTestCase;
import net.xx.xxx.uitest.Steps;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @program: GitIgnoreTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-12 14:58
 **/

public class GitIgnoreTestCase extends AbstractTestCase {
    String spaceKey = null;
    String wsName;
    String fileName;
    String folderName;

    @Before
    public void tearUp() {
        wsName = RandomStringUtils.randomAlphanumeric(8);
        fileName = RandomStringUtils.randomAlphanumeric(6);
        folderName = RandomStringUtils.randomAlphanumeric(6);
        spaceKey = createWsByTemplate(wsName, daWsManagerElementsLocater.pythonTemplateCard());
    }

    @After
    public void tearDown() {
        switchHandles(0);
        deleteProject(wsName);
        deleteWorkspaceBySpacekey(spaceKey);
    }

    @Test
    public void testGitIgnoreFile() throws InterruptedException {
        createFile(spaceKey, fileName);
        enterLatestWs(spaceKey);

        Assert.assertFalse(isElementPresent(fileTreeElementsLocater.gitIgnoreFile()));

        webDriver.findElement(fileTreeElementsLocater.fileLocater(fileName)).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.fileLocater(fileName))).build().perform();
        webDriver.findElement(fileTreeElementsLocater.gitIgnoreBtn()).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.gitIgnoreFile()));
        Assert.assertEquals("/.gitignore",
                webDriver.findElement(fileGroupElementLocater.openedFileinFileGroup()).getAttribute("title"));
        Assert.assertEquals("/" + fileName,
                webDriver.findElement(By.xpath("//div[@class='view-lines']/div/span/span")).getText());

        webDriver.findElement(gitElementsLocater.gitMenuBar()).click();
        webDriver.findElement(gitElementsLocater.gitCommitBtn()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(gitElementsLocater.acceptGitCommit()));
        webDriver.findElement(gitElementsLocater.acceptGitCommit()).click();
        Thread.sleep(1500);
        webDriver.findElement(gitElementsLocater.gitMenuBar()).click();
        webDriver.findElement(gitElementsLocater.pushBtn()).click();

        ((JavascriptExecutor) webDriver).executeScript("window.open('about:blank','_blank');");
        switchHandles(1);
        loginDevPlatform();
        webDriver.navigate().to(Steps.devPlatformUrl + "/u/" + Steps.username + "/p/" + wsName + "/git");

        Thread.sleep(1000);
        Assert.assertEquals(4, numberOfElements(By.xpath("//tbody/tr")));
        webDriver.close();
    }

    @Test
    public void testGitIgnoreFolder() throws InterruptedException {
        createFolder(spaceKey, folderName);
        enterLatestWs(spaceKey);

        Assert.assertFalse(isElementPresent(fileTreeElementsLocater.gitIgnoreFile()));

        webDriver.findElement(fileTreeElementsLocater.fileLocater(folderName)).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.fileLocater(folderName))).build().perform();
        webDriver.findElement(fileTreeElementsLocater.gitIgnoreBtn()).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(fileTreeElementsLocater.gitIgnoreFile()));
        Assert.assertEquals("/.gitignore",
                webDriver.findElement(fileGroupElementLocater.openedFileinFileGroup()).getAttribute("title"));
        Assert.assertEquals("/" + folderName + "/",
                webDriver.findElement(By.xpath("//div[@class='view-lines']/div/span/span")).getText());

        webDriver.findElement(gitElementsLocater.gitMenuBar()).click();
        webDriver.findElement(gitElementsLocater.gitCommitBtn()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(gitElementsLocater.acceptGitCommit()));
        webDriver.findElement(gitElementsLocater.acceptGitCommit()).click();
        Thread.sleep(1500);
        webDriver.findElement(gitElementsLocater.gitMenuBar()).click();
        webDriver.findElement(gitElementsLocater.pushBtn()).click();

        ((JavascriptExecutor) webDriver).executeScript("window.open('about:blank','_blank');");
        switchHandles(1);
        loginDevPlatform();
        webDriver.navigate().to(Steps.devPlatformUrl + "/u/" + Steps.username + "/p/" + wsName + "/git");

        Thread.sleep(1000);
        Assert.assertEquals(4, numberOfElements(By.xpath("//tbody/tr")));
        webDriver.close();
    }
}
