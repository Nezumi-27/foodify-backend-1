package fpt.sep490.entity.map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddressComponent {
    @SerializedName("long_name")
    @Expose
    private String longName;

    @SerializedName("short_name")
    @Expose
    private String shortName;

    @SerializedName("types")
    @Expose
    private List<String> types;
}
