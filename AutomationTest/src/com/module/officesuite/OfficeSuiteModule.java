package com.module.officesuite;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.module.common.CommonModule;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import android.content.Context;
import android.media.AudioManager;
import android.view.KeyEvent;

public class OfficeSuiteModule implements IOfficeSuite{

    AcceptanceTestCase testCase;
    CommonModule commonModule;

    public OfficeSuiteModule(AcceptanceTestCase testCase){
        this.testCase = testCase;

        commonModule = new CommonModule(testCase);
    }

    @Override
    public void launchOfficeSuite() {
        testCase.launchApp("com.mobisystems.office", "com.mobisystems.office.FileBrowser");

        commonModule.wait(2);
        if(testCase.isViewWithTextPresent("For genuine viewing of your documents download Microsoft Compatibility Font Pack.")){
            testCase.clickItemWithText("Don't ask again");
            commonModule.wait(1);
            testCase.clickItemWithText("Later");
        }
    }

    @Override
    public void openFileFromExternalStorage(String fileName) throws UiObjectNotFoundException{

        launchOfficeSuite();

        if(!testCase.isViewWithTextMatches("Internal storage")){
            commonModule.clickResourceId("com.mobisystems.office:id/app_icon");

            commonModule.clickText("Internal storage");
            //commonModule.clickText("Internal storage");
        }

       /* AcceptanceTestCase.assertTrue("Folder named 'Android' should display",
                commonModule.waitForText("Android", 5000));*/

        //files are under 'Android' folder as default
        commonModule.scrollFindText("testresource");

        //commonModule.clickText("Android");
        commonModule.scrollFindTextNotClick(fileName);
        AcceptanceTestCase.assertTrue("File named "+ fileName+ " should display",
                commonModule.waitForText(fileName, 5000));

        commonModule.clickText(fileName);
        commonModule.wait(2);
    }

    
    @Override
    public void openPathFromStorage(String path,boolean clickFile) throws UiObjectNotFoundException{

        launchOfficeSuite();
        
        String[] Array = path.split("\\/");

        if(!testCase.isViewWithTextMatches(Array[0])){
            commonModule.clickResourceId("com.mobisystems.office:id/app_icon");
        }
        commonModule.clickText(Array[0]);
        
        for (int i=1; i<Array.length-1; i++){
        	commonModule.scrollFindText(Array[i]);
        }
        
        
        if (Array[Array.length-1].contains(".")){
        	if (clickFile){
        		commonModule.scrollFindText(Array[Array.length-1]);
        		commonModule.wait(2);
        	}
        }
        else{
        	commonModule.scrollFindText(Array[Array.length-1]);
        }
        
    }

    public void verifyDocFileOpened(){
        AcceptanceTestCase.assertTrue("doc file open failed",commonModule.waitForResourceId("com.mobisystems.office:id/t_font", 3000)
                || commonModule.waitForResourceId("com.mobisystems.office:id/t_format_painter", 5000));
    }

    public void verifyExcelFileOpened(){
        AcceptanceTestCase.assertTrue("excel file open failed",commonModule.waitForResourceId("com.mobisystems.office:id/excel_tabs", 8000)
                || commonModule.waitForResourceId("com.mobisystems.office:id/t_font", 5000));
    }

    public void verifyPdfFileOpened(){
        AcceptanceTestCase.assertTrue("pdf file open failed",commonModule.waitForResourceId("com.mobisystems.office:id/pdf_goto_page", 8000)
                );
    }

    public void verifyPptFileOpened(){
        AcceptanceTestCase.assertTrue("ppt file open failed",commonModule.waitForResourceId("com.mobisystems.office:id/pp_notes_title", 3000)
                );
    }
    
    public void verifyTxtFileOpened() throws UiObjectNotFoundException{
    	//importing plain text file query
    	if(commonModule.waitForText("OK",5000)){
    		commonModule.clickText("OK");
    	}
        AcceptanceTestCase.assertTrue("txt file open failed",commonModule.waitForResourceId("com.mobisystems.office:id/editor_view", 5000)
                );
    }

