package com.cndatacom.qmhz.network.rxjava.observable;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.cndatacom.qmhz.network.rxjava.ProgressDialog;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class DialogTransformer {

    private boolean mCanCelable = true;

    public DialogTransformer(boolean cancelable, Context context) {
        mCanCelable = cancelable;
        mContext = context;
    }

    private Context mContext;

    public DialogTransformer(boolean cancelable, Fragment fragment) {
        mCanCelable = cancelable;
        mFragment = fragment;
    }

    private Fragment mFragment;

    public DialogTransformer(Context context) {
        this(true);
        mContext = context;
    }

    public DialogTransformer(Fragment fragment) {
        this(true);
        mFragment = fragment;
    }

    public DialogTransformer(Fragment fragment, boolean canCelable) {
        this(canCelable);
        mFragment = fragment;
    }

    public DialogTransformer(boolean cancelable) {
        mCanCelable = cancelable;
    }


    public ProgressDialog showDialog(final Disposable disposable) {
        ProgressDialog progressDialog = new ProgressDialog();
        if (mContext != null) {
            if (mContext instanceof AppCompatActivity) {
                AppCompatActivity context = (AppCompatActivity) mContext;
                progressDialog.show(context.getSupportFragmentManager(), "progressDialog");
            }
        }else if(mFragment != null){
            progressDialog.show(mFragment.getFragmentManager(), "progressDialog");
        }
        if(mCanCelable){
            progressDialog.setOnDismissListener(new ProgressDialog.OnDismissListener() {
                @Override
                public void dismiss() {
                    disposable.dispose();
                }
            });
        }
        return progressDialog;
    }


    public  <T> ObservableTransformer<T,T> transformer(){
        return new ObservableTransformer<T, T>() {
            private ProgressDialog mProgressDialog;
            @Override
            public ObservableSource<T> apply(final Observable<T> upstream) {
                return upstream.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(final Disposable disposable) throws Exception {
                        if (Looper.myLooper()!=Looper.getMainLooper()){
                            Handler handler=new Handler(Looper.getMainLooper());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressDialog=showDialog(disposable);
                                }
                            });
                        }else{
                            mProgressDialog=showDialog(disposable);
                        }
                }
                }).doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if(mProgressDialog != null){
                            if(mProgressDialog.getDialog() != null){
                                if(mProgressDialog.getDialog().isShowing()){
                                    mProgressDialog.dismiss();
                                }
                            }
                        }
                    }
                });
            }
        };
    }


}
