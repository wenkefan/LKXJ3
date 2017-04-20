package com.fwk.lkxj3.MVP.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by fanwenke on 16/11/10.
 */

public abstract class MVPBasePresenter<V extends IMVP_view> {

    protected Reference<V> mViewRef;

    protected V proxy;

    public void attachView(V view){
        this.mViewRef = new WeakReference<V>(view);
        MVPInvocationHandler invocationHandler = new MVPInvocationHandler(this.mViewRef.get());
        proxy = (V) Proxy.newProxyInstance(mViewRef.get().getClass().getClassLoader(),mViewRef.get().getClass().getInterfaces(),
                invocationHandler);
    }

    public V getView(){
        return proxy;
    }

    public boolean isAttachView(){
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView(){
        if (mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public class MVPInvocationHandler implements InvocationHandler{

        private IMVP_view mView;

        public MVPInvocationHandler(IMVP_view mView){
            this.mView = mView;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (isAttachView()){
                return method.invoke(this.mView,args);
            }
            return null;
        }
    }

}