    public void openSeveralFileTypeAndDoSomeOperation() throws UiObjectNotFoundException{
        launchOfficeSuite();
        //open doc file
        openPathFromStorage("Internal storage/testresource/testDoc.doc",true);
        verifyDocFileOpened();

        //do word count
        testCase.pressKey(KeyEvent.KEYCODE_MENU);
        commonModule.wait(5);
        commonModule.waitForText("Word count", 3000);
        testCase.clickItemWithText("Word count");

        testCase.assertTextPresent("Whole document");
        testCase.assertTextPresent("Words: ");
        testCase.assertTextPresent("Characters: ");

        commonModule.backOutToHomeScreen();

        launchOfficeSuite();

        //open excel file
        openPathFromStorage("Internal storage/testresource/testExcel.xls",true);
        verifyExcelFileOpened();

        //do close file
        testCase.pressKey(KeyEvent.KEYCODE_MENU);
        commonModule.waitForText("File", 3000);
        testCase.clickItemWithText("File");

        commonModule.waitForText("Close", 3000);
        testCase.clickItemWithText("Close");

        //excel closed and excel file name exist
        commonModule.waitForText("testExcel.xls", 5000);

        commonModule.backOutToHomeScreen();

        launchOfficeSuite();

        //open excel file
        openPathFromStorage("Internal storage/testresource/testPdf.pdf",true);
        verifyPdfFileOpened();

        //do full screen
        testCase.pressKey(KeyEvent.KEYCODE_MENU);
        commonModule.waitForText("Full screen", 3000);
        testCase.clickItemWithText("Full screen");

        AcceptanceTestCase.assertTrue("pdf doesn't goto full scree , some icon still visible",
                !commonModule.isResourceId("com.mobisystems.office:id/pdf_goto_page"));

        commonModule.backOutToHomeScreen();

        launchOfficeSuite();

        //open ppt file
        openPathFromStorage("Internal storage/testresource/testPpt.pptx",true);
        verifyPptFileOpened();

        //do play slide show
        testCase.pressKey(KeyEvent.KEYCODE_MENU);
        commonModule.waitForText("Slide show", 3000);
        testCase.clickItemWithText("Slide show");
        testCase.assertTextPresent("Advance slides");

        //click 'Start' button
        commonModule.clickResourceId("android:id/button1");
        commonModule.wait(2);

        //scroll slide maunlly
        testCase.scrollLeft();
        commonModule.wait(1);

        testCase.scrollLeft();
        commonModule.wait(1);

        commonModule.backOutToHomeScreen();

    }

    public void renameOfficeFile(boolean specialChar) throws UiObjectNotFoundException{
    	launchOfficeSuite();

        if(!testCase.isViewWithTextMatches("Internal storage")){
            commonModule.clickResourceId("com.mobisystems.office:id/app_icon");

            commonModule.clickText("Internal storage");
        }

        commonModule.scrollFindText("testresource");

        commonModule.scrollFindTextNotClick("testDoc.doc");
        AcceptanceTestCase.assertTrue("File named "+ "testDoc.doc"+ " should display",
                commonModule.waitForText("testDoc.doc", 5000));

        commonModule.longClickTextContains("testDoc.doc");
        commonModule.wait(2);
        commonModule.clickText("Rename");
        commonModule.emptyEditorByInstance(0);
        
        if(specialChar){
			        	testCase.enterText("#&*!");
			         commonModule.clickText("OK");
			        	AcceptanceTestCase.assertTrue("File should not renamed",
			                    commonModule.waitForResourceId("com.mobisystems.office:id/new_name", 5000));
        }else{
        			testCase.enterText("newTestDoc");
            commonModule.clickText("OK");
			        AcceptanceTestCase.assertTrue("File named "+ "testDoc.doc"+ " should display",
			                commonModule.waitForText("newTestDoc.doc", 5000));
        }
        
        commonModule.backOutToHomeScreen();
        
    }
    
