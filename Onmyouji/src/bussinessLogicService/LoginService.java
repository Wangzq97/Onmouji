package bussinessLogicService;

import PO.account;

import java.rmi.Remote;

public interface LoginService extends Remote{

    public boolean register (String id, String password);

    public account login(String id, String password);

    public boolean isExist(String id);

}
