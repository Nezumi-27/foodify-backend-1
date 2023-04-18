package fpt.sep490.entity.map;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Geometry {
    @SerializedName("location")
    @Expose
    private Location location;

    @SerializedName("location_type")
    @Expose
    private String locationType;

    @SerializedName("viewport")
    @Expose
    private Viewport viewport;
}
