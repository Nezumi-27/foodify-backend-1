package fpt.sep490.entity.map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Viewport {

    @SerializedName("northeast")
    @Expose
    private Northeast northeast;

    @SerializedName("southwest")
    @Expose
    private Southwest southwest;
}
