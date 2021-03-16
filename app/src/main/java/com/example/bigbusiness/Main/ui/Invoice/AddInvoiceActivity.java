package com.example.bigbusiness.Main.ui.Invoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import android.content.pm.PackageManager;
import android.drm.DrmStore;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;

import com.example.bigbusiness.Main.ui.Inventory.InventoryManager;
import com.example.bigbusiness.Models.BuyerDetails;
import com.example.bigbusiness.Models.InventoryItem;
import com.example.bigbusiness.Models.InvoiceItem;
import com.example.bigbusiness.Models.InvoiceProduct;
import com.example.bigbusiness.R;
import com.example.bigbusiness.Services.UserDataService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AddInvoiceActivity extends AppCompatActivity {

    private TextInputEditText invoiceName, buyerName, itemName, itemQuantity;
    TextView availableInventoryView;
    private Button addItemBtn, clearBtn, saveToPdfBtn;
    RecyclerView inventoryListView;
    InvoiceManager invoiceManager;
    String invoiceId;
    Spinner inventoryItemsSpinner;
    InventoryManager inventoryManager;
    int availableQuantity = 0;
    InventoryItem selectedItem = null;
    FirebaseDatabase database;
    DatabaseReference inventoryReference;
    Bitmap image , scalebitmp;
    int pageWidth = 1200;
    Date date;
    DateFormat dateFormat;
    ConvertDigitsToWords obj = new ConvertDigitsToWords();
    UserDataService userDataService;
    private static int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_invoice);
        userDataService = UserDataService.getInstance();
        database = FirebaseDatabase.getInstance();
        inventoryReference = database.getReference("Users").child(userDataService.getLoggedInUser().getUid()).child("Inventory");

        invoiceManager = InvoiceManager.getInstance();
        inventoryListView = (RecyclerView) findViewById(R.id.inventory_list_view);

        invoiceName = findViewById(R.id.invoiceName);
        buyerName = findViewById(R.id.custName);
        addItemBtn = findViewById(R.id.addItemBtn);
        clearBtn = findViewById(R.id.clearBtn);
        saveToPdfBtn = findViewById(R.id.saveToPdfBtn);
        inventoryManager = InventoryManager.getInstance();
        inventoryItemsSpinner = findViewById(R.id.in_inv_item_spinner);
        availableInventoryView = findViewById(R.id.available_inv);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //final InventoryItem[] selectedItem = new InventoryItem[1];
        invoiceId = "Invoice"+count++;

        List<String> inventoryItems = new ArrayList<>();
        inventoryManager.getInventoryItems().forEach(item -> inventoryItems.add(item.getName()));
        ArrayAdapter<String> invItemsAdapter = new ArrayAdapter<>(AddInvoiceActivity.this,
                android.R.layout.simple_list_item_1, inventoryItems );
        invItemsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inventoryItemsSpinner.setAdapter(invItemsAdapter);
        inventoryItemsSpinner.setOnItemSelectedListener(itemsSpinnerEvent(inventoryItems));
