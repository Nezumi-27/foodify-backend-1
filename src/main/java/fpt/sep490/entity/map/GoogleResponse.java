package fpt.sep490.entity.map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GoogleResponse {

    @SerializedName("results")
    @Expose
    private List<Result> results;

    @SerializedName("status")
    @Expose
    private String status;
}
