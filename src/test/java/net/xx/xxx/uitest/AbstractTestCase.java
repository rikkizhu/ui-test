package net.xx.xxx.uitest;

import io.restassured.response.Response;
import xxxx.uitest.elements.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * @program: PACKAGE_NAME.AbstractTestCase
 * @description:
 * @author: zhuruiqi
 * @create: 2019-01-15 14:57
 **/
public class AbstractTestCase {
    protected static WebDriver webDriver;
    protected Actions actions = new Actions(webDriver);
    protected WebDriverWait wait = new WebDriverWait(webDriver, 30);
    protected DaWsManagerElementsLocater daWsManagerElementsLocater = new DaWsManagerElementsLocater(webDriver);
    protected WsElementsLocater wsElementsLocater = new WsElementsLocater(webDriver);
    protected PluginElementsLocater pluginElementsLocater = new PluginElementsLocater(webDriver);
    protected FileTreeElementsLocater fileTreeElementsLocater = new FileTreeElementsLocater(webDriver);
    protected GitElementsLocater gitElementsLocater = new GitElementsLocater(webDriver);
    protected FileGroupElementLocater fileGroupElementLocater = new FileGroupElementLocater(webDriver);


    @BeforeClass
    public static void enterCloudStudio() {
        Steps steps = new Steps();
        webDriver = steps.openChromeBrowser();
        webDriver.navigate().to(steps.getBaseUrl());
        webDriver.manage().addCookie(new Cookie("DEV-STUDIO-SESSION", steps.getCookie().getValue(),
                steps.getSessionDomain(), "/", null));
        webDriver.navigate().to(steps.getDashboardUrl());
    }

    @AfterClass
    public static void closeBrowser() {
        webDriver.quit();
    }


    public void enterLatestWs(String spaceKey) throws InterruptedException {
        webDriver.navigate().to(Steps.getBaseUrl() + "/ws/" + spaceKey);
        try {
            webDriver.switchTo().alert().accept();
        } catch (Exception e) {
        }
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(wsElementsLocater.wsIframe()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(wsElementsLocater.wsHeader()));
        Thread.sleep(1000);
        wait.until(ExpectedConditions.invisibilityOfElementLocated((wsElementsLocater.enterWsMask())));
    }

    public void createPorject(String proName) {
        Steps.getRequestSpec().formParams(new HashMap<String, String>() {
            {
                put("type", "2");
                put("gitEnabled", "true");
                put("gitReadmeEnabled", "false");
                put("vcsType", "git");
                put("name", proName);
            }
        }).post("/projects");
    }

    public String computeTwoFactorCode(String password) {
        return DigestUtils.sha1Hex(password);
    }

    public void deleteProject(final String projectName) {
        Steps.getRequestSpec().accept("application/vnd.xxx.v2+json")
                .param("projectName", projectName)
                .param("twoFactorCode", computeTwoFactorCode(Steps.password))
                .delete("/project");
    }

    public void deleteWorkspaceBySpacekey(String spaceKey) {
        Steps.getRequestSpec()
                .param("spaceKey", spaceKey).delete("/ws/delete");
    }


    public String createWsWithoutRemoteRepo(final String wsName, final Integer TemplateId) {
        Response response = Steps.getRequestSpec().accept("application/vnd.xxx.v2+json")
                .formParams(new HashMap<String, Object>() {{
                    put("cpuLimit", 2);
                    put("memory", 2048);
                    put("storage", 2);
                    put("workspaceName", wsName);
                    put("ownerName", "xxxxxx");
                    put("projectName", "empty-template");
                    put("templateId", TemplateId);
                }}).post("/workspaces").then().extract().response();

        return response.path("spaceKey");
    }

    public String queryWsStatus(String spaceKey) {
        Response response = Steps.getRequestSpec().accept("application/vnd.xxx.v2+json")
                .get("/workspaces/" + spaceKey).then().extract().response();

        return response.path("workingStatus");
    }

    public String queryWsRemoteRepo(String spaceKey) {
        Response response = Steps.getRequestSpec().accept("application/vnd.xxx.v2+json")
                .get("/workspaces/" + spaceKey).then().extract().response();
        return response.path("project.sshUrl");
    }


