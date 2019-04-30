package eva.interview.backbase.util.asset;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

public class AssetRetrieverImpl extends AssetRetriever {
    private static final String TAG = "AssetRetrieverImpl";

    private static WeakReference<Context> weakContext = new WeakReference<>(null);

    public static void init(Application application) {
        weakContext = new WeakReference<>(application.getApplicationContext());
    }

    protected AssetRetrieverImpl(Context context){
        super();
        weakContext = new WeakReference<>(context);
    }

    public String retrieveFromAssetsAsString(String fileName) {
        if (weakContext.get() != null) {
            try {
                AssetManager manager = weakContext.get().getAssets();
                InputStream file = manager.open(fileName);
                byte[] formArray = new byte[file.available()];
                file.read(formArray);
                file.close();
                return new String(formArray);
            } catch (IOException ex) {
                Log.e(TAG, ex.getLocalizedMessage(), ex);
            }
        }

        return null;
    }
    public InputStream retrieveFromAssetsAsStream(String fileName) {
        if (weakContext.get() != null) {
            try {
                AssetManager manager = weakContext.get().getAssets();
                return manager.open(fileName);
            } catch (IOException ex) {
                Log.e(TAG, ex.getLocalizedMessage(), ex);
            }
        }

        return null;
    }
}

