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

package org.financeapps.hermesmoneymanager.investment;

import android.view.View;

import com.mikepenz.iconics.view.IconicsImageView;
import org.financeapps.hermesmoneymanager.R;
import org.financeapps.hermesmoneymanager.view.RobotoTextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * View Holder pattern for edit price binaryDialog.
 */
public class EditPriceViewHolder {

    @BindView(R.id.symbolTextView) public RobotoTextView symbolTextView;
    @BindView(R.id.amountTextView) public RobotoTextView amountTextView;
    @BindView(R.id.dateTextView) public RobotoTextView dateTextView;
//    @BindView(R.id.dateControl) public DateDisplay dateDisplay;

//    @BindView(R.id.previousDayButton) @Nullable public FontIconView previousDayButton;
    @BindView(R.id.previousDayButton) @Nullable
    public IconicsImageView previousDayButton;

//    @BindView(R.id.nextDayButton) @Nullable public FontIconView nextDayButton;
    @BindView(R.id.nextDayButton) @Nullable public IconicsImageView nextDayButton;

    public void bind(View view) {
        ButterKnife.bind(this, view);
    }

    public void bind(AppCompatActivity activity) {
        ButterKnife.bind(this, activity);
    }
}
