package com.example.wagba.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wagba.model.Basket;
import com.example.wagba.model.OrderItem;
import com.example.wagba.model.Payment;
import com.example.wagba.model.Restaurant;
import com.example.wagba.service.BasketDao;
import com.example.wagba.service.UserRoomDatabase;
import com.example.wagba.utils.BasketState;


public class BasketRepository {

    private static final String TAG = "BasketRepository";
    private static MutableLiveData<Basket> _basketLiveData = new MutableLiveData<>();
    private static Basket _basket;
    private final BasketDao _basketDao;

    public BasketRepository(Application application) {
        UserRoomDatabase userRoomDatabase = UserRoomDatabase.getDataBase(application);
        _basketDao = userRoomDatabase.BasketDao();
        if (_basket == null) {
            _basket = _basketDao.getBasket();
            _basketLiveData.setValue(_basket);
        }
        Log.d(TAG, "BasketRepository: " + _basket);
    }

    public void deleteBasket() {
        synchronized (BasketRepository.class) {
            new DeleteBasketAsyncTask(_basketDao).execute();
            _basket = null;
            _basketLiveData.setValue(null);
        }

    }

    public void newBasket(Restaurant restaurant) {
        if (_basket == null) {
            synchronized (BasketRepository.class) {
                _basket = new Basket();
                _basket.setRestaurantModel(restaurant);
                new AddBasketAsyncTask(_basketDao).execute(_basket);
                Log.d(TAG, "newBasket: " + _basket.getOrderItems().size());
                _basketLiveData.setValue(_basket);
            }
        }
    }

    public LiveData<Basket> getBasket() {
        return _basketLiveData;
    }

    public void addOrderItem(OrderItem orderItem) {
        _basket.addOrderItem(orderItem);
        _basketLiveData.setValue(_basket);
        Log.d(TAG, "addOrderItem: ");
        new AddItemToBasketAsyncTask(_basketDao).execute(orderItem);
        new UpdatePaymentAsyncTask(_basketDao).execute(_basket.getPayment());
    }

    public BasketState getBasketStatus(String restaurantID) {
        if (_basket == null) return BasketState.EMPTY;
        else if (_basket.getRestaurantModel().getUid().equals(restaurantID))
            return BasketState.SAME_RESTAURANT;
        else return BasketState.NOT_SAME_RESTAURANT;
    }

    private static class AddBasketAsyncTask extends AsyncTask<Basket, Void, Void> {

        private BasketDao mAsyncTaskDao;

        AddBasketAsyncTask(BasketDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Basket... params) {
            mAsyncTaskDao.insertBasket(params[0]);
            return null;
        }
    }

    private static class AddItemToBasketAsyncTask extends AsyncTask<OrderItem, Void, Void> {
        private BasketDao mAsyncTaskDao;

        AddItemToBasketAsyncTask(BasketDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(OrderItem... params) {
            mAsyncTaskDao.insertOrderItem(params[0]);
            return null;
        }
    }

    private static class UpdatePaymentAsyncTask extends AsyncTask<Payment, Void, Void> {
        private BasketDao mAsyncTaskDao;

        UpdatePaymentAsyncTask(BasketDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Payment... params) {
            mAsyncTaskDao.updatePayment(params[0]);
            return null;
        }
    }

    private static class DeleteBasketAsyncTask extends AsyncTask<Payment, Void, Void> {
        private BasketDao mAsyncTaskDao;

        DeleteBasketAsyncTask(BasketDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Payment... params) {
            mAsyncTaskDao.deleteBasket();
            return null;
        }
    }



}
