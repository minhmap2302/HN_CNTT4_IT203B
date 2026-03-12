package Bai1.Main;

public class ValidUserName {
    public boolean Test(String Name){
        if(Name==null){
            return false;
        }
        if(Name==" "){
            return false;
        }
        return Name.length()>=6 || Name.length()<=20;
    }
}
