package fpt.sep490.entity.map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Result {
    @SerializedName("address_components")
    @Expose
    private List<AddressComponent> addressComponents;

    @SerializedName("formatted_address")
    @Expose
    private String formattedAddress;

    @SerializedName("geometry")
    @Expose
    private Geometry geometry;

    @SerializedName("partial_match")
    @Expose
    private Boolean partialMatch;

    @SerializedName("place_id")
    @Expose
    private String placeId;

    @SerializedName("plus_code")
    @Expose
    private PlusCode plusCode;

    @SerializedName("types")
    @Expose
    private List<String> types;
}
