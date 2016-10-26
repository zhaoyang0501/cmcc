import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import cmcc.api.SpringBootContext;
import cmcc.common.util.MailSenderUtil;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,classes=SpringBootContext.class)
public class TestMail {
	@Autowired
	private MailSenderUtil mailSenderUtil;
	
	@Test
	public void testSend(){
		this.mailSenderUtil.sendMail("263608237@qq.com", "sdf", "ddd");
	}
}
