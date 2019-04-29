package eva.interview.backbase.cities;

import java.io.Serializable;

public class City implements Serializable {

    private String country;
    private String name;
    private long _id;
    private Coord coord;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (_id != city._id) return false;
        if (!country.equals(city.country)) return false;
        if (!name.equals(city.name)) return false;
        return coord.equals(city.coord);
    }

    @Override
    public int hashCode() {
        int result = country.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (int) (_id ^ (_id >>> 32));
        result = 31 * result + coord.hashCode();
        return result;
    }

    public class Coord implements Serializable {

        private double lon;
        private double lat;

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }
}
