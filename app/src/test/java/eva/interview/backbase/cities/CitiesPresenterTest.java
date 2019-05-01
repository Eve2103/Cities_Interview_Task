package eva.interview.backbase.cities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import eva.interview.backbase.util.asset.AssetRetriever;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CitiesPresenterTest {

    @Mock
    private Cities.View view;

    private Cities.Presenter presenter;

    @Before
    public void init(){
        AssetRetriever.initTestable();
        presenter = new CitiesPresenterImpl(view);
    }

    @Test
    public void testPresenterRetrieval(){
        presenter.filterCity("");
        verify(view).showLoading();
        verify(view, timeout(100)).showCities(ArgumentMatchers.<City>anyList());
    }

}
