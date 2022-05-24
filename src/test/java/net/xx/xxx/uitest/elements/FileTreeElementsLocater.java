package net.xx.xxx.uitest.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @program: FileTreeElementsLocater
 * @description:
 * @author: zhuruiqi
 * @create: 2019-02-11 11:03
 **/
public class FileTreeElementsLocater {

    public WebDriver webDriver;

    public FileTreeElementsLocater(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public By contentMenuCreateFile() {
        return By.id("fileTree.contextMenu.newFile");
    }

    public String newFilePathMessage() {
        return webDriver.findElement(By.id("file.newFilePath")).getText();
    }

    public By inputPathBox() {
        return By.xpath("//input[@class='form-control']");
    }

    public By rootFolder() {
        return By.xpath("//div[@class='filetree-container']/div/div[1]");
    }

    public By fileMenuBar() {
        return By.id("menuBarItems.file.main");
    }

    public By menuBarNewFileBtn() {
        return By.id("menuBarItems.file.newFile");
    }


    public By contentMenuNewFolderBtn() {
        return By.id("fileTree.contextMenu.newFolder");
    }

    public By newFolderPathMessage() {
        return By.id("file.newFileFolderPath");
    }

    public By menuBarNewFolderBtn() {
        return By.id("menuBarItems.file.newFolder");
    }

    public By menuBarSaveBtn() {
        return By.id("menuBarItems.file.save");
    }

    public By readmeFileLocater() {
        return By.id("/README.md");
    }

    public By fileLocater(String fileNanme) {
        return By.id("/" + fileNanme);
    }

    public By cliFolderLocater() {
        return By.xpath("//div[@id='/cli']/div[1]");
    }

    public By contentMenuRenameBtn() {
        return By.id("fileTree.contextMenu.rename");
    }

    public By newFileNameMsg() {
        return By.id("file.newFileName");
    }

    public By deleteFileBtn() {
        return By.id("fileTree.contextMenu.delete");
    }

    public By acceptDeleteBtn() {
        return By.id("file.deleteButton");
    }

    public By addNewFileBtn() {
        return By.xpath("//div[@id='tab_bar_tab_group_2']/div[1]");
    }

    public By newFilePathOtMsg() {
        return By.id("ot.newFilePath");
    }

    public By uploadFileElement() {
        return By.id("filetree-hidden-input");
    }

    public By downloadBtn() {
        return By.id("fileTree.contextMenu.download");
    }

    public By contentMenuSynMenu() {
        return By.id("fileTree.contextMenu.sync");
    }

    public By focusDirArrow() {
        return By.xpath("//div[@class='filetree-node focus']/span[@class='filetree-node-arrow']");
    }

    public By folderArrows(){
        return By.xpath("//span[@class='filetree-node-arrow']");
    }

    public By gitIgnoreBtn() {
        return By.id("fileTree.contextMenu.ignore");
    }

    public By gitIgnoreFile() {
        return By.id("/.gitignore");
    }

    public By fileContentMebuBtnList() {
        return By.xpath("//ul[@class='menu']/li[@class='menu-item']");
    }

    public By uploadBtn() {
        return By.id("fileTree.contextMenu.upload");
    }

    public By synFileTreeBtn(){
        return By.xpath("//i[@class='sync fa fa-refresh ']");
    }

    public By collapseFileTreeBtn(){
        return By.xpath("//span[@class='collapse ']");
    }
}





