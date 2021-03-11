package com.example.bigbusiness.Main.ui.Inventory;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigbusiness.Main.ui.Finance.RemindersAndDues.ReminderCardsManager;
import com.example.bigbusiness.Main.ui.Finance.RemindersAndDues.RemindersAndDuesFragment;
import com.example.bigbusiness.Models.InventoryItem;
import com.example.bigbusiness.Models.Reminder;
import com.example.bigbusiness.R;

import java.util.ArrayList;
import java.util.List;

public class InventoryListAdapter extends RecyclerView.Adapter<InventoryListAdapter.ViewHolder> {

    InventoryManager inventoryManager;
    List<InventoryItem> inventoryItemsList;
    InventoryActivity inventory;
    Context context;

    public InventoryListAdapter(Context context, List<InventoryItem> inventoryItemsList)
    {
        this.context = context;
        this.inventoryItemsList = inventoryItemsList;
        this.inventoryManager = InventoryManager.getInstance();
        this.inventoryManager.setItems(this.inventoryItemsList);
    }


    public InventoryListAdapter(Context context, InventoryActivity inventoryActivity, InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
        this.inventoryItemsList = this.inventoryManager.getInventoryItems();
        this.inventory = inventoryActivity;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InventoryItem itemClicked = this.inventoryItemsList.get(position);
        String id = itemClicked.getItemCode();
        holder.item_name.setText(itemClicked.getName());
        holder.quantitytextview.setText(itemClicked.getQuantity()+"");
        holder.itemprice.setText(String.valueOf(itemClicked.getPrice()));
        holder.item_unit.setText(itemClicked.getUnit());

        byte[] image = itemClicked.getImage();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        holder.hamburger_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(context,holder.hamburger_menu);
                popup.inflate(R.menu.inventory_edit_item);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.edit_item:
                                Intent i = new Intent(context, AddInventoryItem.class);
                                i.putExtra("editCard", itemClicked);
                                context.startActivity(i);
                                break;

                            case R.id.delete_item:
                                inventoryManager.deleteItem(itemClicked);
                                notifyDataSetChanged();
                                break;
                            default:
                                return false;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.inventoryItemsList.size();
    }

    public List<InventoryItem> getInventoryItemsList()
    {
        return this.inventoryItemsList;
    }

    public void setInventoryItemsList(List<InventoryItem> updatedItemsList)
    {
        this.inventoryItemsList = updatedItemsList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_name, quantity, itemprice,item_unit, quantitytextview;
        Spinner unitspinner;
        Button delete;
        ImageView hamburger_menu , imageView;
        private int position = 0 ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quantitytextview = (TextView) itemView.findViewById(R.id.item_quantity);
            item_name = (TextView) itemView.findViewById(R.id.item_name);
           /* increase = (Button) itemView.findViewById(R.id.increase);
            decrease = (Button) itemView.findViewById(R.id.decrease);
            delete = (Button) itemView.findViewById(R.id.deleteitem);*/
            quantity = (TextView) itemView.findViewById(R.id.item_quantity);
            unitspinner = (Spinner)itemView.findViewById(R.id.unitspinner);
            itemprice = (TextView)itemView.findViewById(R.id.itemprice);
            item_unit = (TextView)itemView.findViewById(R.id.item_unit);
            hamburger_menu = (ImageView)itemView.findViewById(R.id.hamburger_menu);
//            imageView = (ImageView)itemView.findViewById(R.id.imageView2);
        }
    }

}