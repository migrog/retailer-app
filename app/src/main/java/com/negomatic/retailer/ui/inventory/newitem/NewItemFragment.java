package com.negomatic.retailer.ui.inventory.newitem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.negomatic.retailer.R;
import com.negomatic.retailer.entity.Category;
import com.negomatic.retailer.entity.Item;
import com.negomatic.retailer.entity.UnitType;
import com.negomatic.retailer.model.Attribute;
import com.negomatic.retailer.ui.categories.CategoriesBottomSheetFragment;
import com.negomatic.retailer.ui.categories.CategoryViewModel;
import com.negomatic.retailer.ui.inventory.InventoryViewModel;
import com.negomatic.retailer.ui.unittypes.UnitTypeViewModel;
import com.negomatic.retailer.ui.unittypes.UnitTypesBottomSheetFragment;
import com.negomatic.retailer.util.enumerado.ActionType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

import static com.negomatic.retailer.App.context;

public class NewItemFragment extends Fragment implements CategoriesBottomSheetFragment.CategoriesDialogListener, UnitTypesBottomSheetFragment.UnitTypesDialogListener {

    public static final int PICK_IMAGE = 1;
    public static final int CAPTURE_PHOTO = 2;
    String currentPhotoPath;

    private Item item = new Item();
    private List<Attribute> attributes = new ArrayList<>();
    private Category category = new Category();
    private UnitType unitType = new UnitType();

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private InventoryViewModel mViewModel;
    private UnitTypeViewModel unitTypeViewModel;
    private CategoryViewModel categoryViewModel;

    @BindView(R.id.iv_image_item)
    ImageView iv_image_item;

    @BindView(R.id.btn_capture_image)
    Button btn_capture_image;

    @BindView(R.id.til_name_item)
    TextInputLayout til_name_item;
    @BindView(R.id.et_name_item)
    TextInputEditText et_name_item;


    @BindView(R.id.til_sale_unit_price)
    TextInputLayout til_sale_unit_price;
    @BindView(R.id.et_sale_unit_price)
    TextInputEditText et_sale_unit_price;

    @BindView(R.id.til_unit)
    TextInputLayout til_unit;
    @BindView(R.id.tv_unit)
    AutoCompleteTextView tv_unit;

    @BindView(R.id.til_category)
    TextInputLayout til_category;
    @BindView(R.id.tv_category)
    AutoCompleteTextView tv_category;

    @BindView(R.id.et_item_code)
    TextInputEditText et_item_code;

    @BindView(R.id.et_barcode)
    TextInputEditText et_barcode;

    @BindView(R.id.et_description)
    TextInputEditText et_description;

    @BindView(R.id.btn_save_item)
    Button btn_save_item;


    @BindView(R.id.sv_new_item)
    ScrollView sv_new_item;


    /*public static NewItemFragment newInstance() {
        return new NewItemFragment();
    }*/
    public NewItemFragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory_new_item, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.configureDagger();

        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(InventoryViewModel.class);

        Bundle bundle = getActivity().getIntent().getExtras();
        if(bundle != null){
            getActivity().setTitle("Editar producto");
            int itemId = bundle.getInt("itemId");

            //unit types
            unitTypeViewModel = new ViewModelProvider(this, viewModelFactory).get(UnitTypeViewModel.class);
            unitTypeViewModel.init();

            //categories
            categoryViewModel = new ViewModelProvider(this, viewModelFactory).get(CategoryViewModel.class);
            categoryViewModel.init();

            mViewModel.getItem(itemId).observe(getViewLifecycleOwner(), _item->updateUI(_item));

        }

