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
package org.financeapps.hermesmoneymanager.transactions;

import org.financeapps.hermesmoneymanager.Constants;
import org.financeapps.hermesmoneymanager.core.TransactionTypes;
import org.financeapps.hermesmoneymanager.database.ISplitTransaction;
import org.financeapps.hermesmoneymanager.database.ITransactionEntity;
import org.financeapps.hermesmoneymanager.domainmodel.SplitCategory;
import org.financeapps.hermesmoneymanager.domainmodel.SplitRecurringCategory;

import info.javaperformance.money.MoneyFactory;

/**
 * The factory that creates the Split Category entities
 */
public class SplitItemFactory {

    public static ISplitTransaction create(String entityClassName, TransactionTypes parentTransactionType) {
        ISplitTransaction entity;
        String recurringSplitName = SplitRecurringCategory.class.getSimpleName();

        if (entityClassName != null && entityClassName.contains(recurringSplitName)) {
            entity = SplitRecurringCategory.create(Constants.NOT_SET, Constants.NOT_SET,
                    Constants.NOT_SET, parentTransactionType, MoneyFactory.fromDouble(0));
        } else {
            entity = SplitCategory.create(Constants.NOT_SET, Constants.NOT_SET,
                    Constants.NOT_SET, parentTransactionType, MoneyFactory.fromDouble(0));
        }

        return entity;
    }
}
