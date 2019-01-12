import com.alibaba.fastjson.JSON;

public class JSONTest {
    public static void main (String[] args) {
        Parser login = new Parser("123", "321");
        String dataByte=JSON.toJSONString(login);
        Object data = JSON.parseObject(dataByte, Parser.class);
    }
}


class Parser {
    private String user;
    private String name;
    private int age;

    /*public Parser(){

    }*/

    public Parser(String user, String name){
        this.user = user;
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return 1;
    }

    public void setSex(int sex) {
        System.out.println("调用age=" + sex);
    }
}