package eva.interview.backbase.util.asset;

import android.app.Application;

import java.io.InputStream;

public abstract class AssetRetriever {

    public abstract InputStream retrieveFromAssetsAsStream(String fileName);

    public abstract String retrieveFromAssetsAsString(String fileName);

    private static AssetRetriever INSTANCE = null;

    public static AssetRetriever getInstance() {
        if (INSTANCE != null)
            return INSTANCE;
        else
            throw new IllegalStateException("AssetRetrieverImpl.init not called!");
    }

    protected AssetRetriever() {

    }

    public static void init(Application application) {
        INSTANCE = new AssetRetrieverImpl(application.getApplicationContext());
    }

    public static void initTestable() {
        INSTANCE = new AssetRetrieverTestableImpl();
    }
}
