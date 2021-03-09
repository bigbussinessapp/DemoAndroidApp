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

import com.example.bigbusiness.Models.InventoryItem;
import com.example.bigbusiness.R;

import java.util.ArrayList;
import java.util.List;

public class InventoryListAdapter extends RecyclerView.Adapter<InventoryListAdapter.ViewHolder> {

    InventoryManager inventoryManager;
    List<InventoryItem> inventoryItemsList;
    InventoryActivity inventory;
    Context context;

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
        InventoryItem itemClicked = this.inventoryManager.getInventoryItems().get(position);
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
                                inventory.editItem(itemClicked);
                                notifyDataSetChanged();
//                                inventoryManager.editItem(itemClicked);

                            case R.id.delete_item:
                                inventoryManager.deleteItem(itemClicked);
                                notifyDataSetChanged();
                            default:
                                return false;
                        }
                    }
                    public void editedItem() {
                        inventoryManager.updateItem(itemClicked);
                        Intent intent = new Intent(context,AddInventoryItem.class);
                        intent.putExtra("invoiceId",String.valueOf(itemClicked.getItemCode()));
                        intent.putExtra("name",String.valueOf(itemClicked.getName()));
                        intent.putExtra("qunatity",String.valueOf(itemClicked.getQuantity()));
                        intent.putExtra("price",String.valueOf(itemClicked.getPrice()));
                        intent.putExtra("units",String.valueOf(itemClicked.getUnit()));
                        context.startActivity(intent);
                    }
                });
                popup.show();
               /* Context context = view.getContext();
                Intent intent = new Intent(context,InventoryEditItem.class);
                context.startActivity(intent);*/
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

    public void setInventoryItemsList(ArrayList<InventoryItem> updatedItemsList)
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