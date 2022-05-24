package net.xx.xxx.uitest;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * @program: PACKAGE_NAME.Steps
 * @description:
 * @author: zhuruiqi
 * @create: 2019-01-15 14:40
 **/
public class Steps {
    private static Cookie cookie;
    public static String password = "xxxx";
    public static String username = "xxxx";
    public static Integer Balnk_TemplateId = 6;
    public static Integer JavaDemo_TemplateId = 2;
    public static Integer WordPress_TemplateId = 10;
    public static Integer PythonDemo_TemplateId = 9;
    public static Integer AI_TemplateId = 7;
    public static String pluginTypeId = String.valueOf(8);
    public static String pluginTemplateId = String.valueOf(8);
    public static String devPlatformUrl="https://dev.xxx.com";

    public static String getBaseUrl() {
        Map<String, String> map = System.getenv();
        return map.get("BASE_URL");
    }

    public String getSessionDomain() {
        Map<String, String> map = System.getenv();
        return map.get("SESSION_DOMAIN");
    }

    public String getDashboardUrl() {
        Map<String, String> map = System.getenv();
        return map.get("DASHBOARD_URL");
    }

    public String getHeadlessMode() {
        Map<String, String> map = System.getenv();
        return map.get("HEADLESS_MODE");
    }

    public WebDriver openChromeBrowser() {
        String downloadFilepath = new File("./target/download").getAbsolutePath();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        Steps steps = new Steps();
        if (steps.getHeadlessMode().equals("true")) {
            chromeOptions.addArguments("--headless");
        }
        return new ChromeDriver(chromeOptions);
    }


    public static Cookie getCookie() {
        if (cookie == null) {
            Map<String, String> map = System.getenv();
            RestAssured.baseURI = map.get("BASE_URL");
            RestAssured.basePath = "/backend";

            String stringCookie = given().when().formParams(new HashMap<String, String>() {
                {
                    put("email", "xxxx");
                    put("password", password);
                }
            }).post("/login").andReturn().cookie("DEV-STUDIO-SESSION");

            cookie = new Cookie.Builder("DEV-STUDIO-SESSION", stringCookie).build();

            return cookie;
        }

        return cookie;
    }

    public static RequestSpecification getRequestSpec() {
        Map<String, String> map = System.getenv();
        RestAssured.baseURI = map.get("BASE_URL");
        RestAssured.basePath = "/backend";
        return given().cookie(getCookie());
    }

}