//
//        inventoryReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot item: snapshot.getChildren())
//                {
//                    InventoryItem invItem = item.getValue(InventoryItem.class);
//                    inventoryItems.add(invItem.getName());
//                }
//                invItemsAdapter.addAll(inventoryItems);
//                inventoryItemsSpinner.setOnItemSelectedListener(itemsSpinnerEvent(inventoryItems));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        inventoryManager.getInventoryItems().forEach(x -> {
//            inventoryItems.add(x.getName());
//        });

        List<InvoiceProduct> itemsList = invoiceManager.getAddedItems();
        InvoiceItemsAdapter invoiceItemsAdapter = new InvoiceItemsAdapter(this, itemsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL , false);
        inventoryListView.setLayoutManager(layoutManager);
        inventoryListView.setAdapter(invoiceItemsAdapter);

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemQuantity = findViewById(R.id.quantity);

                String quantity = itemQuantity.getText().toString();
                if(selectedItem == null || quantity.isEmpty())
                {
                    Toast.makeText(AddInvoiceActivity.this, "Fill all details", Toast.LENGTH_SHORT).show();
                }
                else if(Integer.parseInt(quantity) > availableQuantity)
                {
                    Toast.makeText(AddInvoiceActivity.this, "Only "+availableQuantity+" available!", Toast.LENGTH_SHORT).show();
                }
                else if(selectedItem == null)
                {
                    Toast.makeText(AddInvoiceActivity.this, "Item is null", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    InvoiceProduct item = new InvoiceProduct(String.valueOf(selectedItem.getItemCode()), quantity);
                    invoiceManager.addProduct(item);
                    availableQuantity -= Integer.parseInt(quantity);
                    availableInventoryView.setText("* AvailableInventory: "+String.valueOf(availableQuantity));
                    invoiceItemsAdapter.notifyDataSetChanged();
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemQuantity = findViewById(R.id.quantity);

                inventoryItemsSpinner.clearFocus();
                itemQuantity.setText("");
            }
        });

        saveToPdfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = invoiceName.getText().toString();
                BuyerDetails buyer = new BuyerDetails(buyerName.getText().toString());
                HashMap<String, InvoiceProduct> items = invoiceManager.getAddedItemsWithIds();

                if(name.isEmpty() || buyer.getName().isEmpty())
                {
                    Toast.makeText(AddInvoiceActivity.this, "Fill All details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    InvoiceItem invoiceItem = new InvoiceItem(name, buyer, items);
//                    invoiceItem.setInvoiceId(invoiceId);
                    createPdf();
                    invoiceManager.addInvoice(invoiceItem);
                    invoiceManager.clearItems();
                    Intent i = new Intent(AddInvoiceActivity.this, InvoiceManagementActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    private void createPdf() {
        date = new Date();

        PdfDocument myPdfDocument = new PdfDocument();
        Paint paint = new Paint();
        Paint titlePaint = new Paint();
        PdfDocument.PageInfo PageInfo = new PdfDocument.PageInfo.Builder(1200, 2010, 1).create();
        PdfDocument.Page page1 = myPdfDocument.startPage(PageInfo);

        Canvas canvas = page1.getCanvas();

        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setColor(Color.rgb(0 , 112 , 100));
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT , Typeface.BOLD));
        titlePaint.setTextSize(70f);
        canvas.drawText("TAX INVOICE" , pageWidth/2 , 270 , titlePaint);

        paint.setColor(Color.rgb(0 , 112 , 100));
        paint.setTextSize(45f);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(userDataService.getLoggedInUser().getName() , 20 , 80 , paint);
        paint.setTextSize(32f);
        paint.setColor(Color.GRAY);
        canvas.drawText("Phone no: +91-" + userDataService.getLoggedInUser().getPhoneNo() ,20 , 125 , paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(35f);
        paint.setColor(Color.BLACK);
        canvas.drawText("Bill to : " + buyerName.getText() , 20 , 390 , paint);

        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("Invoice No. :" + invoiceId , pageWidth-20 , 390 , paint);

        dateFormat = new SimpleDateFormat("dd/MM/yy");
        paint.setColor(Color.GRAY);
        canvas.drawText("Date:" +dateFormat.format(date) , pageWidth-20 , 440 , paint);

        dateFormat = new SimpleDateFormat("HH:mm");
        canvas.drawText("Time:" + dateFormat.format(date) , pageWidth-20 , 480 , paint);

        paint.setColor(Color.rgb(247 , 147 , 30));
        canvas.drawRect(20 , 680 , pageWidth-20 , 760 , paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawText("SNo." , 40 , 730 , paint);
        canvas.drawText("Item Name" , 150 , 730 , paint);
        canvas.drawText("Price" , 470 , 730 , paint);
        canvas.drawText("Qty." , 660 , 730 , paint);
        canvas.drawText("Unit" , 830 , 730 , paint);
        canvas.drawText("Amount" , 1020 , 730 , paint);


        canvas.drawLine(120 , 690 , 120 , 740 , paint);
        canvas.drawLine(450 , 690 , 450 , 740 , paint);
        canvas.drawLine(650 , 690 , 650 , 740 , paint);
        canvas.drawLine(800 , 690 , 800 , 740 , paint);
        canvas.drawLine(950 , 690 , 950 , 740 , paint);

        paint.setColor(Color.BLACK);

        float total =0;
        if(inventoryItemsSpinner.getSelectedItemPosition() != 0){
            int count = 1;
            canvas.drawText(""+count , 40 , 830 , paint);
            canvas.drawText(inventoryItemsSpinner.getSelectedItem().toString() , 150 , 830 , paint);
//            canvas.drawText(String.valueOf(prices[ItemsSpinner.getSelectedItemPosition()]) , 470 , 830 , paint);
            canvas.drawText("Rs.255" , 470 , 830 , paint);
            canvas.drawText(itemQuantity.getText().toString() , 675 , 830 , paint);
            canvas.drawText("Pcs" , 830 , 830 , paint);
//            total = Float.parseFloat(Quantity.getText().toString())*prices[ItemsSpinner.getSelectedItemPosition()];
            paint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(String.valueOf(total) , pageWidth-40 , 830 , paint);
            paint.setTextAlign(Paint.Align.LEFT);
            count++;
        }

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawRect(20 , 1200 , pageWidth - 20 , 1270 , paint);
        paint.setStyle(Paint.Style.FILL);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT , Typeface.BOLD));
        titlePaint.setTextSize(35f);
        canvas.drawText("Total" , 150 , 1245 , titlePaint);
        canvas.drawText(String.valueOf(total) , pageWidth-100 , 1245 , titlePaint);
        canvas.drawText(itemQuantity.getText().toString() , 650 , 1245 , titlePaint);

        canvas.drawText("Sub Total" , 700 , 1400 , paint);
        canvas.drawText(":" , 900 , 1400 , paint);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(String.valueOf(total) , pageWidth-40 , 1400 , paint);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Tax (18%)" , 700 , 1500 , paint);
        canvas.drawText(":" , 900 , 1500 , paint);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(String.valueOf(total*18/100) , pageWidth-40 , 1500 , paint);
        paint.setTextAlign(Paint.Align.LEFT);


        paint.setColor(Color.rgb(247 , 147 , 30));
        canvas.drawRect(680 , 1550 , pageWidth-20 , 1650 , paint);
        paint.setColor(Color.WHITE);
        canvas.drawText("Total" , 700, 1615 , paint);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(String.valueOf(total + (total*18/100)) , pageWidth-40 , 1615 , paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setColor(Color.BLACK);
        canvas.drawText("INVOICE AMOUNT IN WORDS" , 20 , 1500 , paint);
        paint.setColor(Color.LTGRAY);
        canvas.drawRect(20 , 1550 , 660 , 1650 , paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(28f);
        //int totalnum = (int) (total + (total*18/100));
        //canvas.drawText(obj.convertNumberToWords(totalnum).toUpperCase(), 20, 1615, paint);
        canvas.drawText("", 20, 1615, paint);

        myPdfDocument.finishPage(page1);

        File file = new File(Environment.getExternalStorageDirectory(), "/bigBusiness.pdf");

        try {
            myPdfDocument.writeTo(new FileOutputStream(file));
            //Toast.makeText(getApplicationContext() , "Your PDF is Generated..!! Please See your local Storage", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        myPdfDocument.close();

    }

    private AdapterView.OnItemSelectedListener itemsSpinnerEvent(List<String> inventoryItems)
    {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = inventoryManager.getInventoryItems().get(position);
                if(selectedItem == null)
                    Toast.makeText(AddInvoiceActivity.this, "Item not found in inventory", Toast.LENGTH_SHORT).show();
                availableQuantity = selectedItem.getQuantity() - invoiceManager.getAddedQuantityById(selectedItem.getItemCode());
                availableInventoryView.setText("* AvailableInventory: "+String.valueOf(availableQuantity));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }
}