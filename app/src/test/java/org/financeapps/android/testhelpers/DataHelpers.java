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
package org.financeapps.android.testhelpers;

import android.content.ContentResolver;
import android.content.Context;

import org.financeapps.hermesmoneymanager.Constants;
import org.financeapps.hermesmoneymanager.account.AccountStatuses;
import org.financeapps.hermesmoneymanager.account.AccountTypes;
import org.financeapps.hermesmoneymanager.core.TransactionTypes;
import org.financeapps.hermesmoneymanager.currency.CurrencyService;
import org.financeapps.hermesmoneymanager.datalayer.AccountRepository;
import org.financeapps.hermesmoneymanager.datalayer.AccountTransactionRepository;
import org.financeapps.hermesmoneymanager.datalayer.AssetClassRepository;
import org.financeapps.hermesmoneymanager.datalayer.AssetClassStockRepository;
import org.financeapps.hermesmoneymanager.datalayer.PayeeRepository;
import org.financeapps.hermesmoneymanager.datalayer.SplitCategoriesRepository;
import org.financeapps.hermesmoneymanager.datalayer.StockRepository;
import org.financeapps.hermesmoneymanager.domainmodel.Account;
import org.financeapps.hermesmoneymanager.domainmodel.AccountTransaction;
import org.financeapps.hermesmoneymanager.domainmodel.AssetClass;
import org.financeapps.hermesmoneymanager.domainmodel.AssetClassStock;
import org.financeapps.hermesmoneymanager.domainmodel.Currency;
import org.financeapps.hermesmoneymanager.domainmodel.Payee;
import org.financeapps.hermesmoneymanager.domainmodel.SplitCategory;
import org.financeapps.hermesmoneymanager.domainmodel.Stock;
import org.financeapps.hermesmoneymanager.servicelayer.AccountService;
import org.financeapps.hermesmoneymanager.servicelayer.PayeeService;

import org.robolectric.fakes.BaseCursor;
import org.robolectric.shadows.ShadowContentResolver;

import info.javaperformance.money.Money;
import info.javaperformance.money.MoneyFactory;

import static org.robolectric.Shadows.shadowOf;

/**
 * Database manipulation. Used for test preparation.
 */
public class DataHelpers {
    public static void insertData() {
        Context context = UnitTestHelper.getContext();

        // add account

        AccountRepository accountRepository = new AccountRepository(context);
        // Bahraini dinar
        Account account = Account.create("cash", AccountTypes.CHECKING, AccountStatuses.OPEN,
                true, 17);
        int accountId = accountRepository.add(account);
        account.setId(accountId);
//        assertThat(accountId).isNotEqualTo(Constants.NOT_SET);

        // add payees

        PayeeRepository repo = new PayeeRepository(context);
        for (int i = 0; i < 3; i++) {
            Payee payee = new Payee();
            payee.setName("payee" + i);
            int payeeId = repo.add(payee);
//            assertThat(payeeId).isNotEqualTo(Constants.NOT_SET);
        }

        // add transactions

        for (int i = 0; i < 3; i++) {
            Money amount = MoneyFactory.fromString("-" + i);
            // this is semantically wrong as there is no category & subcategory!
            createTransaction(accountId, 1, TransactionTypes.Withdrawal, -1, -1, amount);
        }
    }

    public static void createTransaction(int accountId, int payeeId, TransactionTypes type,
                                         int categoryId, int subCategoryId, Money amount) {
        AccountTransactionRepository txRepo = new AccountTransactionRepository(UnitTestHelper.getContext());

        AccountTransaction tx = AccountTransaction.create(accountId, payeeId, type,
                categoryId, subCategoryId, amount);
        int txId = txRepo.add(tx);
//        assertThat(txId).isNotEqualTo(Constants.NOT_SET);

    }

    public static void createSplitTransaction() {
        Context context = UnitTestHelper.getContext();

        // currency
        CurrencyService currencyService = new CurrencyService(context);
        Currency euro = currencyService.getCurrency("EUR");
        // account
        AccountService accountService = new AccountService(context);
        Account account = accountService.createAccount("only", AccountTypes.CHECKING, AccountStatuses.OPEN,
            true, euro.getCurrencyId());
        // payee
        PayeeService payeeService = new PayeeService(context);
        Payee payee = payeeService.createNew("zdravko colic");
        // transaction
        Money amount = MoneyFactory.fromDouble(100);
        AccountTransactionRepository txRepo = new AccountTransactionRepository(context);
        AccountTransaction tx = AccountTransaction.create(account.getId(), payee.getId(),
            TransactionTypes.Withdrawal, 1, -1, amount);
        txRepo.insert(tx);
        // split categories
        SplitCategoriesRepository splitRepo = new SplitCategoriesRepository(context);
        SplitCategory split1 = SplitCategory.create(tx.getId(), 1, -1,
                tx.getTransactionType(), MoneyFactory.fromDouble(25));
        splitRepo.insert(split1);
        SplitCategory split2 = SplitCategory.create(tx.getId(), 1, -1,
                tx.getTransactionType(), MoneyFactory.fromDouble(25));
        splitRepo.insert(split2);
    }

//    private static void setFakeCursor() {
//        ContentResolver resolver = UnitTestHelper.getContext().getContentResolver();
//        ShadowContentResolver shadow = shadowOf(resolver);
//
//        BaseCursor cursor = new AccountCursor();
//        shadow.setCursor(cursor);
//    }

    public static void createAllocation() {
        Context context = UnitTestHelper.getContext();
        AssetClassRepository repo = new AssetClassRepository(context);
        AssetClassStockRepository linkRepo = new AssetClassStockRepository(context);
        StockRepository stockRepo = new StockRepository(context);
        AccountRepository accountRepo = new AccountRepository(context);

        // Currency
        CurrencyService currencyService = new CurrencyService(context);
        Currency eur = currencyService.getCurrency("EUR");

        // Investment account
        Account account = Account.create("investment", AccountTypes.INVESTMENT, AccountStatuses.OPEN,
            true, eur.getCurrencyId());
        accountRepo.save(account);
        int accountId = account.getId();

        // Stock symbols
        Stock stock = Stock.create();
        stock.setSymbol("VHY.ax");
        stock.setHeldAt(accountId);
        stockRepo.insert(stock);

        AssetClass stocks = AssetClass.create("stocks");
        stocks.setAllocation(MoneyFactory.fromString("70"));
        repo.insert(stocks);

        AssetClassStock link = AssetClassStock.create(stocks.getId(), stock.getSymbol());
        linkRepo.insert(link);
    }
}
