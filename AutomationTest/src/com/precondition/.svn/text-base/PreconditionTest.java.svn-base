
package com.precondition;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.module.camera.AbstractCameraFactory;
import com.module.camera.CameraFactory;
import com.module.camera.CameraModule;
import com.module.camera.ICamera;
import com.module.common.CommonModule;
import com.parents.GroupFactories;
import com.sonyericsson.test.acceptancetest.AcceptanceTestCase;

import android.R.integer;
import android.app.AlarmManager;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.Telephony;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;

import java.io.IOException;

import junit.framework.TestCase;

public class PreconditionTest extends AcceptanceTestCase {
	private String NUMERIC = "";

	CommonModule commonModule;

    public void testSetAPN()
            throws IOException {
		GetNumeric();
		ContentValues values = new ContentValues();
		Uri uri;
		values.put(Telephony.Carriers.NAME, "GTE");
		values.put(Telephony.Carriers.APN, "gte-internet");
		values.put(Telephony.Carriers.PROXY, "");
		values.put(Telephony.Carriers.PORT, "");
		values.put(Telephony.Carriers.MMSPROXY, "");
		values.put(Telephony.Carriers.MMSPORT, "");
		values.put(Telephony.Carriers.USER, "");
		values.put(Telephony.Carriers.SERVER, "");
		values.put(Telephony.Carriers.PASSWORD, "");
		values.put(Telephony.Carriers.MMSC, "");
		values.put(Telephony.Carriers.AUTH_TYPE, "");
		values.put(Telephony.Carriers.TYPE, "default,supl");
		values.put(Telephony.Carriers.MCC, NUMERIC.substring(0, 3));
		values.put(Telephony.Carriers.MNC, NUMERIC.substring(3, NUMERIC.length()));
		values.put(Telephony.Carriers.NUMERIC, NUMERIC);
		values.put(Telephony.Carriers.CURRENT, 1);
		uri = this.getInstrumentation().getContext().getContentResolver()
				.insert(Telephony.Carriers.CONTENT_URI, values);

        values = null;
        values = new ContentValues();
        values.put(Telephony.Carriers.NAME, "MMS");
        values.put(Telephony.Carriers.APN, "tmcwap");

        values.put(Telephony.Carriers.PROXY, "");
        values.put(Telephony.Carriers.PORT, "");

        values.put(Telephony.Carriers.USER, "");
        values.put(Telephony.Carriers.SERVER, "");
        values.put(Telephony.Carriers.PASSWORD, "");

        values.put(Telephony.Carriers.MMSC, "http://192.168.23.180:8080");
        values.put(Telephony.Carriers.MMSPROXY, "192.168.23.5");
        values.put(Telephony.Carriers.MMSPORT, "8000");

                values.put(Telephony.Carriers.MCC, NUMERIC.substring(0, 3));
                values.put(Telephony.Carriers.MNC, NUMERIC.substring(3, NUMERIC.length()));
                values.put(Telephony.Carriers.AUTH_TYPE, "");

        values.put(Telephony.Carriers.TYPE, "mms");

        values.put(Telephony.Carriers.PROTOCOL, "IPV4");
        values.put(Telephony.Carriers.ROAMING_PROTOCOL, "IPV4");


                values.put(Telephony.Carriers.NUMERIC, NUMERIC);
                values.put(Telephony.Carriers.CURRENT, 0);
                uri = this.getInstrumentation().getContext().getContentResolver()
                        .insert(Telephony.Carriers.CONTENT_URI, values);
            }

    public void testSetDefaultDate() throws IOException {
        AlarmManager mAlarmManager = (AlarmManager)this.getInstrumentation().getContext()
                .getSystemService(Context.ALARM_SERVICE);
        mAlarmManager.setTime(System.currentTimeMillis());
    }

	protected void GetNumeric() {
		TelephonyManager phoneManager = (TelephonyManager) this.getInstrumentation().getContext()
				.getSystemService(Context.TELEPHONY_SERVICE);
		NUMERIC = phoneManager.getSimOperator();
	}

    public void testOpenAPictureFirstTime() throws UiObjectNotFoundException{
        CommonModule commonModule = new CommonModule(this);

        commonModule.clearData("Camera");

        launchApp("com.sonyericsson.android.camera", "com.sonyericsson.android.camera.CameraActivity");

        if (this.isViewWithIdPresent("alertTitle")) {
            this.clickItemWithText("No");
        }

        commonModule.wait(3);
        this.pressTwoKeys(KeyEvent.KEYCODE_FOCUS, KeyEvent.KEYCODE_CAMERA);

        commonModule.wait(5);
        commonModule.clickResourceId("com.sonyericsson.android.camera:id/content_thumbnail");
        commonModule.wait(5);

        int height = this.getUiDevice().getDisplayHeight();
        int width = this.getUiDevice().getDisplayWidth();
        this.getUiDevice().swipe(width/2, 0, width/2, height/2, 80);
        commonModule.wait(5);
    }


	protected void disableDownloadDictionary() throws InterruptedException {

        launchApp("com.sonyericsson.conversations",
	            "com.sonyericsson.conversations.ui.ConversationListActivity");

        clickId("menu_new_conversation");
        wait(1);
        if (isViewWithTextPresent("Download dictionary?")){
        	click("Cancel");
        	wait(1);
        	click("OK");
        }
        clickId("conversation_edit_text");
        wait(1);
        if (isViewWithTextPresent("Download dictionary?")){
        	click("Cancel");
        	wait(1);
        	click("OK");
        }
        
        
		clickPoint(commonModule.getX(131,720), commonModule.getY(1118,1184));
		click("Continue");
		commonModule.backOutToHomeScreen();
		
	}
}
