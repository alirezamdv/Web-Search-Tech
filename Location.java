public class Location {
    private String id;
    private String location;
    private String country;
    private String geo_id;

    public Location() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGeo_id() {
        return geo_id;
    }

    public void setGeo_id(String geo_id) {
        this.geo_id = geo_id;
    }



    @Override
    public String toString() {
        return id + '\t' +
                location + '\t' +
                country + '\t' +
                geo_id + '\t' ;
    }

}
