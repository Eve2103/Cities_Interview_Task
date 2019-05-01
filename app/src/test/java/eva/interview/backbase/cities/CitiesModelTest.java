package eva.interview.backbase.cities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import eva.interview.backbase.util.asset.AssetRetriever;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CitiesModelTest {

    @Mock
    private Cities.Presenter presenter;

    @Mock
    private AssetRetriever assetRetriever;

    private Cities.Model model;


    @Before
    public void init() {
        AssetRetriever.initTestable();
        model = new CitiesModelImpl(presenter, assetRetriever);
    }

    @Test
    public void testRetrieveAll() {
        when(assetRetriever.retrieveFromAssetsAsStream(anyString()))
                .thenReturn(AssetRetriever.getInstance().retrieveFromAssetsAsStream(""));
        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
        model.getAllCities();
        verify(presenter, times(1)).onLoading();
        verify(presenter, timeout(100)).onCitiesLoaded(captor.capture());
        assertEquals(captor.getValue().size(), 6);
    }
    @Test
    public void testFiltering() {
        when(assetRetriever.retrieveFromAssetsAsStream(anyString()))
                .thenReturn(AssetRetriever.getInstance().retrieveFromAssetsAsStream(""));
        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);

        model.filterCities("H");
        verify(presenter, timeout(100)).onCitiesLoaded(captor.capture());
        assertEquals(captor.getValue().size(), 2);
    }
    @Test
    public void testFilteringLetterCase() {
        when(assetRetriever.retrieveFromAssetsAsStream(anyString()))
                .thenReturn(AssetRetriever.getInstance().retrieveFromAssetsAsStream(""));
        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);

        model.filterCities("h");
        verify(presenter, timeout(100)).onCitiesLoaded(captor.capture());
        assertEquals(captor.getValue().size(), 2);

    }
    @Test
    public void testFilteringNoResult() {
        when(assetRetriever.retrieveFromAssetsAsStream(anyString()))
                .thenReturn(AssetRetriever.getInstance().retrieveFromAssetsAsStream(""));
        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);

        model.filterCities("z");
        verify(presenter, timeout(100)).onCitiesLoaded(captor.capture());
        assertEquals(captor.getValue().size(), 0);
    }
    @Test
    public void testFilteringEmpty() {
        when(assetRetriever.retrieveFromAssetsAsStream(anyString()))
                .thenReturn(AssetRetriever.getInstance().retrieveFromAssetsAsStream(""));
        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);

        model.filterCities("    ");
        verify(presenter, timeout(100)).onCitiesLoaded(captor.capture());
        assertEquals(captor.getValue().size(), 0);
    }
}
