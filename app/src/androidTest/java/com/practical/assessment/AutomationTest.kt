package com.practical.assessment

import android.content.Intent
import android.view.KeyEvent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import com.practical.assessment.ui.constants.loginBtnTest
import com.practical.assessment.ui.constants.notTestBtnTest
import com.practical.assessment.ui.constants.pinFieldTest
import com.practical.assessment.ui.constants.pwdFieldTest
import com.practical.assessment.ui.constants.testBtnTest
import com.practical.assessment.ui.constants.testTextTest
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AutomationTest {

    private lateinit var device: UiDevice

    @Before
    fun setup() {

        val instrumentation = InstrumentationRegistry.getInstrumentation()

        device = UiDevice.getInstance(instrumentation)

        device.pressHome()

        val context = instrumentation.targetContext

        val intent = context.packageManager
            .getLaunchIntentForPackage("com.practical.assessment")

        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        context.startActivity(intent)

        device.wait(
            Until.hasObject(By.pkg("com.practical.assessment")),
            10000
        )

        device.executeShellCommand("am start -n com.practical.assessment/com.practical.assessment.MainActivity")

        device.waitForIdle()
    }

    @Test
    fun fullAutomationFlow() {

        // PASSWORD INPUT
        Thread.sleep(3000)
        device.waitForIdle()

        val password = device.wait(
            Until.findObject(By.res(pwdFieldTest)),
            10000
        )
        assertNotNull("Password field not found", password)
        password.text = "Test@2026"

        // LOGIN BUTTON
        val login = device.wait(
            Until.findObject(By.res(loginBtnTest)),
            10000
        )
        assertNotNull("Login button not found", login)
        login.click()

        device.waitForIdle()

        // CONDITIONAL BUTTON
        val exists = device.wait(
            Until.hasObject(By.res(testTextTest)),
            10000
        )
//            device.hasObject(By.res(testTextTest))

        if (exists) {
            val btnTest = device.wait(
                Until.findObject(By.res(testBtnTest)),
                10000
            )
            assertNotNull("Test button not found", btnTest)
            btnTest.click()

            // PIN INPUT
            val pin = device.wait(
                Until.findObject(By.res(pinFieldTest)),
                10000
            )
            assertNotNull("PIN field not found", pin)
            pin.text = "8526"
        } else {
            val btnNotTest = device.wait(
                Until.findObject(By.desc(notTestBtnTest)),
                10000
            )
            assertNotNull("Not-test button not found", btnNotTest)
            btnNotTest.click()
        }

        device.waitForIdle()
    }
}