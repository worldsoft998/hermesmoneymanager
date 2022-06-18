/*
 * Copyright (C) 2012-2015 The Android Hermes Moneymanager Project Team
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

import android.app.Activity;
import android.content.Context;

import org.financeapps.hermesmoneymanager.BuildConfig;
import org.financeapps.hermesmoneymanager.account.AccountStatuses;
import org.financeapps.hermesmoneymanager.account.AccountTypes;
import org.financeapps.hermesmoneymanager.currency.CurrencyService;
import org.financeapps.hermesmoneymanager.datalayer.AccountRepository;
import org.financeapps.hermesmoneymanager.domainmodel.Account;
import org.financeapps.hermesmoneymanager.domainmodel.Currency;
import org.financeapps.hermesmoneymanager.servicelayer.AccountService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.financeapps.android.testhelpers.TestApplication;
import org.financeapps.android.testhelpers.UnitTestHelper;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;


/**
 * Account Service tests.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, application = TestApplication.class)
public class AccountServiceTests {

    private AccountService testObject;

    @Before
    public void setup() {
        // initialize support for activities (UI)
//        this.controller = UnitTestHelper.getController(MainActivity.class);
//        this.activity = UnitTestHelper.getActivity(this.controller);
        //Activity activity = Robolectric.setupActivity(MainActivity.class);

        // initialize database
        // UnitTestHelper.setupContentProvider();

        Context context = UnitTestHelper.getContext();
        testObject = new AccountService(context);
    }

    @After
    public void tearDown() {
        // Destroy the activity controller.
//        this.controller.destroy();

        testObject = null;
    }

    @Test
    public void instantiation() {
        assertThat(testObject, notNullValue());
    }

    //@Test
    public void getAccountCurrency() {
        // Given
        UnitTestHelper.setupContentProvider();
        String expectedCode = "ISK";
        Context context = UnitTestHelper.getContext();
        CurrencyService currencyService = new CurrencyService(context);
        Currency currency = currencyService.getCurrency(expectedCode);
        AccountRepository repo = new AccountRepository(context);
        Account account = Account.create("bank account", AccountTypes.CHECKING, AccountStatuses.OPEN,
            false, currency.getCurrencyId());
        repo.save(account);
        int accountId = account.getId();

        // When
        String actual = testObject.getAccountCurrencyCode(accountId);

        // Then
        assertThat(actual, equalTo(expectedCode));
    }
}
