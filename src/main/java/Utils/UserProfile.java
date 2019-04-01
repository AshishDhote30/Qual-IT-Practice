package Utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
public class UserProfile {

    private final String profile_name;
    private final String user_name;
    private final String password;
    private final String first_name;
    private final String last_name;
    private final String dob_date;
    private final String dob_month;
    private final String dob_year;
    private final String phone;
    private final String home_address;
    private final String suburb;
    private final String city;
    private final String postcode;


    @JsonCreator
    public UserProfile(
            @JsonProperty("profile_name") String profile_name,
            @JsonProperty("user_name") String user_name,
            @JsonProperty("password") String password,
            @JsonProperty("first_name") String first_name,
            @JsonProperty("last_name") String last_name,
            @JsonProperty("dob_date") String dob_date,
            @JsonProperty("dob_month") String dob_month,
            @JsonProperty("dob_year") String dob_year,
            @JsonProperty("phone") String phone,
            @JsonProperty("home_address") String home_address,
            @JsonProperty("suburb") String suburb,
            @JsonProperty("city") String city,
            @JsonProperty("postcode") String postcode)
    {

        this.profile_name = profile_name;
        this.user_name = user_name;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob_date = dob_date;
        this.dob_month = dob_month;
        this.dob_year = dob_year;
        this.phone = phone;
        this.home_address = home_address;
        this.suburb = suburb;
        this.city = city;
        this.postcode = postcode;
    }

    public String getProfile_name() {
        return profile_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getDob_date() {
        return dob_date;
    }

    public String getDob_month() {
        return dob_month;
    }

    public String getDob_year() {
        return dob_year;
    }

    public String getPhone() {
        return phone;
    }

    public String getHome_address() {
        return home_address;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

}
