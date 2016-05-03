package kankan.km.com.manhupro;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import kankan.km.com.manhupro.tools.tools.Loading;

/**
 * Created by apple on 16/5/3.
 */
public class BaseAcvitiy extends FragmentActivity {

    private Dialog showload;
    public boolean LoadingShut = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showLoad() {
        if (showload == null) {
            showload = new Loading(this);
            showload.setCancelable(LoadingShut);
            showload.setCanceledOnTouchOutside(false);
            if (!showload.isShowing()) {
                showload.show();
            }
        } else {
            if (!showload.isShowing()) {
                showload.show();
            }
        }
    }

    public void SetShowLoadShut(boolean loginshu) {
        LoadingShut = loginshu;
    }

    public void dismissLoad() {
        if (showload != null) {
            if (showload.isShowing()) {
                showload.dismiss();
            }
        }
    }

}