package pojo;

public class IPInfoPojo {

    private String status;
    private String country;
    private String city;
    private String country_code;
    private String region;
    private String ip;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String getRegion() {
        return region;
    }

    public String getIp() {
        return ip;
    }

    @Override
    public String toString() {
        return String.format("IP: %s \nСтрана: %s, %s \nРегион: %s \nГород: %s\n",
                ip,
                (country != null ? country : "Неизвестно"),
                (country_code != null ? country_code : "Неизвестно"),
                (region != null ? region : "Неизвестно"),
                (city != null ? city : "Неизвестно"));
    }

}
