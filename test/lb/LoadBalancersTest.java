package lb;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

class LoadBalancersTest {
    private LoadBalancers loadBalancers;

    @BeforeEach
    void setUp() {
        Property property = new Property(new Properties());
        loadBalancers = new LoadBalancers(property);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void roundRobinRouting() {
    }

    @Test
    void stickyRoundRobingRouting() {
    }

    @Test
    void getServerErrLogFilePathDoesNotExist() {
        String file  = loadBalancers.getServerErrLogFilePath();
        Assertions.assertEquals(file, "Does Not Exist.");
    }

    @Test
    void getServerErrLogFilePathExist() {
        String file  = loadBalancers.getServerErrLogFilePath();
        Assertions.assertEquals(file,  loadBalancers.getServerErrLogFilePath());
    }

    @Test
    void getServerLogFilePathDoesNotExist() {
        String filename = loadBalancers.getServerLogFilePath();
        Assertions.assertEquals(filename, loadBalancers.getServerLogFilePath());
    }

    @Test
    void getServerLogFilePathExist() {
        String filename = loadBalancers.getServerLogFilePath();
        Assertions.assertEquals(filename, loadBalancers.getServerLogFilePath());
    }
}