package com.example.bigbusiness.Main.ui.Inventory;

import android.content.Context;
import android.content.Intent;
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

import java.util.List;

public class NewInventoryListAdapter extends RecyclerView.Adapter<NewInventoryListAdapter.ViewHolder> {
    List<InventoryItem> inventoryItemsList;
    InventoryActivity inventory;
    Context context;

    public NewInventoryListAdapter(Context context, List<InventoryItem> inventoryItemsList)
    {
        this.context = context;
        this.inventoryItemsList = inventoryItemsList;
//        this.inventoryManager = InventoryManager.getInstance();
//        this.inventoryManager.setItems(this.inventoryItemsList);
    }


    public NewInventoryListAdapter(Context context, InventoryActivity inventoryActivity, InventoryManager inventoryManager) {
//        this.inventoryManager = inventoryManager;
//        this.inventoryItemsList = this.inventoryManager.getInventoryItems();
        this.inventory = inventoryActivity;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_inventory_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InventoryItem itemClicked = this.inventoryItemsList.get(position);
        String code = itemClicked.getItemCode();
        holder.name.setText(itemClicked.getName());
        holder.quantity.setText(itemClicked.getQuantity()+"");
        holder.price.setText(String.valueOf(itemClicked.getPrice()));
        holder.unit.setText(itemClicked.getUnit());
        holder.uom.setText(itemClicked.getUom());
        byte[] image = itemClicked.getImage();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        holder.item_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(context,holder.item_menu);
                popup.inflate(R.menu.inventory_edit_item);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.edit_item:
                                Intent i = new Intent(context, NewAddInventoryActivity.class);
                                i.putExtra("editCard", itemClicked);
                                context.startActivity(i);
                                break;

                            case R.id.delete_item:
//                                inventoryManager.deleteItem(itemClicked);
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
        TextView name, quantity, price, unit, uom;
        Spinner unitspinner;
        Button delete;
        ImageView item_menu , imageView;
        private int position = 0 ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.inventory_item_name);
            quantity = itemView.findViewById(R.id.inventoru_item_quantity);
            price = itemView.findViewById(R.id.inventory_item_price);
            unit = itemView.findViewById(R.id.inventory_item_unit);
            uom = itemView.findViewById(R.id.inventory_item_uom);
            item_menu = itemView.findViewById(R.id.inventory_item_menu);

//            imageView = (ImageView)itemView.findViewById(R.id.imageView2);
        }
    }

}