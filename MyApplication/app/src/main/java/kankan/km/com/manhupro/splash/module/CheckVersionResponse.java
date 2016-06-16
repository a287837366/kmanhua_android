package kankan.km.com.manhupro.splash.module;

/**
 * Created by apple on 16/6/16.
 */
public class CheckVersionResponse {
    private String popMsg;
    private String updateType;

    private CheckVersion splashImage;
    private CheckVersion detailImage;
    private CheckVersion mainImage;

    public String getPopMsg() {
        return popMsg;
    }

    public void setPopMsg(String popMsg) {
        this.popMsg = popMsg;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public CheckVersion getSplashImage() {
        return splashImage;
    }

    public void setSplashImage(CheckVersion splashImage) {
        this.splashImage = splashImage;
    }

    public CheckVersion getDetailImage() {
        return detailImage;
    }

    public void setDetailImage(CheckVersion detailImage) {
        this.detailImage = detailImage;
    }

    public CheckVersion getMainImage() {
        return mainImage;
    }

    public void setMainImage(CheckVersion mainImage) {
        this.mainImage = mainImage;
    }
}
