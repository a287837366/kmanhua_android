package kankan.km.com.manhupro.tools.tools;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import kankan.km.com.manhupro.R;

/**
 * Created by apple on 16/5/3.
 */
public class Loading extends Dialog{

    private Context context;

    public Loading(Context context) {
        super(context, R.style.loading_dialog);
        this.context = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }

    public void init(){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_loading, null);
        setContentView(view);

    }
}
