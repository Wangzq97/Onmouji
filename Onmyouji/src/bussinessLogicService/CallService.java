package bussinessLogicService;

import PO.HERO;
import PO.callRecord;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CallService extends Remote{

    public HERO getCommon (String name) throws RemoteException;

    public HERO getSpecial (String name) throws RemoteException;

    public ArrayList<callRecord> checkHistory(String name) throws RemoteException;

}
