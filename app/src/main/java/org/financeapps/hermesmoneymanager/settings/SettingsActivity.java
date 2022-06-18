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
package org.financeapps.hermesmoneymanager.settings;

import android.content.Intent;
import android.os.Bundle;

import org.financeapps.hermesmoneymanager.hmmApplication;
import org.financeapps.hermesmoneymanager.R;
import org.financeapps.hermesmoneymanager.core.Core;

import javax.inject.Inject;

import androidx.preference.PreferenceFragmentCompat;
import dagger.Lazy;

public class SettingsActivity
    extends BaseSettingsFragmentActivity {

    public static final String EXTRA_FRAGMENT = "extraFragment";

    @Inject Lazy<AppSettings> appSettingsLazy;
    @Inject Lazy<Core> coreLazy;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        hmmApplication.getApp().iocComponent.inject(this);

        showFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setTitle(R.string.settings);
    }

    private void showFragment() {
        // figure out which fragment to show
        PreferenceFragmentCompat fragment = null;

        Intent intent = getIntent();

        String fragmentName = intent.getStringExtra(EXTRA_FRAGMENT);
        if (fragmentName != null && fragmentName.equals(PerDatabaseFragment.class.getSimpleName())) {
            fragment = new PerDatabaseFragment();
        }

        // default
        if (fragment == null) {
            fragment = new SettingsFragment();
        }

        setSettingFragment(fragment);
    }
}
