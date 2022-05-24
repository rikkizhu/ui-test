package net.xx.xxx.uitest.filetree;

import net.xx.xxx.uitest.AbstractTestCase;
import net.xx.xxx.uitest.Steps;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * @program: FileTreeButtonListTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-15 10:39
 **/
public class FileTreeButtonListTestCase extends AbstractTestCase {
    String spaceKey = null;
    String wsName = RandomStringUtils.randomAlphanumeric(8);

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
    public void testFileContentMenu(){
        webDriver.findElement(fileTreeElementsLocater.readmeFileLocater()).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.readmeFileLocater())).build().perform();
        Assert.assertEquals(6,numberOfElements(fileTreeElementsLocater.fileContentMebuBtnList()));
        Assert.assertEquals("新建文件...",webDriver.findElement(fileTreeElementsLocater.contentMenuCreateFile()).getText());
        Assert.assertEquals("新建文件夹...",webDriver.findElement(fileTreeElementsLocater.contentMenuNewFolderBtn()).getText());
        Assert.assertEquals("删除...",webDriver.findElement(fileTreeElementsLocater.deleteFileBtn()).getText());
        Assert.assertEquals("重命名...",webDriver.findElement(fileTreeElementsLocater.contentMenuRenameBtn()).getText());
        Assert.assertEquals("下载",webDriver.findElement(fileTreeElementsLocater.downloadBtn()).getText());
        Assert.assertEquals("移出版本控制",webDriver.findElement(fileTreeElementsLocater.gitIgnoreBtn()).getText());
    }

    @Test
    public void testFolderContentMenu(){
        webDriver.findElement(fileTreeElementsLocater.cliFolderLocater()).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.cliFolderLocater())).build().perform();
        Assert.assertEquals(8,numberOfElements(fileTreeElementsLocater.fileContentMebuBtnList()));
        Assert.assertEquals("新建文件...",webDriver.findElement(fileTreeElementsLocater.contentMenuCreateFile()).getText());
        Assert.assertEquals("新建文件夹...",webDriver.findElement(fileTreeElementsLocater.contentMenuNewFolderBtn()).getText());
        Assert.assertEquals("删除...",webDriver.findElement(fileTreeElementsLocater.deleteFileBtn()).getText());
        Assert.assertEquals("重命名...",webDriver.findElement(fileTreeElementsLocater.contentMenuRenameBtn()).getText());
        Assert.assertEquals("下载",webDriver.findElement(fileTreeElementsLocater.downloadBtn()).getText());
        Assert.assertEquals("上传",webDriver.findElement(fileTreeElementsLocater.uploadBtn()).getText());
        Assert.assertEquals("同步文件",webDriver.findElement(fileTreeElementsLocater.contentMenuSynMenu()).getText());
        Assert.assertEquals("移出版本控制",webDriver.findElement(fileTreeElementsLocater.gitIgnoreBtn()).getText());
    }

    @Test
    public void testRootFolderContentMenu(){
        webDriver.findElement(fileTreeElementsLocater.rootFolder()).click();
        actions.contextClick(webDriver.findElement(fileTreeElementsLocater.rootFolder())).build().perform();
        Assert.assertEquals(6,numberOfElements(fileTreeElementsLocater.fileContentMebuBtnList()));
        Assert.assertEquals("新建文件...",webDriver.findElement(fileTreeElementsLocater.contentMenuCreateFile()).getText());
        Assert.assertEquals("新建文件夹...",webDriver.findElement(fileTreeElementsLocater.contentMenuNewFolderBtn()).getText());
        Assert.assertEquals("下载",webDriver.findElement(fileTreeElementsLocater.downloadBtn()).getText());
        Assert.assertEquals("上传",webDriver.findElement(fileTreeElementsLocater.uploadBtn()).getText());
        Assert.assertEquals("同步文件",webDriver.findElement(fileTreeElementsLocater.contentMenuSynMenu()).getText());
        Assert.assertEquals("移出版本控制",webDriver.findElement(fileTreeElementsLocater.gitIgnoreBtn()).getText());
    }

}
