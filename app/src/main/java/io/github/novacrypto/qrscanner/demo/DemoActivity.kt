/*
 *  QrScanner, Activity for Android
 *  Copyright (C) 2018 Alan Evans, NovaCrypto
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *  Original source: https://github.com/NovaCrypto/QrScanner
 *  You can contact the authors via github issues.
 */

package io.github.novacrypto.qrscanner.demo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.github.novacrypto.qrscanner.ScanQrActivity
import io.github.novacrypto.qrscanner.demo.customscanners.XPubScannerActivity
import kotlinx.android.synthetic.main.activity_demo.*
import kotlinx.android.synthetic.main.content_demo.*

const val REQUEST_SCAN = 1

class DemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        setSupportActionBar(toolbar)

        // Default scanner
        launch.setOnClickListener {
            startActivityForResult(Intent(this, ScanQrActivity::class.java).apply {
                putExtra(ScanQrActivity.OPTION_SHOW_BARCODE_BOX, BuildConfig.DEBUG)
                putExtra(ScanQrActivity.AUTOACCEPT_RESULT, false)
            }, REQUEST_SCAN)
        }

        // Auto-accept scanner
        launch_autoaccept.setOnClickListener {
            startActivityForResult(Intent(this, ScanQrActivity::class.java).apply {
                putExtra(ScanQrActivity.OPTION_SHOW_BARCODE_BOX, BuildConfig.DEBUG)
                putExtra(ScanQrActivity.AUTOACCEPT_RESULT, true)
            }, REQUEST_SCAN)
        }

        // Custom scanner
        launch_xpub.setOnClickListener  {
            startActivityForResult(Intent(this, XPubScannerActivity::class.java).apply {
                putExtra(ScanQrActivity.OPTION_SHOW_BARCODE_BOX, BuildConfig.DEBUG)
            }, REQUEST_SCAN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_SCAN) {
            if (resultCode == Activity.RESULT_OK) {
                val barcode = data?.extras?.getString(ScanQrActivity.BARCODE_DATA) ?: ""
                result.text = barcode
            } else {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
