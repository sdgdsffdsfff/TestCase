package com.android.systemui;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.ztemt.test.automator.AutomatorTestCase;

public class UsbConnectOptionsTestCase extends AutomatorTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        getUiDevice().openNotification();

        UiObject options = new UiObject(new UiSelector().resourceId(
                "android:id/text").text("触摸可显示其他 USB 选项。"));
        options.clickAndWaitForNewWindow();
    }

    public void testEnableUMS() throws UiObjectNotFoundException {
        enableOption("UMS", "ums_option");
    }

    public void testDisableUMS() throws UiObjectNotFoundException {
        disableOption("UMS", "ums_option");
    }

    public void testEnableMTP() throws UiObjectNotFoundException {
        enableOption("MTP", "mtp_option");
    }

    public void testDisableMTP() throws UiObjectNotFoundException {
        disableOption("MTP", "mtp_option");
    }

    public void testEnablePTP() throws UiObjectNotFoundException {
        enableOption("PTP", "ptp_option");
    }

    public void testDisablePTP() throws UiObjectNotFoundException {
        disableOption("PTP", "ptp_option");
    }

    private void enableOption(String name, String resId) throws UiObjectNotFoundException {
        UiObject unchecked = new UiObject(new UiSelector()
                .resourceId(String.format("com.android.systemui:id/%s", resId))
                .enabled(true).checked(false));
        if (unchecked.exists()) {
            unchecked.click();
        }

        UiObject checked = new UiObject(new UiSelector()
                .resourceId(String.format("com.android.systemui:id/%s", resId))
                .enabled(true).checked(true));
        assertTrue(String.format("Unable to enable %s", name),
                checked.waitForExists(30000));
    }

    private void disableOption(String name, String resId) throws UiObjectNotFoundException {
        UiObject checked = new UiObject(new UiSelector()
                .resourceId(String.format("com.android.systemui:id/%s", resId))
                .enabled(true).checked(true));
        if (checked.exists()) {
            checked.click();
        }

        UiObject unchecked = new UiObject(new UiSelector()
                .resourceId(String.format("com.android.systemui:id/%s", resId))
                .enabled(true).checked(false));
        assertTrue(String.format("Unable to disable %s", name),
                unchecked.waitForExists(10000));
    }
}
