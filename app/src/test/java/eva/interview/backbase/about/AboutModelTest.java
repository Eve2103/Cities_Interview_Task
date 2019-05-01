package eva.interview.backbase.about;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import eva.interview.backbase.util.asset.AssetRetriever;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AboutModelTest {

    @Mock
    private About.Presenter presenter;

    @Mock
    private AssetRetriever assetRetriever;

    private About.Model model;


    @Before
    public void init(){
        AssetRetriever.initTestable();
        model = new AboutModelImpl(presenter, assetRetriever);
    }

    @Test
    public void testRetrieval(){
        when(assetRetriever.retrieveFromAssetsAsString(anyString()))
                .thenReturn(AssetRetriever.getInstance().retrieveFromAssetsAsString(""));
        model.getAboutInfo();
        verify(presenter, times(1)).onSuccess(any(AboutInfo.class));
    }
    @Test
    public void testRetrieveFail(){
        model.getAboutInfo();
        verify(presenter, times(1)).onFail();
    }

}
