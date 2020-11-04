import org.junit.Test;

/**
 * @author lihai
 * @date 2020/9/30-11:07
 */
public class main {
    @Test
   public void contextLoads() throws Exception {
        String str = "??";
        String strCode = new String(str.getBytes("UTF-8"), "GBK");
        System.out.println(strCode);
    }
}
