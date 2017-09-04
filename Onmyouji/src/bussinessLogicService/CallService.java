package bussinessLogicService;

import PO.HERO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallService extends Remote{

    public HERO getCommon () throws RemoteException;

    public HERO getSpecial () throws RemoteException;

}
