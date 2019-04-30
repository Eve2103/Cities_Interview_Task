package eva.interview.backbase.util.asset;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class AssetRetrieverTestableImpl extends AssetRetriever {

    @Override
    public InputStream retrieveFromAssetsAsStream(String fileName) {
        String citiesStirng = "[\n" +
                "{\"country\":\"UA\",\"name\":\"Hurzuf\",\"_id\":707860,\"coord\":{\"lon\":34.283333,\"lat\":44.549999}},\n" +
                "{\"country\":\"RU\",\"name\":\"Novinki\",\"_id\":519188,\"coord\":{\"lon\":37.666668,\"lat\":55.683334}},\n" +
                "{\"country\":\"NP\",\"name\":\"Gorkhā\",\"_id\":1283378,\"coord\":{\"lon\":84.633331,\"lat\":28}},\n" +
                "{\"country\":\"IN\",\"name\":\"State of Haryāna\",\"_id\":1270260,\"coord\":{\"lon\":76,\"lat\":29}},\n" +
                "{\"country\":\"UA\",\"name\":\"Holubynka\",\"_id\":708546,\"coord\":{\"lon\":33.900002,\"lat\":44.599998}},\n" +
                "{\"country\":\"NP\",\"name\":\"Bāgmatī Zone\",\"_id\":1283710,\"coord\":{\"lon\":85.416664,\"lat\":28}}]";

        return new ByteArrayInputStream(citiesStirng.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String retrieveFromAssetsAsString(String fileName) {
        return
                "{\n" +
                        "  \"companyName\" : \"Backbase\",\n" +
                        "  \"companyAddress\" : \"Jacob Bontiusplaats 9\",\n" +
                        "  \"postalCode\" : \"1018 LL\",\n" +
                        "  \"city\" : \"Amsterdam\",\n" +
                        "  \"details\" : \"This is the Backbase assignment for Android engineering positions.\"\n" +
                        "}";
    }
}
