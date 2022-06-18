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
package org.financeapps.hermesmoneymanager.domainmodel;

import android.content.ContentValues;

import org.parceler.Parcel;

/**
 * Category
 */
@Parcel
public class Category
    extends EntityBase {

    public static final String CATEGID = "CATEGID";
    public static final String CATEGNAME = "CATEGNAME";

    public Category() {
        super();
    }

    public Category(ContentValues contentValues) {
        super(contentValues);
    }

    public int getId() {
        return getInt(CATEGID);
    }

    public void setId(Integer value) {
        setInt(CATEGID, value);
    }

    public String getName() {
        return getString(CATEGNAME);
    }

    public void setName(String value) {
        setString(CATEGNAME, value);
    }
}
