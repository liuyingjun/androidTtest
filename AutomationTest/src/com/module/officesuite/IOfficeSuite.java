package com.module.officesuite;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

public interface IOfficeSuite {

    public void launchOfficeSuite();

    /**
     * the path by default is under 'Android'
     * @param fileName
     */
    public void openFileFromExternalStorage(String fileName) throws UiObjectNotFoundException;

    public void verifyDocFileOpened();

    public void verifyExcelFileOpened();

    public void verifyPdfFileOpened();

    public void verifyPptFileOpened();

    public void openSeveralFileTypeAndDoSomeOperation() throws UiObjectNotFoundException;
   
    public void openPathFromStorage(String path,boolean clickFile) throws UiObjectNotFoundException;
    
    /**
     * rename an exist file 
     * @param specialChar does the new name contain special chars
     */
    public void renameOfficeFile(boolean specialChar) throws UiObjectNotFoundException;
    
    /**
     * edit documents
     * @param fileType Given the edit document type: DOC,PDF,EXCEL,TXT,PPT
     */
    public void editFile(String fileType) throws UiObjectNotFoundException;
    /**
     * open and edit documents
     * @param fileType Given the edit document type: DOC,PDF,EXCEL,TXT,PPT
     * @param fileName Given the document name
     */
    public void openAndEditFile(String fileName, String fileType) throws UiObjectNotFoundException;
    
    public void verifyVideoOpened() throws UiObjectNotFoundException;
    
    public void verifyPictureOpened() throws UiObjectNotFoundException;
    
    public void discardChanges() throws UiObjectNotFoundException;
    
    public void operationAllFiles(String operation) throws UiObjectNotFoundException;
    
    public void emptyFolder() throws UiObjectNotFoundException;
    
    
    
}
