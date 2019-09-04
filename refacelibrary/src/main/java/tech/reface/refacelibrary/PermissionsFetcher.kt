package tech.reface.refacelibrary

import android.content.Intent
import android.content.ComponentName

class PermissionsFetcher {
    companion object {
        const val EXTRA_DATA_TYPE = "EXTRA_DATA_TYPE"
        const val EVENT_DATA_TYPE = 0
        const val GESTURE_DATA_TYPE = 1
        const val ACTIONS_DATA_TYPE = 2

        const val REFACE_APP = "tech.reface.client"
        private const val RIGHTS_ACTIVITY_PATH = "ui.activity.ActivityAuthThirdParty"
        const val ACTIVITY_RESULT_GRANTED = 10
        const val ACTIVITY_RESULT_FAILED = 11

        fun getPermissionsIntent(dataType: List<Int>): Intent {
            val intent = Intent()

            intent.component = ComponentName(REFACE_APP, "$REFACE_APP.$RIGHTS_ACTIVITY_PATH")
            intent.putExtra(EXTRA_DATA_TYPE, dataType.joinToString("_"))
            return intent
        }
    }
}