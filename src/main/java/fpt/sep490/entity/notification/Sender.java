package fpt.sep490.entity.notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sender {

    @SerializedName("to")
    @Expose
    private String to;

    @SerializedName("notification")
    @Expose
    private Notification notification;
}
