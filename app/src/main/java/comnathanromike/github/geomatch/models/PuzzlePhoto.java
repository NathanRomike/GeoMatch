package comnathanromike.github.geomatch.models;

import org.parceler.Parcel;

/**
 * Created by nathanromike on 3/25/16.
 */

@Parcel
public class PuzzlePhoto {
    private String photoId;
    private String ownerId;
    private String title;
    private Double latitude;
    private Double longitude;
    private String thumbnailUrl;
    private String mediumPhotoUrl;
    private String largePhotoUrl;

    public PuzzlePhoto() {}

    public PuzzlePhoto(String photoId, String ownerId, String title, Double latitude, Double longitude, String thumbnailUrl, String mediumPhotoUrl, String largePhotoUrl) {
        this.photoId = photoId;
        this.ownerId = ownerId;
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
        this.thumbnailUrl = thumbnailUrl;
        this.mediumPhotoUrl = mediumPhotoUrl;
        this.largePhotoUrl = largePhotoUrl;
    }

    public String getPhotoId() {
        return photoId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getTitle() {
        return title;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getMediumPhotoUrl() {
        return mediumPhotoUrl;
    }

    public String getLargePhotoUrl() {
        return largePhotoUrl;
    }
}
