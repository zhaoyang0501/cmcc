package cmcc.common.util;

import java.util.Map;

public interface  MailSenderUtil {
	
	/***
	 * 模板邮件
	 * @param to
	 * @param subject
	 * @param template
	 * @param model
	 */
	public void sendMail(String to,String subject,String template,Map<String,Object> model);
	/***
	 * 简单文本邮件
	 * @param to
	 * @param subject
	 * @param text
	 */
	public void sendMail(String to,String subject,String text);
}
