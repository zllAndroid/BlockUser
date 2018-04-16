package com.xm6leefun.zll_user.main_code.about_realm;

import android.content.Context;
import android.util.Log;

import com.xm6leefun.model.RealmTaskResule;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by matou0289 on 2016/10/20.
 */

public class RealmHelper {
    public static final String DB_NAME = "zll.realm";
    private Realm mRealm;


    public RealmHelper(Context context) {

        mRealm = Realm.getDefaultInstance();
    }

    /**
     * add （增）
     */
    public void addDog(final RealmTaskResule dog) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(dog);
        mRealm.commitTransaction();

    }

    /**
     * delete （删）
     */
    public void deleteDog(String qrcode) {
        RealmTaskResule dog = mRealm.where(RealmTaskResule.class).equalTo("qrcode", qrcode).findFirst();
        if (dog!=null) {
            mRealm.beginTransaction();
            dog.deleteFromRealm();
            mRealm.commitTransaction();
        }
    }
    public  void deleteDogBytaskId(String taskId) {
        RealmResults<RealmTaskResule> dogs = mRealm.where(RealmTaskResule.class).equalTo("taskId", taskId).findAll();
//        RealmTaskResule dog = mRealm.where(RealmTaskResule.class).equalTo("taskId", taskId).findFirst();
        if (dogs.size()>0)
        {
            mRealm.beginTransaction();
            for (int i =0;i<dogs.size();i++)
            {
//                dogs.deleteFromRealm(i);
                RealmTaskResule dog = dogs.get(i);
                dog.deleteFromRealm();
            }
            mRealm.commitTransaction();
        }else
        {
            return;
        }
    }
    //
    public void deleteAll() {
        final RealmResults<RealmTaskResule> dogs=  mRealm.where(RealmTaskResule.class).findAll();
        if (dogs!=null) {
            mRealm.beginTransaction();
            dogs.deleteAllFromRealm();
            mRealm.commitTransaction();
        }
    }

    /**
     * update （改）
     */
    public void updateDog(String id, String newName, String epssid) {
        RealmTaskResule dog = mRealm.where(RealmTaskResule.class).equalTo("id", id).findFirst();
        if (dog!=null) {
            mRealm.beginTransaction();
            dog.setExpressId(epssid);
            dog.setExpressNum(newName);
            mRealm.commitTransaction();
        }
    }

    /**
     * query （查询所有）
     */
    public List<RealmTaskResule> queryAllDog() {
        RealmResults<RealmTaskResule> dogs = mRealm.where(RealmTaskResule.class).findAll();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
        if (dogs!=null) {
            //增序排列
            dogs = dogs.sort("qrcode");
        }
//        //降序排列
//        dogs=dogs.sort("id", Sort.DESCENDING);
        return mRealm.copyFromRealm(dogs);
    }

    /**
     * query （根据Id（主键）查）
     */
    public RealmTaskResule queryDogById(String id) {
        RealmTaskResule dog = mRealm.where(RealmTaskResule.class).equalTo("id", id).findFirst();

        return dog;
    }
    /**
     * query （根据qrcode（主键）查）
     */
    public RealmTaskResule queryResultByQrcode(String qrcode) {

        RealmTaskResule dog = mRealm.where(RealmTaskResule.class).equalTo("qrcode", qrcode).findFirst();
        if (dog!=null)
        {
            return dog;
        }
        return null;
    }
    /**
     * query （根据qrcode（主键）查）
     */
    public  RealmResults<RealmTaskResule> queryResultBytaskId(String taskId) {
        RealmResults<RealmTaskResule> dogs = mRealm.where(RealmTaskResule.class).equalTo("taskId", taskId).findAll();
//        RealmTaskResule dog = mRealm.where(RealmTaskResule.class).equalTo("taskId", taskId).findFirst();
        if (dogs!=null)
        {
            return dogs;
        }
        return null;
    }
    public RealmTaskResule queryResultByUid(String uid) {

        RealmTaskResule dog = mRealm.where(RealmTaskResule.class).equalTo("nfcUid", uid).findFirst();
        if (dog!=null)
        {
            return dog;
        }
        return null;
    }


    /**
     * query （根据age查）
     */
    public List<RealmTaskResule> queryDobByAge(int age) {
        RealmResults<RealmTaskResule> dogs = mRealm.where(RealmTaskResule.class).equalTo("age", age).findAll();

        return mRealm.copyFromRealm(dogs);
    }

    public boolean isDogExist(String id){
        RealmTaskResule dog=mRealm.where(RealmTaskResule.class).equalTo("id",id).findFirst();
        if (dog==null){
            return false;
        }else {
            return  true;
        }
    }
    public boolean isHaveExist(String qrCode){
        RealmTaskResule dog=mRealm.where(RealmTaskResule.class).equalTo("qrcode",qrCode).findFirst();
        Log.e("RealmTaskResule","dog="+dog);
        if (dog==null){
            return false;
        }else {
            return  true;
        }
    }
    public boolean isHaveExistBynfcUid(String nfcUid){
        RealmTaskResule dog=mRealm.where(RealmTaskResule.class).equalTo("nfcUid",nfcUid).findFirst();
        Log.e("RealmTaskResule","dog="+dog);
        if (dog==null){
            return false;
        }else {
            return  true;
        }
    }

    public Realm getRealm(){

        return mRealm;
    }

    public void close(){
        if (mRealm!=null){
            mRealm.close();
        }
    }
}
