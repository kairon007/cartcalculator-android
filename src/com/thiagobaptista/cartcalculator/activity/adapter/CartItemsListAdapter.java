/*
 * Cart Calculator - simple, generic shopping cart total due calculator
 * Copyright (c) 2014 Thiago Gonçalves Baptista
 * contato@thiagobaptista.com
 * 
 * This program is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License 
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.thiagobaptista.cartcalculator.activity.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.thiagobaptista.cartcalculator.R;
import com.thiagobaptista.cartcalculator.activity.HomeActivity;
import com.thiagobaptista.cartcalculator.activity.action.ButtonLessItemAction;
import com.thiagobaptista.cartcalculator.activity.action.ButtonPlusItemAction;
import com.thiagobaptista.cartcalculator.model.Cart;
import com.thiagobaptista.cartcalculator.model.CartItem;

public class CartItemsListAdapter extends BaseAdapter
{
	private HomeActivity activity;
	
	private Cart cart;
	
	public CartItemsListAdapter(HomeActivity activity, Cart cart)
	{
		this.activity = activity;
		this.cart = cart;
	}
	
	@Override
	public int getCount()
	{
		return cart.getSize();
	}

	@Override
	public Object getItem(int position)
	{
		return cart.getItems().get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = activity.getLayoutInflater().inflate(R.layout.list_cart_items, null);
		
		CartItem item = (CartItem) getItem(position);
		
		setupViews(view, item);
		
		return view;
	}
	
	public void updateByCart(Cart cart)
	{
		this.cart = cart;
		
		notifyDataSetChanged();
	}

	private void setupViews(View view, CartItem item)
	{
		setupNameTextView(view, item);		
		setupPriceTextView(view, item);		
		setupQuantityTextView(view, item);		
		setupPlusItemButton(view, item);		
		setupLessItemButton(view, item);
	}

	private void setupLessItemButton(View view, CartItem item)
	{
		Button lessItemButton = (Button) view.findViewById(R.id.button_list_cart_items_less_item);
		
		lessItemButton.setOnClickListener( new ButtonLessItemAction(activity, cart, item) );
	}

	private void setupPlusItemButton(View view, CartItem item)
	{
		Button plusItemButton = (Button) view.findViewById(R.id.button_list_cart_items_plus_item);
		
		plusItemButton.setOnClickListener( new ButtonPlusItemAction(activity, item) );
	}

	private void setupQuantityTextView(View view, CartItem item)
	{
		TextView quantityTextView = (TextView) view.findViewById(R.id.text_view_list_cart_items_quantity);
		
		quantityTextView.setText("" + item.getQuantity());
	}

	private void setupPriceTextView(View view, CartItem item)
	{
		TextView priceTextView = (TextView) view.findViewById(R.id.text_view_list_cart_items_price);
		
		priceTextView.setText( item.getUnitPriceText() );
	}

	private void setupNameTextView(View view, CartItem item)
	{
		TextView nameTextView = (TextView) view.findViewById(R.id.text_view_list_cart_items_name);
		
		nameTextView.setText( item.getName() );
	}
}
