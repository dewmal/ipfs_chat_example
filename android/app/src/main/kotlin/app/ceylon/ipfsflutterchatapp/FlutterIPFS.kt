package app.ceylon.i

import android.Manifest
import android.app.Activity


import android.content.Intent
import io.flutter.plugin.common.PluginRegistry

class FlutterIPFS : PluginRegistry.RequestPermissionsResultListener, PluginRegistry.ActivityResultListener {
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    private val REQUEST_CHECK_SETTINGS = 0x1

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>?, grantResults: IntArray?): Boolean {

        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE && permissions?.size == 1) {


            return true
        }


        return false;
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {

        if (requestCode == Activity.RESULT_OK) {
            return true
        }

        return false

    }

}