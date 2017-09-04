package rmi;

import bussinessLogicService.CallService;
import bussinessLogicService.LoginService;

import java.rmi.Remote;

public class RemoteHelper {

    private Remote remote;
    private static RemoteHelper remoteHelper = new RemoteHelper();

    public static RemoteHelper getInstance() {
        return remoteHelper;
    }

    private RemoteHelper() {
    }

    public void setRemote(Remote remote) {
        this.remote = remote;

    }

    public CallService getCallService() {
        return (CallService) remote;
    }

    public LoginService getLoginService(){
        return (LoginService) remote;
    }


}
