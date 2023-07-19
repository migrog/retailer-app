package com.negomatic.retailer.di.component;

import android.app.Application;

import com.negomatic.retailer.di.module.FragmentModule;
import com.negomatic.retailer.App;
import com.negomatic.retailer.di.module.ActivityModule;
import com.negomatic.retailer.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules={ActivityModule.class, FragmentModule.class, AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(App app);
}