        btn_capture_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage(view);
            }
        });

        et_name_item.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                til_name_item.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_sale_unit_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                til_sale_unit_price.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tv_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCategories(view);
            }
        });
        til_category.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(category == null){
                    showCategories(view);
                }else{
                    if(category.getId() == 0){
                        showCategories(view);
                        return;
                    }
                    category = null;
                    tv_category.setText("");
                    til_category.setEndIconDrawable(R.drawable.ic_baseline_arrow_drop_down_24);
                }

            }
        });
        tv_unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUnitTypes(view);
            }
        });
        til_unit.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(unitType == null){
                    showUnitTypes(view);
                }else{
                    if(unitType.getId() == 0){
                        showUnitTypes(view);
                        return;
                    }
                    unitType = null;
                    tv_unit.setText("");
                    til_unit.setEndIconDrawable(R.drawable.ic_baseline_arrow_drop_down_24);
                }
            }
        });




        btn_save_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validar inputs
                if(!isInputsValid())
                    return;

                //guardar
                //requeridos
                item.setName(et_name_item.getText().toString());
                item.setItemTypeId("0");
                item.setUnitTypeId(String.valueOf(unitType.getId()));
                item.setCurrencyTypeId("0");
                item.setSaleUnitPrice(Double.parseDouble(et_sale_unit_price.getText().toString()));
                item.setPurchaseHasIgv(1);
                item.setHasIgv(1);
                item.setPurchaseUnitPrice(0.000000);
                item.setHasIsc(0);
                item.setAmountPlasticBagTaxes(0.1);
                item.setPercentageIsc(0.00);
                item.setSaleAffectationIgvTypeId("0");
                item.setPurchaseAffectationIgvTypeId("0");
                item.setCalculateQuantity(0);
                item.setIsSet(0);
                item.setImage("imagen-no-disponible.jpg");
                item.setImageMedium("imagen-no-disponible.jpg");
                item.setImageSmall("imagen-no-disponible.jpg");
                item.setStock(0.0000);
                item.setStockMin(0.00);
                item.setHasPlasticBagTaxes(0);
                item.setLotsEnabled(0);
                item.setSeriesEnabled(0);
                item.setPercentageOfProfit(0.00);
                item.setHasPerception(0);
                item.setActive(1);
                item.setWebPlatformId(0);
                item.setStatus(1);
                item.setApply_store(0);
                item.setCategoryId(category.getId());

                Attribute a = new Attribute();
                //a.setDescription("atributo prueba");
                attributes.add(a);
                item.setAttributes(attributes);

                //opcionales
                item.setItemCode(et_item_code.getText().toString());
                item.setBarcode(et_barcode.getText().toString());
                item.setDescription(et_description.getText().toString());

                item = mViewModel.saveItem(item);
                //Toast.makeText(context,"Datos guardado",Toast.LENGTH_SHORT).show();
                Snackbar.make(view, "Guardado exitoso!", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode != Activity.RESULT_CANCELED){
            switch (requestCode){
                case PICK_IMAGE:
                    if (resultCode == Activity.RESULT_OK) {
                        if (data == null) {
                            //Display an error
                            return;
                        }
                        try {
                            InputStream inputStream = context.getContentResolver().openInputStream(data.getData());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
                    }
                    break;
                case CAPTURE_PHOTO:
                    if(resultCode==Activity.RESULT_OK){
                        try {
                            //setPic();
                            Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
                            iv_image_item.setImageBitmap(bitmap);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    break;
            }
        }
    }
    public void pickImage(View view){
        //OPCION 1
        /*Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar imagen"), PICK_IMAGE);*/

        //OPCION2
        /*Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        startActivityForResult(chooserIntent, PICK_IMAGE);*/

        //OPCION 3
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent, PICK_IMAGE);
    }
    public void captureImage(View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(context,
                        "com.negomatic.retailer.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAPTURE_PHOTO);
            }
        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir("items/images");
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        Log.d("currentPhotoPath",currentPhotoPath);

        return image;
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = iv_image_item.getWidth();
        int targetH = iv_image_item.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        iv_image_item.setImageBitmap(bitmap);
    }

    public void showCategories(View view){
        CategoriesBottomSheetFragment b = CategoriesBottomSheetFragment.newInstance();
        b.setStyle(DialogFragment.STYLE_NORMAL,R.style.BottomSheetDialogTheme);
        b.setTargetFragment(NewItemFragment.this, 300);
        b.show(getFragmentManager(), "dialog");
    }
    public void showUnitTypes(View view){
        UnitTypesBottomSheetFragment b = UnitTypesBottomSheetFragment.newInstance();
        b.setStyle(DialogFragment.STYLE_NORMAL,R.style.BottomSheetDialogTheme);
        b.setTargetFragment(NewItemFragment.this, 300);
        b.show(getFragmentManager(), "dialog");
    }

    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }

    private boolean isNameValid(String value){
        if(value.isEmpty()){
            til_name_item.setError("Requerido");
            sv_new_item.smoothScrollTo(til_name_item.getScrollX(),til_name_item.getScrollY());
            return false;
        }
        return true;
    }

    private boolean isSaleUnitPriceValid(String value){
        if(value.isEmpty()){
            til_sale_unit_price.setError("Requerido");
            sv_new_item.smoothScrollTo(til_sale_unit_price.getScrollX(),til_sale_unit_price.getScrollY());
            return false;
        }
        return true;
    }

    private boolean isInputsValid(){
        String saleUnitPrice =  til_sale_unit_price.getEditText().getText().toString();
        String name = til_name_item.getEditText().getText().toString();

        boolean a = isSaleUnitPriceValid(saleUnitPrice);
        boolean b = isNameValid(name);

        if (a && b) {
            return true;
        }
        return false;
    }

    @Override
    public void setCategory(Category value, ActionType actionType) {
        if(actionType == ActionType.ADD || actionType == ActionType.SELECT) {
            this.category = value;
            updateCategory(value);
            return;
        }
        if(actionType == ActionType.EDIT){
            if(value.getId() == this.category.getId()){
                this.category = value;
                updateCategory(value);
                return;
            }
        }
        if (actionType == ActionType.DELETE){
            if(value.getId() == this.category.getId()){
                this.category = null;
                updateCategory(null);
                return;
            }
        }
    }

    @Override
    public void setUnitType(UnitType value, ActionType actionType) {
        if(actionType == ActionType.ADD || actionType == ActionType.SELECT) {
            this.unitType = value;
            updateUnitType(value);
            return;
        }
        if(actionType == ActionType.EDIT){
            if(value.getId() == this.unitType.getId()){
                this.unitType = value;
                updateUnitType(value);
                return;
            }
        }
        if (actionType == ActionType.DELETE){
            if(value.getId() == this.unitType.getId()){
                this.unitType = null;
                updateUnitType(null);
                return;
            }
        }
    }

    public void updateCategory(Category category) {
        if(category != null) {
            tv_category.setText(category.getName());
            til_category.setEndIconDrawable(R.drawable.ic_baseline_close_24);
        }else {
            tv_category.setText("");
            til_category.setEndIconDrawable(R.drawable.ic_baseline_arrow_drop_down_24);
        }
    }
    public void updateUnitType(UnitType unitType) {
        if(unitType != null) {
            tv_unit.setText(unitType.getDescription());
            til_unit.setEndIconDrawable(R.drawable.ic_baseline_close_24);
        }else {
            tv_unit.setText("");
            til_unit.setEndIconDrawable(R.drawable.ic_baseline_arrow_drop_down_24);
        }
    }
    private void updateUI(Item item){
        this.item = item;
        if(item != null){
            et_name_item.setText(item.getName());
            et_sale_unit_price.setText(String.valueOf(item.getSaleUnitPrice()));
            et_item_code.setText(item.getItemCode());
            et_barcode.setText(item.getBarcode());
            et_description.setText(item.getDescription());

            //unidad
            unitTypeViewModel.list().observe(getViewLifecycleOwner(), _unitTypes->{
                if(_unitTypes != null){
                    UnitType _unitType = _unitTypes.stream().filter(x-> String.valueOf(x.getId()).equals(item.getUnitTypeId())).findFirst().orElse(null);
                    if(_unitType != null)
                        setUnitType(_unitType, ActionType.SELECT);
                }
            });

            //categoria
            categoryViewModel.list().observe(getViewLifecycleOwner(),_categories->{
                if(_categories != null){
                    Category _category = _categories.stream().filter(x->x.getId() == item.getCategoryId()).findFirst().orElse(null);
                    if(_category != null)
                        setCategory(_category, ActionType.SELECT);
                }
            });

        }
    }
}