package com.negomatic.retailer.di.module;

import android.app.Application;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.negomatic.retailer.api.AuthApi;
import com.negomatic.retailer.api.ItemApi;
import com.negomatic.retailer.api.ProductApi;
import com.negomatic.retailer.api.UserApi;
import com.negomatic.retailer.db.MyDatabase;
import com.negomatic.retailer.db.dao.ConfigurationDao;
import com.negomatic.retailer.db.dao.ItemDao;
import com.negomatic.retailer.db.dao.OrderNoteDao;
import com.negomatic.retailer.db.dao.PersonDao;
import com.negomatic.retailer.db.dao.ProductDao;
import com.negomatic.retailer.db.dao.UserDao;
import com.negomatic.retailer.entity.CurrencyType;
import com.negomatic.retailer.entity.Country;
import com.negomatic.retailer.entity.IdentityDocumentType;
import com.negomatic.retailer.entity.StateType;
import com.negomatic.retailer.repository.ConfigurationRepository;
import com.negomatic.retailer.repository.ItemRepository;
import com.negomatic.retailer.repository.OrderNoteRepository;
import com.negomatic.retailer.repository.PersonRepository;
import com.negomatic.retailer.repository.ProductRepository;
import com.negomatic.retailer.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    // --- DATABASE INJECTION ---
    /*static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };*/

    @Provides
    @Singleton
    MyDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application, MyDatabase.class, "MyDatabase.db")
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                        //state_types
                        List<StateType> stateTypes = new ArrayList<>();
                        stateTypes.add(new StateType("01","Registrado"));
                        stateTypes.add(new StateType("03","Enviado"));
                        stateTypes.add(new StateType("05","Aceptado"));
                        stateTypes.add(new StateType("07","Observado"));
                        stateTypes.add(new StateType("09","Rechazado"));
                        stateTypes.add(new StateType("11","Anulado"));
                        stateTypes.add(new StateType("13","Por anular"));

                        for(StateType i : stateTypes){
                            ContentValues values = new ContentValues();
                            values.put("id", i.getId());
                            values.put("description", i.getDescription());
                            db.insert("state_types", SQLiteDatabase.CONFLICT_IGNORE, values);
                        }

                        //document_types
                        List<IdentityDocumentType> identityDocumentTypes = new ArrayList<>();
                        identityDocumentTypes.add(new IdentityDocumentType(0, "0","Sin documento", "PE",1));
                        identityDocumentTypes.add(new IdentityDocumentType(1, "1","DNI", "PE",1));
                        identityDocumentTypes.add(new IdentityDocumentType(2, "4","Carnet de Extranjería", "PE",1));
                        identityDocumentTypes.add(new IdentityDocumentType(3, "6","RUC", "PE",1));
                        identityDocumentTypes.add(new IdentityDocumentType(4, "7","Pasaporte", "PE",1));

                        for(IdentityDocumentType i : identityDocumentTypes){
                            ContentValues values = new ContentValues();
                            values.put("id", i.getId());
                            values.put("code", i.getCode());
                            values.put("description", i.getDescription());
                            values.put("countryId", "PE");
                            values.put("active", i.getActive());
                            db.insert("cat_identity_document_types", SQLiteDatabase.CONFLICT_REPLACE, values);
                        }

                        //currency_types
                        List<CurrencyType> currencyTypes = new ArrayList<>();

                        currencyTypes.add(new CurrencyType("PAB","PAB","B/.","BALBOA",1));//PANAMA
                        currencyTypes.add(new CurrencyType("BOB","BOB","Bs.","BOLIVIANO",1));//BOLIVIA
                        currencyTypes.add(new CurrencyType("VES","VES","Bs.","BOLIVAR DIGITAL",1));//VENEZUELA
                        currencyTypes.add(new CurrencyType("CRC","CRC","₡","COLÓN COSTARRICENSE",1));//COSTARICA
                        currencyTypes.add(new CurrencyType("NIO","NIO","C$","CÓRDOBA NICARAGÜENCE",1));//NICARAGUA
                        currencyTypes.add(new CurrencyType("HNL","HNL","L.","LEMPIRA",1));//HONDURAS
                        currencyTypes.add(new CurrencyType("ARS","ARS","$","PESO ARGENTINO",1));//ARGENTINA
                        currencyTypes.add(new CurrencyType("CLP","CLP","$","PESO CHILENO",1));//CHILE
                        currencyTypes.add(new CurrencyType("COP","COP","$","PESO COLOMBIANO",1));//COLOMBIA
                        currencyTypes.add(new CurrencyType("MXN","MXN","$","PESO MEXICANO",1));//MEXICO
                        currencyTypes.add(new CurrencyType("UYU","UYU","$","PESO URUGUAYO",1));//URUGUAY
                        currencyTypes.add(new CurrencyType("GTQ","GTQ","Q","QUETZAL",1));//GUATEMALA
                        currencyTypes.add(new CurrencyType("PEN","PEN","S/","SOL",1));//PERU

                        currencyTypes.add(new CurrencyType("USD","USD","$","DOLAR AMERICADO",1));
                        currencyTypes.add(new CurrencyType("BTC","BTC","₿","BITCOIN",1));


                        for (CurrencyType i : currencyTypes){
                            ContentValues values = new ContentValues();
                            values.put("id", i.getId());
                            values.put("code", i.getCode());
                            values.put("symbol", i.getSymbol());
                            values.put("description", i.getDescription());
                            values.put("active", i.getActive());
                            db.insert("cat_currency_types", SQLiteDatabase.CONFLICT_REPLACE, values);

                        }

                        //countries
                        List<Country> countries = new ArrayList<>();

                        countries.add(new Country("AR","AR", "ARGENTINA", "ARS",1));
                        countries.add(new Country("BO","BO", "BOLIVIA", "BOB",1));
                        countries.add(new Country("CL","CL", "CHILE","CLP",1));
                        countries.add(new Country("CO","CO", "COLOMBIA","COP",1));
                        countries.add(new Country("CR","CR", "COSTA RICA","CRC",1));
                        countries.add(new Country("EC","EC", "ECUADOR","USD",1));
                        countries.add(new Country("SV","SV", "EL SALVADOR","USD",1));
                        countries.add(new Country("GT","GT", "GUATEMALA","GTQ",1));
                        countries.add(new Country("HN","HN", "HONDURAS","HNL",1));
                        countries.add(new Country("MX","MX", "MEXICO","MXN",1));
                        countries.add(new Country("NI","NI", "NICARAGUA","NIO",1));
                        countries.add(new Country("PA","PA", "PANAMA","PAB",1));
                        countries.add(new Country("PE","PE", "PERU","PEN",1));
                        countries.add(new Country("UY","UY", "URUGUAY","UYU",1));
                        countries.add(new Country("VE","VE", "VENEZUELA","VES",1));

                        for (Country i : countries){
                            ContentValues values = new ContentValues();
                            values.put("id", i.getId());
                            values.put("code", i.getCode());
                            values.put("description", i.getDescription());
                            values.put("active", i.getActive());
                            values.put("currencyId",i.getCurrencyId());
                            db.insert("countries", SQLiteDatabase.CONFLICT_REPLACE, values);
                        }
                    }
                })
                //.addMigrations(MIGRATION_1_2)
                .build();
    }

    @Provides
    @Singleton
    UserDao provideUserDao(MyDatabase database) { return database.userDao(); }

    @Provides
    @Inject
    ProductDao provideProductDao(MyDatabase database){return database.productDao();}

    @Provides
    @Inject
    ItemDao provideItemDao(MyDatabase database){return database.itemDao();}

    @Provides
    @Inject
    OrderNoteDao provideOrderNoteDao(MyDatabase database){return database.orderNoteDao();}

    @Provides
    @Inject
    ConfigurationDao provideConfigurationDao(MyDatabase database){return database.configurationDao();}

    @Provides
    @Inject
    PersonDao providePersonDao(MyDatabase database){return database.personDao();}

    // --- REPOSITORY INJECTION ---

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    ExecutorService provideExecutorService() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserApi webservice, UserDao userDao, Executor executor) {
        return new UserRepository(webservice, userDao, executor);
    }


    @Provides
    @Singleton
    ProductRepository provideProductRepository(ProductApi webservice, ProductDao productDao, Executor executor){
        return new ProductRepository(webservice,productDao,executor);
    }

    @Provides
    @Singleton
    ItemRepository provideItemRepository(ItemApi webservice, ItemDao itemDao, Executor executor, ExecutorService executorService){
        return new ItemRepository(webservice,itemDao,executor,executorService);
    }
    @Provides
    @Singleton
    OrderNoteRepository provideOrderNoteRepository(OrderNoteDao orderNoteDao, Executor executor, ExecutorService executorService){
        return new OrderNoteRepository(orderNoteDao,executor,executorService);
    }
    @Provides
    @Singleton
    ConfigurationRepository provideConfigurationRepository(ConfigurationDao configurationDao, Executor executor, ExecutorService executorService){
        return new ConfigurationRepository(configurationDao,executor,executorService);
    }
    @Provides
    @Singleton
    PersonRepository providePersonRepository(PersonDao personDao, Executor executor, ExecutorService executorService){
        return new PersonRepository(personDao,executor,executorService);
    }
    // --- NETWORK INJECTION ---

    private static String BASE_URL = "http://retailer1.facturadorpro3.oo:81/api/";

    @Provides
    Gson provideGson() { return new GsonBuilder().create(); }

    @Provides
    Retrofit provideRetrofit(Gson gson) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjA0MDY3YmIwMWZkZTFkNWMyMDc4MDFkNjVkNTA4NjM3NTkzMzIxZmRiMjYzODhlNDU0ZTJhZWU5MTlmMzE4MDhkMTVjNjVkMzQyOTczMjgwIn0.eyJhdWQiOiIxIiwianRpIjoiMDQwNjdiYjAxZmRlMWQ1YzIwNzgwMWQ2NWQ1MDg2Mzc1OTMzMjFmZGIyNjM4OGU0NTRlMmFlZTkxOWYzMTgwOGQxNWM2NWQzNDI5NzMyODAiLCJpYXQiOjE1NzE3NzgzNDIsIm5iZiI6MTU3MTc3ODM0MiwiZXhwIjoxNjAzNDAwNzQxLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.aMF5GhkBGMrP_ZLO17phRLBe4P-QgYbfykHHzAtXbZ5Nd-QYYtcyaIPun3uInfrszNXxt9IWPOclSDpZuPT6bVBhiI2VNa2tX0G43GnNa7TmBSQNkKUQxoXUc70NIifW_d11kpP3-iw05vVLvaN29TbWu0FIOl3ckwm25WTBswMpgPBsf0vE6-y6M2bl-ZJa895DcGpKwubgX2WRZfseo0Nai2ufyxKJVAUFp8EnZ64nf04X4pK3K85RUPgx6JF70hjvlLnRQNAGKvWHNYUX_ZAVYIhvd-d7gevs20PbvRmdtvOCDwCOZ06PLD_e2G6HEGWnTVAtyLZroh288YfcCQnTRoTK1KVB2jH2NDvqGy_GkG65VxX4CPx9wXgz6Tk-YdS7dRnsC3wCppiIyTE4s00dzO83-i9OGPSDFJmc7jhOZYJW_-2T_vssndhf02a7yfyHMxZV8MX9bowh0bk1AerHy7P0t0Wp1qiDDVGNsMrdENMnlm4A2iidzIMBOLj31q6OwWY2yw_bWEGwXjtRiZYobFsZHvWVVqMHDoWdNIHB4fYbCQWw92orMueOxeh32rE45kScMnR_PDsE7PhT9LcsSb7G-Lyy-2g3qKt-9FnnsT7MwQMC2VSIj2xlB22rEzv4QuJKpYjQi5X90wJek57xN7tD55PKqNHB4_KrIJI") //SessionPrefs.get(App.context).getString("PREF_SESSION_TOKEN",""))
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl(BASE_URL)
                .build();
        return retrofit;
    }


    @Provides
    @Singleton
    UserApi provideUserApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(UserApi.class);
    }


    @Provides
    @Singleton
    ProductApi provideProductApiWebservice(Retrofit resAdapter){
        return resAdapter.create(ProductApi.class);
    }

    @Provides
    @Singleton
    AuthApi provideAuthApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(AuthApi.class);
    }

    @Provides
    @Singleton
    ItemApi provideItemApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(ItemApi.class);
    }
}
