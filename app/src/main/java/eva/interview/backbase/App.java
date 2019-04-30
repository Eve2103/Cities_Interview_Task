package eva.interview.backbase;

import android.app.Application;

import eva.interview.backbase.util.asset.AssetRetriever;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AssetRetriever.init(this);
    }
}
