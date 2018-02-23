import com.aliadnank.components.TransactionStore;
import com.aliadnank.services.impl.TransactionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@SpringBootTest(classes = {TransactionServiceImpl.class, TransactionStore.class})
@RunWith(SpringRunner.class)
public class ApplicationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void shouldCreateApplication() throws Exception {
        assertThat(context, notNullValue());
    }
}