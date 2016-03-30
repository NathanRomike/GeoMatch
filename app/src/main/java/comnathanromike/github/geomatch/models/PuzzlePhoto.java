package comnathanromike.github.geomatch.models;

import org.parceler.Parcel;

/**
 * Created by nathanromike on 3/25/16.
 */

@Parcel
public class PuzzlePhoto {
    private String mPhotoId;
    private String mOwnerId;
    private String mTitle;
    private Double mLatitude;
    private Double mLongitude;
    private String mThumbnailUrl;
    private String mMediumPhotoUrl;
    private String mLargePhotoUrl;

    public PuzzlePhoto() {}

    public PuzzlePhoto(String photoId, String ownerId, String title, Double latitude, Double longitude, String thumbnailUrl, String mediumPhotoUrl, String largePhotoUrl) {
        this.mPhotoId = photoId;
        this.mOwnerId = ownerId;
        this.mTitle = title;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mThumbnailUrl = thumbnailUrl;
        this.mMediumPhotoUrl = mediumPhotoUrl;
        this.mLargePhotoUrl = largePhotoUrl;
    }

    public String getPhotoId() {
        return mPhotoId;
    }

    public String getOwnerId() {
        return mOwnerId;
    }

    public String getTitle() {
        return mTitle;
    }

    public Double getLatitude() {
        return mLatitude;
    }

    public Double getLongitude() {
        return mLongitude;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public String getMediumPhotoUrl() {
        return mMediumPhotoUrl;
    }

    public String getLargePhotoUrl() {
        return mLargePhotoUrl;
    }
}
