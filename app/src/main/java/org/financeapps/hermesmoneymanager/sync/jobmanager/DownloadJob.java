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

package org.financeapps.hermesmoneymanager.sync.jobmanager;

import com.evernote.android.job.Job;

import androidx.annotation.NonNull;

/**
 * Download job for JobManager
 */

public class DownloadJob extends Job {
    @NonNull
    @Override
    protected Result onRunJob(Params params) {
        return null;
    }
}
