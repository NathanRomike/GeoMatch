package comnathanromike.github.geomatch.models;

/**
 * Created by nathanromike on 3/25/16.
 */
public class FlickrGroup {
    private String mTotalPhotos;
    private String mPhotoId;
    private String mOwnerId;
    private String mTitle;
    private String mPhotoOwnerId;

    public FlickrGroup(String totalPhotos, String photoId, String ownerId, String title, String photoOwnerId) {
        this.mTotalPhotos = totalPhotos;
        this.mPhotoId = photoId;
        this.mOwnerId = ownerId;
        this.mTitle = title;
        this.mPhotoOwnerId = photoOwnerId;

    }

    public String getTotalPhotos() {
        return mTotalPhotos;
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

    public String getPhotoOwnerId() {
        return mPhotoOwnerId;
    }

}
