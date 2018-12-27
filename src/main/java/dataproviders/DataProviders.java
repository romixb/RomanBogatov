package dataproviders;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider
    public Object[][]dataProvider(){
        return new Object[][]{
                {"String1", 1},
                {"String2", 2},
                {"String3", 3}
        };
    }
}
