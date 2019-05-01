package eva.interview.backbase.about;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import eva.interview.backbase.util.asset.AssetRetriever;

/**
 * Created by Backbase R&D B.V on 28/06/2018.
 */

public class AboutModelImpl implements About.Model {

    private static final String TAG = AboutModelImpl.class.getSimpleName();
    private final About.Presenter presenter;
    private static final String FILE_NAME = "aboutInfo.json";
    private AssetRetriever assetRetriever;

    public AboutModelImpl(@NonNull About.Presenter presenter, @NonNull AssetRetriever assetRetriever){
        this.presenter = presenter;
        this.assetRetriever = assetRetriever;
    }

    @Override
    public void getAboutInfo() {
        String aboutInfoJson = assetRetriever.retrieveFromAssetsAsString(FILE_NAME);

        if(aboutInfoJson != null && !aboutInfoJson.isEmpty()){
    		AboutInfo aboutInfo = parseAboutInfo(aboutInfoJson);
    		if (aboutInfo != null){
        		presenter.onSuccess(aboutInfo);
        		return;
   		 	}
		}

		presenter.onFail();
    }

    private AboutInfo parseAboutInfo(String aboutInfoJson) {
        return new Gson().fromJson(aboutInfoJson, AboutInfo.class);
    }

}
