package other.activity.src

import com.android.tools.idea.wizard.template.ModuleTemplateData
import other.WizardTemplateProviderImpl

fun mvpManifest(provider: WizardTemplateProviderImpl, data: ModuleTemplateData) = """
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="${provider.appPackageName.value}">
    <application>
${
    if (data.isNewModule) {
//    if (false) {
        """
        <activity android:name="${provider.activityPackageName.value}.${provider.pageName.value}Activity"
         android:exported="false"
            android:launchMode="singleTop"
            android:screenOrientation="portrait"
            android:windowSoftInputMode="stateAlwaysHidden">
	    </activity> 
    """
    } else {
        """
        <activity
	        android:name="${provider.activityPackageName.value}.${provider.pageName.value}Activity"
            android:exported="false"
            android:launchMode="singleTop"
            android:screenOrientation="portrait"
            android:windowSoftInputMode="stateAlwaysHidden"
	        />
    """
    }
}
    </application>
</manifest>
"""