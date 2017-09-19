package TestCases.Runner;

import TestCases.US.LoginAndSettingsSuite.LoginAndSettings1_LoginScreen;
import TestCases.US.LoginAndSettingsSuite.LoginAndSettings2_DefaultDropdownValues;
import TestCases.US.LoginAndSettingsSuite.LoginAndSettings3_BaseSMS;
import TestCases.US.LoginAndSettingsSuite.LoginAndSettings4_SettingsScreen;
import TestCases.US.Texts.Texts1_DialogueCreationAndLeftSwipe;
import TestCases.US.Texts.Texts2_PushNotifications;
import TestCases.US.US_Test_2_Missed_Call_Left_Swipe;
import TestCases.US.US_Test_3_Missed_Call_Details;
import TestCases.US.US_Test_4_Voicemail_Navigation_Bar_and_Left_Swipe;
import TestCases.US.US_Test_5_Voicemail_Details;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
//        LoginAndSettings1_LoginScreen.class,
//        LoginAndSettings2_DefaultDropdownValues.class,
//        LoginAndSettings3_BaseSMS.class,
//        LoginAndSettings4_SettingsScreen.class,
//        US_Test_2_Missed_Call_Left_Swipe.class,
//        US_Test_3_Missed_Call_Details.class,
        US_Test_4_Voicemail_Navigation_Bar_and_Left_Swipe.class,
        US_Test_5_Voicemail_Details.class

//        Texts1_DialogueCreationAndLeftSwipe.class,
//        Texts2_PushNotifications.class
})

public class TestSuite {

}
