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

package org.financeapps.hermesmoneymanager.assetallocation.list;

import android.view.View;

import org.financeapps.hermesmoneymanager.R;
import org.financeapps.hermesmoneymanager.common.events.ListItemClickedEvent;
import org.financeapps.hermesmoneymanager.view.RobotoTextView;

import org.greenrobot.eventbus.EventBus;

import androidx.recyclerview.widget.RecyclerView;

/**
 * View holder for the list item.
 */
public class AssetClassListItemViewHolder
    extends RecyclerView.ViewHolder {

    public int id;

    public RobotoTextView nameView;

    public AssetClassListItemViewHolder(View itemView) {
        super(itemView);

        nameView = itemView.findViewById(R.id.nameView);
        nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RobotoTextView textView = (RobotoTextView) v;
                EventBus.getDefault().post(new ListItemClickedEvent(id, textView.getText().toString(), v));
            }
        });
    }
}
