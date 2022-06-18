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

package org.financeapps.hermesmoneymanager.core.ioc;

import org.financeapps.hermesmoneymanager.MmxContentProvider;
import org.financeapps.hermesmoneymanager.budget.BudgetAdapter;
import org.financeapps.hermesmoneymanager.common.CalculatorActivity;
import org.financeapps.hermesmoneymanager.core.Core;
import org.financeapps.hermesmoneymanager.core.FormatUtilities;
import org.financeapps.hermesmoneymanager.core.Passcode;
import org.financeapps.hermesmoneymanager.core.UIHelper;
import org.financeapps.hermesmoneymanager.currency.CurrencyService;
import org.financeapps.hermesmoneymanager.currency.list.CurrencyListFragment;
import org.financeapps.hermesmoneymanager.datalayer.StockHistoryRepositorySql;
import org.financeapps.hermesmoneymanager.datalayer.StockRepositorySql;
import org.financeapps.hermesmoneymanager.home.HomeFragment;
import org.financeapps.hermesmoneymanager.home.MainActivity;
import org.financeapps.hermesmoneymanager.home.SelectDatabaseActivity;
import org.financeapps.hermesmoneymanager.investment.EditPriceDialog;
import org.financeapps.hermesmoneymanager.investment.prices.ISecurityPriceUpdater;
import org.financeapps.hermesmoneymanager.investment.InvestmentTransactionEditActivity;
import org.financeapps.hermesmoneymanager.investment.PriceEditActivity;
import org.financeapps.hermesmoneymanager.investment.PriceEditModel;
import org.financeapps.hermesmoneymanager.investment.morningstar.MorningstarPriceUpdater;
import org.financeapps.hermesmoneymanager.recurring.transactions.RecurringTransactionEditActivity;
import org.financeapps.hermesmoneymanager.recurring.transactions.RecurringTransactionListFragment;
import org.financeapps.hermesmoneymanager.reports.BaseReportFragment;
import org.financeapps.hermesmoneymanager.search.SearchParametersFragment;
import org.financeapps.hermesmoneymanager.servicelayer.InfoService;
import org.financeapps.hermesmoneymanager.settings.AppSettings;
import org.financeapps.hermesmoneymanager.settings.DatabaseSettingsFragment;
import org.financeapps.hermesmoneymanager.settings.SettingsActivity;
import org.financeapps.hermesmoneymanager.sync.SyncManager;
import org.financeapps.hermesmoneymanager.sync.SyncPreferenceFragment;
import org.financeapps.hermesmoneymanager.sync.SyncService;
import org.financeapps.hermesmoneymanager.sync.SyncServiceMessageHandler;
import org.financeapps.hermesmoneymanager.transactions.CheckingTransactionEditActivity;
import org.financeapps.hermesmoneymanager.transactions.EditTransactionCommonFunctions;
import org.financeapps.hermesmoneymanager.utils.MmxDatabaseUtils;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Main Dagger 2 Component. Represents the link between the Modules and Injections.
 * Component consumes functionality.
 */
@Singleton
@Component(modules = MmxModule.class)
public interface MmxComponent {
    // Example on how to expose a provision method.
//    MmxOpenHelper getHelper();

    // Activities
    void inject(CalculatorActivity activity);
    void inject(CheckingTransactionEditActivity activity);
    void inject(InvestmentTransactionEditActivity activity);
    void inject(MainActivity activity);
    void inject(PriceEditActivity activity);
    void inject(RecurringTransactionEditActivity activity);
    void inject(SelectDatabaseActivity activity);
    void inject(SettingsActivity activity);

    // Fragments
    void inject(BaseReportFragment fragment);
    void inject(CurrencyListFragment fragment);
    void inject(DatabaseSettingsFragment fragment);
    void inject(HomeFragment fragment);
    void inject(RecurringTransactionListFragment fragment);
    void inject(SearchParametersFragment fragment);
    void inject(SyncPreferenceFragment fragment);

    // Dialogs
    void inject(EditPriceDialog dialog);

    // Models
    void inject(PriceEditModel model);

    // Custom objects
    void inject(ISecurityPriceUpdater updater);
    void inject(MorningstarPriceUpdater updater);
    void inject(AppSettings settings);
    void inject(Core core);
    void inject(MmxContentProvider provider);
    void inject(MmxDatabaseUtils utils);
    void inject(SyncManager sync);
    void inject(SyncServiceMessageHandler handler);
    void inject(Passcode object);
    void inject(EditTransactionCommonFunctions object);

    // Helpers
    void inject(UIHelper helper);
    void inject(FormatUtilities utils);

    // Business Services
    void inject(CurrencyService service);
    void inject(InfoService service);

    // Intent Services
    void inject(SyncService service);

    // Repositories
    void inject(StockRepositorySql repository);
    void inject(StockHistoryRepositorySql repository);

    // Adapters
    void inject(BudgetAdapter adapter);
}
