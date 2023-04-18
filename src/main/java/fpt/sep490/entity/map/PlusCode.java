package fpt.sep490.entity.map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlusCode {

    @SerializedName("compound_code")
    @Expose
    private String compoundCode;

    @SerializedName("global_code")
    @Expose
    private String globalCode;
}
