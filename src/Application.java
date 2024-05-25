import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public static void main(String[] args) {
    Properties props = new Properties();
    try(FileInputStream inputStream = new FileInputStream("src/application.properties")){
        props.load(inputStream);
    }catch (IOException _){
    }
    System.out.println(props.get("load.balancer.port"));
}
