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

package io.github.novacrypto.qrscanner

import android.graphics.Point
import android.graphics.Rect
import com.google.android.gms.common.images.Size

internal fun Size.orientationDifferent(width: Int, height: Int) =
        (this.width > this.height) xor (width > height)

internal fun Size.swap() = Size(this.height, this.width)

internal fun Size.swapIfOrientationDifferent(width: Int, height: Int) =
        if (this.orientationDifferent(width, height)) {
            this.swap()
        } else {
            this
        }

internal infix fun Point.distanceSquared(rect: Rect): Int {
    val dx = this.x - rect.centerX()
    val dy = this.y - rect.centerY()
    return dx * dx + dy * dy
}

internal infix fun Point.isInside(rect: Rect) = rect.contains(this.x, this.y)