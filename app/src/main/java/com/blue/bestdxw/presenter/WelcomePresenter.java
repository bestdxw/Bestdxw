package com.blue.bestdxw.presenter;


import com.blue.bestdxw.contract.WelcomeContract;
import com.blue.bestdxw.model.WelcomeModel;

/**
 * 持有view和model的借口
 * @author Blue
 * @date 2018/6/3 0003 13:47
 */
public class WelcomePresenter implements WelcomeContract.Presenter {
    private WelcomeContract.View mView;
    private WelcomeModel welcomeModel;

    public WelcomePresenter(WelcomeContract.View view){
        mView = view;
        welcomeModel = new WelcomeModel();
    }

    @Override
    public void onSuccess(){
        mView.jump();
    }

    @Override
    public void changeText(String s) {
        mView.changeText(s);
    }

    @Override
    public void onStop() {
        welcomeModel.endCount(this);
    }

    @Override
    public void onCancel() {
        welcomeModel.cancelCount(this);
    }

    public void startTime(){
       welcomeModel.startCount(this);
    }

    @Override
    public void showGirl(String url) {
        mView.showGirl(url);
    }

    @Override
    public void getGrilFromGank() {
        welcomeModel.loadGirlFromUrl(this);
    }

}