    public void editFile(String fileType) throws UiObjectNotFoundException{
    	if(fileType=="PDF"){
    				verifyPdfFileOpened();
    				editPdfFile();
    	}else{
    			 
    			 if(fileType=="DOC"){
    				 			verifyDocFileOpened();
    				 			commonModule.clickResourceId("com.mobisystems.office:id/editor_view");
    			 }else if(fileType=="EXCEL"){
    				 			verifyExcelFileOpened();
    				 			commonModule.clickResourceId("com.mobisystems.office:id/cell_text");
    			 }else if(fileType=="TXT"){
    				 			verifyTxtFileOpened();
    				 			commonModule.clickResourceId("com.mobisystems.office:id/editor_view");
    			 }else if(fileType=="PPT"){
    				 			verifyPptFileOpened();
    				 			commonModule.clickResourceId("com.mobisystems.office:id/pp_notes_title");
    				 			commonModule.wait(2);
    				 			commonModule.clickResourceId("com.mobisystems.office:id/pp_notes");
        }else{
        				AcceptanceTestCase.assertTrue("Please give an avaliable file type", false);
        				}
    			 UiObject obj = new UiObject(new UiSelector().description("Save"));
    			 commonModule.wait(2);
    			 AcceptanceTestCase.assertTrue("Cannot open edit keypad", testCase.isInputMethodWindowOpened());
  
        testCase.enterText("This document is for reliability testing!");
        if(fileType=="EXCEL"){
			        	commonModule.clickResourceId("com.mobisystems.office:id/excel_commitcell");
			        }
        AcceptanceTestCase.assertTrue("Enter text failed",obj.isEnabled());
        commonModule.clickDescription("Save");
        if(commonModule.isTextExists("OK")){
        	commonModule.clickText("OK");
        }
    	}
        if(testCase.isInputMethodWindowOpened()){
        	testCase.pressKey(KeyEvent.KEYCODE_BACK);
        }
        testCase.pressKey(KeyEvent.KEYCODE_BACK);
    }
/*    
    public void editExcelFile() throws UiObjectNotFoundException{
					 UiObject obj = new UiObject(new UiSelector().description("Save"));
						commonModule.clickResourceId("com.mobisystems.office:id/cell_text");
						commonModule.wait(2);
						AcceptanceTestCase.assertTrue("Cannot open edit keypad", testCase.isInputMethodWindowOpened());
						
						testCase.enterText("This document is for reliability testing!");
						commonModule.clickResourceId("com.mobisystems.office:id/excel_commitcell");
						AcceptanceTestCase.assertTrue("Enter text failed",obj.isEnabled());
						obj.click();
						if(testCase.isInputMethodWindowOpened()){
							testCase.pressKey(KeyEvent.KEYCODE_BACK);
						}
						testCase.pressKey(KeyEvent.KEYCODE_BACK);
}
    
    public void editPptFile() throws UiObjectNotFoundException{
					 UiObject obj = new UiObject(new UiSelector().description("Save"));
						commonModule.clickResourceId("com.mobisystems.office:id/pp_notes_title");
						commonModule.wait(2);
						commonModule.clickText("Click here to add notes");
						AcceptanceTestCase.assertTrue("Cannot open edit keypad", testCase.isInputMethodWindowOpened());
						
						testCase.enterText("This document is for reliability testing!");
						AcceptanceTestCase.assertTrue("Enter text failed",obj.isEnabled());
						obj.click();
						if(testCase.isInputMethodWindowOpened()){
							testCase.pressKey(KeyEvent.KEYCODE_BACK);
						}
						testCase.pressKey(KeyEvent.KEYCODE_BACK);
			}
    
    public void editTxtFile() throws UiObjectNotFoundException{
						 UiObject obj = new UiObject(new UiSelector().description("Save"));
							commonModule.clickResourceId("com.mobisystems.office:id/editor_view");
							commonModule.wait(2);
							AcceptanceTestCase.assertTrue("Cannot open edit keypad", testCase.isInputMethodWindowOpened());
							
							testCase.enterText("This document is for reliability testing!");
							AcceptanceTestCase.assertTrue("Enter text failed",obj.isEnabled());
							obj.click();
							if(testCase.isInputMethodWindowOpened()){
								testCase.pressKey(KeyEvent.KEYCODE_BACK);
							}
							testCase.pressKey(KeyEvent.KEYCODE_BACK);
}*/
    
