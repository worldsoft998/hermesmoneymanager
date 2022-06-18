/*
 * Copyright (C) 2012-2018 The Android Hermes Moneymanager Project Team
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.financeapps.android.tests;

import android.util.Log;

import org.financeapps.hermesmoneymanager.BuildConfig;
import org.financeapps.hermesmoneymanager.utils.MmxDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.financeapps.android.testhelpers.TestApplication;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Tests for conversion from JodaTime back to the standard Java date types.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, application = TestApplication.class)

public class MmxDateTimeTests {

    private MmxDate _util;

    @Before
    public void setup() {
        _util = new MmxDate();
    }

    @Test
    public void basicTests() {
        String current = _util.getCalendar().toString();
        Log.d("test", current);
    }
}