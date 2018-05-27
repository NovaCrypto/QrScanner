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

package io.github.novacrypto.qrscanner.demo.customscanners

import io.github.novacrypto.bip32.ExtendedPublicKey
import io.github.novacrypto.qrscanner.ScanQrActivity

/**
 * This custom scanner pre checks the validity of the scanned data before allowing user to accept.
 * For example you can check lengths, or check sums or content of the data.
 * Here we check that the text can be deserialized as an xpub.
 */
class XPubScannerActivity : ScanQrActivity() {
    override fun getBarcodeFilter() = CharSequence::isValidXPub
}

private fun CharSequence.isValidXPub() =
        try {
            ExtendedPublicKey.deserializer().deserialize(this)
            true
        } catch (e: Exception) {
            false
        }
