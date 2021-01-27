package com.vishwanath.rbcproject.foodfinderapp.service.database.implementation;

import com.vishwanath.rbcproject.foodfinderapp.model.Business;
import com.vishwanath.rbcproject.foodfinderapp.model.TotalBusiness;
import com.vishwanath.rbcproject.foodfinderapp.service.database.interfaces.IFoodFinderDatabase;
import com.vishwanath.rbcproject.foodfinderapp.service.database.interfaces.IManagedRealm;

public class FoodFinderRealm extends BaseRealmHelper implements IFoodFinderDatabase {

    public FoodFinderRealm(IManagedRealm managedRealm) {
        super(managedRealm);
    }

    @Override
    public TotalBusiness getRestaurantList(String query) {
/*        return getRealm().where(Business.class)
                    .equalTo(Business., name)
                    .findFirst().getRestaurantList().sort(Runner., Sort.ASCENDING);*/
        return null;
    }

    @Override
    public void saveBusiness(Business restaurant) {
        executeTransaction(realm -> realm.copyToRealmOrUpdate(restaurant));
    }

   /* @Override
    public void setRanking(String name) {
        getRealm().executeTransaction(realm -> {
            RealmResults<Runner> runnerListForAgeGroup1 =  realm.where(Race.class)
                    .equalTo(Race.NAME, name)
                    .findFirst().getRestaurantList().where().beginGroup().between(Runner.AGE, 0, 15).endGroup().findAll().sort(Runner.TIME, Sort.ASCENDING);

            RealmResults<Runner> runnerListForAgeGroup2 =  realm.where(Race.class)
                    .equalTo(Race.NAME, name)
                    .findFirst().getRestaurantList().where().beginGroup().between(Runner.AGE, 16, 29).endGroup().findAll().sort(Runner.TIME, Sort.ASCENDING);
            RealmResults<Runner> runnerListForAgeGroup3 =  realm.where(Race.class)
                    .equalTo(Race.NAME, name)
                    .findFirst().getRestaurantList().where().beginGroup().greaterThan(Runner.AGE, 29).endGroup().findAll().sort(Runner.TIME, Sort.ASCENDING);

            setRank(runnerListForAgeGroup1, realm);
            setRank(runnerListForAgeGroup2, realm);
            setRank(runnerListForAgeGroup3, realm);
        });
    }

    private void setRank(RealmResults<Runner> runners, Realm realm){
        for(int i = 0; i < runners.size() ;  i++){
            if(i != 0 && runners.get(i).getTime() == runners.get(i - 1).getTime()){
                runners.get(i).setRank(runners.get(i - 1).getRank());
            } else {
                runners.get(i).setRank(i + 1);
            }
        }
       realm.copyToRealm(runners);
    }

    @Override
    public Subscription subscribeToRaceDetail(String name, Action1<RealmResults<Race>> action) {
        RealmResults<Race> races = getRealm().where(Race.class).equalTo(Race.NAME, name).findAll();
        if (races.size() == 0) {
            getRealm().beginTransaction();
            getRealm().createObject(Race.class, name);
            getRealm().commitTransaction();
            races = getRealm().where(Race.class).equalTo(Race.NAME, name).findAll();
        }
        return races.asObservable().subscribe(action);
    }*/


}
