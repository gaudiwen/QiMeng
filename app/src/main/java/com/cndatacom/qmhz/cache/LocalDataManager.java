package com.cndatacom.qmhz.cache;

import com.cndatacom.qmhz.bean.HawkConstant;
import com.cndatacom.qmhz.bean.LaucherConfigBean;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

public class LocalDataManager {

    private LocalDataManager(){}

    public static LocalDataManager getInstance(){
        return Holder.instance;
    }

   private static class Holder{
        private static LocalDataManager instance = new LocalDataManager();
   }

    public void saveToken(String token, long passTime) {
//        Hawk.put(HawkConstant.TOKEN, token);
//        Hawk.put(HawkConstant.TOKEN_PASS_TIME,passTime+ System.currentTimeMillis()/ 1000);
    }

   public boolean isTokenPassGoOut(){
//        if(Hawk.get(HawkConstant.TOKEN_PASS_TIME,0L) == 0){
//            return true;
//        }else {
//          return Hawk.get(HawkConstant.TOKEN_PASS_TIME,0L) < System.currentTimeMillis()/ 1000;
//        }
       return false;
   }

    public void saveLaucherConfigBean(LaucherConfigBean laucherConfigBean) {

        Hawk.put(HawkConstant.LAUCHER_CONFIGBEAN,laucherConfigBean);
    }


   public LaucherConfigBean getLaucherConfigBean(){

        if(Hawk.get(HawkConstant.LAUCHER_CONFIGBEAN)!=null){
            LaucherConfigBean laucherConfigBean = Hawk.get(HawkConstant.LAUCHER_CONFIGBEAN);
            return laucherConfigBean;
        }else {
            return null;
        }
   }

    public void logout(){
        Hawk.deleteAll();
    }

}
