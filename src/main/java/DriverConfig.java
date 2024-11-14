import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverConfig

{
    private static DriverConfig config=null;
    private WebDriver wd;
    private DriverConfig()
    {

    }

    public WebDriver returnDriver()
    {
        WebDriverManager.chromedriver().setup();
        wd=new ChromeDriver();
        return wd;
    }
        public static DriverConfig getInstance()
            {
                if(config==null)
                {
                    config=new DriverConfig();
                }
            return config;
            }


}
