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

import android.content.Context;

import org.financeapps.hermesmoneymanager.Constants;
import org.financeapps.hermesmoneymanager.hmmApplication;
import org.financeapps.hermesmoneymanager.core.FormatUtilities;
import org.financeapps.hermesmoneymanager.utils.MmxDate;
import org.financeapps.hermesmoneymanager.utils.MmxDateTimeUtils;

import javax.inject.Inject;

import dagger.Lazy;
import info.javaperformance.money.Money;

/**
 * Model for editing the security price.
 */

public class PriceEditModel {
    public PriceEditModel() {
        hmmApplication.getApp().iocComponent.inject(this);
    }

    public int accountId;

    public String symbol;
    public Money price;
    public MmxDate date;

    public int currencyId = Constants.NOT_SET;

    @Inject Lazy<MmxDateTimeUtils> dateTimeUtilsLazy;

    public void display(Context context, EditPriceViewHolder viewHolder) {
        viewHolder.symbolTextView.setText(symbol);

        String dateDisplay = dateTimeUtilsLazy.get().getUserFormattedDate(context, this.date.toDate());
        viewHolder.dateTextView.setText(dateDisplay);

        String amount;
        FormatUtilities format = new FormatUtilities(context);
//        if (currencyId == Constants.NOT_SET) {
//            // use base currency?
//            amount = format.getValueFormattedInBaseCurrency(price);
//        } else {
//            amount = format.format(price, currencyId);
//        }

        amount = format.format(price, Constants.PRICE_FORMAT);

        viewHolder.amountTextView.setText(amount);
    }
}
