//author:Daniel Santos
package ex1;

public class ClienteConsulta {
    public int id;
    public InterfaceCallBack interfaceCallBack;

    public ClienteConsulta(int id, InterfaceCallBack interfaceCallBack) {
        this.id = id;
        this.interfaceCallBack = interfaceCallBack;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InterfaceCallBack getInterfaceCallBack() {
        return interfaceCallBack;
    }

    public void setInterfaceCallBack(InterfaceCallBack interfaceCallBack) {
        this.interfaceCallBack = interfaceCallBack;
    }
    
    
}
