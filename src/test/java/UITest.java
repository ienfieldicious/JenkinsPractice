import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UITest
{

    @BeforeTest
    public void setenv()
    {

    }
    @Test
    public void Ui1() throws InterruptedException {
        WebDriver w1= DriverConfig.getInstance().returnDriver();
        w1.get("https://reqres.in/");
        String s=w1.findElement(By.xpath("(//h2[@class='tagline'])[1]")).getText();
        System.out.println("*Printing from first block"+s);
        Thread.sleep(3000);
        w1.quit();
    }
    @Test
    public void Ui2() throws InterruptedException {
        WebDriver w2= DriverConfig.getInstance().returnDriver();
        w2.get("https://reqres.in/");
        String s=w2.findElement(By.xpath("(//h2[@class='tagline'])[1]")).getText();
        System.out.println("*Printing from second block"+s);
        Thread.sleep(3000);
        w2.quit();
    }
}