    public void editPdfFile() throws UiObjectNotFoundException{
			    	testCase.pressKey(KeyEvent.KEYCODE_MENU);
			    	commonModule.clickText("Comment");
			    	commonModule.clickText("Sticky note");
			    	for(int i=0;i<3;i++){
			    			commonModule.clickResourceId("com.mobisystems.office:id/pdf_view");
			    			if(commonModule.isTextExists("Annotation")){
			    				break;
			    			}else{
			    				commonModule.wait(2);
			    			}
			    	}
    	
			    	commonModule.clickText("Annotation text");
			    	commonModule.wait(2);
			    	AcceptanceTestCase.assertTrue("Cannot open edit keypad", testCase.isInputMethodWindowOpened());
		
			    	testCase.enterText("This document is for reliability testing!");
			    	commonModule.clickText("OK");
			    	testCase.pressKey(KeyEvent.KEYCODE_MENU);
			    	commonModule.clickText("File");
			    	commonModule.clickText("Save");
				}
    
    public void openAndEditFile(String fileName, String fileType) throws UiObjectNotFoundException{
    	            openPathFromStorage("Internal storage/testresource/"+fileName,true);
					editFile(fileType);
					if(commonModule.isResourceId("com.mobisystems.office:id/subtitle")){
							testCase.pressKey(KeyEvent.KEYCODE_BACK);
					}
    }
    public void verifyPictureOpened() throws UiObjectNotFoundException{
		    	if(commonModule.isTextExists("Complete action using")){
		    		commonModule.clickText("Album");
		    		commonModule.clickText("Always");
		    	}
        AcceptanceTestCase.assertTrue("picture open failed",commonModule.waitForResourceId("com.sonyericsson.album:id/currentscenicview", 3000)
                );
    }
    
    public void verifyVideoOpened() throws UiObjectNotFoundException{
		    	if(commonModule.isTextExists("Complete action using")){
		    		commonModule.clickText("Movies");
		    		commonModule.clickText("Always");
		    	}
        AcceptanceTestCase.assertTrue("video file open failed",commonModule.waitForResourceId("com.sonyericsson.video:id/video_surface_view", 3000)
                );
    }

	@Override
	public void discardChanges() throws UiObjectNotFoundException {
		// TODO Auto-generated method stub
        launchOfficeSuite();
        
		if (testCase.isViewWithTextPresent("Discard changes")){
			testCase.click("Discard changes");
		}
		
		
		
	}

	@Override
	public void operationAllFiles(String operation)
			throws UiObjectNotFoundException {
	
		if(!commonModule.isResourceId("com.mobisystems.office:id/list_item_label")){
	        AcceptanceTestCase.assertTrue("There isn't anything on there folder.",false);
	    }
		
		else{
			testCase.clickId("edit_menu");
			testCase.click("Select All");
			
			testCase.clickId("edit_"+operation);

		}
	}

	@Override
	public void emptyFolder() throws UiObjectNotFoundException {

		if(commonModule.isResourceId("com.mobisystems.office:id/list_item_label")){
			testCase.clickId("edit_menu");
			testCase.click("Select All");
			
			testCase.clickId("edit_delete");
			
			testCase.click("OK");
			commonModule.wait(3);
		    
			AcceptanceTestCase.assertTrue("Delete all file failed.",
					commonModule.waitForText("This folder is empty", 3000));  
	    }
		
	}
}
