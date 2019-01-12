import java.util.Scanner;

public class Test {
    @org.junit.Test
    public void test(){
       /* ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        Map<String,ChannelHandler> handlerMap = ctx.getBeansOfType(ChannelHandler.class);
        Object result = handlerMap;*/


    }

    private static volatile boolean isOk;
    public static void main(String[] args){
//        ApplicationContext ap1 = new ClassPathXmlApplicationContext("application.xml");
//        ApplicationContext ap2 = new ClassPathXmlApplicationContext("application.xml");
//
//        ap2.
/*
        String[] user = {"123", "344", "23123"};
        String a = Arrays.asList(user).parallelStream().filter((str) -> str.equals("123")).findAny().get();
        System.out.println(a);
*/
/*        List<String> a  = Arrays.asList("a", "b", "c");
        List b = a;*/

        /*Scanner s = new Scanner(System.in);
        while(s.hasNext()) {
            String label = s.nextLine();
            System.out.println("输出："+ label);
            if(label.equals(":o")){
                return;
            }

        }*/
        Scanner s = new Scanner(System.in);
        while(s.hasNext()) {
            System.out.println("一级输入开始：");
            String command = s.nextLine();
            if("over".equals(command)){
                return;
            }
            if("2".equals(command)) {
                Thread thread2 = new Thread(new Mytest(s));
                thread2.start();
                /*try {
                    thread2.join();
                }catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }*/

            }
            System.out.println("一级输入结束：" + command);
        }
    }

    static class Mytest implements Runnable{

        private static Scanner s;
        Mytest(){

        }
        Mytest(Scanner s){
            this.s = s;
        }
        @Override
        public void run() {
            Scanner s2 = new Scanner(System.in);
            try{
                Scanner s = new Scanner(System.in);
                while(s.hasNext()) {
                    String input = s.nextLine();
                    System.out.println("2级输入" + input);
                    if("2o".equals(input)) {
                        break;
                    }
                }
            }catch (Exception e) {

            }finally {
                s = null;
            }

        }
    }
}
