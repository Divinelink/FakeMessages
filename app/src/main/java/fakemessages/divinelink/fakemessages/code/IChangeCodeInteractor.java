package fakemessages.divinelink.fakemessages.code;


import android.content.Context;

public interface IChangeCodeInteractor {


    void setNewCode(OnSetNewCodeChangeListener listener, Context ctx, int id);

    void getCode (OnGetNewCodeListener listener, Context ctx);

    interface OnSetNewCodeChangeListener {
        void onSuccessNewCode(int code);
    }

    interface OnGetNewCodeListener {
        void onSuccessGetCode(int code);
    }
}