    public String getFirstDevelopedPluginId(WebDriver webDriver) {
        PluginElementsLocater pluginElementsLocater = new PluginElementsLocater(webDriver);
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 30);
        webDriver.navigate().to(Steps.getBaseUrl() + "/dashboard/plugin/mine");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(pluginElementsLocater.firstDevelopedPluginManageBtn()));
        webDriver.findElement(pluginElementsLocater.firstDevelopedPluginManageBtn()).click();

        return webDriver.findElement(pluginElementsLocater.pluginIdinMyDevelopedPlugin()).getText();

    }

    public void deletePlugin(String pluginId) {
        Steps.getRequestSpec().accept("application/vnd.xxx.v2+json")
                .delete("/user-plugin/" + pluginId);
    }

    public String createPlugin(String pluginName, String repoName, String typeId, String pluginTemplateId, String remark) {
        Response response = Steps.getRequestSpec().accept("application/vnd.xxx.v2+json")
                .formParams(new HashMap<String, Object>() {{
                    put("cpuLimit", 2);
                    put("memory", 2048);
                    put("storage", 2);
                    put("pluginName", pluginName);
                    put("repoName", repoName);
                    put("typeId", typeId);
                    put("pluginTemplateId", pluginTemplateId);
                    put("remark", remark);
                }}).post("/user-plugin/create")
                .then().extract().response();
        return response.path("data.spaceKey");
    }

    public Boolean queryIsPrePublished(String pluginId) {
        Response response = Steps.getRequestSpec().accept("application/vnd.xxx.v2+json")
                .get("/user-plugin/info?pluginId=" + pluginId)
                .then().extract().response();
        Object isPrePublished = response.path("data.pluginVersions");
        if (isPrePublished == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isElementPresent(By selector) {
        return webDriver.findElements(selector).size() > 0;
    }

    public int numberOfElements(By selector){
        return webDriver.findElements(selector).size();
    }

    public String generateFile(String filename, String fileContent) throws IOException {
        File dir = new File("./target/upload");
        dir.mkdir();
        File file = new File("./target/upload/" + filename);
        file.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(fileContent);
        bufferedWriter.flush();
        bufferedWriter.close();
        return file.getAbsolutePath();
    }

    public void deleteGeneratedFile(String path) {
        FileUtils.deleteQuietly(new File(path));
    }

    public static boolean isFileExists(File downloadPath, String fileName) {

        File[] files = downloadPath.listFiles();
        for (File file : files) {
            if (file.getName().equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    public String createWsByTemplate(String wsName, By templateLocater) {
        Steps steps = new Steps();
        if (webDriver.getCurrentUrl()!=steps.getDashboardUrl()){
            webDriver.navigate().to(steps.getDashboardUrl());
            try {
                webDriver.switchTo().alert().accept();
            } catch (Exception e) {
            }
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(daWsManagerElementsLocater.createWorkspaceCard()));
        webDriver.findElement(daWsManagerElementsLocater.createWorkspaceCard()).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(daWsManagerElementsLocater.createWsByTemplateTab()));
        webDriver.findElement(daWsManagerElementsLocater.createWsByTemplateTab()).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(daWsManagerElementsLocater.proNameInput()));
        webDriver.findElement(daWsManagerElementsLocater.proNameInput()).sendKeys(wsName);
        webDriver.findElement(templateLocater).click();

        webDriver.findElements(daWsManagerElementsLocater.createWorkspaceBtn()).get(1).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(daWsManagerElementsLocater.firstWsCard()));

        return webDriver.findElement(daWsManagerElementsLocater.firstWsCard()).getAttribute("href").split("/")[4];
    }

    public void createFile(String spaceKey, String fileName) {
        Steps.getRequestSpec().header("x-space-key", spaceKey)
                .accept("application/vnd.xxx.v2+json")
                .param("path", "/" + fileName)
                .post("/workspaces/" + spaceKey + "/files");
    }

    public void createFolder(String spaceKey,String folderName){
        Steps.getRequestSpec().accept("application/vnd.xxx.v2+json")
                .header("x-space-key", spaceKey)
                .formParams(new HashMap<String, String>() {{
                    put("path", folderName);
                }}).post("/workspaces/" + spaceKey + "/mkdir");
    }

    public void loginDevPlatform() {
        if (ifLoginedDevPlatform() == false) {
            webDriver.findElement(By.xpath("//*[@id='loginBox']/div/div/div[3]/div[2]/div/a/span")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='clg-input J-username']")));
            webDriver.findElement(By.xpath("//input[@class='clg-input J-username']")).sendKeys("codemarttest@gmail.com");
            webDriver.findElement(By.xpath("//input[@class='clg-input J-password']")).sendKeys("xxx_123");
            webDriver.findElement(By.xpath("//*[@id='loginBox']/div/div/div[2]/div[2]/div[2]/a[1]")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='info-content-Neo']")));
        } else if (ifLoginedDevPlatform() == true) {
        }

    }

    public Boolean ifLoginedDevPlatform() {
        webDriver.navigate().to(Steps.devPlatformUrl+"/login");
        WebDriverWait waitIframe = new WebDriverWait(webDriver, 5);
        try {
            waitIframe.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("login_iframe")));
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public void switchHandles(int windowIndex){
        String[] handles = new String[webDriver.getWindowHandles().size()];
        webDriver.getWindowHandles().toArray(handles);
        webDriver.switchTo().window(handles[windowIndex]);
    }


}
