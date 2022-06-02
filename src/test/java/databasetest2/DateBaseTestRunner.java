package databasetest2;

import com.unitedcoder.configutility.ApplicationConfig;
import databasetest.ConnectionManager;
import databasetest.ConnectionType;
import databasetest.DataAccess;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;

public class DateBaseTestRunner {
    Connection connection=null;
    final static String configFile="config-qa.properties";
    @BeforeClass
    public void setUp(){
        String dbUrl= ApplicationConfig.readFromConfigProperties(configFile,"qa.dbUrl");
        String dbPort= ApplicationConfig.readFromConfigProperties(configFile,"qa.dbPort");
        String dbdefaultDatabase= ApplicationConfig.readFromConfigProperties(configFile,"qa.defaultDatabase");
        String dbuserName= ApplicationConfig.readFromConfigProperties(configFile,"qa.userName");
        String dbpassWord= ApplicationConfig.readFromConfigProperties(configFile,"qa.passWord");
        connection= databasetest.ConnectionManager.connectToDataBaseServer(dbUrl,dbPort,dbdefaultDatabase,dbuserName,dbpassWord,
                ConnectionType.MYSQL);

    }

    @Test(description = "Verify a product in the database")
    public void verifyProductTest(){
        databasetest.DataAccess dataAccess=new databasetest.DataAccess();
        boolean isProductExist=dataAccess.getProductName("semetway",connection);
        Assert.assertTrue(isProductExist);
    }
    @Test(description = "get doc_name")
    public void verifyDocName(){
        databasetest.DataAccess dataAccess=new databasetest.DataAccess();
        boolean isDocumentExist=dataAccess.getDocName("About Us",connection);
        Assert.assertTrue(isDocumentExist);
    }
    @Test(description = "Verify a customer in the database")
            public void verifyCustomerTest(){
        databasetest.DataAccess dataAccess=new DataAccess();
        boolean isCustomerExist= dataAccess.getCustomer("dolkuntest@test.com",connection);
        Assert.assertTrue(isCustomerExist);
    }
    @AfterClass
    public void tearDown(){
        ConnectionManager.closeDataBaseConnection(connection);
    }
}
