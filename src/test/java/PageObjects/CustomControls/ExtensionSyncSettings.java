package PageObjects.CustomControls;

/**
 * Represents Extension row on Sync and Notification Settings screen
 */
public class ExtensionSyncSettings {

    public String extension;

    public boolean showInApp;

    public boolean missedCalls;

    public boolean messages;


    public ExtensionSyncSettings(String extension, boolean showInApp, boolean missedCalls, boolean messages){
        this.extension = extension;
        this.showInApp = showInApp;
        this.missedCalls = missedCalls;
        this.messages = messages;
    }

}
