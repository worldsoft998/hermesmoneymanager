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
package org.financeapps.hermesmoneymanager.datalayer;

import android.content.Context;

import org.financeapps.hermesmoneymanager.Constants;
import org.financeapps.hermesmoneymanager.database.DatasetType;
import org.financeapps.hermesmoneymanager.domainmodel.Payee;
import org.financeapps.hermesmoneymanager.utils.MmxDatabaseUtils;

/**
 * Payee repository
 */
public class PayeeRepository
    extends RepositoryBase{

    public PayeeRepository(Context context) {
        super(context, "payee_v1", DatasetType.TABLE, "payee");

    }

    @Override
    public String[] getAllColumns() {
        return new String[] { "PAYEEID AS _id",
            Payee.PAYEEID,
            Payee.PAYEENAME,
            Payee.CATEGID,
            Payee.SUBCATEGID
        };
    }

    public int add(Payee entity) {
        return insert(entity.contentValues);
    }

    public boolean delete(int id) {
        if (id == Constants.NOT_SET) return false;

        int result = delete(Payee.PAYEEID + "=?", MmxDatabaseUtils.getArgsForId(id));
        return result > 0;
    }

    public Payee load(Integer id) {
        if (id == null || id == Constants.NOT_SET) return null;

        Payee payee = (Payee) super.first(Payee.class,
                getAllColumns(),
                Payee.PAYEEID + "=?", MmxDatabaseUtils.getArgsForId(id),
                null);

        return payee;
    }

    public boolean save(Payee payee) {
        int id = payee.getId();
        return super.update(payee, Payee.PAYEEID + "=" + id);
    }
}
