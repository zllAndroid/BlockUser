//package com.xm6leefun.zll_shipper.utils;
//
//import io.realm.Realm;
//import io.realm.RealmConfiguration;
//import io.realm.internal.Context;
//
///**
// * Created by Administrator on 2018/3/9 0009.
// */
//
//public class RealmUtlis {
//
//    public void inMemory(String file_name, Context context)
//    {
////        创建非持久化的Realm，也就是保持在内存中，应用关闭后就清除了。
//        RealmConfiguration myConfig = new RealmConfiguration.Builder()
//                .name("myrealm.realm")//保存在内存中
//                .inMemory() .build();
//
//    }
//    public void realmAdd(Realm mRealm, Context context)
//    {
//               mRealm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                ModelScanResule user = realm.createObject(ModelScanResule.class);
//            }
//        });
//    }
//}
