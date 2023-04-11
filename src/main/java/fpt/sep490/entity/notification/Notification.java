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
public class Notification {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("sound")
    @Expose
    private String sound;

    @SerializedName("android_channel_id")
    @Expose
    private String androidChannelId;
}
