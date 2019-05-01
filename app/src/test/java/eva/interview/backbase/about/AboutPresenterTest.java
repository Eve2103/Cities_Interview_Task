package eva.interview.backbase.about;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import eva.interview.backbase.util.asset.AssetRetriever;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AboutPresenterTest {

    private About.Presenter presenter;

    @Mock
    private About.View view;

    @Before
    public void init(){
        AssetRetriever.initTestable();
        presenter = new AboutPresenterImpl(view);
    }

    @Test
    public void testAboutRetrieve(){
        presenter.getAboutInfo();
        verify(view, times(1)).showProgress();
        verify(view, times(1)).hideProgress();
        verify(view, times(1)).setAboutInfo(anyString());
        verify(view, times(1)).setCompanyAddress(anyString());
        verify(view, times(1)).setCompanyCity(anyString());
        verify(view, times(1)).setCompanyName(anyString());
        verify(view, times(1)).setCompanyPostalCode(anyString());
    }
    @Test
    public void testFailure(){
        presenter.onFail();
        verify(view, times(1)).showError();
    }
}
